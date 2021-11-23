package es.cuatrogatos.aemet.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.aemet.boundary.AemetClient;

public class EstacionMetrics {
    private String idema=null;

    public EstacionMetrics(String idema){
        this.idema=idema;
    }

    public Gauge<Float> getLastTemp(){
        return new Gauge<Float>() {
            @Override
            public Float getValue() {
                return AemetClient.getLastDatosEstacion(idema).getTa();
            }
        };
    }

    public Gauge<Float> getLastRelativeHumidity(){
        return new Gauge<Float>() {
            @Override
            public Float getValue() {
                return AemetClient.getLastDatosEstacion(idema).getHr();
            }
        };
    }

    public Gauge<Float> getLastMaxTemp(){
        return new Gauge<Float>() {
            @Override
            public Float getValue() {
                return AemetClient.getLastDatosEstacion(idema).getTamax();
            }
        };
    }

    public Gauge<Float> getLastMinTemp(){
        return new Gauge<Float>() {
            @Override
            public Float getValue() {
                return AemetClient.getLastDatosEstacion(idema).getTamin();
            }
        };
    }

}
