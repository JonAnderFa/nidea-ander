package com.ipartek.formacion.nidea.pojo;

public class Material {

	private int id;
	private String nombre;
	private float precio;
	private int id_usuario;

	public Material() {
		super();
		this.id = -1;
		this.nombre = "";
		this.precio = 0f;
		this.setId_usuario(1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

}
