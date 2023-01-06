
package com.portfolio.pnodari.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class HyS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombreHS;
    private int porcentaje;

    public HyS() { }

    public HyS(String nombreHS, int porcentaje) {
        this.nombreHS = nombreHS;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    

   

  
    
    
    
    
}
