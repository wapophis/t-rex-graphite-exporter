package es.cuatrogatos.eisos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "CYM", "COF2TD","PMHPCB","SAHPCB","PMHCYM",
        "SAHCYM",
        "FOMPCB",
        "FOMCYM",
        "FOSPCB",
        "FOSCYM",
        "INTPCB",
        "INTCYM",
        "PCAPPCB",
        "PCAPCYM",
        "TEUPCB",
        "TEUCYM",
        "CCVPCB",
        "CCVCYM",
        "EDSRPCB",
        "EDSRCYM" })
public class DayHourPrice {
    private String dia;
    private String hora;
    private String pcb;

    @JsonProperty("Dia")
    public String getDia() {
        return dia;
    }
    @JsonProperty("Dia")
    public void setDia(String dia) {
        this.dia = dia;
    }
    @JsonProperty("Hora")
    public String getHora() {
        return hora;
    }
    @JsonProperty("Hora")
    public void setHora(String hora) {
        this.hora = hora;
    }
    @JsonProperty("PCB")
    public String getPcb() {
        return pcb;
    }
    @JsonProperty("PCB")
    public void setPcb(String pcb) {
        this.pcb = pcb;
    }

    public Double getDPcb(){
        return Double.valueOf(getPcb().replace(",","."));
    }
}
  /**     {
   "Dia": "29/10/2021",
   "Hora": "00-01",
   "PCB": "180,88",
   "CYM": "180,88",
   "COF2TD": "0,000082325691000000",
   "PMHPCB": "169,78",
   "PMHCYM": "169,78",
   "SAHPCB",
   "SAHCYM",
   "FOMPCB",
   "FOMCYM",
   "FOSPCB",
   "FOSCYM",
   "INTPCB",
   "INTCYM",
   "PCAPPCB",
   "PCAPCYM",
   "TEUPCB",
   "TEUCYM",
   "CCVPCB",
   "CCVCYM",
   "EDSRPCB",
   "EDSRCYM"
   },*/