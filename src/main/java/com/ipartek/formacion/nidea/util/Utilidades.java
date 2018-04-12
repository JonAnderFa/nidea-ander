package com.ipartek.formacion.nidea.util;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.nidea.ejemplos.Ordenable;

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

	/**
	 * Metodo para ordenar una coleccion con el algoritmo BubbleSort, ordenando del
	 * menor al mayor, basandose en el metodo getValor() de la interfaz ordenable
	 * 
	 * @see com.ipartek.formacion.nidea.ejemplos.Ordenable
	 * @param coleccion
	 *            List<Ordenable>coleccion con los elementos a ordenadar
	 * @return en caso de null retornamos unal ista vacia
	 */
	public static List<Ordenable> bubbleSort(List<Ordenable> coleccion) {
		List<Ordenable> resul = new ArrayList<Ordenable>();
		List<Ordenable> temp = new ArrayList<Ordenable>();
		if (coleccion != null) {
			resul = coleccion;
			int n = resul.size();
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < (n - i); j++) {
					int x = (j - 1);
					if (resul.get(x).getValor() > resul.get(j).getValor()) {
						// swap elements

						temp.set(0, resul.get(x));
						resul.set(x, temp.get(0));
					}

				}
			}
			return resul;
		}

		return resul;
	}

}
