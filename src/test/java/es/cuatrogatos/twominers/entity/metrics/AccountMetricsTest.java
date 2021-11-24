package es.cuatrogatos.twominers.entity.metrics;

import es.cuatrogatos.aemet.boundary.AemetClient;
import es.cuatrogatos.twominers.boundary.TwoMinersClient;
import es.cuatrogatos.twominers.entity.Account;
import no.bouvet.jsonclient.JsonClient;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class AccountMetricsTest {

    AccountMetrics accountMetrics;

    String poolUrl="https://eth.2miners.com/api/accounts/";
    String poolUser=System.getenv("TWO_MINERS_TEST_ACCOUNT");
    @Before
    public void setUp() throws Exception {
        accountMetrics=new AccountMetrics();
        accountMetrics.setPoolUrl(poolUrl);
        accountMetrics.setPoolUser(poolUser);
    }

    @Test
    public void getBreakEventInDays() {
        Double result=accountMetrics.getBreakEventInDays(1000.0).getValue();
        Logger.getAnonymousLogger().warning(""+result);

    }

    @Test
    public void getUnpaidBalanceInEuros() {
        String poolUrl="https://eth.2miners.com/api/accounts/";
        Double result=accountMetrics.getUnpaidBalanceInEuros().getValue();
        Logger.getAnonymousLogger().warning(""+result);
    }
}