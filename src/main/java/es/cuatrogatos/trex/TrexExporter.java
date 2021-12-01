package es.cuatrogatos.trex;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import es.cuatrogatos.trex.boundary.TrexClient;
import es.cuatrogatos.trex.entity.Gpu;
import es.cuatrogatos.trex.entity.metrics.SummaryMetrics;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class TrexExporter {
    Logger logger=Logger.getLogger("TREX-EXPORTER");

    private boolean metricsInitialized=false;
    private long exportInterval=-1;

    private SummaryMetrics summaryMetrics;

    MetricRegistry metricRegistry=null;

    public TrexExporter(long flushInterval){
        this.exportInterval=flushInterval;
        summaryMetrics=new SummaryMetrics();
        this.initializeMetrics();
    }

    public MetricRegistry initializeMetrics(){
        if(!metricsInitialized){
            metricRegistry=new MetricRegistry();

            /// GPU REGISTER METRICS
            TrexClient.getSummary().getGpus().forEach(new Consumer<Gpu>() {
                @Override
                public void accept(Gpu gpu) {
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".memTemp",summaryMetrics.getGpuMemoryTemp(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".coreTemp", summaryMetrics.getGpuTemp(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".hashRate",summaryMetrics.getGpuHashRate(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".memClock",summaryMetrics.getGpuMemoryClocks(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".coreClock",summaryMetrics.getGpuCoreClock(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".fanSpeed",summaryMetrics.getGpuFanSpeed(gpu.getGpu_id()));
                    metricRegistry.register(gpu.getVendor()+"."+gpu.getName()+"."+gpu.getUuid()+".power",summaryMetrics.getGpuPower(gpu.getGpu_id()));
                }
            });



            /// GENERAL DATA
            metricRegistry.register("trex.paused",summaryMetrics.isPaused());
            metricRegistry.register("trex.hashRate",summaryMetrics.getTotalHashRate());
            metricRegistry.register("trex.upTime",summaryMetrics.getUptime());
            metricRegistry.register("trex.acceptedCount",summaryMetrics.getAcceptedCount());
            metricRegistry.register("trex.rejectedCount",summaryMetrics.getRejectedCount());
            metricRegistry.register("trex.invalidCount",summaryMetrics.getInvalidCount());
            metricRegistry.register("trex.solvedCount",summaryMetrics.getSolvedCount());

            /// OPERATIONAL STATUS

            metricRegistry.register("trex.running",summaryMetrics.isMinerRunning());
            metricRegistry.register("trex.stopped",summaryMetrics.isMinerStopped());

            // ACTIVEPOOL
            metricRegistry.register("trex.active_pool.retries",summaryMetrics.getConnectionLostRetries());

            //metricRegistry.register("trex.active_pool.difficulty",summaryMetrics.getDifficulty());

            // NETWORK DATA
            //metricRegistry.register("trex.network.difficulty",summaryMetrics.getNetWorkDifficulty());

            // PERFORMANCE MINER RATES
            metricRegistry.register("trex.sharerate",summaryMetrics.getShareRate());
            metricRegistry.register("trex.sharerate_avg",summaryMetrics.getAverageShareRate());


            metricsInitialized=true;

        }

        return metricRegistry;
    }

    public void export(String hostname,int port,boolean dryRun){

        logger.warning("STARTING THE EXPORT ROUTINE TO SERVER "+hostname+":"+port);
        final Graphite graphite = new Graphite(new InetSocketAddress(hostname, port));
        final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith(summaryMetrics.getWorker())
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        long initialDelay=(((new Date().getTime()+exportInterval)/exportInterval)*exportInterval)-new Date().getTime();

        if(!dryRun){
            remoteReporter.start(initialDelay,exportInterval, TimeUnit.MILLISECONDS);
            logger.warning("DRYRUN IN FALSE, REPORTING...");
        }

    }
}
