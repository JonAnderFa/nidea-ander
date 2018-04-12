package com.ipartek.formacion.nidea.ejemplos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrdenacionInterfacezTest {
	static ArrayList<Ordenable> coleccion;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coleccion = new ArrayList<Ordenable>();

	}

	@After
	public void tearDown() throws Exception {
		coleccion = null;
	}

	@Before
	public void setup() {
		VehiculoElectrico v1 = new VehiculoElectrico();
		VehiculoElectrico v2 = new VehiculoElectrico();
		Triangulo t1 = new Triangulo();
		Triangulo t2 = new Triangulo();
		v2.setPuertas(9);
		t1.setX(1);
		t2.setX(0);
		coleccion.add(v1);
		coleccion.add(v2);
		coleccion.add(t1);
		coleccion.add(t2);

	}

	@AfterClass
	public static void tearDownAfterClass() {
		coleccion = null;
	}

	@Test
	public void test() {
		// coleccion = (ArrayList<Ordenable>) Utilidades.bubbleSort(coleccion);
		Collections.sort(coleccion, new ComparatorOrdenables());

		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());

		Collections.reverse(coleccion);
		assertEquals(0, coleccion.get(0).getValor());
		assertEquals(1, coleccion.get(1).getValor());
		assertEquals(3, coleccion.get(2).getValor());
		assertEquals(9, coleccion.get(3).getValor());

		Ordenable elemento = coleccion.get(0);
		if (elemento instanceof Triangulo) {
			Triangulo t = (Triangulo) elemento;

		} else if (elemento instanceof VehiculoElectrico) {
			VehiculoElectrico ve = (VehiculoElectrico) elemento;
		} else {
			fail("No esperamos esta Clase de Objetos");
		}
	}

}
