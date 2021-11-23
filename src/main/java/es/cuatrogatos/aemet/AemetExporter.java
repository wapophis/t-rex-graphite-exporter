package es.cuatrogatos.aemet;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import es.cuatrogatos.aemet.boundary.AemetClient;
import es.cuatrogatos.aemet.entity.metrics.EstacionMetrics;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AemetExporter {

    private long exportInterval=-1;

    private boolean metricsInitialized=false;

    private MetricRegistry metricRegistry = new MetricRegistry();

    private EstacionMetrics estacionMetrics;


    public AemetExporter(long exportInterval, String stationId, String token){
        this.exportInterval=exportInterval;
        estacionMetrics=new EstacionMetrics(stationId);
        AemetClient.setToken(token);
        this.initializeMetrics();
    }


    public MetricRegistry initializeMetrics(){

        if(!metricsInitialized) {
            metricRegistry.register("last.MaxTemp", estacionMetrics.getLastMaxTemp());
            metricRegistry.register("last.MinTemp", estacionMetrics.getLastMinTemp());
            metricRegistry.register("last.Temp", estacionMetrics.getLastTemp());
            metricRegistry.register("last.HumRel", estacionMetrics.getLastRelativeHumidity());
            metricsInitialized=true;
        }

        return metricRegistry;

    }

    public void export(String hostName,int port,boolean dryRun){
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                Logger.getAnonymousLogger().warning("STARTING AEMET DATA PUSHING USING INTERVAL "+exportInterval+ " IN MILLISECONDS");
                /// START PUSHING
                final Graphite graphite = new Graphite(new InetSocketAddress(hostName, port));
                final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                        .prefixedWith("aemet.")
                        .convertRatesTo(TimeUnit.SECONDS)
                        .convertDurationsTo(TimeUnit.MILLISECONDS)
                        .filter(MetricFilter.ALL)
                        .build(graphite);

                long initialDelay=(((new Date().getTime()+exportInterval)/exportInterval)*exportInterval)-new Date().getTime();
                if(!dryRun) {
                    remoteReporter.start(initialDelay,exportInterval, TimeUnit.MILLISECONDS);
                }
            }
        });
    }

}
