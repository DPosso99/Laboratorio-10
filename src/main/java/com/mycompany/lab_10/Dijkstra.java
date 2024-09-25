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
import java.util.List;
import java.util.Set;

public class Dijkstra {

    public static Map<String, Integer> encontrarCaminoMasCorto(Grafo grafo, String origen, String destino, String tipo) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, Boolean> visitado = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();  // Para rastrear el camino
        PriorityQueue<String> colaPrioridad = new PriorityQueue<>((a, b) -> distancias.get(a) - distancias.get(b));

        // Inicializar distancias, visitado y predecesores
        for (String ciudad : grafo.getCiudades()) {
            distancias.put(ciudad, Integer.MAX_VALUE);
            visitado.put(ciudad, false);
            predecesores.put(ciudad, null);  // Inicializamos los predecesores
        }

        distancias.put(origen, 0);
        colaPrioridad.add(origen);

        while (!colaPrioridad.isEmpty()) {
            String actual = colaPrioridad.poll();

            if (visitado.get(actual)) continue;
            visitado.put(actual, true);

            if (actual.equals(destino)) break;

            for (Arista arista : grafo.obtenerAdyacencias(actual)) {
                String vecino = arista.getCiudadDestino();
                int peso = tipo.equals("distancia") ? arista.getKm() : arista.getMinutos();
                int nuevaDistancia = distancias.get(actual) + peso;

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, actual);  // Guardar el predecesor
                    colaPrioridad.add(vecino);
                }
            }
        }

        // Imprimir el camino
        if (predecesores.get(destino) == null) {
            System.out.println("No hay camino disponible entre " + origen + " y " + destino);
        } else {
            String camino = destino;
            String predecesor = predecesores.get(destino);
            while (predecesor != null) {
                camino = predecesor + " -> " + camino;
                predecesor = predecesores.get(predecesor);
            }
            System.out.println("Camino más corto: " + camino);
        }

        return distancias;
    }
}
