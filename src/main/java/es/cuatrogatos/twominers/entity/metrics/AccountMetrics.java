package es.cuatrogatos.twominers.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.coinbase.boundary.CoinBaseClient;
import es.cuatrogatos.twominers.boundary.TwoMinersClient;
import es.cuatrogatos.twominers.entity.Account;
import es.cuatrogatos.twominers.entity.SumRewards;

import javax.swing.*;
import java.text.SimpleDateFormat;
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
