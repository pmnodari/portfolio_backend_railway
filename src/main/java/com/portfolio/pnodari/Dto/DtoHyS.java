/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.pnodari.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author pmnod
 */
public class DtoHyS {
    
    @NotBlank
    private String nombreHS;
    
    @NotBlank
    private int porcentaje;

    public DtoHyS() { }

    public DtoHyS(String nombreHS, int porcentaje) {
        this.nombreHS = nombreHS;
        this.porcentaje = porcentaje;
    }

    public String getNombreHS() {
        return nombreHS;
    }

    public void setNombreHS(String nombreHS) {
        this.nombreHS = nombreHS;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentajeHS(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    

    
    
    
     
    
    
}
