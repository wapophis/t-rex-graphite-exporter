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



        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            processExtraOptions(line);
            processCarbonOptions(line);
            processTrexOptions(line);
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




//        if(metricsInitialized) {
//           /// try {
//                BeaconsManager.initMetricsAndReport(trexPollTime);
//          //      eisosExporter.setPowerConsumption(calcGPUsConsumption(130));
//          //      eisosExporter.export(summaryMetrics.getWorker());
//
//
//                if(aemetExporter!=null) {
//                    aemetExporter.export();
//                }
//
//                /// POLLING AL MINERO
//             //   pollTrex();
//
//            /*} catch (UnreachableTrexApiException e) {
//                summaryMetrics.getSummary().setUptime(0L);
//                Thread.sleep(carbonFlushInterval);
//                throw  e;
//            }*/
//        }else{
//            Logger.getAnonymousLogger().warning("SomeThing goes wrong");
//        }

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




//    public static void pollTrex()  {
//                Executors.newFixedThreadPool(1).submit(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        long startTime = System.currentTimeMillis();
//                        long endTime = startTime;
//
//                        JsonClient jsonClient = new JsonClient();
//                        Summary oVal = null;
//
//                        try {
//                            while (endTime - startTime < trexPollTime) {
//                                Thread.sleep(trexPollTime - (endTime - startTime));
//
//                                oVal = jsonClient.http().get(trexApiUrl + "/summary").object(Summary.class);
//
//                                endTime = System.currentTimeMillis();
//                                startTime = System.currentTimeMillis();
//                                Logger.getAnonymousLogger().warning(oVal.toString());
//                            //    summaryMetrics.setSummary(oVal);
//                                eisosExporter.setPowerConsumption(calcGPUsConsumption(130));
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                            // SET SUMMARY TO EMPTY ONE TO TRIGGER OFF EVENT.
//                           // summaryMetrics.setSummary(new Summary());
//                            BeaconsManager.updateBeacon(new TrexOnErrorBeacon().trigguer());
//                            try {
//                                Thread.sleep(5000L);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
//
//                            pollTrex();
//
////                    if(e.getCause().getClass().equals(ConnectException.class) && trexRetries>0){
////                        Logger.getAnonymousLogger().warning("Trying to recover from error connection to the trex api");
////                        Thread.sleep(trexRetries*trexPollTime);
////                        trexRetries--;
////                        pollTrex();
////                    }
////
////                    if(trexRetries==0){
////                        throw new UnreachableTrexApiException(trexApiUrl);
////                    }
//
//
//                        }
//
//                    }
//                });
//
//            }




//    private static void initializeMetricsRegistry(){
//
//        if(!metricsInitialized) {
//            try{
//            MetricRegistry metricRegistry=new MetricRegistry();
//            JsonClient jsonClient = new JsonClient();
//            summaryMetrics=new SummaryMetrics();
//            summaryMetrics.setSummary(jsonClient.http().get(trexApiUrl + "/summary").object(Summary.class));
//
//                /**
//                 * POOL METRICS INITIALIZATION
//                 */
//            poolExporter=new TwoMinersExporter(summaryMetrics.getSummary().getActive_pool().get("url").toString(),summaryMetrics.getSummary().getActive_pool().get("user").toString(),xCapitalInvested);
//            //poolExporter=new es.cuatrogatos.twominers.Exporter("https://eth.2miners.com","bc1qh53hwckqrdl92fsy8cmuhs8f99u5d0a4mzz85e");
//
//            if(!poolExporter.initializeMetricsRegistry(60000)){
//                throw new RuntimeException("Cannot connect to pool api. Aborting");
//            }
//
//                /**
//                 *  MINER METRICS INITIALIZATION
//                 */
//            SharedMetricRegistries.setDefault("trexmetrics",metricRegistry);
//
//            /// GPU REGISTER METRICS
//            summaryMetrics.getSummary().getGpus().forEach(new Consumer<Gpu>() {
//                public void accept(Gpu gpu) {
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".memTemp",summaryMetrics.getGpuMemoryTemp(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".coreTemp", summaryMetrics.getGpuTemp(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".hashRate",summaryMetrics.getGpuHashRate(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".memClock",summaryMetrics.getGpuMemoryClocks(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".coreClock",summaryMetrics.getGpuCoreClock(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".fanSpeed",summaryMetrics.getGpuFanSpeed(gpu.getGpu_id()));
//                    SharedMetricRegistries.tryGetDefault().register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".power",summaryMetrics.getGpuPower(gpu.getGpu_id()));
//                }
//            });
//
//            /// GENERAL DATA
//                SharedMetricRegistries.tryGetDefault().register("trex.paused",summaryMetrics.isPaused());
//                SharedMetricRegistries.tryGetDefault().register("trex.hashRate",summaryMetrics.getTotalHashRate());
//                SharedMetricRegistries.tryGetDefault().register("trex.upTime",summaryMetrics.getUptime());
//                SharedMetricRegistries.tryGetDefault().register("trex.acceptedCount",summaryMetrics.getAcceptedCount());
//                SharedMetricRegistries.tryGetDefault().register("trex.rejectedCount",summaryMetrics.getRejectedCount());
//                SharedMetricRegistries.tryGetDefault().register("trex.invalidCount",summaryMetrics.getInvalidCount());
//                SharedMetricRegistries.tryGetDefault().register("trex.solvedCount",summaryMetrics.getSolvedCount());
//
//            /// OPERATIONAL STATUS
//
//                SharedMetricRegistries.tryGetDefault().register("trex.running",summaryMetrics.isMinerRunning());
//                SharedMetricRegistries.tryGetDefault().register("trex.stopped",summaryMetrics.isMinerStopped());
//
//                //SharedMetricRegistries.tryGetDefault().register("activePool.name",summaryMetrics.getActivePoolName());
//                //SharedMetricRegistries.tryGetDefault().register("activePool.url",summaryMetrics.getActivePoolName());
//
//           /* final ConsoleReporter reporter = ConsoleReporter.forRegistry(SharedMetricRegistries.tryGetDefault())
//                    .convertRatesTo(TimeUnit.SECONDS)
//                    .convertDurationsTo(TimeUnit.MILLISECONDS)
//                    .build();
//            reporter.start(carbonFlushInterval, TimeUnit.MILLISECONDS);*/
//
//
//            final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
//            final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(SharedMetricRegistries.tryGetDefault())
//                    .prefixedWith(summaryMetrics.getWorker())
//                    .convertRatesTo(TimeUnit.SECONDS)
//                    .convertDurationsTo(TimeUnit.MILLISECONDS)
//                    .filter(MetricFilter.ALL)
//                    .build(graphite);
//            remoteReporter.start(carbonFlushInterval, TimeUnit.MILLISECONDS);
//
//
//            metricsInitialized=true;
//            }catch (RuntimeException nE){
//                nE.printStackTrace();
//                // CANNOT FIND T-REX API, ABORT
//                metricsInitialized=false;
//                throw nE;
//            }
//        }
//    }


//    public static double pricePerMwH=0.0;
//    public static double pricePerKwH=0.0;
//    public static double pricePerWatH=0.0;
//    public static double energyCostIncValue =0.0;
//    public static double overallEnergyCostValue=0.0;
//
//    public static BasicClient eisosClient=new BasicClient();
//
//    private static void polleisosdata(String worker, double watsPerHour, long scale){
//        MetricRegistry metricRegistry=new MetricRegistry();
//
//        Executors.newFixedThreadPool(1).submit(new Runnable() {
//            @Override
//            public void run() {
//
//                long startAtMillis=(new Date().getTime()/carbonFlushInterval)*carbonFlushInterval;
//                long endAtMillis=startAtMillis+carbonFlushInterval;
//                metricRegistry.register("cost", new Gauge<Double>() {
//                    @Override
//                    public Double getValue() {
//                        return energyCostIncValue;
//                    }
//                });
//
//                metricRegistry.register("overallCost", new Gauge<Double>() {
//                    @Override
//                    public Double getValue() {
//                        return overallEnergyCostValue;
//                    }
//                });
//
//                metricRegistry.register("PCB", new Gauge<Double>() {
//                    @Override
//                    public Double getValue() {
//                        return null;
//                    }
//                });
//
//                /// START PUSHING
//                final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
//                final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
//                        .prefixedWith(worker+".eisos")
//                        .convertRatesTo(TimeUnit.SECONDS)
//                        .convertDurationsTo(TimeUnit.MILLISECONDS)
//                        .filter(MetricFilter.ALL)
//                        .build(graphite);
//                remoteReporter.start(carbonFlushInterval, TimeUnit.MILLISECONDS);
//
//
//                while (startAtMillis-endAtMillis< carbonFlushInterval) {
//                    Logger.getAnonymousLogger().warning("Calculando el coste de la luz");
//                    try {
//                        Thread.sleep(carbonFlushInterval+startAtMillis-endAtMillis);
//
//                        energyCostIncValue =eisosClient.getConsumedForWatsHour(watsPerHour,new Interval(new Date().getTime(),new Date().getTime()+carbonFlushInterval));
//                        energyCostIncValue=energyCostIncValue*scale;
//                        overallEnergyCostValue+=energyCostIncValue;
//
//                        pricePerKwH=eisosClient.getPriceForKiloWh(new Interval(new Date().getTime(),new Date().getTime()+carbonFlushInterval));
//                        pricePerMwH=eisosClient.getPriceForMegaWh(new Interval(new Date().getTime(),new Date().getTime()+carbonFlushInterval));
//                        pricePerWatH=eisosClient.getPriceForWatsHour(new Interval(new Date().getTime(),new Date().getTime()+carbonFlushInterval));
//
//                        System.out.printf("\n %d : scale:%d energyCostInc:%f energyCostTotaL:%f",new Date().getTime(),scale,energyCostIncValue,overallEnergyCostValue);
//
//                        startAtMillis=new Date().getTime();
//                        endAtMillis=startAtMillis;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//
//    }


  /*  private static void testMetricsFailures(){
        Filter filter = null;        // That's fine if we don't use filters; https://logging.apache.org/log4j/2.x/manual/filters.html
        PatternLayout layout = null; // The layout isn't used in InstrumentedAppender




        MetricRegistry testRegistry=new MetricRegistry();
        testRegistry.register("test", new Gauge<Long>() {
            @Override
            public Long getValue() {
                return 1L;
            }
        });


        InstrumentedAppender appender = new InstrumentedAppender(testRegistry, filter, layout, false);
        appender.start();

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).addAppender(appender, Level.ALL, filter);
        context.updateLoggers(config);

        final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
        final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(testRegistry)
                .prefixedWith("test")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        remoteReporter.start(carbonFlushInterval, TimeUnit.MILLISECONDS);
    }*/
}
