//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.io.Serializable;

public class Tecnico  implements Serializable{
    
    public String nombre;
    private int cedula;
    private String email;
    
    public Tecnico( String unNombre, int unaCedula, String unEmail ){
        this.nombre = unNombre;
        this.cedula = unaCedula;
        this.email = unEmail;
    }
    
     public void setNombre(String unNombre){
        this.nombre = unNombre;
    }
     
     public void setCedula(int unaCedula){   
        this.cedula = unaCedula;
    }
     
     public void setEmail(String unEmail){    
         this.email = unEmail; 
     }
     
      public String getNombre(){        
        return this.nombre;
    }
    
    public int getCedula(){        
        return this.cedula;
    }
    
    public String getEmail(){   
        return this.email;
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }  
}
