    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import paquete1.Club;

/**
 *
 * @author reroes
 */
public class EscrituraSecuencialClub {

    private String nombreArchivo;
    private ObjectOutputStream salida; 
    private Club registro;
    private ArrayList<Club> listaClub;

    public EscrituraSecuencialClub(String nombreArc) {
        nombreArchivo = nombreArc;
        establecerLista(); // obtener los valores (objetos)
                                    // que tiene el archivo.
        try // abre el archivo
        {
            salida = new ObjectOutputStream(
                    new FileOutputStream(nombreArchivo));
            // proceso para ingresar nuevamente los valores del archivo
            if (obtenerLista().size() > 0) {
                for (int i = 0; i < obtenerLista().size(); i++) {
                    establecerRegistro(obtenerLista().get(i));
                    establecerSalida();
                }
            }
        } // fin de try
        catch (IOException ioException) {
            System.err.println("Error al abrir el archivo.");
        } // fin de catch
    }
    
    public void establecerNombreArchivo(String n){
        nombreArchivo = n;
    }

    public void establecerRegistro(Club p) {
        registro = p;
    }

    public void establecerSalida() {
        try {
            salida.writeObject(registro); // envía el registro como 
                                                  // objeto al archivo
        } catch (IOException ex) {
            System.err.println("Error al escribir en el archivo.");
        }
    }

    // en el atributo listaProfesores obtenemos los registros 
    // del archivo
    public void establecerLista() {
        LecturaSecuencialClub l = 
                new LecturaSecuencialClub(obtenerNombreArchivo());
        l.establecerClubs();
        listaClub = l.obtenerClubs();
    }

    public String obtenerNombreArchivo(){
        return nombreArchivo;
    }
    
    public ArrayList<Club> obtenerLista() {
        return listaClub;
    }

    public ObjectOutputStream obtenerSalida(){
        return salida;
    }
    public void cerrarArchivo() {
        try // cierra el archivo
        {
            if (salida != null) {
                salida.close();
            }
        } // fin de try
        catch (IOException ioException) {
            System.err.println("Error al cerrar el archivo.");
            
        } // fin de catch
    } 

}
