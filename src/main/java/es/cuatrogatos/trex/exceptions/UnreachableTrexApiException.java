package es.cuatrogatos.trex.exceptions;

public class UnreachableTrexApiException extends Exception {

    private String apiUri;
    public UnreachableTrexApiException(String apiUri){
        super("Cannot reach the t-rex api at "+apiUri);
    }
}
