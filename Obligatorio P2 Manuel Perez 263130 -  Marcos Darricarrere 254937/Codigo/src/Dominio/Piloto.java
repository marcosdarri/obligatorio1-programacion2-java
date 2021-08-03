//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.io.Serializable;

public class Piloto implements Serializable {
    
    public String nombre;
    private int cedula;
    private String direccion;
    private int experiencia;

    public Piloto (String unNombre, int unaCedula, String unaDireccion, int unaExperiencia){
        this.nombre = unNombre;
        this.cedula = unaCedula;
        this.direccion = unaDireccion;
        this.experiencia = unaExperiencia;
    }
    
    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }
    
    public void setCedula(int unaCedula){  
        this.cedula = unaCedula;
    }
    
    public void setDireccion(String unaDireccion){   
        this.direccion = unaDireccion;
    }
    
    public void setExperiencia(int unaExperiencia){ 
        this.experiencia = unaExperiencia;
    }
    
    public String getNombre(){        
        return this.nombre;
    }
    
    public int getCedula(){       
        return this.cedula;
    }
    
    public String getDireccion(){       
        return this.direccion;
    }

    public int getExperiencia(){       
        return this.experiencia;
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }
}
    