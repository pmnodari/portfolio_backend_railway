
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
    
    //Constructores
    public DtoExperiencia() { }

    public DtoExperiencia(String nombreExp, String descripcionExp) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
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
