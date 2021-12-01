package es.cuatrogatos.coinbase;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import es.cuatrogatos.coinbase.entity.metrics.ExchangeMetrics;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CoinBaseExporter {
    Logger logger=Logger.getLogger("COINBASE-EXPORTER");

    private long exportInterval=-1;

    private boolean metricsInitialized=false;

    private MetricRegistry metricRegistry = new MetricRegistry();

    private ExchangeMetrics exchangeMetrics;


    public CoinBaseExporter(long exportInterval){
        this.exportInterval=exportInterval;
        exchangeMetrics=new ExchangeMetrics();
        this.initializeMetrics();
    }


    public MetricRegistry initializeMetrics(){

        if(!metricsInitialized) {

            metricRegistry.register("eth.eur.exchange", exchangeMetrics.getEthExchangePrice());
            metricRegistry.register("eth.btc.exchange", exchangeMetrics.getEthBtcExchangePrice());
            metricRegistry.register("btc.eur.exchange", exchangeMetrics.getBtcExchangePrice());

            metricsInitialized=true;
        }

        return metricRegistry;

    }

    public void export(String hostName,int port){
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                /// START PUSHING
                logger.warning("STARTING THE EXPORT ROUTINE");

                final Graphite graphite = new Graphite(new InetSocketAddress(hostName, port));
                final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                        .prefixedWith("coinbase.")
                        .convertRatesTo(TimeUnit.SECONDS)
                        .convertDurationsTo(TimeUnit.MILLISECONDS)
                        .filter(MetricFilter.ALL)
                        .build(graphite);
                remoteReporter.start(exportInterval, TimeUnit.MILLISECONDS);
                logger.warning("DRYRUN NOT DEFINED REPORTING....");

            }
        });
    }

}
