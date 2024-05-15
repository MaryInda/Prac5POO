import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class CurlysMultiPieceMino {
    private ArrayList<Ficha> fichas;
    private Jugador[] jugadores;
    private ArrayList<Ficha> fichasEnMesa;
    private int turno;
    private int fichasEnPozo;

    public CurlysMultiPieceMino(ArrayList<Ficha> fichas, Jugador... jugadores) {
        this.fichas = fichas;
        this.jugadores = jugadores;
        this.fichasEnMesa = new ArrayList<>();
        this.turno = 0;
        this.fichasEnPozo = fichas.size();
    }

    public void iniciarJuego() {
        while (fichasEnPozo > 0 && !todosLosJugadoresSinFichas()) {
            Jugador jugadorActual = jugadores[turno % jugadores.length];
            Ficha fichaAUsar = jugadorActual.elegirFicha();
            if (fichaAUsar != null && puedeColocarFicha(fichaAUsar)) {
                fichasEnMesa.add(fichaAUsar); // Agregar la ficha a la mesa
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

    private boolean puedeColocarFicha(Ficha ficha) {
        if (fichasEnMesa.isEmpty()) { 
            return true;
        }
        Ficha primeraFicha = fichasEnMesa.get(0);
        return primeraFicha.lado1 == ficha.lado1 || primeraFicha.lado1 == ficha.lado2 || primeraFicha.lado2 == ficha.lado1 || primeraFicha.lado2 == ficha.lado2;
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
