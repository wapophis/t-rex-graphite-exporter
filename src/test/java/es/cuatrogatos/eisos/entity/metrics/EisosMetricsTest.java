package es.cuatrogatos.eisos.entity.metrics;

import es.cuatrogatos.eisos.boundary.EisosClient;
import org.joda.time.Interval;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class EisosMetricsTest {

    @Test
    public void getFramePowerCost() throws InterruptedException {

        long initialDelay=(((new Date().getTime()+60000)/60000)*60000)-new Date().getTime();
        EisosMetrics metrics=new EisosMetrics("test",60);

        while(true){
            Thread.sleep(initialDelay);
            initialDelay=0;
            Logger.getAnonymousLogger().warning(""+ metrics.getFramePowerCost(140).getValue());
            Logger.getAnonymousLogger().warning(""+ metrics.getPricePerKwH().getValue());
            Logger.getAnonymousLogger().warning(""+ metrics.getOverAllCost().getValue());
            Thread.sleep(60000);
        }
    }
}