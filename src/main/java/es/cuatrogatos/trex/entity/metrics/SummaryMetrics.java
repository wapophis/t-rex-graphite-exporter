package es.cuatrogatos.trex.entity.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import es.cuatrogatos.trex.boundary.TrexClient;
import es.cuatrogatos.trex.entity.Summary;

import java.util.logging.Logger;

public class SummaryMetrics {
    private Logger logger= Logger.getLogger("SummaryMetrics");

    public Gauge<Long> getGpuMemoryTemp(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getMemory_temperature();
            }
        };
    }

    public Gauge<Long> getGpuHashRate(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getHashrate_instant();
            }
        };
    }

    public Gauge<Long> getGpuTemp(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getTemperature();
            }
        };
    }

    public Gauge<Long> getGpuMemoryClocks(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getMclock();
            }
        };
    }
    public Gauge<Long> getGpuCoreClock(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getCclock();
            }
        };
    }

    public Gauge<Long> getGpuFanSpeed(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getFan_speed();
            }
        };
    }


    public Gauge<Long> getGpuPower(int index){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getGpus().get(index).getPower();
            }
        };
    }

    public Gauge<Long> getTotalHashRate(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getHashrate();
            }
        };
    }

    public Gauge<Long> getUptime(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getUptime();
            }
        };
    }

    public Gauge<Long> isMinerRunning(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TrexClient.getSummary().getUptime()>0?1L:0L;
            }
        };
    }

    public Gauge<Long> isMinerStopped(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                if(TrexClient.getSummary()==null || TrexClient.getSummary().getUptime()<=0){
                    return 1L;
                }else{
                    return 0L;
                }
            }
        };
    }

    public Gauge<Boolean> isPaused(){
        return new Gauge<Boolean>() {
            @Override
            public Boolean getValue() {
                return TrexClient.getSummary().isPaused();
            }
        };
    }

    public Gauge<Integer> getRejectedCount(){
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return TrexClient.getSummary().getRejected_count();
            }
        };
    }

    public Gauge<Integer> getAcceptedCount(){
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return TrexClient.getSummary().getAccepted_count();
            }
        };
    }

    public Gauge<Integer> getInvalidCount(){
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return TrexClient.getSummary().getInvalid_count();
            }
        };
    }

    public Gauge<Integer> getSolvedCount(){
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return TrexClient.getSummary().getSolved_count();
            }
        };
    }



    public String getWorker(){
        logger.warning("GETWORKER");
        return TrexClient.getSummary().getActive_pool().get("worker").toString();
    }
}
