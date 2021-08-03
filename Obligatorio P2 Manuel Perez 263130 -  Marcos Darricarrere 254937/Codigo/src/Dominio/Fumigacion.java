//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.io.Serializable;

public class Fumigacion  implements Serializable{

    Piloto piloto;
    Tecnico tecnico;
    Producto producto;
    int dia;
    String zona;

   public Fumigacion(Piloto unPiloto, Tecnico unTecnico, Producto unProducto, int unDia, String unaZona) {
        this.piloto = unPiloto;
        this.tecnico = unTecnico;
        this.producto = unProducto;
        this.dia = unDia;
        this.zona = unaZona;
    }

    public void setPiloto(Piloto unPiloto) {
        this.piloto = unPiloto;
    }

    public Piloto getPiloto() {
        return this.piloto;
    }

    public void setTecnico(Tecnico unTecnico) {
        this.tecnico = unTecnico;
    }

    public Tecnico getTecnico() {
        return this.tecnico;
    }

    public void setProducto(Producto unProducto) {
        this.producto = unProducto;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setDia(int unDia) {
        this.dia = unDia;
    }

    public int getDia() {
        return this.dia;
    }

    public void setZona(String unaZona) {
        this.zona = unaZona;
    }

    public String getZona() {
        return this.zona;
    }

    @Override
    public String toString() {
        return this.getPiloto() + " " + this.getTecnico() + " " + this.getProducto() + " " + this.getDia() + " " + this.getZona();
    }
}
