package es.cuatrogatos.trex.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gpu {
    private long cclock;
    private int dag_build_mode;
    private int device_id;
    private Efficiency efficiency;
    private long fan_speed;
    private int gpu_id;
    private int gpu_user_id;
    private long hashrate;
    private long hashrate_day;
    private long hashrate_hour;
    private long hashrate_instant;
    private long hashrate_minute;
    private long intensity;
    private long lhr_tune;
    private boolean low_load;
    private long mclock;
    private long memory_temperature;
    private int mtweak;
    private String name;
    private boolean paused;
    private Pci pci;
    private boolean potentially_unstable;
    private long power;
    private long power_avr;
    private Shares shares;
    private long temperature;
    private String uuid;
    private String vendor;


    public long getCclock() {
        return cclock;
    }

    public void setCclock(long cclock) {
        this.cclock = cclock;
    }

    public int getDag_build_mode() {
        return dag_build_mode;
    }

    public void setDag_build_mode(int dag_build_mode) {
        this.dag_build_mode = dag_build_mode;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public Efficiency getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = new Efficiency();
        this.efficiency.setEfficiency(Long.parseLong(efficiency.split("\\D")[0]));
        this.efficiency.setEfficiency_units(efficiency.split(efficiency.split("\\D")[0])[1]);
    }

    public long getFan_speed() {
        return fan_speed;
    }

    public void setFan_speed(long fan_speed) {
        this.fan_speed = fan_speed;
    }

    public int getGpu_id() {
        return gpu_id;
    }

    public void setGpu_id(int gpu_id) {
        this.gpu_id = gpu_id;
    }

    public int getGpu_user_id() {
        return gpu_user_id;
    }

    public void setGpu_user_id(int gpu_user_id) {
        this.gpu_user_id = gpu_user_id;
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

    public long getHashrate_instant() {
        return hashrate_instant;
    }

    public void setHashrate_instant(long hashrate_instant) {
        this.hashrate_instant = hashrate_instant;
    }

    public long getHashrate_minute() {
        return hashrate_minute;
    }

    public void setHashrate_minute(long hashrate_minute) {
        this.hashrate_minute = hashrate_minute;
    }

    public long getIntensity() {
        return intensity;
    }

    public void setIntensity(long intensity) {
        this.intensity = intensity;
    }

    public long getLhr_tune() {
        return lhr_tune;
    }

    public void setLhr_tune(long lhr_tune) {
        this.lhr_tune = lhr_tune;
    }

    public boolean isLow_load() {
        return low_load;
    }

    public void setLow_load(boolean low_load) {
        this.low_load = low_load;
    }

    public long getMclock() {
        return mclock;
    }

    public void setMclock(long mclock) {
        this.mclock = mclock;
    }

    public long getMemory_temperature() {
        return memory_temperature;
    }

    public void setMemory_temperature(long memory_temperature) {
        this.memory_temperature = memory_temperature;
    }

    public int getMtweak() {
        return mtweak;
    }

    public void setMtweak(int mtweak) {
        this.mtweak = mtweak;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Pci getPci() {
        return pci;
    }

    @JsonProperty("pci_bus")
    public void setPciBus(int pciBus) {
        if(this.pci==null){
            this.pci=new Pci();
        }
        this.pci.setPci_bus(pciBus);
    }
    @JsonProperty("pci_domain")
    public void setPciDomain(int pciDomain){
        if(this.pci==null){
            this.pci=new Pci();
        }
        this.pci.setPci_domain(pciDomain);
    }

    @JsonProperty("pci_id")
    public void setPciId(int pciId){
        if(this.pci==null){
            this.pci=new Pci();
        }
        this.pci.setPci_id(pciId);
    }

    public boolean isPotentially_unstable() {
        return potentially_unstable;
    }

    public void setPotentially_unstable(boolean potencially_unstable) {
        this.potentially_unstable = potencially_unstable;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public long getPower_avr() {
        return power_avr;
    }

    public void setPower_avr(long power_avr) {
        this.power_avr = power_avr;
    }

    public Shares getShares() {
        return shares;
    }

    public void setShares(Shares shares) {
        this.shares = shares;
    }

    public long getTemperature() {
        return temperature;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
/**
 * {"cclock":1488,"dag_build_mode":0,"device_id":0,"efficiency":"391kH/W","fan_speed":100,"gpu_id":0,"gpu_user_id":0,"hashrate":100797662
 * ,"hashrate_day":100791032,"hashrate_hour":100794291,"hashrate_instant":100718800,"hashrate_minute":100797384,"intensity":22.0,"lhr_tune":0.0
 * ,"low_load":false,"mclock":10541,"memory_temperature":84,"mtweak":0,"name":"RTX 3080","paused":false,"pci_bus":2,"pci_domain":0
 * ,"pci_id":0,"potentially_unstable":false,"power":258,"power_avr":258,"shares":{"accepted_count":259,"invalid_count":0,"last_share_diff":0.0,"last_share_submit_ts":0,"max_share_diff":0.0,"max_share_submit_ts":0,"rejected_count":0,"solved_count":0}
 * ,"temperature":42,"uuid":"3c2916092403773bc07439debbf5332f","vendor":"Gigabyte"}
 */