/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import paquete1.Club;

public class LecturaSecuencialClub {

    private ObjectInputStream entrada;
    private ArrayList<Club> clubs;
    private String nombreArchivo;
    private Club registroBuscado; // es el registro que debo buscar
                                  // por defecto es null

    public LecturaSecuencialClub(String n) {
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

    public void establecerClubs() {
        // 
        clubs = new ArrayList<>();
        File f = new File(obtenerNombreArchivo());
        if (f.exists()) {

            while (true) {
                try {
                    Club registro = (Club) entrada.readObject();
                    clubs.add(registro);
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
    
    public void establecerRegistroBuscado(String cadena) {
        // 
        
        File f = new File(obtenerNombreArchivo());
        if (f.exists()) {

            while (true) {
                try {
                    Club registro = (Club) entrada.readObject();
                    if (registro.obtenerSiglas().equals(cadena)) {
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

    public ArrayList<Club> obtenerClubs() {
        return clubs;
    }
    
    public Club obtenerRegistroBuscado() {
        return registroBuscado;
    }

    public String obtenerNombreArchivo() {
        return nombreArchivo;
    }

    @Override
    public String toString() {
        String cadena = "Lista de Clubs\n";
        for (int i = 0; i < obtenerClubs().size(); i++) {
            Club p = obtenerClubs().get(i);
            cadena = String.format("%s(%d) %s-%d-%s\n", cadena,
                    i + 1,
                    p.obtenerNombre(),
                    p.obtenerFundacion(),
                    p.obtenerSiglas());
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
