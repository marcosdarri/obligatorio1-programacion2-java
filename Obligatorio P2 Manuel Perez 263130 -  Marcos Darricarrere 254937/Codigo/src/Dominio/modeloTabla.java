//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import javax.swing.table.AbstractTableModel;

public class modeloTabla extends AbstractTableModel{
    private String[] columnas;
    private int[][] datos;
    
    
    public modeloTabla(int[][] matrizFumigacion){       
        this.columnas = columnas();
        this.datos = matrizFumigacion;   
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos[rowIndex][columnIndex];
    }
    
    // Le asigna el valor del 1 al 50 a las columnas
    private String[] columnas(){
        String[] col = new String[50];
        for( int i = 0; i < col.length; i++){
            col[i] = Integer.toString(i+1);
        }
        return col;
    }   
}
    