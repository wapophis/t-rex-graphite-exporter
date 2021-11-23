package es.cuatrogatos.coinbase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * {
 *     "data": {
 *         "base": "BTC",
 *         "currency": "EUR",
 *         "amount": "57007.17"
 *     }
 * }
 */

public class Spot {
    @JsonProperty("data")
    private HashMap<String,Object> data;

    private long updatedAt;

    public String getBase() {
        return (String) data.get("base");
    }



    public String getCurrency() {
        return (String) data.get("currency");
    }



    public double getAmount() {
        return Double.valueOf((String) data.get("amount"));
    }


    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
