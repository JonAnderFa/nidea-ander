package com.ipartek.formacion.nidea.pojo;

public class Usuario {

	private int id;
	private String nombre;
	private String password;
	private int id_rol;

	public Usuario() {
		super();
		this.id = -1;
		this.setNombre("nada");
		this.setPassword("123456");
		this.setId_rol(2);
	}

	public Usuario(int id, String nombre) {
		this();
		this.setId(id);
		this.setNombre(nombre);
	}

	public Usuario(int id, String nombre, String pass) {
		this();
		this.setId(id);
		this.setNombre(nombre);
		this.setPassword(pass);
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + "]";
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
