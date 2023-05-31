/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete0;

import java.util.Scanner;
import paquete1.Club;
import paquete1.Jugador;
import paquete2.EscrituraSecuencialClub;
import paquete2.LecturaSecuencialClub;
import paquete3.EscrituraSecuencialJugador;
import paquete3.LecturaSecuencialJugador;

/**
 *
 * @author reroes
 */
public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean bandera = true;
        while (bandera) {
            System.out.printf("%s\n%s\n%s\n%s\n%s\n",
                    "Opciones",
                    "1) Ingrese Club",
                    "2) Listar Club",
                    "3) Ingrese Jugador",
                    "4) Listar Jugador");
            int opcion = entrada.nextInt();
            if (opcion == 1) {
                agregarClubs();
                System.out.println("Club creado");
            } else {
                if (opcion == 2) {
                    verClubs();
                } else {
                    if (opcion == 3) {
                        boolean jugador = agregarJugador();
                        if (jugador) {
                            System.out.println("Jugador creado");
                        } else {
                            System.out.println("Jugador no creado");
                        }
                    } else {
                        if (opcion == 4) {
                            verJugadores();
                        }else{
                            System.out.println("Opción incorrecta");
                        }
                    }

                }

            }

            entrada.nextLine();
            System.out.println("Desea salir del proceso. Ingrese:  si");
            String salida = entrada.nextLine();
            if (salida.equals("si")) {
                bandera = false;
            }
        }
    }

    public static void agregarClubs() {
        String nombreArchivo = "data/clubs.dat";
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese nombre del club");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese siglas del club");
        String siglas = entrada.nextLine();
        System.out.println("Ingrese siglas de fundación del club");
        int fundacion = entrada.nextInt();

        Club c = new Club(nombre, siglas, fundacion);
        EscrituraSecuencialClub archivo
                = new EscrituraSecuencialClub(nombreArchivo);

        // establecer el valor del atributo registro
        archivo.establecerRegistro(c);
        // establecer en el archivo el atributo del registro
        archivo.establecerSalida();
        archivo.cerrarArchivo();
    }

    public static void verClubs() {
        String nombreArchivo = "data/clubs.dat";
        LecturaSecuencialClub lectura
                = new LecturaSecuencialClub(nombreArchivo);
        lectura.establecerClubs();
        System.out.println(lectura);
        lectura.cerrarArchivo();
    }

    public static boolean agregarJugador() {
        boolean bandera = false;
        String nombreArchivo = "data/jugadores.dat";
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese nombre del jugador");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese dorsal del jugador");
        int dorsal = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Ingrese siglas del club");
        String siglasEquipo = entrada.nextLine();

        // proceso para saber si existe el club
        String nombreArchivoClub = "data/clubs.dat";
        LecturaSecuencialClub lectura
                = new LecturaSecuencialClub(nombreArchivoClub);
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
    
    public static void verJugadores() {
        String nombreArchivo = "data/jugadores.dat";
        LecturaSecuencialJugador lectura
                = new LecturaSecuencialJugador(nombreArchivo);
        lectura.establecerJugadores();
        System.out.println(lectura);
        lectura.cerrarArchivo();
    }

}
