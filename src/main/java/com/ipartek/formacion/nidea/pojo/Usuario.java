package com.ipartek.formacion.nidea.pojo;

public class Usuario {

	private int id;
	private String nombre;
	private String password;
	private Rol rol;
	public static int ROL_ADMIN = 1;
	public static int ROL_USER = 2;

	public Usuario() {
		super();
		this.id = -1;
		this.setNombre("nada");
		this.setPassword("123456");
		this.rol = new Rol();
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
