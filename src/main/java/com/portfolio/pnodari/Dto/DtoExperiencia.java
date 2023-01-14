
package com.portfolio.pnodari.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * Replicamos los campos que se tiene en el Entity pero sin Id
 * Le decimos que no pueden estar en blancos
 */
public class DtoExperiencia {
    
    @NotBlank
    private String nombreExp;
    
    @NotBlank
    private String descripcionExp;
    
    private String fecha_fin;
    
    @NotBlank
    private String fecha_inicio;
    
    //Constructores
    public DtoExperiencia() { }

    public DtoExperiencia(String nombreExp, String descripcionExp, String fecha_fin, String fecha_inicio) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
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
    
    //Getter y Setter
    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcionExp() {
        return descripcionExp;
    }

    public void setDescripcionExp(String descripcionExp) {
        this.descripcionExp = descripcionExp;
    }
    
    
}
