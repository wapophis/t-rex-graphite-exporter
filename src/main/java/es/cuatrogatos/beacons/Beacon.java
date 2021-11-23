package es.cuatrogatos.beacons;

import com.codahale.metrics.Gauge;

public abstract class Beacon {
    private String key;
    private String description;
    private boolean trigguered;
    protected long triggueredCount=0;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTrigguered() {
        return trigguered;
    }

    public void setTrigguered(boolean trigguered) {
        this.trigguered = trigguered;
    }

    public abstract Gauge<Long> getMetricGauge();


    public abstract Beacon trigguer();
}
