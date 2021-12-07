package es.cuatrogatos.eisos.boundary;

import es.cuatrogatos.eisos.entity.Pvpc;
import no.bouvet.jsonclient.JsonClient;
import org.joda.time.Interval;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EisosClient {

    private static long cachedTtl=60*60*24*1000;
    private static Pvpc cachedData;
    private static long lastUpdatedData=0L;

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


    public static void setCachedTtl(long cachedTtl) {
        EisosClient.cachedTtl = cachedTtl;
    }

    private static long getNextTick(){
        long timeToNextTick=((lastUpdatedData+cachedTtl)/cachedTtl)*cachedTtl;
        return timeToNextTick;
    }

    private static synchronized Pvpc retrieveData(){
        if(cachedData==null || new Date().getTime()>=getNextTick()){
            cachedData=new JsonClient().http().get("https://api.esios.ree.es/archives/70/download_json?date=" + URLEncoder.encode(sdf.format(new Date()))).object(Pvpc.class);
            lastUpdatedData=new Date().getTime();
        }

        return cachedData;
    }

    public static BigDecimal getPricePerKwH(Interval interval) {
        return getPricePerMegaWh(interval).divide(new BigDecimal(1000));
    }

    public static BigDecimal getPricePerMegaWh(Interval interval) {
        return new BigDecimal(PvpcSearcher.getPriceFor(retrieveData(),interval));
    }

    public static BigDecimal getPricePerWh(Interval interval) {
        return getPricePerKwH(interval).divide(new BigDecimal(1000),20, RoundingMode.HALF_UP);
    }

    public static BigDecimal getPricePerWs(Interval interval) {
        return getPricePerWh(interval).divide(new BigDecimal(3600),20, RoundingMode.HALF_UP);
    }

    public static Double getPowerCostAt(Interval interval,Double powerConsumption) {
        int scale=1000000;
        return getPricePerWs(interval).doubleValue()*interval.toDuration().getStandardSeconds()*powerConsumption*scale;
    }
}
