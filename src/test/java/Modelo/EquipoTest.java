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
public class EquipoTest {

    private Equipo equipo;
    private Competidor competidor1;
    private Competidor competidor2;

    @BeforeEach
    void setUp() {
        equipo = new Equipo("Team A", "Country X");
        competidor1 = new Competidor("John Doe", 25, "Country X", 1.80, 75.0);
        competidor2 = new Competidor("Jane Smith", 22, "Country X", 1.70, 60.0);
    }

    @Test
    void testAgregarCompetidor() {
        equipo.agregarCompetidor(competidor1);
        assertEquals(1, equipo.getCompetidores().size());
        assertTrue(equipo.getCompetidores().contains(competidor1));

        equipo.agregarCompetidor(competidor2);
        assertEquals(2, equipo.getCompetidores().size());
        assertTrue(equipo.getCompetidores().contains(competidor2));
    }


}
