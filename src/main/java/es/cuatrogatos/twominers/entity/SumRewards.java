package es.cuatrogatos.twominers.entity;

public class SumRewards {
    private long inverval;
    private long reward;
    private long numreward;
    private String name;
    private long offset;

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public long getNumreward() {
        return numreward;
    }

    public void setNumreward(long numreward) {
        this.numreward = numreward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getInverval() {
        return inverval;
    }

    public void setInverval(long inverval) {
        this.inverval = inverval;
    }
}
/**
 {
 "inverval": 3600,
 "reward": 0,
 "numreward": 0,
 "name": "Last 60 minutes",
 "offset": 0
 },**/