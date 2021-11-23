package es.cuatrogatos.eisos;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import es.cuatrogatos.eisos.boundary.BasicClient;
import es.cuatrogatos.eisos.boundary.EisosClient;
import es.cuatrogatos.eisos.entity.metrics.EisosMetrics;
import es.cuatrogatos.trex.boundary.TrexClient;
import org.joda.time.Interval;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EisosExporter {

    private BigDecimal pricePerMwH;
    private BigDecimal pricePerKwH;
    private BigDecimal pricePerWatH;
    private BigDecimal pricePerWats;
    private double energyCostIncValue;
    private double overallEnergyCostValue;

    private long exportInterval=-1;

    private boolean metricsInitialized=false;

    private MetricRegistry metricRegistry = new MetricRegistry();

    private BasicClient eisosClient=new BasicClient();

    private double powerConsumptionOffset;

    private String worker;

    EisosMetrics eisosMetrics;


    public EisosExporter(long exportInterval,String workerName,double powerConsumptionOffset){
        this.exportInterval=exportInterval;
        this.worker=workerName;
        this.powerConsumptionOffset=powerConsumptionOffset;
        eisosMetrics=new EisosMetrics(workerName,Math.round(exportInterval/1000));
        this.initializeMetrics();
        EisosClient.setCachedTtl(24*60*60*1000);
    }


    public void export(String hostName,int port,boolean dryRun){
        /// START PUSHING
                final Graphite graphite = new Graphite(new InetSocketAddress(hostName, port));
                final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                        .prefixedWith(worker+".eisos")
                        .convertRatesTo(TimeUnit.SECONDS)
                        .convertDurationsTo(TimeUnit.MILLISECONDS)
                        .filter(MetricFilter.ALL)
                        .build(graphite);
                long initialDelay=(((new Date().getTime()+exportInterval)/exportInterval)*exportInterval)-new Date().getTime();
        if(!dryRun){
            remoteReporter.start(initialDelay,exportInterval, TimeUnit.MILLISECONDS);
        }

    }

//    public void export(){
//
//        Executors.newFixedThreadPool(1).submit(new Runnable() {
//            @Override
//            public void run() {
//
//                long startAtMillis=(new Date().getTime()/exportInterval)*exportInterval;
//                long endAtMillis=startAtMillis+exportInterval;
//
//                /// START PUSHING
//                final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
//                final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
//                        .prefixedWith(worker+".eisos")
//                        .convertRatesTo(TimeUnit.SECONDS)
//                        .convertDurationsTo(TimeUnit.MILLISECONDS)
//                        .filter(MetricFilter.ALL)
//                        .build(graphite);
//                remoteReporter.start(exportInterval, TimeUnit.MILLISECONDS);
//                try {
//
//                while (startAtMillis-endAtMillis< exportInterval) {
//                    Logger.getAnonymousLogger().warning("Calculando el coste de la luz");
//
//
//                        Thread.sleep(exportInterval+startAtMillis-endAtMillis);
//
//                        pricePerMwH=new BigDecimal(eisosClient.getPriceForMegaWh(new Interval(new Date().getTime(),new Date().getTime()+exportInterval)));
//                        pricePerKwH=pricePerMwH.divide(new BigDecimal(1000));
//                        pricePerWatH=pricePerKwH.divide(new BigDecimal(1000),20,RoundingMode.HALF_UP);
//                        pricePerWats=pricePerWatH.divide(new BigDecimal(3600),20, RoundingMode.HALF_UP);
//
//                        Interval consumedInterval=new Interval(new Date().getTime(),new Date().getTime()+exportInterval);
//
//                        energyCostIncValue =pricePerWats.doubleValue()*consumedInterval.toDuration().getStandardSeconds()*powerConsumption*1000000;
//                        overallEnergyCostValue+=energyCostIncValue;
//
//                        Logger.getLogger("eisos/Exporter").log(Level.WARNING,"interval:{0} (s) | consumption:{1} | pricePerMwH:{2} | pricePerKwH:{3} | pricePerWh:{4} | pricePerWat/s:{5}"
//                                , new Object[]{consumedInterval.toDuration().getStandardSeconds(), powerConsumption, pricePerMwH, pricePerKwH.toString(), pricePerWatH.toString(),pricePerWats.toString()});
//
//                        Logger.getLogger("eisos/Exporter").log(Level.WARNING," | energyCostInc:{0} | energyCostTotaL:{1} |",
//                                new Object[]{energyCostIncValue,overallEnergyCostValue}
//                        );
//
//
//                        startAtMillis=new Date().getTime();
//                        endAtMillis=startAtMillis;
//
//                  }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    try {
//                        // SLEEP THREAD AND CONTINUOS RETRY
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    export(worker);
//
//                }
//            }
//        });
//    }

    public MetricRegistry initializeMetrics(){

        if(!metricsInitialized) {
            metricRegistry=new MetricRegistry();
            metricRegistry.register("cost",eisosMetrics.getFramePowerCost(TrexClient.getWorkerPowerConsumption(powerConsumptionOffset)));
            metricRegistry.register("overallCost",eisosMetrics.getOverAllCost());
            metricRegistry.register("priceKWh",eisosMetrics.getPricePerKwH());
            metricRegistry.register("priceMWh",eisosMetrics.getPricePerMwH());
            metricRegistry.register("priceWh",eisosMetrics.getPricePerWH());
            metricRegistry.register("priceWs",eisosMetrics.getPricePerWs());
            metricsInitialized=true;
//            metricRegistry.register("cost", new Gauge<Double>() {
//                @Override
//                public Double getValue() {
//                    return energyCostIncValue;
//                }
//            });
//
//            metricRegistry.register("overallCost", new Gauge<Double>() {
//                @Override
//                public Double getValue() {
//                    return overallEnergyCostValue;
//                }
//            });
//
//            metricRegistry.register("priceKWh", new Gauge<BigDecimal>() {
//                @Override
//                public BigDecimal getValue() {
//                    return pricePerKwH;
//                }
//            });
//
//            metricRegistry.register("priceMWh", new Gauge<BigDecimal>() {
//                @Override
//                public BigDecimal getValue() {
//                    return pricePerMwH;
//                }
//            });
//
//            metricRegistry.register("priceWh", new Gauge<BigDecimal>() {
//                @Override
//                public BigDecimal getValue() {
//                    return pricePerWatH;
//                }
//            });
//
//            metricRegistry.register("priceWs", new Gauge<BigDecimal>() {
//                @Override
//                public BigDecimal getValue() {
//                    return pricePerWats;
//                }
//            });

        }
        return metricRegistry;

    }

}
