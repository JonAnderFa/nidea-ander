package com.ipartek.formacion.nidea.ejemplos.figuras;

public class Triangulo extends ObjetoGrafico {

	public Triangulo() {
		super();
		this.setX(2);
		this.setY(3);
	}

	@Override
	public void dibujar() {
		System.out.println("Estoy dibujado un triangulo con x=" + this.getX() + " e y=" + this.getY());

	}

	@Override
	public void imprimir() {

		System.out.println("*********************");
		System.out.println("Estoy imprimiendo");
		System.out.println("*********************");
	}

}
