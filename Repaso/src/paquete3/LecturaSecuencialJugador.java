/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;

import paquete1.Jugador;

public class LecturaSecuencialJugador {

    private ObjectInputStream entrada;
    private ArrayList<Jugador> jugadores;
    private String nombreArchivo;
    private boolean verificar;
    private Jugador establecerjugadorbuscado;
    

    public LecturaSecuencialJugador(String n) {
        nombreArchivo = n;
        File f = new File(nombreArchivo);
        if (f.exists()) {
            try // abre el archivo
            {
                entrada = new ObjectInputStream(
                        new FileInputStream(n));
            } // fin de try
            catch (IOException ioException) {
                System.err.println("Error al abrir el archivo." + ioException);
            } // fin de catch
        }
    }

    public void establecerNombreArchivo(String n) {
        nombreArchivo = n;
    }

    public void establecerJugadores() {
        // 
        jugadores = new ArrayList<>();
        File f = new File(obtenerNombreArchivo());
        if (f.exists()) {

            while (true) {
                try {
                    Jugador registro = (Jugador) entrada.readObject();
                    jugadores.add(registro);
                } catch (EOFException endOfFileException) {
                    return; // se llegó al fin del archivo
                    // se puede usar el break;
                    // System.err.println("Fin de archivo: " + endOfFileException);

                } catch (IOException ex) {
                    System.err.println("Error al leer el archivo: " + ex);
                } catch (ClassNotFoundException ex) {
                    System.err.println("No se pudo crear el objeto: " + ex);
                } catch (Exception ex) {
                    System.err.println("No hay datos en el archivo: " + ex);

                }
            }
        }
    }

    
    
    
    public ArrayList<Jugador> obtenerJugadores() {
        return jugadores;
    }

    public String obtenerNombreArchivo() {
        return nombreArchivo;
    }
    
    /*public void establecerJugadorBuscado(String cadena) {
        // 
        
        File f = new File(obtenerNombreArchivo());
        if (f.exists()) {

            while (true) {
                try {
                    Jugador registro = (Jugador) entrada.readObject();
                    if (registro.obtenerJugadores().equals(cadena)) {
                        registroBuscado = registro;
                    }
                } catch (EOFException endOfFileException) {
                    return; // se llegó al fin del archivo
                    // se puede usar el break;
                    // System.err.println("Fin de archivo: " + endOfFileException);

                } catch (IOException ex) {
                    System.err.println("Error al leer el archivo: " + ex);
                } catch (ClassNotFoundException ex) {
                    System.err.println("No se pudo crear el objeto: " + ex);
                } catch (Exception ex) {
                    System.err.println("No hay datos en el archivo: " + ex);

                }
            }
        }
    }
*/
    public void establecerVerificado(String nombre){
        
       // proceso para saber si existe el club
        String nombreArchivoClub = "data/jugadores.dat";
        LecturaSecuencialJugador lectura
                = new LecturaSecuencialJugador(nombreArchivoClub);
        lectura.establecerRegistroBuscado(siglasEquipo);
        Club c = lectura.obtenerRegistroBuscado();
        
        // print para presentar si existe el club. Informativo
        if (c==null) {
            System.out.println("Debe seleccionar de forma correcta el club");
        }
        
        if (c != null) {
            EscrituraSecuencialJugador archivo
                    = new EscrituraSecuencialJugador(nombreArchivo);
            Jugador j = new Jugador(nombre, dorsal, c);
            // establecer el valor del atributo registro
            archivo.establecerRegistro(j);
            // establecer en el archivo el atributo del registro
            archivo.establecerSalida();
            archivo.cerrarArchivo();
            bandera = true;
        }
        return bandera;
    }
        
        
     return verificar;
     
    }
  

    @Override
    public String toString() {
        String cadena = "Lista de Jugadores\n";
        for (int i = 0; i < obtenerJugadores().size(); i++) {
            Jugador p = obtenerJugadores().get(i);
            cadena = String.format("%s(%d) %s-%d (%s|%s|%d)\n", cadena,
                    i + 1,
                    p.obtenerNombre(),
                    p.obtenerDorsal(),
                    p.obtenerClub().obtenerNombre(),
                    p.obtenerClub().obtenerSiglas(),
                    p.obtenerClub().obtenerFundacion());
        }

        return cadena;
    }

    // cierra el archivo y termina la aplicación
    public void cerrarArchivo() {
        try // cierra el archivo y sale
        {
            if (entrada != null) {
                entrada.close();
            }
            // System.exit(0);
        } // fin de try
        catch (IOException ioException) {
            System.err.println("Error al cerrar el archivo.");
            System.exit(1);
        } // fin de catch
    } // fin del método cerrarArchivo
    
    
    
    
    
    
    
}
