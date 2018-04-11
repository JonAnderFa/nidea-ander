package com.ipartek.formacion.nidea.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ipartek.formacion.nidea.util.Utilidades;

public class UtilidadesTest {

	@Test
	public void testLimpiarEspacios() {
		assertEquals("hola que haxe", Utilidades.LimpiarEspacios("     hola    que     haxe"));
		assertEquals("", Utilidades.LimpiarEspacios(null));
	}

}
