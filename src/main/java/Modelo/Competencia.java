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
public class Competencia {
    
    private String nombreEvento;
    private List<Equipo> equipos;

    public Competencia(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        this.equipos = new ArrayList<>();
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    public void agregarEquipo(Equipo e) {
        this.equipos.add(e);
    }
    
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("REPORTE DE COMPETENCIA: ").append(nombreEvento).append("\n");
        sb.append("==================================================\n");
        sb.append("Total de equipos inscritos: ").append(equipos.size()).append("\n\n");

        for (Equipo equipo : equipos) {
            sb.append(equipo.obtenerDatosEquipo()).append("\n");
        }

        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Competencia [Evento: " + nombreEvento + ", Equipos: " + equipos.size() + "]";
    }
    
}
