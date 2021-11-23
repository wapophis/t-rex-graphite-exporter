package es.cuatrogatos.eisos.boundary;

import es.cuatrogatos.eisos.entity.DayHourPrice;
import org.joda.time.Interval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayHourPricesProcessor {



    public static Date getDay(DayHourPrice dayPrice) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(dayPrice.getDia());
    }

    public static Interval toInterval(DayHourPrice dayHourPrice) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return new Interval((sdf.parse(dayHourPrice.getDia()).getTime()+getLowEndpointMillisOffset(dayHourPrice)),
                (sdf.parse(dayHourPrice.getDia()).getTime()+getHighEndpointMillisOffset(dayHourPrice)));
    }

    private static long getLowEndpointMillisOffset(DayHourPrice dayHourPrice){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return Long.parseLong(dayHourPrice.getHora().split("-")[0])*60*60*1000;
    }

    private static long getHighEndpointMillisOffset(DayHourPrice dayHourPrice){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        return Long.parseLong(dayHourPrice.getHora().split("-")[1])*60*60*1000;
    }


}
