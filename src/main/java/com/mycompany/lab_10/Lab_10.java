/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab_10;

/**
 *
 * @author David
 */

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Lab_10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar el grafo desde el archivo CSV
        String archivo = "archivo.csv";  // Cambia este nombre si es necesario
        Grafo grafo = cargarGrafo(archivo);

        if (grafo == null) {
            System.out.println("Error al cargar el archivo CSV.");
            return;  // Detener la ejecución si no se cargó el archivo correctamente
        }

        // Interacción con el usuario
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar ciudades disponibles");
            System.out.println("2. Ver si dos ciudades están conectadas");
            System.out.println("3. Encontrar el camino más corto por distancia");
            System.out.println("4. Encontrar el camino más corto por tiempo");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1: // Mostrar ciudades disponibles
                    grafo.mostrarCiudadesDisponibles();
                    break;
                case 2: // Ver si dos ciudades están conectadas
                    System.out.print("Ingresa la ciudad A: ");
                    String ciudadA = scanner.nextLine();
                    System.out.print("Ingresa la ciudad B: ");
                    String ciudadB = scanner.nextLine();
                    if (grafo.estanConectadas(ciudadA, ciudadB)) {
                        System.out.println(ciudadA + " y " + ciudadB + " están conectadas por una carretera.");
                    } else {
                        System.out.println(ciudadA + " y " + ciudadB + " no están conectadas.");
                    }
                    break;
                case 3: // Encontrar el camino más corto por distancia
                    System.out.print("Ingresa la ciudad de origen: ");
                    String origenDistancia = scanner.nextLine();
                    System.out.print("Ingresa la ciudad de destino: ");
                    String destinoDistancia = scanner.nextLine();
                    Map<String, Integer> distancias = Dijkstra.encontrarCaminoMasCorto(grafo, origenDistancia, destinoDistancia, "distancia");
                    System.out.println("Distancia más corta desde " + origenDistancia + " a " + destinoDistancia + ": " + distancias.get(destinoDistancia) + " km");
                    break;
                case 4: // Encontrar el camino más corto por tiempo
                    System.out.print("Ingresa la ciudad de origen: ");
                    String origenTiempo = scanner.nextLine();
                    System.out.print("Ingresa la ciudad de destino: ");
                    String destinoTiempo = scanner.nextLine();
                    Map<String, Integer> tiempos = Dijkstra.encontrarCaminoMasCorto(grafo, origenTiempo, destinoTiempo, "tiempo");
                    System.out.println("Tiempo más corto desde " + origenTiempo + " a " + destinoTiempo + ": " + tiempos.get(destinoTiempo) + " minutos");
                    break;
                case 5: // Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    // Cargar el grafo desde un archivo CSV
    public static Grafo cargarGrafo(String archivo) {
        Grafo grafo = new Grafo();
        try (Scanner scanner = new Scanner(new File(archivo))) {
            boolean esPrimeraLinea = true;  // Añadir un flag para saltar la primera línea
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;  // Saltar la primera línea del CSV
                    continue;
                }
                String[] datos = linea.split(",");
                String ciudadA = datos[0].trim();
                String ciudadB = datos[1].trim();
                int km = Integer.parseInt(datos[2].trim());
                int minutos = Integer.parseInt(datos[3].trim());
                grafo.agregarArista(ciudadA, ciudadB, km, minutos);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
        return grafo;
    }
}
