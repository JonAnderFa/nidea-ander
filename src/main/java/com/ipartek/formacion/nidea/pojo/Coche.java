package com.ipartek.formacion.nidea.pojo;

public class Coche implements AutoCloseable {
	public Coche() {
		super();
		System.out.println("Creamos Coche");
	}

	public void conducir() {
		System.out.println("Brum Brum estamos conduciendo");
	}

	@Override
	public void close() throws Exception {
		System.out.println("Paramos coche");

	}

	public static void main(String[] args) {
		// Si declaramos un objeto que implemente la interfaz Autoclosable
		// dentro de los parentesis de TRY, cuabdi llega al FINALLY se ejecuta
		// de forma automatica su metodo "close()"

		try (Coche c = new Coche()) {
			System.out.println("Empezandos programa");
			c.conducir();
		} catch (Exception e) {
			System.out.println("Tenemos una excepcion");
		} finally {
			System.out.println("finalizamos");
		}
	}

}
