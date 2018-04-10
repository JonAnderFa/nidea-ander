package com.ipartek.formacion.nidea.ejemplos;

public class EjercicioVehiculos {

	public static void main(String[] args) {
		/*
		 * No se puede crear una instancia de una clase abstracta
		 * 
		 * Vehiculo rayoMacQueen = new Vehiculo();
		 * System.out.println(rayoMacQueen.toString()); rayoMacQueen.arrancar();
		 * System.out.println("------------------------------------");
		 */
		VehiculoElectrico tesla = new VehiculoElectrico();
		System.out.println(tesla.toString());
		tesla.arrancar();
		System.out.println("------------------------------------");

	}

}
