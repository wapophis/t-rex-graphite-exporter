package es.cuatrogatos.trex.entity;

public class Shares {
    private int accepted_count;
    private int invalid_count;
    private long last_share_diff;
    private int last_share_submit;
    private long last_share_submit_ts;
    private long max_share_diff;
    private int max_share_submit_ts;
    private int rejected_count;
    private int solved_count;

    public int getAccepted_count() {
        return accepted_count;
    }

    public void setAccepted_count(int accepted_count) {
        this.accepted_count = accepted_count;
    }

    public int getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(int invalid_count) {
        this.invalid_count = invalid_count;
    }

    public long getLast_share_diff() {
        return last_share_diff;
    }

    public void setLast_share_diff(long last_share_diff) {
        this.last_share_diff = last_share_diff;
    }

    public int getLast_share_submit() {
        return last_share_submit;
    }

    public void setLast_share_submit(int last_share_submit) {
        this.last_share_submit = last_share_submit;
    }

    public long getMax_share_diff() {
        return max_share_diff;
    }

    public void setMax_share_diff(long max_share_diff) {
        this.max_share_diff = max_share_diff;
    }

    public int getMax_share_submit_ts() {
        return max_share_submit_ts;
    }

    public void setMax_share_submit_ts(int max_share_submit_ts) {
        this.max_share_submit_ts = max_share_submit_ts;
    }

    public int getRejected_count() {
        return rejected_count;
    }

    public void setRejected_count(int rejected_count) {
        this.rejected_count = rejected_count;
    }

    public int getSolved_count() {
        return solved_count;
    }

    public void setSolved_count(int solved_count) {
        this.solved_count = solved_count;
    }

    public long getLast_share_submit_ts() {
        return last_share_submit_ts;
    }

    public void setLast_share_submit_ts(long last_share_submit_ts) {
        this.last_share_submit_ts = last_share_submit_ts;
    }
}
/*"shares":{"accepted_count":259,"invalid_count":0,"last_share_diff":0.0,"last_share_submit_ts":0,"max_share_diff":0.0,"max_share_submit_ts":0
,"rejected_count":0,"solved_count":0}*/