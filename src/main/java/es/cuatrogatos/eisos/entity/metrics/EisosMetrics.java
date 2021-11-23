package es.cuatrogatos.eisos.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.eisos.boundary.EisosClient;
import org.joda.time.Interval;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;

public class EisosMetrics {

        private String worker;
        private double overallEnergyCostValue=0.0;

        private Interval frameInterval=null;
        private Integer  frameUnitsInMillis=null;


        public EisosMetrics(String workerName,Integer unitsInSeconds){
            this.worker=workerName;
            this.frameUnitsInMillis=unitsInSeconds*1000;
            initFrameInterval();
        }


        private void initFrameInterval(){
            long startIntervalAt=(new Date().getTime()/frameUnitsInMillis)*frameUnitsInMillis;
            frameInterval=new Interval(startIntervalAt,startIntervalAt+(frameUnitsInMillis));
        }

        private void advanceFrame(){
            if(frameInterval.overlap(new Interval(new Date().getTime(),new Date().getTime()+frameUnitsInMillis))==null){
                frameInterval=new Interval(frameInterval.getEndMillis(),frameInterval.getEndMillis()+frameUnitsInMillis);
                Logger.getAnonymousLogger().warning(frameInterval.toString());
            }
        }

        public Gauge<Double> getFramePowerCost(double power){
            return  new Gauge<Double>() {
                @Override
                public Double getValue() {
                    Double increment=EisosClient.getPowerCostAt(frameInterval,power);
                    overallEnergyCostValue+=increment;
                    advanceFrame();
                    return increment;
                }
            };
        }

        public Gauge<Double> getOverAllCost() {
            return new Gauge<Double>() {
                @Override
                public Double getValue() {
                    return overallEnergyCostValue;
                }
            };
        }

        public Gauge<BigDecimal> getPricePerKwH(){
            return new Gauge<BigDecimal>() {
                @Override
                public BigDecimal getValue() {
                    advanceFrame();
                    return EisosClient.getPricePerKwH(frameInterval);
                }
            };
        }

        public Gauge<BigDecimal> getPricePerMwH(){
            return new Gauge<BigDecimal>() {
                @Override
                public BigDecimal getValue() {
                    advanceFrame();
                    return EisosClient.getPricePerMegaWh(frameInterval);
                }
            };
        }

        public Gauge<BigDecimal> getPricePerWH(){
        return new Gauge<BigDecimal>() {
            @Override
            public BigDecimal getValue() {
                advanceFrame();
                return EisosClient.getPricePerWh(frameInterval);
            }
            };
        }


        public Gauge<BigDecimal> getPricePerWs(){
            return new Gauge<BigDecimal>() {
                @Override
                public BigDecimal getValue() {
                    advanceFrame();
                    return EisosClient.getPricePerWs(frameInterval);
                }
            };
        }
}
