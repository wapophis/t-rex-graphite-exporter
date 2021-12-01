package es.cuatrogatos.twominers.boundary;

import es.cuatrogatos.twominers.entity.Account;
import es.cuatrogatos.twominers.entity.Stats;
import no.bouvet.jsonclient.JsonClient;

import java.util.Date;
import java.util.logging.Logger;

public class TwoMinersClient {
    private static Logger  logger=Logger.getLogger("TwoMinersClient");

    private static long cacheTtl=1000L;
    private static long lastUpdatedCache=0L;

    private static Account myAccount=null;


    public static void setCacheTtl(long ttl){
        cacheTtl=ttl;
    }

    public static Stats getStats(String uri,String poolUser) {
        return TwoMinersClient.getAccount(uri,poolUser).getStats();
    }

    public static Account getAccount(String uri,String poolUser) {
        return retrieveAccount(getPoolUrl(uri),poolUser);
    }

    public static long isPoolConnected(String uri,String poolUser) {
        if(TwoMinersClient.getAccount(uri,poolUser)!=null && TwoMinersClient.getStats(uri,poolUser)!=null){
            return 1L;
        }
        return 0L;
    }

    private static synchronized Account retrieveAccount(String uri,String account){
      //  logger.info("retrieveAccount at"+uri+" "+account);

        if(myAccount==null || lastUpdatedCache+cacheTtl<=new Date().getTime()){
            logger.info("GETTTING FROM API");
            myAccount= new JsonClient().http().get(uri + "/api/accounts/" + account).object(Account.class);
            logger.info("DATA RETURNED "+myAccount.getCurrentHashrate());
            lastUpdatedCache=new Date().getTime();
        }

        return myAccount;
    }

    private static String getPoolUrl(String uri){
        return "https://"+(uri.split("//")[1].split(":")[0]);
    }
}
