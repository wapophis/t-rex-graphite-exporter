package es.cuatrogatos.trex.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.trex.boundary.TrexClient;

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

    public Gauge<Integer> getConnectionLostRetries(){
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return (Integer) TrexClient.getSummary().getActive_pool().get("retries");
            }
        };
    }



    public String getWorker(){
        logger.warning("GETWORKER");
        return TrexClient.getSummary().getActive_pool().get("worker").toString();
    }

    // Current pool difficulty
    public Gauge<Integer> getDifficulty() {
        return new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                try {
                    //"difficulty":"8.73 G"
                    return (Integer) TrexClient.getSummary().getActive_pool().get("difficulty");
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    public Gauge<Float> getShareRate() {
        return new Gauge<Float>() {
            @Override
            public Float getValue() {
                try {
                    //"difficulty":"8.73 G"
                    return (Float) TrexClient.getSummary().getSharerate();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    /**
     *
     * @return
     */
    public Gauge<Float> getAverageShareRate() {
         return new Gauge<Float>() {
            @Override
            public Float getValue() {
                try {
                    //"difficulty":"8.73 G"
                    return (Float) TrexClient.getSummary().getSharerate_average();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }
}
