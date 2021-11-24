package es.cuatrogatos.aemet.boundary;

import es.cuatrogatos.aemet.entity.DatosEstacion;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class AemetClientTest {

    @Before
    public void setUp() throws Exception {
        AemetClient.setToken(System.getenv("AEMET_TOKEN"));
    }

    @Test
    public void getDatosEstacion() {
        DatosEstacion data=AemetClient.getDatosEstacion("5429X",new DateTime().minusMinutes(60));
        Logger.getAnonymousLogger().warning(data.toString());
    }

    @Test
    public void getLastDatosEstacion() {
        DatosEstacion data=AemetClient.getLastDatosEstacion("5429X");
        Logger.getAnonymousLogger().warning(data.toString());
    }
}