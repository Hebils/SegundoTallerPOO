/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Angello
 */
public class ContadorPuntaje {
    public String asignarPuntos(List<Competidor> competidores) {
        StringBuilder sb = new StringBuilder();
        sb.append("Asignación de Puntos\n\n");

        int[] puntosPorPosicion = {50, 30, 20, 10, 5}; //Puntos para el 1er, 2do, 3er, 4to y 5to lugar

        for (int i = 0; i < competidores.size(); i++) {
            Competidor c = competidores.get(i);
            int puntosObtenidos = 0;

            if (i < puntosPorPosicion.length) {
                puntosObtenidos = puntosPorPosicion[i];
            } else {
                puntosObtenidos = 1; //Punto de participación
            }

            //Acumular los puntos al competidor
            c.setPuntos(c.getPuntos() + puntosObtenidos);

            //Actualizar el ranking con la sobrecarga
            int nuevoRanking = c.ranking(puntosObtenidos);

            sb.append(String.format("Competidor: %s | Puntos obtenidos: %d | Total Puntos: %d | Nuevo Ranking: %d\n",
                    c.getNombre(), puntosObtenidos, c.getPuntos(), nuevoRanking));
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
        int totalCompetencia = 0;

        for (Equipo equipo : competencia.getEquipos()) {
            int puntajeEquipo = calcularPuntajeTotalEquipo(equipo);
            sb.append(String.format("Equipo %s (%s): %d puntos\n",
                    equipo.getNombre(), equipo.getPais(), puntajeEquipo));
            totalCompetencia += puntajeEquipo;
        }

        sb.append("--------------------------------------------------\n");
        sb.append("Puntaje Total de la Competencia: ").append(totalCompetencia).append(" puntos\n");

        return sb.toString();
    }
}
