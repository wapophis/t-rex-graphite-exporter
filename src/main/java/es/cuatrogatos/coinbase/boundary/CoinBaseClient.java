package es.cuatrogatos.coinbase.boundary;

import es.cuatrogatos.coinbase.entity.ExchangeRates;
import es.cuatrogatos.coinbase.entity.Spot;
import no.bouvet.jsonclient.JsonClient;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

public class CoinBaseClient {
    private static long cachedTtl=60000;

    private static ExchangeRates exchangeRates;
    private static HashMap<String,Spot> spotCache=new HashMap<>();

    public static synchronized Spot getCurrentSpotPrice(String pair,String date){
            if(!spotCache.containsKey(pair+"_"+date)){
                spotCache.put(pair + "_" + date, new JsonClient().http().get("https://api.coinbase.com/v2/prices/" + pair + "/spot?date=" + date).object(Spot.class));
            }

        return spotCache.get(pair+"_"+date);
    }

    public static synchronized ExchangeRates loadExchageRates(String currency){
        if(currency==null){
            currency="USD";
        }
        if(exchangeRates==null || exchangeRates.getUpdatedAt()+cachedTtl<=new Date().getTime()) {
          //  Logger.getAnonymousLogger().warning("Queriying COINBASE FOR "+currency);
            exchangeRates = new JsonClient().http().get("https://api.coinbase.com/v2/exchange-rates?currency=" + currency).object(ExchangeRates.class);
            exchangeRates.setUpdatedAt(new Date().getTime());
        }else{
          //  Logger.getAnonymousLogger().warning("RESULT FROM CACHE HIT "+currency);
        }

        return exchangeRates;
    }

    public static Double getExchangeRate(String currency,String pair){
        if(pair.split("-").length<=1){
            return Double.valueOf(loadExchageRates(currency).getRates().get(pair));
        }else{
            String pairStart=pair.split("-")[0];
            String pairEnd=pair.split("-")[1];

            Double valueForCurrencyStart=Double.valueOf(loadExchageRates(currency).getRates().get(pairStart));
            Double valueForCurrencyEnd=Double.valueOf(loadExchageRates(currency).getRates().get(pairEnd));
            Double relation=valueForCurrencyEnd/valueForCurrencyStart;
            return relation;
        }

    }


}
