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
import java.util.Collections;
import java.util.Comparator;
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


            double estatura = Double.parseDouble(JOptionPane.showInputDialog("Estatura (en metros, ej: 1.75):"));
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso (en kg, ej: 65.0):"));

            Competidor nuevoCompetidor = new Competidor(nombre, edad, pais, estatura, peso);

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

        // 1. Obtener el orden de llegada de los competidores por parte del usuario
        List<Competidor> competidoresOrdenados = solicitarOrdenLlegada(todosCompetidores);

        if (competidoresOrdenados == null) {
            JOptionPane.showMessageDialog(null, "Simulación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 2. Asignar puntos y actualizar rankings basado en el orden ingresado
        String resumenPuntos = contador.asignarPuntos(competidoresOrdenados);

        JTextAreaWithScroll.show(resumenPuntos, "Simulación de Resultados");
    }
    
    private static void generarReporteCompetencia() {
        if (mundialCiclismo == null || mundialCiclismo.getEquipos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay datos suficientes para generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Asegurarse de que los competidores estén ordenados por puntos antes de generar el reporte
        List<Competidor> todosCompetidoresOrdenados = new ArrayList<>(todosCompetidores);
        todosCompetidoresOrdenados.sort(Comparator.comparingInt(Competidor::getPuntos).reversed());

        StringBuilder reporte = new StringBuilder();
        reporte.append("==================================================\n");
        reporte.append("REPORTE DE COMPETENCIA: ").append(mundialCiclismo.getNombreEvento()).append("\n");
        reporte.append("==================================================\n");
        reporte.append("\n--- Ranking General de Competidores (por puntos) ---\n");
        for (int i = 0; i < todosCompetidoresOrdenados.size(); i++) {
            Competidor c = todosCompetidoresOrdenados.get(i);
            reporte.append(String.format("%d. %s (Equipo: %s, Puntos: %d)\n",
                    (i + 1),
                    c.getNombre(),
                    mundialCiclismo.getEquipos().stream()
                            .filter(equipo -> equipo.getCompetidores().contains(c))
                            .findFirst().map(Equipo::getNombre).orElse("N/A"),
                    c.getPuntos()));
        }
        reporte.append("\n");

        reporte.append(mundialCiclismo.generarReporte()); // Reporte original de equipos

        JTextAreaWithScroll.show(reporte.toString(), "Reporte de Competencia: " + mundialCiclismo.getNombreEvento());
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

        // Mostrar el toString() de todos los competidores
        StringBuilder sb = new StringBuilder();
        sb.append("Datos de Competidores (usando toString()):\n\n");
        for (Competidor c : todosCompetidores) {
            sb.append(c.toString()).append("\n--------------------\n");
        }

        JTextAreaWithScroll.show(sb.toString(), "Método toString()");
    }
    
    private static List<Competidor> solicitarOrdenLlegada(List<Competidor> competidoresDisponibles) {
        if (competidoresDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay competidores para simular.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        List<Competidor> competidoresOrdenados = new ArrayList<>();
        List<Competidor> restantes = new ArrayList<>(competidoresDisponibles);

        for (int i = 0; i < competidoresDisponibles.size(); i++) {
            String[] opciones = restantes.stream()
                    .map(c -> c.getNombre() + " (Puntos: " + c.getPuntos() + ")")
                    .toArray(String[]::new);

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el competidor en la posición " + (i + 1) + ":\n(Competidores restantes: " + restantes.size() + ")",
                    "Definir Orden de Llegada",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (seleccion == null) {
                return null; // Usuario canceló
            }

            // Extraer el nombre del competidor de la selección
            String nombreCompetidorSeleccionado = seleccion.substring(0, seleccion.indexOf(" ("));

            Competidor competidorElegido = restantes.stream()
                    .filter(c -> c.getNombre().equals(nombreCompetidorSeleccionado))
                    .findFirst()
                    .orElse(null);

            if (competidorElegido != null) {
                competidoresOrdenados.add(competidorElegido);
                restantes.remove(competidorElegido);
            } else {
                JOptionPane.showMessageDialog(null, "Error al seleccionar competidor. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                i--; // Repetir la iteración actual
            }
        }
        return competidoresOrdenados;
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
                              "4. Simular Resultados (Ingresar Orden de Llegada, Asignar Puntos y Actualizar Ranking)\n" +
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
