package com.labpoo.curlysdomino_tridomino;

/**
 *
 * @author Usuario
 */
public class Ficha implements Movible {
    protected int lado1;
    protected int lado2;

    public Ficha(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getPuntos() {
        return lado1 + lado2;
    }

    @Override
    public void rotateRight() {
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }

    @Override
    public void rotateLeft() {
        int temp = lado2;
        lado2 = lado1;
        lado1 = temp;
    }

    @Override
    public String toString() {
        return "[" + lado1 + "|" + lado2 + "]";
    }
}
