package com.labpoo.curlysdomino_tridomino;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Usuario
 */
public class JuegoCurlys {
    private ArrayList<Ficha> fichas;
    private Jugador[] jugadores;
    private int turno;
    private int fichasEnPozo;

     public static void main(String[] args) {
        // Crear y mezclar las fichas
        ArrayList<Ficha> fichas = new ArrayList<>();
        // Agregar fichas de dominó
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fichas.add(new FichaDomino(i, j));
            }
        }
        // Agregar fichas de tridominó
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                for (int k = j; k <= 6; k++) {
                    fichas.add(new FichaTridomino(i, j, k));
                }
            }
        }
        // Mezclar las fichas
        Collections.shuffle(fichas);

        // Crear los jugadores
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");

        // Distribuir las fichas a los jugadores
        for (int i = 0; i < 10; i++) {
            jugador1.agregarFicha(fichas.remove(0));
            jugador2.agregarFicha(fichas.remove(0));
        }

        // Determinar quién empieza
        Ficha fichaJugador1 = jugador1.elegirFichaAleatoria();
        Ficha fichaJugador2 = jugador2.elegirFichaAleatoria();

        int puntosJugador1 = fichaJugador1.getPuntos();
        int puntosJugador2 = fichaJugador2.getPuntos();

        System.out.println("Jugador 1 ha elegido " + fichaJugador1 + " con " + puntosJugador1 + " puntos.");
        System.out.println("Jugador 2 ha elegido " + fichaJugador2 + " con " + puntosJugador2 + " puntos.");

        if (puntosJugador1 > puntosJugador2) {
            jugador1.setTurno(true);
            System.out.println("Jugador 1 inicia la partida.");
        } else if (puntosJugador2 > puntosJugador1) {
            jugador2.setTurno(true);
            System.out.println("Jugador 2 inicia la partida.");
        } else {
            System.out.println("Ambos jugadores tienen la misma cantidad de puntos. Se debe volver a elegir.");
        }

        // Crear un ArrayList que contenga ambos tipos de fichas
        ArrayList<Ficha> fichasMixtas = new ArrayList<>();
    fichasMixtas.addAll(jugador1.getFichas());
    fichasMixtas.addAll(jugador2.getFichas());

    // Iniciar el juego con el ArrayList que contiene ambos tipos de fichas
    CurlysMultiPieceMino juego = new CurlysMultiPieceMino(fichasMixtas, jugador1, jugador2);
    juego.iniciarJuego();
}


    public JuegoCurlys(ArrayList<Ficha> fichas, Jugador... jugadores) {
        this.fichas = fichas;
        this.jugadores = jugadores;
        this.turno = 0;
        this.fichasEnPozo = fichas.size();
    }

    public void iniciarJuego() {
        while (fichasEnPozo > 0 && !todosLosJugadoresSinFichas()) {
            Jugador jugadorActual = jugadores[turno % jugadores.length];
            Ficha fichaAUsar = jugadorActual.elegirFicha();
            if (fichaAUsar != null) {
                jugadorActual.aumentarPuntuacion(fichaAUsar.getPuntos());
                fichasEnPozo--;
            }
            turno++;
        }
        determinarGanador();
    }

    private boolean todosLosJugadoresSinFichas() {
        for (Jugador jugador : jugadores) {
            if (jugador.getFichas().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void determinarGanador() {
        int maxPuntuacion = 0;
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntuacion() > maxPuntuacion) {
                maxPuntuacion = jugador.getPuntuacion();
                ganador = jugador;
            }
        }
        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getNombre() + " con " + ganador.getPuntuacion() + " puntos.");
        } else {
            System.out.println("¡Hubo un empate!");
        }
    }
}
