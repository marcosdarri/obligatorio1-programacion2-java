//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class tablaFumigacion extends JTable{
    
    private int[][] matriz;
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    
    // Creamos el JTable y le aplicamos todas las propiedades para que visualmente sea parecida al ejemplo
    public tablaFumigacion(int[][] matrizFumigacion){
        // Cargamos la matrizFumigacion para saber que celdas pintar
        this.matriz = matrizFumigacion;
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        this.setMaximumSize(new java.awt.Dimension(2147483647, 300));
        this.setMinimumSize(new java.awt.Dimension(750, 300));
        this.setName("Matriz");
        this.setPreferredSize(new java.awt.Dimension(3750, 300));
        this.setRowHeight(20);
        this.getTableHeader().setBackground(Color.PINK);
        this.setEnabled(false);
    
    }
    
    // Recorre la matriz de fumigacion y va guardando en una lista los distintos valores de fumigacion
    public void degrade(){
        for(int i = 0; i < matriz.length; i++){
           for(int j = 0; j < matriz[0].length; j++){
               if(!lista.contains(matriz[i][j])){
                   lista.add(matriz[i][j]);
               }
            } 
        }
        lista.sort((o1, o2) -> o1.compareTo(o2));
    }
    
    // Este metodo se encarga de pintar las celdas de la matriz dependiendo de cuanto se haya fumigado en cada una
    @Override
    public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
        Component componente = super.prepareRenderer(renderer,rowIndex,columnIndex);
        componente.setForeground(Color.white);
        if(getValueAt(rowIndex,columnIndex).getClass().equals(Integer.class)){        
            int valor = Integer.parseInt(this.getValueAt(rowIndex, columnIndex).toString());
            degrade();
            if(lista.size() == 1){
                if( valor == 0){
                    componente.setBackground(new Color(0,0,0));
                }else{
                    componente.setBackground(new Color(255,0,0));
                }
            }else{
                for(int i = 0; i < lista.size(); i++){
                    if(valor == lista.get(i)){
                        int color = Math.round(( 255 / ( lista.size() - 1 ) ) * i );
                        componente.setBackground(new Color( color ,0,0));
                    }
                }
            }
        }
        return componente;
    }
    
    public void setMatriz(int[][] unaMatriz){
        this.matriz = unaMatriz;
    }    
}