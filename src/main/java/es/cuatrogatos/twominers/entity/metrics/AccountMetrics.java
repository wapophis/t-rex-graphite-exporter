package es.cuatrogatos.twominers.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.coinbase.boundary.CoinBaseClient;
import es.cuatrogatos.twominers.boundary.TwoMinersClient;
import es.cuatrogatos.twominers.entity.Account;
import es.cuatrogatos.twominers.entity.Reward;
import es.cuatrogatos.twominers.entity.SumRewards;
import org.joda.time.Interval;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AccountMetrics {
    private Account account;
    private String poolUrl;
    private String poolUser;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Gauge<Long> getUnpaidBalance(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getStats(poolUrl,poolUser).getBalance();
                //return account.getStats().getBalance();
            }
        };
    }

    public Gauge<Double> getUnpaidBalanceInEuros(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                return ((double) TwoMinersClient.getStats(poolUrl,poolUser).getBalance() /1000000000)*(1/CoinBaseClient.getExchangeRate("EUR","ETH"));
            }
        };
    }

    public Gauge<Long> getUnConfirmedBalance(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getStats(poolUrl,poolUser).getImmature();
              //  return account.getStats().getImmature();
            }
        };
    }


    public Gauge<Long> getPaid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getStats(poolUrl,poolUser).getPaid();
               // return account.getStats().getPaid();
            }
        };
    }


    public Gauge<Long> getPending(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getStats(poolUrl,poolUser).getPending();
               // return account.getStats().getPending();
            }
        };
    }

    public Gauge<Double> getPendingInEuros(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                return TwoMinersClient.getStats(poolUrl,poolUser).getPending()*(1/CoinBaseClient.getExchangeRate("EUR","ETH"));
            }
        };
    }

    public Gauge<Long> getHashRate(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getHashrate();
            }
        };
    }

    public Gauge<Long> getCurrentHashRate(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getCurrentHashrate();
            }
        };
    }

    public Gauge<Double> getLuck(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getCurrentLuck();
            }
        };
    }

    public Gauge<Long> getWorkersOffline(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getWorkersOffline();
            }
        };
    }

    public Gauge<Long> getWorkersOnline(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getWorkersOnline();
            }
        };
    }

    public Gauge<Long> getWorkersTotal(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getWorkersTotal();
            }
        };
    }

    public Gauge<Long> getSharesStale(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesStale();
            }
        };
    }

    public Gauge<Long> getSharesInvalid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesInvalid();
            }
        };
    }

    public Gauge<Long> getSharesValid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesValid();
            }
        };
    }

    public Gauge<Long> getRoundShares(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getRoundShares();
            }
        };
    }


    public Gauge<Long> isPoolConnected(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.isPoolConnected(poolUrl,poolUser);
            }
        };
    }


    public Gauge<Long> getSumReward(long interval){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSumrewards().parallelStream().filter(new Predicate<SumRewards>() {
                    @Override
                    public boolean test(SumRewards sumRewards) {
                        return sumRewards.getInverval()==interval;
                    }
                }).findFirst().get().getReward();
            }
        };
    }


    /**
     * Metric which get the last day sum rewards from 00:00:00 to 23:59:59
     * @param date date to take sum metrics
     * @return long value
     */
    public Gauge<Long> getSumDayRewardAt(Date date){

        return new Gauge<Long>() {
            long oVal=0L;
            long dayStartAt=(date.getTime()/(24*60*60*1000))*(24*60*60*1000);
            long dayEndAt=dayStartAt+(24*60*60*1000);

            @Override
            public Long getValue() {
                TwoMinersClient.getAccount(poolUrl,poolUser).getRewards().forEach(new Consumer<Reward>() {
                    @Override
                    public void accept(Reward reward) {
                        if(reward.getTimestamp()>=dayStartAt && reward.getTimestamp()<=dayEndAt){
                            oVal+=reward.getReward();
                        }
                    }
                });
            return oVal;
            };
        };
    }


    /**
     * Returns the share in the last registered reward *
     * @return
     */
    public Gauge<Long> getLastSharePercentInBlock(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                ArrayList<Reward> copyOf=new ArrayList<>();
                copyOf.addAll(TwoMinersClient.getAccount(poolUrl,poolUser).getRewards());
                copyOf.sort(new Comparator<Reward>() {
                    @Override
                    public int compare(Reward reward, Reward t1) {
                        return Long.compare(reward.getTimestamp(),t1.getReward());
                    }
                });
                return copyOf.get(copyOf.size()).getPercent();
            }
        };

    }


    /**
     * Returns the share in the last registered reward *
     * @return
     */
    public Gauge<Long> getLastShareRewardInBlock(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                ArrayList<Reward> copyOf=new ArrayList<>();
                copyOf.addAll(TwoMinersClient.getAccount(poolUrl,poolUser).getRewards());
                copyOf.sort(new Comparator<Reward>() {
                    @Override
                    public int compare(Reward reward, Reward t1) {
                        return Long.compare(reward.getTimestamp(),t1.getReward());
                    }
                });
                return copyOf.get(copyOf.size()).getReward();
            }
        };

    }



    public Gauge<Double> getBreakEventInDays(final Double baseCapital){

        return new Gauge<Double>() {
            @Override
            public Double getValue() {

                 return (baseCapital-getPayedValueInEur().getValue())/( ((Double.valueOf(getSumReward(86400L).getValue().toString())/1000000000)*(1/CoinBaseClient.getExchangeRate("EUR","ETH")))
                 );
            }
        };
    }


    public Gauge<Double> getPayedValueInEur(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Double valuePayed=new Double(0.0);
                // EACH PAYMENT IN BTC IS CALCULATED TO THE SPOT PRICE OF THAT DAY
                for(int i=0;i<account.getPayments().size();i++){
                    HashMap<String,Object> payment= (HashMap<String, Object>) account.getPayments().get(i);
                    Date payedAt=new Date(Integer.valueOf((Integer) payment.get("timestamp")).longValue()*1000);
                    Integer amount= (Integer) payment.get("amount");
                    Double ethInUsd=CoinBaseClient.getCurrentSpotPrice("ETH-USD",sdf.format(payedAt)).getAmount();
                    Double btcInUsd=CoinBaseClient.getCurrentSpotPrice("BTC-USD",sdf.format(payedAt)).getAmount();
                    Double btcInEur=CoinBaseClient.getCurrentSpotPrice("BTC-EUR",sdf.format(payedAt)).getAmount();
                    Double eth2btc=ethInUsd/btcInUsd;
                    valuePayed+=((Double.valueOf(amount.toString())/1000000000)*eth2btc)*btcInEur;
                }
                return valuePayed;
            }
        };
    }


    public Gauge<Long> isPoolDisconnected(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                if(account==null || account.getStats()==null){
                    return 1L;
                }
                return 0L;
            }
        };
    }

    public void setPoolUrl(String poolUrl) {
        this.poolUrl=poolUrl;
    }

    public void setPoolUser(String poolUser) {
        this.poolUser=poolUser;
    }
}
