/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Angello
 */
public class Competidor {
    
    private String nombre;
    private int edad;
    private String pais;
    private int rankingMundial;
    private double estatura;
    private double peso;
    private int puntos;
    
    public Competidor(String nombre, int edad, String pais, int rankingMundial, double estatura, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pais = pais;
        this.rankingMundial = rankingMundial;
        this.estatura = estatura;
        this.peso = peso;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getRankingMundial() {
        return rankingMundial;
    }

    public void setRankingMundial(int rankingMundial) {
        this.rankingMundial = rankingMundial;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void actualizarRanking(int nuevoRanking) {
        this.rankingMundial = nuevoRanking;
    }
    
    public int actualizarRanking(int puntosObtenidos, boolean conBonificacion) {
        // Lógica de ejemplo para la estructura anidada y sobrecarga
        int ajusteRanking = puntosObtenidos / 10; // Cada 10 puntos, mejora 1 posición
        
        if (conBonificacion) {
            if (puntosObtenidos > 100) {
                ajusteRanking += 5; // Bonificación de 5 posiciones por alto puntaje
            }
        }
        
        // El ranking mundial es un número, si el competidor mejora, el número baja.
        int nuevoRanking = this.rankingMundial - ajusteRanking;
        
        // Asegurarse de que el ranking no sea menor a 1
        this.rankingMundial = Math.max(1, nuevoRanking);
        
        return this.rankingMundial;
    }
    
    public String obtenerDatos() {
        return "Competidor: " + nombre + " | País: " + pais + " | Edad: " + edad +
               " | Ranking Mundial: " + rankingMundial + " | Puntos: " + puntos;
    }
    
    @Override
    public String toString() {
        return "Datos del Competidor\n\n" +
               "Nombre: " + nombre + "\n" +
               "Edad: " + edad + " años\n" +
               "País: " + pais + "\n" +
               "Ranking Mundial: " + rankingMundial + "\n" +
               "Estatura: " + estatura + " m\n" +
               "Peso: " + peso + " kg\n" +
               "Puntos Acumulados: " + puntos + "\n";
    }
}
