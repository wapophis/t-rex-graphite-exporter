package es.cuatrogatos.eisos.boundary;

import es.cuatrogatos.eisos.entity.Pvpc;
import org.joda.time.Interval;

import java.text.ParseException;

public class PvpcSearcher {

    public static double getPriceFor(Pvpc pvpc, Interval interval){
        return pvpc.getPvpc().parallelStream().filter(dayPrices -> {
                    try {
                        return DayHourPricesProcessor.toInterval(dayPrices).overlap(interval)!=null;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                }

        ).findFirst().get().getDPcb();
    }
}
