package es.cuatrogatos.aemet.boundary;

import es.cuatrogatos.aemet.entity.ApiResponse;
import es.cuatrogatos.aemet.entity.DatosEstacion;
import no.bouvet.jsonclient.JsonClient;
import no.bouvet.jsonclient.SimpleJsonClient;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class AemetClient {

    private static String token=null;

    private static ApiResponse lastApiResponse;
    private static DateTime lastUpdatedApiResponse;
    private static long ttlCachedApiResponse=60*1000L;

    private static ArrayList<DatosEstacion> cachedEstacionData;


    public static void setToken(String token){
        AemetClient.token=token;
    }


    public static ArrayList<DatosEstacion> getDatosEstacion(String idema){
        return getLastDatosEstacionCached(idema);
    }

    public static DatosEstacion getDatosEstacion(String idema, DateTime date){
        /*2021-11-13T12:00:00*/
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Interval rDateInterval=new Interval(date.getMillis(),date.getMillis());
        DatosEstacion oVal=null;
       return getLastDatosEstacionCached(idema).stream().filter(new Predicate<DatosEstacion>() {
           @Override
           public boolean test(DatosEstacion datosEstacion) {
               Date fData= null;
               try {
                   fData = sdf.parse(datosEstacion.getFint());
               } catch (ParseException e) {
                   e.printStackTrace();
               }
               Interval dataInterval=new Interval(fData.getTime(),fData.getTime()+(60*60*1000));
               return dataInterval.overlap(rDateInterval)!=null;
           }
       }).findFirst().get();

    }


    public static DatosEstacion getLastDatosEstacion(String idema){
        try {
            ArrayList<DatosEstacion> dataList = getLastDatosEstacionCached(idema);
            Logger.getAnonymousLogger().warning("AEMET DATA:" + dataList.get(dataList.size() - 1).getTamax());
            return dataList.get(dataList.size() - 1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static synchronized ArrayList<DatosEstacion> getLastDatosEstacionCached(String idema){
        Logger.getAnonymousLogger().warning("QUERY AEMET DATA AT IDEMA "+idema);
        HashMap<String,String> headers=new HashMap<>();
        headers.put("api_key",token);
        if(lastApiResponse!=null && lastUpdatedApiResponse.getMillis()+ttlCachedApiResponse<=new DateTime().getMillis()){
            return cachedEstacionData;
        }

        cachedEstacionData= (ArrayList<DatosEstacion>) retrieveDatosEstacion(new SimpleJsonClient().http()
                .get("https://opendata.aemet.es/opendata/api/observacion/convencional/datos/estacion/"+idema,headers)
                .object(ApiResponse.class).getDatos()
        );
        lastUpdatedApiResponse=new DateTime();
        return cachedEstacionData;
    }

    private static List<DatosEstacion> retrieveDatosEstacion(String url){
        return new JsonClient().http().get(url).list(DatosEstacion.class);
    }
}
