package com.ipartek.formacion.nidea.ejemplos;

public abstract class Vehiculo implements Ordenable {
	private int puertas;
	private String color;

	public Vehiculo() {
		super();
		this.puertas = 3;
		this.color = "blanco";
		System.out.println("Instanciado Vehiculo");
	}

	public abstract void arrancar();

	public void encenderLuces() {
		System.out.println("Luces ON");
	}

	public int getValor() {
		return this.puertas;
	}

	@Override
	public String toString() {
		return "Vehiculo [puertas=" + puertas + ", color=" + color + "]";
	}

	public int getPuertas() {
		return puertas;
	}

	public void setPuertas(int puertas) {
		this.puertas = puertas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
