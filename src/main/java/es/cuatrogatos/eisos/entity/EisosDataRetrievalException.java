package es.cuatrogatos.eisos.entity;

public class EisosDataRetrievalException extends Exception{

    public EisosDataRetrievalException(Throwable cause){
        super("Cannot get data from eisos api.",cause);
    }
}
