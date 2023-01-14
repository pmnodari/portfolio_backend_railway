
package com.portfolio.pnodari.Dto;

import javax.validation.constraints.NotBlank;


public class DtoEducacion {
    
    @NotBlank
    private String nombreEdu;
    @NotBlank
    private String descripcionEdu;
    
    private String fecha_fin;
    
    @NotBlank
    private String fecha_inicio;

    public DtoEducacion() {}

    public DtoEducacion(String nombreEdu, String descripcionEdu, String fecha_fin, String fecha_inicio) {
        this.nombreEdu = nombreEdu;
        this.descripcionEdu = descripcionEdu;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    

    public String getNombreEdu() {
        return nombreEdu;
    }

    public void setNombreEdu(String nombreEdu) {
        this.nombreEdu = nombreEdu;
    }

    public String getDescripcionEdu() {
        return descripcionEdu;
    }

    public void setDescripcionEdu(String descripcionEdu) {
        this.descripcionEdu = descripcionEdu;
    }
    
    
    
    
}
