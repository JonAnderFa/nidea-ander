package com.ipartek.formacion.nidea.util;

public class Utilidades {
	/**
	 * Metodo estatico para poder usarse desde la propia Clase, sin tener que
	 * instanciar un objeto.
	 * 
	 * Limpia lo caracteres vacios " " de una cadena String
	 * 
	 * Hacemos Trim, ademas de sustituis todos los espacios en blanco por uno unico,
	 * ej: "hola que haxe"=> "hola que haxe"
	 * 
	 * @param cadena
	 * @return en caso de null retorna cadena vacia
	 * 
	 */
	public static String LimpiarEspacios(String cadena) {
		String resul = "";
		if (cadena != null) {
			resul = cadena.trim();
			resul = resul.replaceAll("\\s+", " ");
		}
		return resul;
	}

}
