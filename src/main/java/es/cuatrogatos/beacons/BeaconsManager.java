package es.cuatrogatos.beacons;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class BeaconsManager {

    private static HashMap<String,Beacon> beacons=new HashMap<>();

    private static MetricRegistry metricRegistry;

    private static String metricsGroup;

    private static boolean metricsInitilized=false;


    public static void updateBeacon(Beacon beacon){
        if(beacons.containsKey(beacon.getKey())){
            beacons.remove(beacon.getKey());
            metricRegistry.remove(beacon.getKey());
        }
        beacons.put(beacon.getKey(),beacon);
        metricRegistry.register(beacon.getKey(),beacon.getMetricGauge());
    }

    public static void initMetricsAndReport(long flushPeriod){
        if(!metricsInitilized) {


            metricRegistry = new MetricRegistry();
            beacons.forEach(new BiConsumer<String, Beacon>() {
                @Override
                public void accept(String s, Beacon beacon) {
                    metricRegistry.register(s, beacon.getMetricGauge());
                }
            });

            /// START PUSHING
            final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
            final GraphiteReporter remoteReporter = GraphiteReporter.forRegistry(metricRegistry)
                    .prefixedWith("trex-reporter.beacons")
                    .convertRatesTo(TimeUnit.SECONDS)
                    .convertDurationsTo(TimeUnit.MILLISECONDS)
                    .filter(MetricFilter.ALL)
                    .build(graphite);
            remoteReporter.start(flushPeriod, TimeUnit.MILLISECONDS);
            metricsInitilized=true;
        }
    }

}
