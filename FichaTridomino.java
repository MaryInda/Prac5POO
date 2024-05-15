package com.labpoo.curlysdomino_tridomino;

/**
 *
 * @author Usuario
 */
public class FichaTridomino extends Ficha {
    private int lado3;

    public FichaTridomino(int lado1, int lado2, int lado3) {
        super(lado1, lado2);
        this.lado3 = lado3;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + lado3;
    }

    @Override
    public void rotateRight() {
        int temp = lado1;
        lado1 = lado3;
        lado3 = lado2;
        lado2 = temp;
    }

    @Override
    public void rotateLeft() {
        int temp = lado1;
        lado1 = lado2;
        lado2 = lado3;
        lado3 = temp;
    }

    @Override
    public String toString() {
        return "[" + lado1 + "|" + lado2 + "|" + lado3 + "]";
    }
}
