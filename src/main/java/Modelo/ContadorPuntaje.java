/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Angello
 */
public class ContadorPuntaje {
    public String asignarPuntos(List<Competidor> competidores) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Asignación de Puntos ---\n\n");

        int[] puntosPorPosicion = {50, 30, 20, 10, 5}; // Puntos para el 1er, 2do, 3er, 4to y 5to lugar

        for (int i = 0; i < competidores.size(); i++) {
            Competidor c = competidores.get(i);
            int puntosObtenidos = 0;

            if (i < puntosPorPosicion.length) {
                puntosObtenidos = puntosPorPosicion[i];
            } else {
                puntosObtenidos = 1; // Punto de participación para los demás
            }

            // Acumular los puntos al competidor
            c.setPuntos(c.getPuntos() + puntosObtenidos);

            // Actualizar el ranking con la sobrecarga
            // El método actualizarRanking de Competidor ya no modifica el ranking mundial, solo devuelve los puntos obtenidos.
            // El ranking se establecerá externamente por los puntos acumulados.
            c.actualizarRanking(puntosObtenidos, true); // Llamada para mantener la estructura, aunque no actualice rankingMundial

            sb.append(String.format("Posición %d: %s | Puntos obtenidos: %d | Total Puntos: %d\n",
                    (i + 1), c.getNombre(), puntosObtenidos, c.getPuntos()));
        }

        return sb.toString();
    }

    public int calcularPuntajeTotalEquipo(Equipo equipo) {
        int puntajeTotal = 0;
        for (Competidor c : equipo.getCompetidores()) {
            puntajeTotal += c.getPuntos();
        }
        return puntajeTotal;
    }
    
    public String calcularPuntajeTotalEquipos(Competencia competencia) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Puntaje Total por Equipo en ").append(competencia.getNombreEvento()).append(" ---\n");

        // Ordenar equipos por puntaje total de mayor a menor
        List<Equipo> equiposOrdenados = new ArrayList<>(competencia.getEquipos());
        equiposOrdenados.sort(Comparator.comparingInt(this::calcularPuntajeTotalEquipo).reversed());

        int totalCompetencia = 0;

        for (int i = 0; i < equiposOrdenados.size(); i++) {
            Equipo equipo = equiposOrdenados.get(i);
            int puntajeEquipo = calcularPuntajeTotalEquipo(equipo);
            sb.append(String.format("%d. Equipo %s (%s): %d puntos\n",
                    (i + 1), equipo.getNombre(), equipo.getPais(), puntajeEquipo));
            totalCompetencia += puntajeEquipo;
        }

        sb.append("--------------------------------------------------\n");
        sb.append("Puntaje Total de la Competencia: ").append(totalCompetencia).append(" puntos\n");

        return sb.toString();
    }
}
