package es.cuatrogatos.trex.entity;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.beacons.Beacon;

public class TrexOnErrorBeacon extends Beacon {

    public TrexOnErrorBeacon(){
        super();
        super.setDescription("TRex beacon, triguerred at error reported by trex client");;
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
