package es.cuatrogatos.twominers.entity;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.beacons.Beacon;

public class MinerPoolErrorBeacon extends Beacon {

    public MinerPoolErrorBeacon(){
        super();
        super.setDescription("2Miners pool beacon, triguerred at error reported by 2miners client");;
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
