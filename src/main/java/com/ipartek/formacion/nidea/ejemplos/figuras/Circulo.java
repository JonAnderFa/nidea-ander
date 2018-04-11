package com.ipartek.formacion.nidea.ejemplos.figuras;

public class Circulo extends ObjetoGrafico {

	public Circulo() {
		super();
		this.setX(4);
		this.setY(6);
	}

	@Override
	public void dibujar() {
		System.out.println("Estas dibuando un circulocon x=" + this.getX() + " e y=" + this.getY());

	}

	@Override
	public void imprimir() {

		System.out.println("*********************");
		System.out.println("Estoy imprimiendo");
		System.out.println("*********************");
	}

	public void area() {
		System.out.println("La formula del area para el circulo es 2p*r ^2");
	}

}
