package es.cuatrogatos.twominers.entity;

public class Reward {
    private long blockheight;
    private long timestamp;
    private long reward;
    private long percent;
    private boolean immature;
    private boolean orphan;
    private boolean uncle;

    public long getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(long blockheight) {
        this.blockheight = blockheight;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public long getPercent() {
        return percent;
    }

    public void setPercent(long percent) {
        this.percent = percent;
    }



    public boolean isOrphan() {
        return orphan;
    }

    public void setOrphan(boolean orphan) {
        this.orphan = orphan;
    }

    public boolean isUncle() {
        return uncle;
    }

    public void setUncle(boolean uncle) {
        this.uncle = uncle;
    }

    public boolean isImmature() {
        return immature;
    }

    public void setImmature(boolean immature) {
        this.immature = immature;
    }
}

/**
 {
 "blockheight": 13500695,
 "timestamp": 1635355120,
 "reward": 47002,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 **/