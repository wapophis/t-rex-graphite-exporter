package es.cuatrogatos.twominers.entity;

public class Stats {
    private long balance;
    private long immature;
    private long lastShare;
    private long paid;
    private long pending;

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
    /**  "stats": {
     "balance": 5854643,
     "immature": 0,
     "lastShare": 1635355445
     },*/

}
