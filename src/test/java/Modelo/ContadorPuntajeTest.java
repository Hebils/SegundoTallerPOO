/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Angello
 */
public class ContadorPuntajeTest {

    private ContadorPuntaje contador;
    private Competidor comp1, comp2, comp3, comp4, comp5, comp6;
    private Equipo equipoA, equipoB;
    private Competencia competencia;

    @BeforeEach
    void setUp() {
        contador = new ContadorPuntaje();

        comp1 = new Competidor("Comp1", 20, "Pais1", 1.70, 65.0);
        comp2 = new Competidor("Comp2", 22, "Pais1", 1.75, 70.0);
        comp3 = new Competidor("Comp3", 24, "Pais2", 1.80, 75.0);
        comp4 = new Competidor("Comp4", 21, "Pais2", 1.68, 60.0);
        comp5 = new Competidor("Comp5", 23, "Pais3", 1.72, 68.0);
        comp6 = new Competidor("Comp6", 25, "Pais3", 1.78, 72.0);

        equipoA = new Equipo("Equipo Alpha", "Pais1");
        equipoB = new Equipo("Equipo Beta", "Pais2");

        equipoA.agregarCompetidor(comp1);
        equipoA.agregarCompetidor(comp2);
        equipoB.agregarCompetidor(comp3);
        equipoB.agregarCompetidor(comp4);

        competencia = new Competencia("Gran Carrera");
        competencia.agregarEquipo(equipoA);
        competencia.agregarEquipo(equipoB);
    }

    @Test
    void testAsignarPuntos() {
        List<Competidor> competidores = Arrays.asList(comp1, comp2, comp3, comp4, comp5, comp6);


        contador.asignarPuntos(competidores);

        assertEquals(50, comp1.getPuntos());
        assertEquals(30, comp2.getPuntos());
        assertEquals(20, comp3.getPuntos());
        assertEquals(10, comp4.getPuntos());
        assertEquals(5, comp5.getPuntos());
        assertEquals(1, comp6.getPuntos());


        contador.asignarPuntos(competidores);
        assertEquals(100, comp1.getPuntos());
        assertEquals(60, comp2.getPuntos()); 
    }

    @Test
    void testCalcularPuntajeTotalEquipo() {
        comp1.setPuntos(50);
        comp2.setPuntos(30);
        assertEquals(80, contador.calcularPuntajeTotalEquipo(equipoA));

        comp3.setPuntos(20);
        comp4.setPuntos(10);
        assertEquals(30, contador.calcularPuntajeTotalEquipo(equipoB));


        Equipo emptyEquipo = new Equipo("Empty Team", "None");
        assertEquals(0, contador.calcularPuntajeTotalEquipo(emptyEquipo));
    }

    @Test
    void testCalcularPuntajeTotalEquipos() {
        comp1.setPuntos(100);
        comp2.setPuntos(50);
        comp3.setPuntos(120);
        comp4.setPuntos(30);

        String report = contador.calcularPuntajeTotalEquipos(competencia);

        assertTrue(report.contains("--- Puntaje Total por Equipo en Gran Carrera ---"));
        assertTrue(report.contains("Equipo Alpha (Pais1): 150 puntos"));
        assertTrue(report.contains("Equipo Beta (Pais2): 150 puntos"));
        assertTrue(report.contains("Puntaje Total de la Competencia: 300 puntos"));


        comp3.setPuntos(200); 
        report = contador.calcularPuntajeTotalEquipos(competencia);
        

        int indexAlpha = report.indexOf("Equipo Alpha (Pais1): 150 puntos");
        int indexBeta = report.indexOf("Equipo Beta (Pais2): 230 puntos");
        
        assertTrue(indexBeta < indexAlpha, "Equipo Beta should appear before Equipo Alpha");
    }
}