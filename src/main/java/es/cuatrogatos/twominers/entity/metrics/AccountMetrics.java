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
    private String poolUrl;
    private String poolUser;



    public Gauge<Long> getUnpaidBalance(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getStats(poolUrl,poolUser).getBalance();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
                //return account.getStats().getBalance();
            }
        };
    }

    public Gauge<Double> getUnpaidBalanceInEuros(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                try{
                return ((double) TwoMinersClient.getStats(poolUrl,poolUser).getBalance() /1000000000)*(1/CoinBaseClient.getExchangeRate("EUR","ETH"));
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getUnConfirmedBalance(){
        return new Gauge<Long>(){
            @Override
            public Long getValue() {
                try {
                    return TwoMinersClient.getStats(poolUrl, poolUser).getImmature();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
              //  return account.getStats().getImmature();
            }
        };
    }


    public Gauge<Long> getPaid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getStats(poolUrl,poolUser).getPaid();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
               // return account.getStats().getPaid();
            }
        };
    }


    public Gauge<Long> getPending(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getStats(poolUrl,poolUser).getPending();
               // return account.getStats().getPending();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Double> getPendingInEuros(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                try{
                return TwoMinersClient.getStats(poolUrl,poolUser).getPending()*(1/CoinBaseClient.getExchangeRate("EUR","ETH"));
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getHashRate(){
        return new Gauge<Long>() {

            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getHashrate();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getCurrentHashRate(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getCurrentHashrate();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Double> getLuck(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getCurrentLuck();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getWorkersOffline(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getWorkersOffline();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getWorkersOnline(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getWorkersOnline();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
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

                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesStale();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getSharesInvalid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesInvalid();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getSharesValid(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSharesValid();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public Gauge<Long> getRoundShares(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getRoundShares();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    public Gauge<Long> isPoolConnected(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.isPoolConnected(poolUrl,poolUser);
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    public Gauge<Long> getSumReward(long interval){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                return TwoMinersClient.getAccount(poolUrl,poolUser).getSumrewards().parallelStream().filter(new Predicate<SumRewards>() {
                    @Override
                    public boolean test(SumRewards sumRewards) {
                        return sumRewards.getInverval()==interval;
                    }
                }).findFirst().get().getReward();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
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
                try{
                TwoMinersClient.getAccount(poolUrl,poolUser).getRewards().forEach(new Consumer<Reward>() {
                    @Override
                    public void accept(Reward reward) {
                        if(reward.getTimestamp()>=dayStartAt && reward.getTimestamp()<=dayEndAt){
                            oVal+=reward.getReward();
                        }
                    }
                });
            return oVal;
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
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
                try{
                ArrayList<Reward> copyOf=new ArrayList<>();
                copyOf.addAll(TwoMinersClient.getAccount(poolUrl,poolUser).getRewards());
                copyOf.sort(new Comparator<Reward>() {
                    @Override
                    public int compare(Reward reward, Reward t1) {
                        return Long.compare(reward.getTimestamp(),t1.getReward());
                    }
                });
                return copyOf.get(copyOf.size()-1).getPercent();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
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
                try{
                ArrayList<Reward> copyOf=new ArrayList<>();
                copyOf.addAll(TwoMinersClient.getAccount(poolUrl,poolUser).getRewards());
                copyOf.sort(new Comparator<Reward>() {
                    @Override
                    public int compare(Reward reward, Reward t1) {
                        return Long.compare(reward.getTimestamp(),t1.getReward());
                    }
                });
                return copyOf.get(copyOf.size()).getReward();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };

    }

    public Gauge<Double> getBreakEventInDays(final Double baseCapital){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                try{
                 return (baseCapital-getPayedValueInEur().getValue())/( ((Double.valueOf(getSumReward(86400L).getValue().toString())/1000000000)*(1/CoinBaseClient.getExchangeRate("EUR","ETH"))));
                // V2 return (baseCapital-getPayedValueInEur().getValue())/( (getSumDayRewardAt(new Date(((new Date().getTime()/(24*60*60*1000))*(24*60*60*1000))-1))).getValue()/1000000000)*(1/CoinBaseClient.getExchangeRate("EUR","ETH"));
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    public Gauge<Double> getPayedValueInEur(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                try{
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Double valuePayed=new Double(0.0);
                // EACH PAYMENT IN BTC IS CALCULATED TO THE SPOT PRICE OF THAT DAY
                for(int i=0;i<TwoMinersClient.getAccount(poolUrl,poolUser).getPayments().size();i++){
                    HashMap<String,Object> payment= (HashMap<String, Object>) TwoMinersClient.getAccount(poolUrl,poolUser).getPayments().get(i);
                    Date payedAt=new Date(Integer.valueOf((Integer) payment.get("timestamp")).longValue()*1000);
                    Integer amount= (Integer) payment.get("amount");
                    Double ethInUsd=CoinBaseClient.getCurrentSpotPrice("ETH-USD",sdf.format(payedAt)).getAmount();
                    Double btcInUsd=CoinBaseClient.getCurrentSpotPrice("BTC-USD",sdf.format(payedAt)).getAmount();
                    Double btcInEur=CoinBaseClient.getCurrentSpotPrice("BTC-EUR",sdf.format(payedAt)).getAmount();
                    Double eth2btc=ethInUsd/btcInUsd;
                    valuePayed+=((Double.valueOf(amount.toString())/1000000000)*eth2btc)*btcInEur;
                }
                return valuePayed;
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }


    public Gauge<Long> isPoolDisconnected(){
        return new Gauge<Long>() {
            @Override
            public Long getValue() {
                try{
                if(TwoMinersClient.getAccount(poolUrl,poolUser)==null || TwoMinersClient.getAccount(poolUrl,poolUser).getStats()==null){
                    return 1L;
                }
                return 0L;
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
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
