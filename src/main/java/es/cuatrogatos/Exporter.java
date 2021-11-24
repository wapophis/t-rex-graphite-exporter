package es.cuatrogatos;


import es.cuatrogatos.aemet.AemetExporter;
import es.cuatrogatos.coinbase.CoinBaseExporter;
import es.cuatrogatos.eisos.EisosExporter;
import es.cuatrogatos.trex.TrexExporter;
import es.cuatrogatos.trex.boundary.TrexClient;
import es.cuatrogatos.trex.exceptions.UnreachableTrexApiException;
import es.cuatrogatos.twominers.TwoMinersExporter;
import es.cuatrogatos.twominers.boundary.TwoMinersClient;
import es.cuatrogatos.utils.ConsoleColors;
import org.apache.commons.cli.*;

import java.util.logging.Logger;

public class Exporter {
    public final static String TURL="turl";
    public final static String TURL_GNU="t-rex-url";
    public final static String CURL="curl";
    public final static String CPORT="cport";
    public final static String CPORT_GNU="carbon-port";
    public final static String CURL_GNU="carbon-url";
    public final static String TPOLL="tpoll";
    public final static String TPOLL_GNU="t-rex-polling-interval";

    //TODO: COMPLETE DRYRUN OPERATIONS WHEN ENABLED
    public final static String DRYRUN="dry";
    public final static String DRYRUN_GNU="dry-run";

    public final static String EISOS_CADENCY_IN_MILLIS="eif";
    public final static String EISOS_CADENCY_IN_MILLIS_GNU="eisos-flush-interval";

    /// AEMET CLIENT OPTIONS
    public final static String AEMET_CADENCY_IN_MILLIS="aef";
    public final static String AEMET_CADENCY_IN_MILLIS_GNU="aemet-flush-interval";
    public final static String AEMET_IDEMA_STATION_ID="aes";
    public final static String AEMET_IDEMA_STATION_ID_GNU="aemet-idema-station-id";
    public final static String AEMET_TOKEN="aet";
    public final static String AEMET_TOKEN_GNU="aemet-token";

    // EXTRA OPTIONS

    public final static String X_CAPITAL_INVESTED="xcap";
    public final static String X_CAPITAL_INVESTED_GNU="xtra-capital-invested";

    public final static String X_POWER_CONSUMPTION_OFFSET="xpo";
    public final static String X_POWER_CONSUMPTION_OFFSET_GNU="xtra-power-offset";


    // TREX
    private static long trexPollTime=0L;
    private static String trexApiUrl=null;

    // GRAPHITE CARBON
    private static String carbonApiUrl="localhost";
    private static Integer carbonPort=2003;


    /// AEMET INTEGRATION DEFAULT VALUES
    private static long aemetFlushInterval=0L;
    private static String aemetIdemaStation=null;
    private static String aemetToken=null;

    /// EXTRA OPTIONS
    private static Double xCapitalInvested=null;
    private static Float  xPowerOffset=new Float(0.0);
    private static boolean dryRun=false;

    // POOL OPTIONS
    private static String poolUrl= null;
    private static String poolAccount= null;


    private static TwoMinersExporter poolExporter;
    private static EisosExporter eisosExporter;
    private static CoinBaseExporter coinbaseExporter;
    private static AemetExporter aemetExporter;
    private static TrexExporter trexExporter;

    private static Logger myLogger=Logger.getLogger("LAUNCHER");



    public static void main(String args[]) throws InterruptedException, UnreachableTrexApiException {
        Options options=new Options();
        CommandLineParser parser = new DefaultParser();

        options.addRequiredOption(TURL,TURL_GNU,true,"Trex api endpoint");
        options.addOption(CURL,CURL_GNU,true,"Carbon database endpoint");
        options.addOption(CPORT,CPORT_GNU,true,"Carbon database tcp port ");

        options.addRequiredOption(TPOLL,TPOLL_GNU,true,"Polling interval to the trex api in milliseconds");
//        options.addRequiredOption(CFLUSH,CFLUSH_GNU,true,"Sending metrics to the carbon database in milliseconds");
        options.addOption(DRYRUN,DRYRUN_GNU,false,"Enable Dry-run mode, no send data to carbon server");

        options.addOption(AEMET_CADENCY_IN_MILLIS,AEMET_CADENCY_IN_MILLIS_GNU,true,"Setup aemet client flush time. Set a value to enable integration");
        options.addOption(AEMET_IDEMA_STATION_ID,AEMET_IDEMA_STATION_ID_GNU,true,"Setup aemet station id to query for");
        options.addOption(AEMET_TOKEN,AEMET_TOKEN_GNU,true,"Setup aemet api token");

        options.addOption(X_CAPITAL_INVESTED,X_CAPITAL_INVESTED_GNU,true,"Capital invest to get break event");
        options.addOption(X_POWER_CONSUMPTION_OFFSET,X_POWER_CONSUMPTION_OFFSET_GNU,true,"Offset to apply to the power consumption");

        options.addOption(EISOS_CADENCY_IN_MILLIS,EISOS_CADENCY_IN_MILLIS_GNU,true," Calc price power consumption in spain using eisos api to retrieve current electrical cost. Setup the interval to report this info to the graphite server and calc cadency.");



        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            processTrexOptions(line);
            processExtraOptions(line);
            processCarbonOptions(line);
            processAemetOptions(line);
            processEisosOptions(line);
            processTwoMinersOptions(line);
            coinbaseExporter=new CoinBaseExporter(60*1000);
            coinbaseExporter.export(carbonApiUrl,carbonPort);
        }
        catch( ParseException exp ) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar trexexporter.jar", options);
            return ;
        }

    }

    private static void processTrexOptions(CommandLine line){
        ////// TREX INTEGRATION CONFIG
        trexApiUrl=line.getOptionValue(TURL);
        trexPollTime=Long.parseLong(line.getOptionValue(TPOLL));

        if(trexApiUrl!=null && !trexApiUrl.isEmpty()){
            TrexClient.setTrexApiUrl(trexApiUrl);
            TrexClient.setCachedTttl(trexPollTime);
            trexExporter=new TrexExporter(trexPollTime);
            trexExporter.export(carbonApiUrl,carbonPort,dryRun);
            poolUrl= (String) TrexClient.getSummary().getActive_pool().get("url");
            poolAccount= (String) TrexClient.getSummary().getActive_pool().get("user");

        }

    }

    private static void processAemetOptions(CommandLine line){
        /// AEMET INTEGRATION CONFIG
        if(line.hasOption(AEMET_TOKEN)){
            aemetToken=line.getOptionValue(AEMET_TOKEN);
            aemetIdemaStation=line.getOptionValue(AEMET_IDEMA_STATION_ID);
            aemetFlushInterval=Long.parseLong(line.getOptionValue(AEMET_CADENCY_IN_MILLIS));
            aemetExporter=new AemetExporter(aemetFlushInterval,aemetIdemaStation,aemetToken);
            aemetExporter.export(carbonApiUrl,carbonPort,dryRun);
        }

    }

    private static void processCarbonOptions(CommandLine line){
        //// CARBON INTEGRATION CONFIG
        if(!line.hasOption(DRYRUN)) {
//            carbonFlushInterval = Long.parseLong(line.getOptionValue(CFLUSH));
            if(line.hasOption(CURL)) {
                carbonApiUrl = line.getOptionValue(CURL);
            }else{
                myLogger.warning(ConsoleColors.CYAN+"CARBON SERVER AT LOCALHOST BY DEFAULT"+ConsoleColors.RESET);
            }
            if(line.hasOption(CPORT)){
                carbonPort=Integer.parseInt(line.getOptionValue(CPORT));
            }else{
                myLogger.warning(ConsoleColors.CYAN+"CARBON SERVER AT TCP 2003 BY DEFAULT"+ConsoleColors.RESET);
            }

        }else{
          myLogger.warning(ConsoleColors.CYAN+"DRY_RUN mode activated, nothing will be reported to carbon"+ConsoleColors.RESET);
        }

    }

    private static void processEisosOptions(CommandLine line){
        if(line.hasOption(EISOS_CADENCY_IN_MILLIS)){
            eisosExporter=new EisosExporter(Long.parseLong(line.getOptionValue(EISOS_CADENCY_IN_MILLIS)),TrexClient.getWorkerName(),xPowerOffset);
            eisosExporter.export(carbonApiUrl,carbonPort,dryRun);
            myLogger.warning(ConsoleColors.GREEN+" EISOS CLIENT IS ACTIVATED AND REPORTING AT "+TrexClient.getWorkerName()+" EVERY "+Long.parseLong(line.getOptionValue(EISOS_CADENCY_IN_MILLIS))+" "+ConsoleColors.RESET);
        }else{
            myLogger.warning(ConsoleColors.RED+" EISOS CLIENT IS NOT ACTIVATED"+ConsoleColors.RESET);
        }
    }

    private static void processTwoMinersOptions(CommandLine line){
        if(poolUrl==null || poolAccount==null){
            myLogger.warning(ConsoleColors.RED+" THERE IS NO CONNECTION TO THE MINER TO GET POOL INFO"+ConsoleColors.RESET);
            return;
        }
        poolExporter=new TwoMinersExporter(poolUrl,poolAccount,xCapitalInvested,60000);
        poolExporter.export(carbonApiUrl,carbonPort,dryRun);
        TwoMinersClient.setCacheTtl(60000);
    }


    private static void processExtraOptions(CommandLine line){
        if(line.hasOption(X_CAPITAL_INVESTED)){
            xCapitalInvested=Double.valueOf(line.getOptionValue(X_CAPITAL_INVESTED));
            myLogger.warning(ConsoleColors.GREEN+" CLIENT IS REPORTING BREAK EVENT WITH A CAPITAL OF "+xCapitalInvested+" € "+ConsoleColors.RESET);
        }
        if(line.hasOption(X_POWER_CONSUMPTION_OFFSET)){
            xPowerOffset=Float.valueOf(line.getOptionValue(X_POWER_CONSUMPTION_OFFSET));
            myLogger.warning(ConsoleColors.GREEN+" CLIENT IS REPORTING WITH AN OFFSET OF "+xPowerOffset+" Wh "+ConsoleColors.RESET);
        }
    }

}
