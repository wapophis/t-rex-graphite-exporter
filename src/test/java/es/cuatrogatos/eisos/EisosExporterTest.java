package es.cuatrogatos.eisos;

import org.junit.Test;

import static org.junit.Assert.*;

public class EisosExporterTest {

    @Test
    public void export() throws InterruptedException {
        EisosExporter exporter=new EisosExporter(60*1000L,"testworker",100);
        exporter.export("localhost",2003,true);

    }
}