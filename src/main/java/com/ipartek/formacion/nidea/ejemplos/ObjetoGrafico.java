package com.ipartek.formacion.nidea.ejemplos;

public abstract class ObjetoGrafico implements Imprimible {
	private int x;
	private int y;

	public void mover() {
		System.out.println("Moviendo el objeto");
	}

	public abstract void dibujar();

	@Override
	public String toString() {
		return "ObjetoGrafico [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
