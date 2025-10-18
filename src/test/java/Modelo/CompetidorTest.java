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
        
        int puntosObtenidos = 50;
        int resultado = competidor.actualizarRanking(puntosObtenidos, true);
        assertEquals(0, competidor.getPuntos()); 
        
    }



}