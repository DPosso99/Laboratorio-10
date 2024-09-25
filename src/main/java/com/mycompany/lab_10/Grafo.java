/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_10;

/**
 *
 * @author David
 */
import java.util.*;

public class Grafo {
    private Map<String, List<Arista>> adjList;  // Lista de adyacencia para ciudades

    public Grafo() {
        adjList = new HashMap<>();
    }

    // Añadir una carretera entre dos ciudades con distancia y tiempo
    public void agregarArista(String ciudadA, String ciudadB, int km, int minutos) {
        adjList.putIfAbsent(ciudadA, new ArrayList<>());
        adjList.putIfAbsent(ciudadB, new ArrayList<>());

        adjList.get(ciudadA).add(new Arista(ciudadB, km, minutos));
        adjList.get(ciudadB).add(new Arista(ciudadA, km, minutos));  // Carretera bidireccional
    }

    // Obtener todas las ciudades (nodos) del grafo
    public Set<String> getCiudades() {
        return adjList.keySet();  // Devuelve las claves del mapa (las ciudades)
    }

    // Obtener las adyacencias (carreteras) de una ciudad específica
    public List<Arista> obtenerAdyacencias(String ciudad) {
        return adjList.getOrDefault(ciudad, new ArrayList<>());  // Devuelve las aristas que salen de la ciudad
    }

    // Mostrar todas las ciudades disponibles
    public void mostrarCiudadesDisponibles() {
        System.out.println("Ciudades disponibles:");
        for (String ciudad : adjList.keySet()) {
            System.out.println(ciudad);
        }
    }

    public boolean estanConectadas(String ciudadA, String ciudadB) {
        if (adjList.containsKey(ciudadA)) {
            for (Arista arista : adjList.get(ciudadA)) {
                if (arista.getCiudadDestino().equals(ciudadB)) {
                    return true;  // Si se encuentra una arista que conecta A y B
                }
            }
        }
        return false;
    }
}


