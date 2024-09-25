/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_10;

/**
 *
 * @author David
 */
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import java.util.*;

public class Dijkstra {

    public static int encontrarCaminoMasCorto(Grafo grafo, String origen, String destino, String tipo) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, Boolean> visitado = new HashMap<>();
        PriorityQueue<String> colaPrioridad = new PriorityQueue<>((a, b) -> distancias.get(a) - distancias.get(b));

        // Inicializar distancias y visitado para todas las ciudades
        for (String ciudad : grafo.getCiudades()) {
            distancias.put(ciudad, Integer.MAX_VALUE);
            visitado.put(ciudad, false);
        }

        distancias.put(origen, 0);
        colaPrioridad.add(origen);

        while (!colaPrioridad.isEmpty()) {
            String actual = colaPrioridad.poll();

            // Si la ciudad ya fue visitada, la ignoramos
            if (visitado.get(actual)) continue;

            visitado.put(actual, true);

            // Si hemos llegado a la ciudad destino, terminamos
            if (actual.equals(destino)) {
                return distancias.get(actual);  // Devolver la distancia/tiempo hacia el destino
            }

            for (Arista arista : grafo.obtenerAdyacencias(actual)) {
                String vecino = arista.getCiudadDestino();
                int peso = tipo.equals("distancia") ? arista.getKm() : arista.getMinutos();
                int nuevaDistancia = distancias.get(actual) + peso;

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    colaPrioridad.add(vecino);
                }
            }
        }

        return -1;  // Si no se encuentra el camino, devolvemos -1
    }
}
