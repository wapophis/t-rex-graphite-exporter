package es.cuatrogatos.aemet.entity;

/**
 * {
 *   "idema" : "5429X",
 *   "lon" : -4.462227,
 *   "fint" : "2021-11-13T12:00:00",
 *   "prec" : 0.0,
 *   "alt" : 275.0,
 *   "vmax" : 3.9,
 *   "vv" : 1.4,
 *   "dv" : 4.0,
 *   "lat" : 37.81039,
 *   "dmax" : 40.0,
 *   "ubi" : "CÓRDOBA  PRAGDENA",
 *   "hr" : 47.0,
 *   "tamin" : 17.9,
 *   "ta" : 19.6,
 *   "tamax" : 19.6
 * }
 */
public class DatosEstacion {
    private String idema;
    private float lon;
    private float lat;
    private float prec;
    private float alt;
    private float vmax;
    private float vv;
    private float dv;
    private String fint;
    private float dmax;
    private String ubi;
    private float hr;
    private float tamin;
    private float tamax;
    private float ta;

    public String getIdema() {
        return idema;
    }

    public void setIdema(String idema) {
        this.idema = idema;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getPrec() {
        return prec;
    }

    public void setPrec(float prec) {
        this.prec = prec;
    }

    public float getAlt() {
        return alt;
    }

    public void setAlt(float alt) {
        this.alt = alt;
    }

    public float getVmax() {
        return vmax;
    }

    public void setVmax(float vmax) {
        this.vmax = vmax;
    }

    public float getVv() {
        return vv;
    }

    public void setVv(float vv) {
        this.vv = vv;
    }

    public float getDv() {
        return dv;
    }

    public void setDv(float dv) {
        this.dv = dv;
    }

    public String getFint() {
        return fint;
    }

    public void setFint(String fint) {
        this.fint = fint;
    }

    public float getDmax() {
        return dmax;
    }

    public void setDmax(float dmax) {
        this.dmax = dmax;
    }

    public String getUbi() {
        return ubi;
    }

    public void setUbi(String ubi) {
        this.ubi = ubi;
    }

    public float getHr() {
        return hr;
    }

    public void setHr(float hr) {
        this.hr = hr;
    }

    public float getTamin() {
        return tamin;
    }

    public void setTamin(float tamin) {
        this.tamin = tamin;
    }

    public float getTamax() {
        return tamax;
    }

    public void setTamax(float tamax) {
        this.tamax = tamax;
    }

    public float getTa() {
        return ta;
    }

    public void setTa(float ta) {
        this.ta = ta;
    }
}
