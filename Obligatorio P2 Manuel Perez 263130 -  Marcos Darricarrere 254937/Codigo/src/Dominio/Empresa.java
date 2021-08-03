//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;





public class Empresa  implements Serializable{ 

    public ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>();
    public ArrayList<Tecnico> listaTecnicos = new ArrayList<Tecnico>();
    public ArrayList<Producto> listaProductos = new ArrayList<Producto>();
    public ArrayList<Fumigacion> listaFumigacion = new ArrayList<Fumigacion>();
    private PropertyChangeSupport manejador;
    public int[][] matrizFumigacion = new int[15][50];
    public int[][] matrizFumigacionFiltrada = new int[15][50];
    static ArrayList<String> listaNombresPilotos = new ArrayList<String>();
    static ArrayList<String> listaNombresProductos = new ArrayList<String>();
    static ArrayList<String> listaNombresTecnicos = new ArrayList<String>();
    
    public Empresa(){
        this.listaPilotos = new ArrayList<Piloto>();
        this.listaTecnicos = new ArrayList<Tecnico>();
        this.listaProductos = new ArrayList<Producto>();
        this.listaFumigacion = new ArrayList<Fumigacion>();
        this.manejador = new PropertyChangeSupport(this);
        this.matrizFumigacion = new int[15][50];
        this.matrizFumigacionFiltrada = new int[15][50];
        this.listaNombresPilotos = new ArrayList<String>();
        this.listaNombresProductos = new ArrayList<String>();
        this.listaNombresTecnicos = new ArrayList<String>();

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.manejador.addPropertyChangeListener(listener);
    }
   
    public void agregarPiloto(Piloto unP){
        this.getListaPilotos().add(unP);
        manejador.firePropertyChange("", null, null);
    }
    
    public void agregarProducto(Producto unP){
        this.getListaProductos().add(unP);
        manejador.firePropertyChange("", null, null);
    }
    
    public void agregarTecnico(Tecnico unT){
        this.getListaTecnicos().add(unT);
        manejador.firePropertyChange("", null, null);
    }
    
    public void agregarFumigacion(Fumigacion unF){
        this.getListaFumigacion().add(unF);
    }

    public ArrayList<Piloto> getListaPilotos() {
        return this.listaPilotos;
    }
    
    public ArrayList<Producto> getListaProductos() {
        return this.listaProductos;
    }
    
    public ArrayList<Tecnico> getListaTecnicos() {
        return this.listaTecnicos;
    }
    
    public ArrayList<Fumigacion> getListaFumigacion(){
        return this.listaFumigacion;
    }
    
    public String[] getListaNombresPilotos (){
        
        return listaNombresPilotos.toArray(new String[listaNombresPilotos.size()]);
    }
    
    // Busca en la lista de pilotos si el piloto ingresado esta repetido
    public boolean buscarRepetidosPiloto(Piloto unP){ 
        boolean repetido = false;
        for( int i = 0; i < this.getListaPilotos().size(); i++ ){
            if( this.getListaPilotos().get(i).getCedula() == unP.getCedula()){
                repetido = true;
            }
        }
        return repetido;
    }
     
 
    public void ActualizarListaNombresPilotos(){
        //Ordena la lista de Pilotos por años de experiencia
        Collections.sort(this.getListaPilotos(), new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                if( p2.getExperiencia() > p1.getExperiencia() ){
                    return 1;
                }
                if( p2.getExperiencia() < p1.getExperiencia() ){
                    return -1;
                }
            return 0;
            }
        });
        // Sustituye la lista de nombres de pilotos por una nueva ordenada
        this.listaNombresPilotos = new ArrayList<String>();
        for( int i = 0; i < this.getListaPilotos().size(); i++ ){
            this.listaNombresPilotos.add(i, this.getListaPilotos().get(i).getNombre());
        }
    }
    
    // Convierte la lista de pilotos en un array
    public String[] getListaNombresProductos (){
        this.listaNombresProductos = new ArrayList<String>();
        for( int i = 0; i < this.getListaProductos().size(); i++ ){
            this.listaNombresProductos.add(i, this.getListaProductos().get(i).getNombre());
        }
        return this.listaNombresProductos.toArray(new String[this.listaNombresProductos.size()]);
    }
    
    // Busca en la lista de productos si el producto ingresado esta repetido
    public boolean buscarRepetidosProducto(Producto unP){          
        boolean repetido = false;
        for( int i = 0; i < this.getListaProductos().size(); i++ ){
            if(this.getListaProductos().get(i).getNombre().equals(unP.getNombre())){
                repetido = true;
            }
        } 
        return repetido;
    }
    
    // Convierte la lista de Tecnicos en un array
    public String[] getListaNombreTecnicos (){ 
        this.listaNombresTecnicos = new ArrayList<String>();
        for( int i = 0; i < this.getListaTecnicos().size(); i++ ){
            this.listaNombresTecnicos.add(i, this.getListaTecnicos().get(i).getNombre());
        }
        //Ordena alfabeticamente la lista de nombres de tecnicos
        Collections.sort(this.listaNombresTecnicos); 
        return listaNombresTecnicos.toArray(new String[listaNombresTecnicos.size()]);
    }
    
    // Busca en la lista de tecnicos si el tecnico ingresado esta repetido
    public boolean buscarRepetidosTecnico(Tecnico unT){    
        boolean repetido = false;
        for( int i = 0; i < this.getListaTecnicos().size(); i++ ){
            if(this.getListaTecnicos().get(i).getCedula() == unT.getCedula()){
                repetido = true;
            }
        } 
        return repetido;
    }
    
    // Metodo que actualiza la matriz de fumigacion y la matriz de fumigacion filtrada ingresandole una nueva
    // zona en la cual se fumiga y un booleano que indica que matriz tiene que actualizar
    public void actualizarMatriz( String zona, Boolean filtro){
        int[][] matriz;
        if(filtro){
            matriz = matrizFumigacionFiltrada;
        }else{
            matriz = matrizFumigacion;
        }
        // Se inician los parametros
        int x1 = 0;
        int x2 = 0;
        int y1 = 0;
        int y2 = 0;
        int lugarx2 = 4;
        // Dependiendo del largo de la zona podemos saber si las columnas fumigadas son de uno o dos digitos y
        // con ese conocimento extraemos los datos
        switch (zona.length()){
            case 7: y2 = Character.getNumericValue(zona.charAt(zona.length()-1));
                    y1  = Character.getNumericValue(zona.charAt(2));
                break;
            case 8: if( zona.charAt(3) != '-' ){
                        lugarx2 = 5;
                        y2 = Character.getNumericValue(zona.charAt(zona.length()-1));
                        y1  =  Integer.parseInt(Character.toString(zona.charAt(2)) + Character.toString(zona.charAt(3)));
                }else{
                    y2 =  Integer.parseInt(Character.toString(zona.charAt(zona.length()-2)) + Character.toString(zona.charAt(zona.length()-1)));
                    y1  = Character.getNumericValue(zona.charAt(2));
                } 
                break;
            case 9: lugarx2 = 5;
                    y2 = Integer.parseInt(Character.toString(zona.charAt(zona.length()-2)) + Character.toString(zona.charAt(zona.length()-1)));
                    y1  = Integer.parseInt(Character.toString(zona.charAt(2)) + Character.toString(zona.charAt(3))); 
                break;
        
        }
        // Dependiendo de cual sea la primera letra cual es el valor de la primera fila
        switch(zona.charAt(0)){
            case 'A' : x1 = 0;
                break;
            case 'B' : x1 = 1;
                break;
            case 'C' : x1 = 2;
                break;
            case 'D' : x1 = 3;
                break;
            case 'E' : x1 = 4;
                break;
            case 'F' : x1 = 5;
                break;
            case 'G' : x1 = 6;
                break;
            case 'H' : x1 = 7;
                break;
            case 'I' : x1 = 8;
                break;
            case 'J' : x1 = 9;
                break;
            case 'K' : x1 = 10;
                break;
            case 'L' : x1 = 11;
                break;
            case 'M' : x1 = 12;
                break;
            case 'N' : x1 = 13;
                break;
            case 'O' : x1 = 14;
                break;
        }
        // Dependiendo del tamaño del string para saber en donde esta la letra y de que letra sea sabemos el valor
        // de la segunda fila
        switch(zona.charAt(lugarx2)){
            case 'A' : x2 = 0;
                break;
            case 'B' : x2 = 1;
                break;
            case 'C' : x2 = 2;
                break;
            case 'D' : x2 = 3;
                break;
            case 'E' : x2 = 4;
                break;
            case 'F' : x2 = 5;
                break;
            case 'G' : x2 = 6;
                break;
            case 'H' : x2 = 7;
                break;
            case 'I' : x2 = 8;
                break;
            case 'J' : x2 = 9;
                break;
            case 'K' : x2 = 10;
                break;
            case 'L' : x2 = 11;
                break;
            case 'M' : x2 = 12;
                break;
            case 'N' : x2 = 13;
                break;
            case 'O' : x2 = 14;
                break;
        }
        // Lo que buscamos con estos metodos es que x1 e y1 siempre sean mayores que x2 y2 para asi poder
        // hacer un for y sumarle uno a los casilleros fumigados 
        if( x1 == x2){
            if(y1 > y2){
                int Y;
                Y = y2;
                y2 = y1;
                y1 = Y;  
            }   
            if(y1 < y2){
                for( int i = y1 - 1; i < y2; i++){
                    matriz[x1][i] += 1;
                }
            }   
        }
        if( x1 > x2 ){
            int X;
            X = x2;
            x2 = x1;
            x1 = X;
            int Y;
            Y = y2;
            y2 = y1;
            y1 = Y;  
        }
        if( x2 > x1 ){
            while(x1 <= x2){
                if( y1 != 0){
                    for( int i = y1 - 1; i < matriz[0].length; i++){
                        matriz[x1][i] += 1;
                    }
                }
                if( y1 == 0 && x1 != x2){
                    for( int i = y1; i < matriz[0].length; i++){
                        matriz[x1][i] += 1;
                    }
                }
                if(y1 == 0 && x1 == x2){
                    for( int i = y1; i < y2; i++){
                        matriz[x1][i] += 1;
                    }
                }
                x1++;
                y1 = 0;
            }
        
        }
        if(filtro){
            this.matrizFumigacionFiltrada = matriz;
        }else{
            this.matrizFumigacion = matriz;
        }  
    }
    
    public int[][] getMatrizFumigacion(){
        return this.matrizFumigacion;
    }
    
    public int[][] getMatrizFumigacionFiltrada(){
        return this.matrizFumigacionFiltrada;
    }
    
    // Filtra la matriz de fumigacion por el dia seleccionado
    public void filtrarMatrizDia( int diaSeleccionado){
        for( int i = 0; i < this.listaFumigacion.size(); i++){
            if(this.listaFumigacion.get(i).getDia() == diaSeleccionado){
                this.actualizarMatriz(this.listaFumigacion.get(i).getZona(),true);
            }
        }
    }
    
    // Filtra la matriz de fumigacion filtrada por el producto seleccionado
    public void filtrarMatrizProducto( String prodSeleccionado ){
        for( int i = 0; i < this.listaFumigacion.size(); i++){
            if(this.listaFumigacion.get(i).getProducto().getNombre().equals( prodSeleccionado )){ 
                this.actualizarMatriz(this.listaFumigacion.get(i).getZona(),true);
            }
        }
    }
    
    // Filtra la matriz de fumigacion filtrada por el producto seleccionado
    public void filtrarMatrizProdYDia( int diaSeleccionado,String prodSeleccionado ){
        for( int i = 0; i < this.listaFumigacion.size(); i++){
            if(this.listaFumigacion.get(i).getDia() == diaSeleccionado && this.listaFumigacion.get(i).getProducto().getNombre().equals(prodSeleccionado)){
                this.actualizarMatriz(this.listaFumigacion.get(i).getZona(),true);
            }
        }
    }
    
    // Resetea la matriz de fumigacion filtrada
    public void resetMatrizFiltrada(){
        this.matrizFumigacionFiltrada = new int[15][50];
    }
}
