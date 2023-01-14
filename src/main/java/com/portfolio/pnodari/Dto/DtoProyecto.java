
package com.portfolio.pnodari.Dto;

import javax.validation.constraints.NotBlank;


public class DtoProyecto {
    
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String fecha_realizacion;
    @NotBlank
    private String img;

    public DtoProyecto() {  }

    public DtoProyecto(String nombreP, String descripcionP, String fecha_realizacion, String img ) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.fecha_realizacion = fecha_realizacion;
        this.img = img;
    }

    public String getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(String fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    
    
}
