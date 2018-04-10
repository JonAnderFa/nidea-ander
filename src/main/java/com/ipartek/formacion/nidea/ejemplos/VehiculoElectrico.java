package com.ipartek.formacion.nidea.ejemplos;

public class VehiculoElectrico extends Vehiculo {
	private float potencia;// KW

	public VehiculoElectrico() {
		super();
		potencia = 0;
		System.out.println("Instanciado vehiculo electrico");
	}

	public VehiculoElectrico(float potencia) {
		this();// cambiar super por this
		this.potencia = potencia;
	}

	@Override
	public void arrancar() {
		// super.arrancar();
		System.out.println("Pulsar boton de encencido");
	}

	@Override
	public String toString() {
		return super.toString() + "VehiculoElectrico [potencia=" + potencia + "]";
	}

	public float getPotencia() {
		return potencia;
	}

	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}

}
