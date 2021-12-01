package es.cuatrogatos.twominers.entity.mappings;

import es.cuatrogatos.twominers.entity.Account;
import es.cuatrogatos.twominers.entity.metrics.AccountMetrics;
import no.bouvet.jsonclient.JsonConverter;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class AccountMappingTest {
    Account account;
    private String jsonInput=new String(Files.readAllBytes(Paths.get("src/test/resources/twominersresponse.json")));

    String poolUrl="https://eth.2miners.com";

    public AccountMappingTest() throws IOException {
    }


    @Test
    public void testBulkMappings() {
        JsonConverter mapper=new JsonConverter();
        account=mapper.toObject(jsonInput,Account.class);
        Logger.getLogger("").warning("Finished");
    }

}
