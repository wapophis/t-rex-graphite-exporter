package es.cuatrogatos.twominers.entity;

public class Stats {
    private long balance;
    private long immature;
    private long lastShare;
    private long paid;
    private long pending;
    private long blocksFound;

    public long getLastShare() {
        return lastShare;
    }

    public void setLastShare(long lastShare) {
        this.lastShare = lastShare;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getImmature() {
        return immature;
    }

    public void setImmature(long immature) {
        this.immature = immature;
    }

    public long getPaid() {
        return paid;
    }

    public void setPaid(long paid) {
        this.paid = paid;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public long getBlocksFound() {
        return blocksFound;
    }

    public void setBlocksFound(long blocksFound) {
        this.blocksFound = blocksFound;
    }
}
    /**  "stats": {
     "balance": 5854643,
     "immature": 0,
     "lastShare": 1635355445
     },*/


    /*"stats": {
        "balance": 18409822,
                "blocksFound": 1,
                "immature": 126710,
                "lastShare": 1638359043,
                "paid": 235293019,
                "pending": 0
    },*/


