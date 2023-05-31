    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import paquete1.Jugador;

/**
 *
 * @author reroes
 */
public class EscrituraSecuencialJugador {

    private String nombreArchivo;
    private ObjectOutputStream salida; 
    private Jugador registro;
    private ArrayList<Jugador> listaJugador;

    public EscrituraSecuencialJugador(String nombreArc) {
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

    public void establecerRegistro(Jugador p) {
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
        LecturaSecuencialJugador l = 
                new LecturaSecuencialJugador(obtenerNombreArchivo());
        l.establecerJugadores();
        listaJugador = l.obtenerJugadores();
    }

    public String obtenerNombreArchivo(){
        return nombreArchivo;
    }
    
    public ArrayList<Jugador> obtenerLista() {
        return listaJugador;
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
