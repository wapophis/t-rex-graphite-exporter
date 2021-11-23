package es.cuatrogatos.coinbase.entity.metrics;

import com.codahale.metrics.Gauge;
import es.cuatrogatos.coinbase.boundary.CoinBaseClient;

import java.util.logging.Logger;

public class ExchangeMetrics {

    public Gauge<Double> getEthExchangePrice(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                //Logger.getAnonymousLogger().warning("ETH-EUR:"+( 1/CoinBaseClient.getExchangeRate("EUR","ETH")) );
                return 1/CoinBaseClient.getExchangeRate("EUR","ETH");
            }
        };
    }


    public Gauge<Double> getBtcExchangePrice(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                //Logger.getAnonymousLogger().warning("BTC-EUR:"+( 1/CoinBaseClient.getExchangeRate("EUR","BTC")) );
                return 1/CoinBaseClient.getExchangeRate("EUR","BTC");
            }
        };
    }


    public Gauge<Double> getEthBtcExchangePrice(){
        return new Gauge<Double>() {
            @Override
            public Double getValue() {
                Logger.getAnonymousLogger().warning("ETH-BTC:"+CoinBaseClient.getExchangeRate("EUR","ETH"));
                return 1/CoinBaseClient.getExchangeRate("EUR","ETH-BTC");
            }
        };
    }
}
