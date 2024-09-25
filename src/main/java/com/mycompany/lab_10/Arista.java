/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_10;

/**
 *
 * @author David
 */
public class Arista {
    private String ciudadDestino;
    private int km;
    private int minutos;

    public Arista(String ciudadDestino, int km, int minutos) {
        this.ciudadDestino = ciudadDestino;
        this.km = km;
        this.minutos = minutos;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public int getKm() {
        return km;
    }

    public int getMinutos() {
        return minutos;
    }
}

