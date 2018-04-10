package com.ipartek.formacion.nidea.ejemplos;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class UtilidadesTest {

	@Test
	public void testLimpiarEspacios() {
		assertArrayEquals("hola que haxe", Utilidades.LimpiarEspacios("     hola    que     haxe"));
		assertArrayEquals("", Utilidades.LimpiarEspacios(null));
	}

}
