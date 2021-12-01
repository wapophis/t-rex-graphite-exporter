package es.cuatrogatos.trex.boundary;

import es.cuatrogatos.trex.entity.Summary;
import no.bouvet.jsonclient.JsonClient;

import java.util.Date;
import java.util.logging.Logger;

public class TrexClient {
    private static Logger  logger=Logger.getLogger("TREXCLIENT");
    private static String trexApiUrl;
    private static long cachedTttl;

    private static Summary cachedSummary;
    private static long lastUpdatedCached;

    public static Summary getSummary(){
     //   logger.info("getSummary");
        return retrieveCachedSummary();
    }
    public static String getWorkerName(){
        return getSummary().getActive_pool().get("worker").toString();
    }

    public static double getWorkerPowerConsumption(double powerOffset){
        ///****CALCULO DEL CONSUMO ENERGETICO CON INTEGRACION CON EISOS ***///
        double consumption=0.0;
        for(int i=0;i<getSummary().getGpu_total();i++){
            consumption+=getSummary().getGpus().get(i).getPower();
        }
        /// CPU + MOBO + OTHER OVERHEAD
        return consumption+powerOffset;
    };

    public static void setTrexApiUrl(String trexApiUrl) {
        TrexClient.trexApiUrl = trexApiUrl;
    }


    private static synchronized Summary retrieveCachedSummary(){
       // logger.info("retrieveCachedSummary");
        if(cachedSummary==null || lastUpdatedCached+cachedTttl<=new Date().getTime()){
            cachedSummary=new JsonClient().http().get(trexApiUrl + "/summary").object(Summary.class);
            lastUpdatedCached=new Date().getTime();
        }
        return cachedSummary;
    }

    public static void setCachedTttl(long cachedTttl) {
        TrexClient.cachedTttl = cachedTttl;
    }
}
