/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taller2punto1;

/**
 *
 * @author Angello
 */
import Modelo.Competencia;
import Modelo.Competidor;
import Modelo.ContadorPuntaje;
import Modelo.Equipo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taller2Punto1 {

    private static Competencia mundialCiclismo = null;
    private static ContadorPuntaje contador = new ContadorPuntaje();
    private static List<Competidor> todosCompetidores = new ArrayList<>();
    
    private static class JTextAreaWithScroll {
        public static void show(String text, String title) {
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(text);
            textArea.setEditable(false);
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
            JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private static void inicializarCompetencia() {
        String nombreEvento = JOptionPane.showInputDialog(null, "Ingrese el nombre del evento de Ciclismo de Pista:", "Inicializar Competencia", JOptionPane.QUESTION_MESSAGE);
        if (nombreEvento != null && !nombreEvento.trim().isEmpty()) {
            mundialCiclismo = new Competencia(nombreEvento);
            JOptionPane.showMessageDialog(null, "Competencia \"" + nombreEvento + "\" inicializada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del evento no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void registrarEquipo() {
        if (mundialCiclismo == null) {
            JOptionPane.showMessageDialog(null, "Primero debe inicializar la competencia (Opción 1).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del equipo:", "Registro de Equipo", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty()) return;

        String pais = JOptionPane.showInputDialog(null, "Ingrese el país del equipo:", "Registro de Equipo", JOptionPane.QUESTION_MESSAGE);
        if (pais == null || pais.trim().isEmpty()) return;

        Equipo nuevoEquipo = new Equipo(nombre, pais);
        mundialCiclismo.agregarEquipo(nuevoEquipo);
        JOptionPane.showMessageDialog(null, "Equipo \"" + nombre + "\" (" + pais + ") registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void registrarCompetidor() {
        if (mundialCiclismo == null) {
            JOptionPane.showMessageDialog(null, "Primero debe inicializar la competencia (Opción 1).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Equipo> equipos = mundialCiclismo.getEquipos();
        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar al menos un equipo antes de registrar competidores (Opción 2).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 1. Captura de datos del competidor
            String nombre = JOptionPane.showInputDialog("Nombre del competidor:");
            if (nombre == null || nombre.trim().isEmpty()) return;

            int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            String pais = JOptionPane.showInputDialog("País que representa:");
            if (pais == null || pais.trim().isEmpty()) return;

            int rankingMundial = Integer.parseInt(JOptionPane.showInputDialog("Ranking Mundial (posición):"));
            double estatura = Double.parseDouble(JOptionPane.showInputDialog("Estatura (en metros, ej: 1.75):"));
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso (en kg, ej: 65.0):"));

            Competidor nuevoCompetidor = new Competidor(nombre, edad, pais, rankingMundial, estatura, peso);

            // 2. Selección de equipo
            Object[] opcionesEquipo = equipos.stream().map(Equipo::getNombre).toArray();
            String equipoSeleccionadoNombre = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el equipo para el competidor " + nombre + ":",
                    "Asignar Equipo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesEquipo,
                    opcionesEquipo[0]);

            if (equipoSeleccionadoNombre == null) return;

            // 3. Asignación al equipo
            Equipo equipoSeleccionado = equipos.stream()
                    .filter(e -> e.getNombre().equals(equipoSeleccionadoNombre))
                    .findFirst()
                    .orElse(null);

            if (equipoSeleccionado != null) {
                equipoSeleccionado.agregarCompetidor(nuevoCompetidor);
                todosCompetidores.add(nuevoCompetidor);
                JOptionPane.showMessageDialog(null, "Competidor " + nombre + " registrado y asignado a " + equipoSeleccionadoNombre + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al encontrar el equipo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de formato en la entrada numérica. Por favor, ingrese solo números para edad, ranking, estatura y peso.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void simularResultados() {
        if (todosCompetidores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar competidores primero (Opción 3).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simular orden de llegada (simplemente usamos la lista actual)
        // En una aplicación real, se pediría el orden de llegada.
        String resumenPuntos = contador.asignarPuntos(todosCompetidores);

        JTextAreaWithScroll.show(resumenPuntos, "Simulación de Resultados");
    }
    
    private static void generarReporteCompetencia() {
        if (mundialCiclismo == null || mundialCiclismo.getEquipos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay datos suficientes para generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String reporte = mundialCiclismo.generarReporte();
        JTextAreaWithScroll.show(reporte, "Reporte de Competencia: " + mundialCiclismo.getNombreEvento());
    }
    
    private static void mostrarPuntajeTotal() {
        if (mundialCiclismo == null || mundialCiclismo.getEquipos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados para calcular el puntaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resumenPuntajeTotal = contador.calcularPuntajeTotalEquipos(mundialCiclismo);
        JTextAreaWithScroll.show(resumenPuntajeTotal, "Puntaje Total por Equipo");
    }
    
    private static void usarToString() {
        if (todosCompetidores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar competidores primero (Opción 3).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Competidor primerCompetidor = todosCompetidores.get(0);
        String datosC = primerCompetidor.toString();

        JOptionPane.showMessageDialog(null, "Uso del método sobreescrito toString() para el primer competidor registrado:\n\n" + datosC, "Método toString()", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        int opcion = 0;
        boolean salir = false;

        while (!salir) {
            try {
                String menu = "Mundial de Ciclismo de Pista - Menú Principal\n\n" +
                              "1. Inicializar/Nombrar Competencia\n" +
                              "2. Registrar Nuevo Equipo\n" +
                              "3. Registrar Competidor y Asignar a Equipo\n" +
                              "----------------------------------------------------\n" +
                              "4. Simular Resultados (Asignar Puntos y Actualizar Ranking)\n" +
                              "5. Generar Reporte Completo de la Competencia\n" +
                              "6. Mostrar Puntaje Total por Equipo\n" +
                              "7. Mostrar uso de toString() (Sobreescritura)\n" +
                              "8. Salir";

                String input = JOptionPane.showInputDialog(null, menu, "Menú", JOptionPane.PLAIN_MESSAGE);

                if (input == null) {
                    salir = true; // El usuario presionó Cancelar o cerró la ventana
                    continue;
                }

                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        inicializarCompetencia();
                        break;
                    case 2:
                        registrarEquipo();
                        break;
                    case 3:
                        registrarCompetidor();
                        break;
                    case 4:
                        simularResultados();
                        break;
                    case 5:
                        generarReporteCompetencia();
                        break;
                    case 6:
                        mostrarPuntajeTotal();
                        break;
                    case 7:
                        usarToString();
                        break;
                    case 8:
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Adiós!", "Salir", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
}
