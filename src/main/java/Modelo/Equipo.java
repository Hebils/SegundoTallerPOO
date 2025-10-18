/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angello
 */
public class Equipo {
    
    private String nombre;
    private String pais;
    private List<Competidor> competidores;

    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.competidores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Competidor> getCompetidores() {
        return competidores;
    }

    public void setCompetidores(List<Competidor> competidores) {
        this.competidores = competidores;
    }
    
    public void agregarCompetidor(Competidor c) {
        this.competidores.add(c);
    }
    
    public String obtenerDatosEquipo() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Datos del Equipo ---\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("País: ").append(pais).append("\n");
        sb.append("Competidores (").append(competidores.size()).append("):\n");

        for (Competidor competidor : competidores) {
            sb.append("  - ").append(competidor.getNombre()).append(" (Ranking: ").append(competidor.getRankingMundial()).append(")\n");
        }

        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Equipo [Nombre: " + nombre + ", País: " + pais + ", Competidores: " + competidores.size() + "]";
    }
}
