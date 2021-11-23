package es.cuatrogatos.aemet.entity;

/**
 * {
 *   "descripcion": "exito",
 *   "estado": 200,
 *   "datos": "https://opendata.aemet.es/opendata/sh/f21e60fd",
 *   "metadatos": "https://opendata.aemet.es/opendata/sh/55c2971b"
 * }
 */
public class ApiResponse {

    private String descripcion;
    private int estado;
    private String datos;
    private String metadatos;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getMetadatos() {
        return metadatos;
    }

    public void setMetadatos(String metadatos) {
        this.metadatos = metadatos;
    }
}
