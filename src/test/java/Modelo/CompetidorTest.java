/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

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
public class CompetidorTest {

    private Competidor competidor;



    @Test
    void testActualizarRanking() {
        // Este método ya no modifica el ranking mundial directamente, solo devuelve los puntos obtenidos.
        // Su propósito principal es mantener la compatibilidad de la firma.
        int puntosObtenidos = 50;
        int resultado = competidor.actualizarRanking(puntosObtenidos, true);
        assertEquals(puntosObtenidos, resultado);
        // Asegurarse de que los puntos del competidor no se modifican por esta llamada, solo se devuelven los puntos.
        // Los puntos se acumulan con setPuntos por ContadorPuntaje.
        assertEquals(0, competidor.getPuntos()); 
    }



}