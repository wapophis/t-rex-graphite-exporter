package es.cuatrogatos.twominers.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    @JsonProperty("24hnumreward")
    private long daynumreward;
    @JsonProperty("24hreward")
    private long dayhreward;
    private long apiVersion;
    private HashMap<String,Object> config;
    private long currentHashrate;
    private double currentLuck;
    private long hashrate;
    private ArrayList payments;
    private long paymentsTotal;
    private ArrayList<Reward> rewards;
    private ArrayList<SumRewards> sumrewards;
    private long updatedAt;
    private HashMap<String,Object> workers;
    private long workersOffline;
    private long workersOnline;
    private long workersTotal;
    private long pageSize;
    private long roundShares;
    private long sharesStale;
    private long sharesValid;
    private long sharesInvalid;
    private Stats stats;



    public long getDaynumreward() {
        return daynumreward;
    }

    public void setDaynumreward(long daynumreward) {
        this.daynumreward = daynumreward;
    }

    public long getDayhreward() {
        return dayhreward;
    }

    public void setDayhreward(long dayhreward) {
        this.dayhreward = dayhreward;
    }

    public long getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(long apiVersion) {
        this.apiVersion = apiVersion;
    }

    public long getCurrentHashrate() {
        return currentHashrate;
    }

    public void setCurrentHashrate(long currentHashrate) {
        this.currentHashrate = currentHashrate;
    }

    public double getCurrentLuck() {
        return currentLuck;
    }

    public void setCurrentLuck(double currentLuck) {
        this.currentLuck = currentLuck;
    }

    public long getHashrate() {
        return hashrate;
    }

    public void setHashrate(long hashrate) {
        this.hashrate = hashrate;
    }

    public ArrayList getPayments() {
        return payments;
    }

    public void setPayments(ArrayList payments) {
        this.payments = payments;
    }

    public long getPaymentsTotal() {
        return paymentsTotal;
    }

    public void setPaymentsTotal(long paymentsTotal) {
        this.paymentsTotal = paymentsTotal;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public HashMap<String, Object> getWorkers() {
        return workers;
    }

    public void setWorkers(HashMap<String, Object> workers) {
        this.workers = workers;
    }

    public long getWorkersOffline() {
        return workersOffline;
    }

    public void setWorkersOffline(long workersOffline) {
        this.workersOffline = workersOffline;
    }

    public long getWorkersOnline() {
        return workersOnline;
    }

    public void setWorkersOnline(long workersOnline) {
        this.workersOnline = workersOnline;
    }

    public long getWorkersTotal() {
        return workersTotal;
    }

    public void setWorkersTotal(long workersTotal) {
        this.workersTotal = workersTotal;
    }

    public HashMap<String, Object> getConfig() {
        return config;
    }

    public void setConfig(HashMap<String, Object> config) {
        this.config = config;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getRoundShares() {
        return roundShares;
    }

    public void setRoundShares(long roundShares) {
        this.roundShares = roundShares;
    }

    public long getSharesStale() {
        return sharesStale;
    }

    public void setSharesStale(long sharesStale) {
        this.sharesStale = sharesStale;
    }

    public long getSharesValid() {
        return sharesValid;
    }

    public void setSharesValid(long sharesValid) {
        this.sharesValid = sharesValid;
    }

    public long getSharesInvalid() {
        return sharesInvalid;
    }

    public void setSharesInvalid(long sharesInvalid) {
        this.sharesInvalid = sharesInvalid;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ArrayList<SumRewards> getSumrewards() {
        return sumrewards;
    }

    public void setSumrewards(ArrayList<SumRewards> sumrewards) {
        this.sumrewards = sumrewards;
    }
}

/**
 {
 "24hnumreward": 157,
 "24hreward": 4082938,
 "apiVersion": 200,
 "config": {
 "allowedMaxPayout": 10000000000,
 "allowedMinPayout": 5000000,
 "defaultMinPayout": 5000000,
 "ipHint": "x.x.x.75",
 "ipWorkerName": "sunforceone",
 "minPayout": 5000000,
 "paymentHubHint": "BTC"
 },
 "currentHashrate": 0,
 "currentLuck": "0.29",
 "hashrate": 0,
 "pageSize": 65,
 "payments": null,
 "paymentsTotal": 0,
 "rewards": [
 {
 "blockheight": 13500709,
 "timestamp": 1635355367,
 "reward": 23099,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13500695,
 "timestamp": 1635355120,
 "reward": 47002,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500687,
 "timestamp": 1635355030,
 "reward": 42278,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500681,
 "timestamp": 1635354943,
 "reward": 26906,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500679,
 "timestamp": 1635354937,
 "reward": 27210,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500646,
 "timestamp": 1635354545,
 "reward": 67597,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500621,
 "timestamp": 1635354190,
 "reward": 17640,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500584,
 "timestamp": 1635353713,
 "reward": 33800,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500546,
 "timestamp": 1635353346,
 "reward": 47614,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500545,
 "timestamp": 1635353340,
 "reward": 71036,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500510,
 "timestamp": 1635352879,
 "reward": 44856,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500492,
 "timestamp": 1635352656,
 "reward": 35279,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500491,
 "timestamp": 1635352632,
 "reward": 28394,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500411,
 "timestamp": 1635351587,
 "reward": 17324,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13500406,
 "timestamp": 1635351545,
 "reward": 26305,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500346,
 "timestamp": 1635350575,
 "reward": 39430,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500289,
 "timestamp": 1635349804,
 "reward": 21765,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500280,
 "timestamp": 1635349663,
 "reward": 15072,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500279,
 "timestamp": 1635349653,
 "reward": 20527,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500224,
 "timestamp": 1635348935,
 "reward": 6996,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500202,
 "timestamp": 1635348598,
 "reward": 22518,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500108,
 "timestamp": 1635347290,
 "reward": 40520,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500098,
 "timestamp": 1635347151,
 "reward": 7790,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500077,
 "timestamp": 1635346892,
 "reward": 34177,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500067,
 "timestamp": 1635346814,
 "reward": 18890,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13500047,
 "timestamp": 1635346558,
 "reward": 36861,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499964,
 "timestamp": 1635345296,
 "reward": 46041,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499898,
 "timestamp": 1635344238,
 "reward": 27297,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499834,
 "timestamp": 1635343355,
 "reward": 5774,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13499805,
 "timestamp": 1635342975,
 "reward": 26439,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499787,
 "timestamp": 1635342737,
 "reward": 14399,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499739,
 "timestamp": 1635342161,
 "reward": 17324,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13499721,
 "timestamp": 1635341985,
 "reward": 34100,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499701,
 "timestamp": 1635341717,
 "reward": 20590,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499699,
 "timestamp": 1635341702,
 "reward": 7794,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499664,
 "timestamp": 1635341285,
 "reward": 47219,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499639,
 "timestamp": 1635340984,
 "reward": 7008,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499609,
 "timestamp": 1635340599,
 "reward": 44058,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499480,
 "timestamp": 1635338901,
 "reward": 7467,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499468,
 "timestamp": 1635338712,
 "reward": 34506,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499450,
 "timestamp": 1635338489,
 "reward": 20002,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499438,
 "timestamp": 1635338364,
 "reward": 28683,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499366,
 "timestamp": 1635337451,
 "reward": 39691,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499259,
 "timestamp": 1635336137,
 "reward": 14896,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499251,
 "timestamp": 1635335976,
 "reward": 6673,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499214,
 "timestamp": 1635335464,
 "reward": 24114,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499187,
 "timestamp": 1635335087,
 "reward": 20641,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499135,
 "timestamp": 1635334321,
 "reward": 13881,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499074,
 "timestamp": 1635333347,
 "reward": 26600,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499068,
 "timestamp": 1635333304,
 "reward": 13496,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499047,
 "timestamp": 1635332999,
 "reward": 40214,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13499018,
 "timestamp": 1635332478,
 "reward": 27847,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498963,
 "timestamp": 1635331773,
 "reward": 21388,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498940,
 "timestamp": 1635331483,
 "reward": 29252,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498861,
 "timestamp": 1635330272,
 "reward": 32910,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498860,
 "timestamp": 1635330250,
 "reward": 40057,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498786,
 "timestamp": 1635329279,
 "reward": 23233,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498748,
 "timestamp": 1635328739,
 "reward": 63629,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498741,
 "timestamp": 1635328638,
 "reward": 20130,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498673,
 "timestamp": 1635327623,
 "reward": 35230,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498663,
 "timestamp": 1635327515,
 "reward": 78411,
 "percent": 0.00003,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498633,
 "timestamp": 1635327177,
 "reward": 26525,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498623,
 "timestamp": 1635327034,
 "reward": 22315,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498613,
 "timestamp": 1635326859,
 "reward": 33387,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498579,
 "timestamp": 1635326362,
 "reward": 27548,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498543,
 "timestamp": 1635325931,
 "reward": 13457,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498530,
 "timestamp": 1635325811,
 "reward": 28874,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13498464,
 "timestamp": 1635325006,
 "reward": 45939,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498434,
 "timestamp": 1635324607,
 "reward": 27235,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498429,
 "timestamp": 1635324579,
 "reward": 57368,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498400,
 "timestamp": 1635324203,
 "reward": 20646,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498387,
 "timestamp": 1635324004,
 "reward": 13545,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498372,
 "timestamp": 1635323815,
 "reward": 26995,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498322,
 "timestamp": 1635323078,
 "reward": 16137,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498319,
 "timestamp": 1635322981,
 "reward": 19895,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498316,
 "timestamp": 1635322964,
 "reward": 6821,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498292,
 "timestamp": 1635322709,
 "reward": 11560,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498291,
 "timestamp": 1635322693,
 "reward": 7303,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498288,
 "timestamp": 1635322683,
 "reward": 13199,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498265,
 "timestamp": 1635322331,
 "reward": 14300,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498257,
 "timestamp": 1635322147,
 "reward": 20131,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498162,
 "timestamp": 1635320900,
 "reward": 28595,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498157,
 "timestamp": 1635320850,
 "reward": 7308,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498142,
 "timestamp": 1635320604,
 "reward": 14780,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498117,
 "timestamp": 1635320301,
 "reward": 13489,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498094,
 "timestamp": 1635320004,
 "reward": 20117,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498006,
 "timestamp": 1635318616,
 "reward": 22108,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13498005,
 "timestamp": 1635318608,
 "reward": 34514,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497989,
 "timestamp": 1635318371,
 "reward": 25765,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497926,
 "timestamp": 1635317435,
 "reward": 9109,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497909,
 "timestamp": 1635317207,
 "reward": 28874,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13497907,
 "timestamp": 1635317193,
 "reward": 27637,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497902,
 "timestamp": 1635317090,
 "reward": 18129,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497882,
 "timestamp": 1635316855,
 "reward": 20325,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497849,
 "timestamp": 1635316451,
 "reward": 33480,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497843,
 "timestamp": 1635316344,
 "reward": 20094,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497832,
 "timestamp": 1635316226,
 "reward": 20014,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497804,
 "timestamp": 1635315741,
 "reward": 8196,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497749,
 "timestamp": 1635314900,
 "reward": 27165,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497718,
 "timestamp": 1635314605,
 "reward": 26827,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497692,
 "timestamp": 1635314334,
 "reward": 27904,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497606,
 "timestamp": 1635313248,
 "reward": 16855,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497566,
 "timestamp": 1635312848,
 "reward": 10742,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497548,
 "timestamp": 1635312637,
 "reward": 13256,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497511,
 "timestamp": 1635312125,
 "reward": 49799,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497496,
 "timestamp": 1635311977,
 "reward": 23755,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497491,
 "timestamp": 1635311865,
 "reward": 46329,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497482,
 "timestamp": 1635311783,
 "reward": 36711,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497478,
 "timestamp": 1635311739,
 "reward": 35866,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497425,
 "timestamp": 1635311013,
 "reward": 33594,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497378,
 "timestamp": 1635310282,
 "reward": 35122,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497273,
 "timestamp": 1635308672,
 "reward": 6710,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497228,
 "timestamp": 1635307969,
 "reward": 26432,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497175,
 "timestamp": 1635307287,
 "reward": 40424,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13497172,
 "timestamp": 1635307227,
 "reward": 51469,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497127,
 "timestamp": 1635306786,
 "reward": 6833,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497111,
 "timestamp": 1635306608,
 "reward": 28041,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497108,
 "timestamp": 1635306562,
 "reward": 20844,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497089,
 "timestamp": 1635306328,
 "reward": 20862,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497065,
 "timestamp": 1635306044,
 "reward": 33272,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497058,
 "timestamp": 1635305902,
 "reward": 26844,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497051,
 "timestamp": 1635305789,
 "reward": 17324,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13497039,
 "timestamp": 1635305583,
 "reward": 58342,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497035,
 "timestamp": 1635305536,
 "reward": 26597,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13497019,
 "timestamp": 1635305398,
 "reward": 40424,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13496972,
 "timestamp": 1635304881,
 "reward": 21251,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496947,
 "timestamp": 1635304571,
 "reward": 7277,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496894,
 "timestamp": 1635303975,
 "reward": 26939,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496882,
 "timestamp": 1635303820,
 "reward": 19912,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496818,
 "timestamp": 1635302880,
 "reward": 23228,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496805,
 "timestamp": 1635302688,
 "reward": 13559,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496791,
 "timestamp": 1635302510,
 "reward": 33547,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496774,
 "timestamp": 1635302303,
 "reward": 13690,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496769,
 "timestamp": 1635302229,
 "reward": 44713,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496724,
 "timestamp": 1635301582,
 "reward": 20098,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496677,
 "timestamp": 1635301018,
 "reward": 21189,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496676,
 "timestamp": 1635301006,
 "reward": 19833,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496529,
 "timestamp": 1635299103,
 "reward": 19984,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496515,
 "timestamp": 1635298845,
 "reward": 17324,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13496334,
 "timestamp": 1635296591,
 "reward": 13595,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496328,
 "timestamp": 1635296505,
 "reward": 6666,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496326,
 "timestamp": 1635296492,
 "reward": 9238,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496228,
 "timestamp": 1635295021,
 "reward": 52087,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496217,
 "timestamp": 1635294773,
 "reward": 18563,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496203,
 "timestamp": 1635294443,
 "reward": 6819,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13496056,
 "timestamp": 1635292579,
 "reward": 19809,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495978,
 "timestamp": 1635291373,
 "reward": 26547,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495953,
 "timestamp": 1635291105,
 "reward": 20002,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495916,
 "timestamp": 1635290491,
 "reward": 6849,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495906,
 "timestamp": 1635290343,
 "reward": 6810,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495901,
 "timestamp": 1635290242,
 "reward": 28501,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495884,
 "timestamp": 1635289979,
 "reward": 13418,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495747,
 "timestamp": 1635288141,
 "reward": 20638,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495719,
 "timestamp": 1635287767,
 "reward": 9604,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495655,
 "timestamp": 1635287024,
 "reward": 16050,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495591,
 "timestamp": 1635286281,
 "reward": 7429,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495569,
 "timestamp": 1635285982,
 "reward": 121832,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495528,
 "timestamp": 1635285467,
 "reward": 30506,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495526,
 "timestamp": 1635285438,
 "reward": 20543,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495497,
 "timestamp": 1635285101,
 "reward": 7392,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495479,
 "timestamp": 1635284881,
 "reward": 90713,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495478,
 "timestamp": 1635284806,
 "reward": 23099,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13495452,
 "timestamp": 1635284540,
 "reward": 40632,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495394,
 "timestamp": 1635283884,
 "reward": 26757,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495373,
 "timestamp": 1635283595,
 "reward": 13273,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13495349,
 "timestamp": 1635283272,
 "reward": 63120,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494932,
 "timestamp": 1635277514,
 "reward": 52917,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494915,
 "timestamp": 1635277358,
 "reward": 13467,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494912,
 "timestamp": 1635277320,
 "reward": 24768,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494877,
 "timestamp": 1635276911,
 "reward": 6652,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494828,
 "timestamp": 1635276186,
 "reward": 19877,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494808,
 "timestamp": 1635275915,
 "reward": 9234,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494787,
 "timestamp": 1635275637,
 "reward": 21535,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494762,
 "timestamp": 1635275283,
 "reward": 32451,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494755,
 "timestamp": 1635275172,
 "reward": 27174,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494744,
 "timestamp": 1635275036,
 "reward": 14052,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494700,
 "timestamp": 1635274496,
 "reward": 13395,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494659,
 "timestamp": 1635273936,
 "reward": 20040,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494646,
 "timestamp": 1635273733,
 "reward": 27340,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494613,
 "timestamp": 1635273214,
 "reward": 41585,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494596,
 "timestamp": 1635273019,
 "reward": 24733,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494580,
 "timestamp": 1635272755,
 "reward": 33844,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494510,
 "timestamp": 1635271734,
 "reward": 29699,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13494437,
 "timestamp": 1635270818,
 "reward": 23334,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494434,
 "timestamp": 1635270776,
 "reward": 32745,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494419,
 "timestamp": 1635270561,
 "reward": 20903,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494399,
 "timestamp": 1635270285,
 "reward": 83604,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494342,
 "timestamp": 1635269539,
 "reward": 11549,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13494317,
 "timestamp": 1635269280,
 "reward": 28874,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13494307,
 "timestamp": 1635269180,
 "reward": 9447,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494295,
 "timestamp": 1635268953,
 "reward": 6611,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494227,
 "timestamp": 1635267921,
 "reward": 22254,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494216,
 "timestamp": 1635267798,
 "reward": 39962,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494208,
 "timestamp": 1635267624,
 "reward": 19931,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494194,
 "timestamp": 1635267490,
 "reward": 35217,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494161,
 "timestamp": 1635266966,
 "reward": 35020,
 "percent": 0.000016667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494154,
 "timestamp": 1635266845,
 "reward": 18675,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494136,
 "timestamp": 1635266564,
 "reward": 13441,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494110,
 "timestamp": 1635266266,
 "reward": 6822,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494094,
 "timestamp": 1635266128,
 "reward": 29054,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494080,
 "timestamp": 1635266012,
 "reward": 27498,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13494064,
 "timestamp": 1635265759,
 "reward": 84029,
 "percent": 0.000023333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489193,
 "timestamp": 1635199960,
 "reward": 6884,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489162,
 "timestamp": 1635199526,
 "reward": 6820,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489109,
 "timestamp": 1635198772,
 "reward": 13715,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489089,
 "timestamp": 1635198449,
 "reward": 17324,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13489076,
 "timestamp": 1635198326,
 "reward": 11549,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13489044,
 "timestamp": 1635197872,
 "reward": 13245,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489042,
 "timestamp": 1635197851,
 "reward": 13558,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489040,
 "timestamp": 1635197820,
 "reward": 13640,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13489031,
 "timestamp": 1635197699,
 "reward": 26570,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488986,
 "timestamp": 1635196963,
 "reward": 19799,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488936,
 "timestamp": 1635196273,
 "reward": 19911,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488919,
 "timestamp": 1635196038,
 "reward": 27082,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488904,
 "timestamp": 1635195858,
 "reward": 7709,
 "percent": 0.000003333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488896,
 "timestamp": 1635195781,
 "reward": 42521,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488831,
 "timestamp": 1635194909,
 "reward": 27600,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488799,
 "timestamp": 1635194463,
 "reward": 26485,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488757,
 "timestamp": 1635193949,
 "reward": 20152,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488715,
 "timestamp": 1635193310,
 "reward": 34649,
 "percent": 0.00002,
 "immature": false,
 "orphan": false,
 "uncle": true
 },
 {
 "blockheight": 13488669,
 "timestamp": 1635192790,
 "reward": 23541,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488645,
 "timestamp": 1635192469,
 "reward": 22444,
 "percent": 0.00001,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13488633,
 "timestamp": 1635192310,
 "reward": 57364,
 "percent": 0.000026667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13482688,
 "timestamp": 1635112631,
 "reward": 13700,
 "percent": 0.000006667,
 "immature": false,
 "orphan": false,
 "uncle": false
 },
 {
 "blockheight": 13482681,
 "timestamp": 1635112599,
 "reward": 27675,
 "percent": 0.000013333,
 "immature": false,
 "orphan": false,
 "uncle": false
 }
 ],
 "roundShares": 0,
 "sharesInvalid": 0,
 "sharesStale": 0,
 "sharesValid": 0,
 "stats": {
 "balance": 5854643,
 "immature": 0,
 "lastShare": 1635355445
 },
 "sumrewards": [
 {
 "inverval": 3600,
 "reward": 0,
 "numreward": 0,
 "name": "Last 60 minutes",
 "offset": 0
 },
 {
 "inverval": 43200,
 "reward": 1584480,
 "numreward": 57,
 "name": "Last 12 hours",
 "offset": 0
 },
 {
 "inverval": 86400,
 "reward": 4082938,
 "numreward": 157,
 "name": "Last 24 hours",
 "offset": 0
 },
 {
 "inverval": 604800,
 "reward": 5854643,
 "numreward": 225,
 "name": "Last 7 days",
 "offset": 0
 },
 {
 "inverval": 2592000,
 "reward": 5854643,
 "numreward": 225,
 "name": "Last 30 days",
 "offset": 0
 }
 ],
 "updatedAt": 1635372358320,
 "workers": {},
 "workersOffline": 0,
 "workersOnline": 0,
 "workersTotal": 0
 }**/