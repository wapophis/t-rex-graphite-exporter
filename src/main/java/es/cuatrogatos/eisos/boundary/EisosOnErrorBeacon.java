package es.cuatrogatos.eisos.boundary;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.beacons.Beacon;

public class EisosOnErrorBeacon extends Beacon {

    public EisosOnErrorBeacon(){
        super();
        super.setDescription("Eisos beacon, triguerred at error reported by eisos client");;
        super.setKey(getClass().getName());
       triggueredCount=0;
    }


    @Override
    public Gauge<Long> getMetricGauge() {
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return triggueredCount;
            }
        };
    }

    @Override
    public Beacon trigguer() {
        triggueredCount++;
        return this;
    }


}
