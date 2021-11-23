package es.cuatrogatos.trex.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Summary {
    private int accepted_count;
    private HashMap<String,Object> active_pool;
    private String algorithm;
    private String api;
    private String build_date;
    private String coin;
    private String description;
    private String driver;
    private int gpu_total;
    private ArrayList<Gpu> gpus;
    private long hashrate;
    private long hashrate_day;
    private long hashrate_hour;
    private long hashrate_minute;
    private int invalid_count;
    private String name;
    private String os;
    private boolean paused;
    private int rejected_count;
    private String revision;
    private long sharerate;
    private long sharerate_average;
    private int solved_count;
    private int success;
    private long time;
    private HashMap<String,Object> updates;
    private long uptime;
    private boolean validate_shares;
    private String version;
    private HashMap<String,Object> watchdog_stat;


    public ArrayList<Gpu> getGpus() {
        return gpus;
    }

    public void setGpus(ArrayList<Gpu> gpus) {
        this.gpus = gpus;
    }

    public int getAccepted_count() {
        return accepted_count;
    }

    public void setAccepted_count(int accepted_count) {
        this.accepted_count = accepted_count;
    }

    public HashMap<String, Object> getActive_pool() {
        return active_pool;
    }

    public void setActive_pool(HashMap<String, Object> active_pool) {
        this.active_pool = active_pool;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getBuild_date() {
        return build_date;
    }

    public void setBuild_date(String build_date) {
        this.build_date = build_date;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getGpu_total() {
        return gpu_total;
    }

    public void setGpu_total(int gpu_total) {
        this.gpu_total = gpu_total;
    }

    public long getHashrate() {
        return hashrate;
    }

    public void setHashrate(long hashrate) {
        this.hashrate = hashrate;
    }

    public long getHashrate_day() {
        return hashrate_day;
    }

    public void setHashrate_day(long hashrate_day) {
        this.hashrate_day = hashrate_day;
    }

    public long getHashrate_hour() {
        return hashrate_hour;
    }

    public void setHashrate_hour(long hashrate_hour) {
        this.hashrate_hour = hashrate_hour;
    }

    public long getHashrate_minute() {
        return hashrate_minute;
    }

    public void setHashrate_minute(long hashrate_minute) {
        this.hashrate_minute = hashrate_minute;
    }

    public int getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(int invalid_count) {
        this.invalid_count = invalid_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getRejected_count() {
        return rejected_count;
    }

    public void setRejected_count(int rejected_count) {
        this.rejected_count = rejected_count;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public long getSharerate() {
        return sharerate;
    }

    public void setSharerate(long sharerate) {
        this.sharerate = sharerate;
    }

    public long getSharerate_average() {
        return sharerate_average;
    }

    public void setSharerate_average(long sharerate_average) {
        this.sharerate_average = sharerate_average;
    }

    public int getSolved_count() {
        return solved_count;
    }

    public void setSolved_count(int solved_count) {
        this.solved_count = solved_count;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public HashMap<String, Object> getUpdates() {
        return updates;
    }

    public void setUpdates(HashMap<String, Object> udpates) {
        this.updates = udpates;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public boolean isValidate_shares() {
        return validate_shares;
    }

    public void setValidate_shares(boolean validate_shares) {
        this.validate_shares = validate_shares;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HashMap<String, Object> getWatchdog_stat() {
        return watchdog_stat;
    }

    public void setWatchdog_stat(HashMap<String, Object> watchdog_stat) {
        this.watchdog_stat = watchdog_stat;
    }
}
