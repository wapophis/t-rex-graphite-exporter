package es.cuatrogatos.eisos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Pvpc {


    private ArrayList<DayHourPrice> pvpc;
    @JsonProperty("PVPC")
    public ArrayList<DayHourPrice> getPvpc() {
        return pvpc;
    }
    @JsonProperty("PVPC")
    public void setPvpc(ArrayList<DayHourPrice> pvpc) {
        this.pvpc = pvpc;
    }



}
