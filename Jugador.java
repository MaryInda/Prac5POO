package com.labpoo.curlysdomino_tridomino;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
class Jugador {
    private String nombre;
    private ArrayList<Ficha> fichas;
    private int puntuacion;
    private boolean turno;

public Jugador(String nombre) {
    this.nombre = nombre;
    this.fichas = new ArrayList<>();
    this.puntuacion = 0;
    this.turno = false;
}

public String getNombre() {
    return nombre;
}

public ArrayList<Ficha> getFichas() {
    return fichas;
}

public int getPuntuacion() {
    return puntuacion;
}

public void aumentarPuntuacion(int puntos) {
    this.puntuacion += puntos;
}

public void agregarFicha(Ficha ficha) {
    this.fichas.add(ficha);
}

public Ficha elegirFicha() {
    // Aquí puedes implementar la lógica para que el jugador elija una ficha según tus necesidades.
    if (!fichas.isEmpty()) {
        return fichas.remove(0);
    } else {
        return null;
    }
}

public Ficha elegirFichaAleatoria() {
    return fichas.get((int) (Math.random() * fichas.size()));
}

public boolean isTurno() {
    return turno;
}

public void setTurno(boolean turno) {
    this.turno = turno;
    }
}
