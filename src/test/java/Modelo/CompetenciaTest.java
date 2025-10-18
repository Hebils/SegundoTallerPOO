/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
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
public class CompetenciaTest {

    private Competencia competencia;
    private Equipo equipo1;
    private Equipo equipo2;
    private Competidor competidor1;
    private Competidor competidor2;
    private Competidor competidor3;

    @BeforeEach
    void setUp() {
        competencia = new Competencia("Mundial de Ciclismo");
        equipo1 = new Equipo("Team A", "Pais A");
        equipo2 = new Equipo("Team B", "Pais B");
        competidor1 = new Competidor("Comp1", 20, "Pais A", 1.70, 65.0);
        competidor2 = new Competidor("Comp2", 22, "Pais A", 1.75, 70.0);
        competidor3 = new Competidor("Comp3", 24, "Pais B", 1.80, 75.0);

        equipo1.agregarCompetidor(competidor1);
        equipo1.agregarCompetidor(competidor2);
        equipo2.agregarCompetidor(competidor3);

        competencia.agregarEquipo(equipo1);
        competencia.agregarEquipo(equipo2);
    }

    @Test
    void testAgregarEquipo() {
        Competencia compVacia = new Competencia("Vacia");
        assertEquals(0, compVacia.getEquipos().size());

        compVacia.agregarEquipo(equipo1);
        assertEquals(1, compVacia.getEquipos().size());
        assertTrue(compVacia.getEquipos().contains(equipo1));
    }

}
