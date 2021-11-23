package es.cuatrogatos.eisos.boundary;

import org.joda.time.Interval;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class EisosClientTest {

    @Test
    public void getPowerCostAt() throws InterruptedException {
        EisosClient.setCachedTtl(24*60*60*1000);
        long initialDelay=(((new Date().getTime()+60000)/60000)*60000)-new Date().getTime();

        while(true){
            Thread.sleep(initialDelay);
            initialDelay=0;
            Logger.getAnonymousLogger().warning(""+EisosClient.getPowerCostAt(new Interval(new Date().getTime(),new Date().getTime()+60000),130.0));
            Logger.getAnonymousLogger().warning(""+EisosClient.getPricePerKwH(new Interval(new Date().getTime(),new Date().getTime()+60000)));
            Thread.sleep(60000);
        }
    }
}