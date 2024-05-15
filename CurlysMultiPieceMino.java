import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Usuario
 */
public class CurlysMultiPieceMino {
    private ArrayList<Ficha> fichas;
    private ArrayList<Ficha> pozo;
    private ArrayList<Ficha> mesa;
    private Jugador jugador1;
    private Jugador jugador2;

    public CurlysMultiPieceMino() {
        // Inicializar las fichas
        this.fichas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fichas.add(new Ficha(i, j));
            }
        }
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                for (int k = j; k <= 6; k++) {
                    fichas.add(new FichaTridomino(i, j, k));
                }
            }
        }
        Collections.shuffle(fichas);

        // Inicializa el pozo y la mesa
        this.pozo = new ArrayList<>(fichas.subList(20, fichas.size()));
        this.mesa = new ArrayList<>();
        this.fichas.removeAll(pozo);

        // Inicializa jugadores y distribuir fichas
        this.jugador1 = new Jugador("Jugador 1");
        this.jugador2 = new Jugador("Jugador 2");
        for (int i = 0; i < 10; i++) {
            jugador1.agregarFicha(fichas.remove(0));
            jugador2.agregarFicha(fichas.remove(0));
        }
    }

    public void jugar() {
        // Determina quién empieza
        Jugador jugadorInicial = determinarJugadorInicial();

        // Inicia el juego
        Jugador jugadorActual = jugadorInicial;
        while (true) {
            // Muestra el estado actual del juego
            System.out.println("=== Turno de " + jugadorActual.getNombre() + " ===");
            System.out.println("Mesa: " + mesa);
            System.out.println("Pozo: " + pozo);
            System.out.println("Fichas de " + jugador1.getNombre() + ": " + jugador1.getFichas());
            System.out.println("Fichas de " + jugador2.getNombre() + ": " + jugador2.getFichas());

            // Elige ficha y la coloca en la mesa
            Ficha fichaAUsar = jugadorActual.elegirFicha();
            if (fichaAUsar == null) {
                System.out.println(jugadorActual.getNombre() + " no tiene fichas para jugar.");
                break;
            } else {
                System.out.println(jugadorActual.getNombre() + " ha elegido la ficha " + fichaAUsar);
                mesa.add(fichaAUsar);
                jugadorActual.aumentarPuntuacion(fichaAUsar.getPuntos());
                System.out.println(jugadorActual.getNombre() + " ha ganado " + fichaAUsar.getPuntos() + " puntos.");
            }

            // Cambia de jugador
            jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
        }

        // Determina el ganador
        Jugador ganador = (jugador1.getPuntuacion() > jugador2.getPuntuacion()) ? jugador1 :
                ((jugador2.getPuntuacion() > jugador1.getPuntuacion()) ? jugador2 : null);
        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getNombre() + " con " + ganador.getPuntuacion() + " puntos.");
        } else {
            System.out.println("¡Hubo un empate!");
        }
    }

    private Jugador determinarJugadorInicial() {
        // Voltea una ficha de cada jugador y determina quién tiene más puntos
        Ficha fichaJugador1 = jugador1.elegirFichaAleatoria();
        Ficha fichaJugador2 = jugador2.elegirFichaAleatoria();
        System.out.println("Jugador 1 ha elegido la ficha " + fichaJugador1);
        System.out.println("Jugador 2 ha elegido la ficha " + fichaJugador2);
        int puntosJugador1 = fichaJugador1.getPuntos();
        int puntosJugador2 = fichaJugador2.getPuntos();
        if (puntosJugador1 > puntosJugador2) {
            System.out.println("Jugador 1 inicia la partida.");
            return jugador1;
        } else if (puntosJugador2 > puntosJugador1) {
            System.out.println("Jugador 2 inicia la partida.");
            return jugador2;
        } else {
            System.out.println("Ambos jugadores tienen la misma cantidad de puntos. Se debe volver a elegir.");
            return determinarJugadorInicial();
        }
    }
}
