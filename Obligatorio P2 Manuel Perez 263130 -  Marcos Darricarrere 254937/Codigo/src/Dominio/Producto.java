//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.io.Serializable;

public class Producto  implements Serializable{
    
    public String nombre;
    private int costo;
    private int origen;

    public Producto (String unNombre, int unCosto, int unOrigen){
        this.nombre = unNombre;
        this.costo = unCosto;
        this.origen = unOrigen;  
    }
    
    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setCosto(int unCosto){
        this.costo = unCosto;
    }
    
    public int getCosto(){
        return this.costo;
    }
    
    public void setOrigen(int unOrigen){
        this.origen = unOrigen;
    }
    
    public int getOrigen(){
        return this.origen;
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    } 
}
