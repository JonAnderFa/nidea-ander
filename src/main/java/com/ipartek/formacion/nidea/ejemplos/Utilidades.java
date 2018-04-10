package com.ipartek.formacion.nidea.ejemplos;

import java.util.StringTokenizer;

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
	public static String LimpiarEspacios(String sCadenaSinBlancos) {
		StringTokenizer stTexto = new StringTokenizer(sCadenaSinBlancos);

		while (stTexto.hasMoreElements())
			sCadenaSinBlancos += stTexto.nextElement();
		return sCadenaSinBlancos;

	}

}
