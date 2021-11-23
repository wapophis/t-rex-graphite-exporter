package es.cuatrogatos.twominers.entity.metrics;

import es.cuatrogatos.twominers.entity.Account;
import no.bouvet.jsonclient.JsonClient;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class AccountMetricsTest {

    @Test
    public void getBreakEventInDays() {
        String poolUrl="https://eth.2miners.com/api/accounts/";
        AccountMetrics accountMetrics=new AccountMetrics();
        accountMetrics.setAccount(new JsonClient().http().get(poolUrl).object(Account.class));
        Double inDays=accountMetrics.getBreakEventInDays(8000.0).getValue();
        Logger.getAnonymousLogger().warning("");
    }

    @Test
    public void getUnpaidBalanceInEuros() {

        String poolUrl="https://eth.2miners.com/api/accounts/";
        AccountMetrics accountMetrics=new AccountMetrics();
        accountMetrics.setAccount(new JsonClient().http().get(poolUrl).object(Account.class));
        Double result=accountMetrics.getUnpaidBalanceInEuros().getValue();
        Logger.getAnonymousLogger().warning(""+result);
    }
}