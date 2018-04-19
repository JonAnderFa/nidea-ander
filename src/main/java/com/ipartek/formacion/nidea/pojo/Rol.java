package com.ipartek.formacion.nidea.pojo;

public class Rol {
	private int idRol;
	private String clase;

	public Rol() {
		super();
		this.setIdRol(-1);
		this.setClase("SinRol");
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int id) {
		this.idRol = id;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

}
