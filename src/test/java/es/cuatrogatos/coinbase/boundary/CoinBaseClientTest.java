package es.cuatrogatos.coinbase.boundary;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CoinBaseClientTest {

    private CoinBaseClient client;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getCurrentSpotPrice() {


    }

    @Test
    public void loadExchageRates() {
    }

    @Test
    public void getExchangeRate() throws InterruptedException {
            Logger.getAnonymousLogger().warning("EUR-BTC"+CoinBaseClient.getExchangeRate("EUR", "BTC"));
            Logger.getAnonymousLogger().warning("EUR:BTC-ETH"+CoinBaseClient.getExchangeRate("EUR", "BTC-ETH"));
    }

}