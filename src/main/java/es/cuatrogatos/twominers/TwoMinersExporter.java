package es.cuatrogatos.twominers;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import es.cuatrogatos.beacons.BeaconsManager;
import es.cuatrogatos.twominers.entity.Account;
import es.cuatrogatos.twominers.entity.MinerPoolErrorBeacon;
import es.cuatrogatos.twominers.entity.metrics.AccountMetrics;
import no.bouvet.jsonclient.JsonClient;
import org.joda.time.DateTime;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TwoMinersExporter {
    private String poolUrl;
    private String poolUser;

    private boolean metricsInialized=false;

    private static AccountMetrics accountMetrics=null;

    private static Double capitalInvested;

    private static MetricRegistry metricRegistry;

    private long flushInterval=0l;


    public TwoMinersExporter(String poolUrl, String poolUser, Double capitalInvested,long flushInterval){
        this.poolUrl="https://"+(poolUrl.split("//")[1].split(":")[0]);
        this.poolUser=poolUser;
        this.capitalInvested=capitalInvested;
        this.flushInterval=flushInterval;
        this.initializeMetricsRegistry();
    }


    public boolean initializeMetricsRegistry(){
        try {
            if (!metricsInialized) {
                metricRegistry = new MetricRegistry();
                accountMetrics = new AccountMetrics();
                accountMetrics.setPoolUrl(poolUrl);
                accountMetrics.setPoolUser(poolUser);


                SharedMetricRegistries.add("2miners.account", metricRegistry);

                // STATS
                metricRegistry.register("stats.unConfirmedBalance", accountMetrics.getUnConfirmedBalance());

                metricRegistry.register("stats.unPaidBalance", accountMetrics.getUnpaidBalance());
                metricRegistry.register("stats.eur.unPaidBalance",accountMetrics.getUnpaidBalanceInEuros());

                metricRegistry.register("stats.paid", accountMetrics.getPaid());
                metricRegistry.register("stats.eur.paid",accountMetrics.getPayedValueInEur());

                metricRegistry.register("stats.pending", accountMetrics.getPending());
                metricRegistry.register("stats.eur.pending",accountMetrics.getPendingInEuros());

                // GENERAL
                metricRegistry.register("hashRate", accountMetrics.getHashRate());
                metricRegistry.register("currentHashRate",accountMetrics.getCurrentHashRate());
                metricRegistry.register("luck",accountMetrics.getLuck());
                // WORKERS
                metricRegistry.register("workersOnline",accountMetrics.getWorkersOnline());
                metricRegistry.register("workersOffline",accountMetrics.getWorkersOffline());
                metricRegistry.register("workersTotal",accountMetrics.getWorkersTotal());
                // SHARES
                metricRegistry.register("shares.invalid",accountMetrics.getSharesInvalid());
                metricRegistry.register("shares.valid",accountMetrics.getSharesValid());
                metricRegistry.register("shares.stale",accountMetrics.getSharesStale());
                metricRegistry.register("shares.roundShares",accountMetrics.getRoundShares());

                // CONN STATUS
                metricRegistry.register("conn.connected",accountMetrics.isPoolConnected());
                metricRegistry.register("conn.disconnected",accountMetrics.isPoolDisconnected());

                // REWARDS
                metricRegistry.register("rewards.last24Hours",accountMetrics.getSumReward(86400L));
                metricRegistry.register("rewards.yesterday",accountMetrics.getSumDayRewardAt(new Date(((new Date().getTime()/(24*60*60*1000))*(24*60*60*1000))-1)));


                // PRODUCTIVITY MINING
                metricRegistry.register("rewards.getLastSharePercentInBlock",accountMetrics.getLastSharePercentInBlock());
                metricRegistry.register("rewards.getLastShareRewardInBlock",accountMetrics.getLastSharePercentInBlock());


                // PAYMENTS
                metricRegistry.register("payments.payed.eur",accountMetrics.getPayedValueInEur());

                // BREAK EVENT IN DAYS
                if(capitalInvested!=null) {
                    metricRegistry.register("stats.breakEvent", accountMetrics.getBreakEventInDays(capitalInvested));
                }

                metricsInialized = true;


            }
        }catch (Exception e){
            metricsInialized=false;
            e.printStackTrace();
        }

        return metricsInialized;
    }




    public void export(String hostname,int port,boolean dryRun){
        final Graphite graphite = new Graphite(new InetSocketAddress(hostname,port));
        final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("2miners."+poolUser)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);

        long initialDelay=(((new Date().getTime()+flushInterval)/flushInterval)*flushInterval)-new Date().getTime();
        if(!dryRun){
            remoteReporter.start(initialDelay,flushInterval, TimeUnit.MILLISECONDS);
        }



    }


   /* public void poll2Miners(long interval) {

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long endTime = startTime;


                JsonClient jsonClient = new JsonClient();
                if(accountMetrics==null) {
                    accountMetrics = new AccountMetrics();
                }
                accountMetrics.setAccount(jsonClient.http().get(poolUrl + "/api/accounts/" + poolUser).object(Account.class));
                try {
                while(endTime - startTime < interval) {

                        Thread.sleep(interval-(endTime-startTime));

                    accountMetrics.setAccount(jsonClient.http().get(poolUrl + "/api/accounts/" + poolUser).object(Account.class));
                    Logger.getAnonymousLogger().warning("REQUEST TO THE POOL LAUNCHED WITH RESULT:"+accountMetrics.getUnConfirmedBalance().getValue());
                    endTime = System.currentTimeMillis();
                    startTime=System.currentTimeMillis();

                    }
                } catch (Exception e) {
                    Logger.getLogger("2miners/polling").throwing(this.getClass().getName(),"poll2Miners",e.getCause());
                    BeaconsManager.updateBeacon(new MinerPoolErrorBeacon().trigguer());
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                        poll2Miners(interval);

                }

            }
        });


    }


*/
}
