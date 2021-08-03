//  Manuel Perez 263130 -  Marcos Darricarrere 254937

package Dominio;

import Interfaz.VentanaMenu;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema {
    
    public static void main(String[] args) {
       Empresa miEmpresa = new Empresa();
       miEmpresa = guardado(miEmpresa);
       new VentanaMenu(miEmpresa).setVisible(true);
    }

    // Este metodo chequea si hay un archivo de donde cargar datos, si no lo hay lo crea y luego si corresponde carga datos
    public static Empresa guardado(Empresa miEmpresa){              
        File archivo = new File("Saves.txt");
        try{
            // crea el archivo si no existe y si existe no hace nada
            archivo.createNewFile();
        }catch(IOException ex){
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Chequea por las dudas si el archivo existe y si no esta vacio y lo sobreescribe la empresa con la guardada
        // en el archivo Saves.txt
       if (archivo.exists() && archivo.length() != 0) {
           try{
                ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("Saves.txt")));
                miEmpresa = (Empresa)in.readObject();
                in.close();
            }catch(IOException|ClassNotFoundException e){
                System.exit(1);
            }
       }
       return miEmpresa;
    }
}
