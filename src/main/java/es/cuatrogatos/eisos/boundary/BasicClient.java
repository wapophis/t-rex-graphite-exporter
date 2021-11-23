package es.cuatrogatos.eisos.boundary;

import es.cuatrogatos.beacons.BeaconsManager;
import es.cuatrogatos.eisos.entity.Pvpc;
import no.bouvet.jsonclient.JsonClient;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Deprecated
public class BasicClient {

    private static EisosOnErrorBeacon errorBeacon=new EisosOnErrorBeacon();
    private Pvpc currentData;

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public Pvpc getCurrentData() {
        return currentData;
    }

    public void setCurrentData(Pvpc currentData) {
        this.currentData = currentData;
    }

    public boolean currentDataIsFromToday() {
        return currentData.getPvpc().parallelStream().allMatch(dayPrices -> {
            try {
                return DayHourPricesProcessor.getDay(dayPrices).equals(new LocalDate().toDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        });
    }


    /**
     * Request to api
     */
    public void requestDataForToday(){
        if(currentData!=null && currentDataIsFromToday()){
            return;
        }

        JsonClient jsonClient=new JsonClient();
        try {
            this.currentData = jsonClient.http().get("https://api.esios.ree.es/archives/70/download_json?date=" + URLEncoder.encode(sdf.format(new Date()))).object(Pvpc.class);
        }catch (RuntimeException e){
            Logger.getLogger("eisos/BasicClient").throwing(getClass().getName(),"requestDataForToday",e.getCause());
            e.printStackTrace();
            BeaconsManager.updateBeacon(errorBeacon.trigguer());
            return;
        }
        this.currentData.toString();
    }

    /**
     * Calcs cost of electricity for a consumed wat hour for an interval. Precition seconds. If interval is between two hours range, gets the lower
     * date interval.
     * @param consumedWats
     * @param consumedInterval
     * @return
     */
    public double getConsumedForWatsHour(double consumedWats, Interval consumedInterval){

        // TODO QUERY DATA FOR THE CONSUMED INTERVAL SPECIFIED TO BE MORE GENERIC
        refreshData();

        double watsPerSecond=consumedWats/3600;
        double hourPricePerSecond=(PvpcSearcher.getPriceFor(currentData,consumedInterval)/1000)/3600;

        return consumedInterval.toDuration().getStandardSeconds()*watsPerSecond*hourPricePerSecond;
    }

    public double getPriceForWatsHour(Interval interval){
        refreshData();
        return PvpcSearcher.getPriceFor(currentData,interval)/1000000;
    }

    public double getPriceForKiloWh(Interval interval){
        refreshData();
        return PvpcSearcher.getPriceFor(currentData,interval)/1000;
    }

    public double getPriceForMegaWh(Interval interval){
        refreshData();
        return PvpcSearcher.getPriceFor(currentData,interval);
    }



    private void refreshData(){
        if(currentData==null || (currentData!=null && !currentDataIsFromToday())){
            requestDataForToday();
        }
    }


}
