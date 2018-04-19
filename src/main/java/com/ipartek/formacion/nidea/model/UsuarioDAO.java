package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Usuario;

public class UsuarioDAO implements Persistible<Usuario> {
	private static UsuarioDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N instancias
	private UsuarioDAO() {

	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
	}

	public static UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id, u.nombre,u.id_rol,r.nombre FROM usuario as u,rol as r  WHERE u.id_rol = r.id;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Usuario m = null;
				while (rs.next()) {
					m = mapper(rs);
					lista.add(m);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

	}

	/**
	 * Variable CHECK que retorna si encuentra un usuario, en caso contrario retorna
	 * un null
	 * 
	 * @param recibe
	 *            el nombre y la pass para comprobarlo
	 * @param pass
	 * @return devuelve un Usuario si existe y si no un null
	 */
	public Usuario buscar(String nombre, String pass) {

		Usuario usuario = null;
		String sql = "SELECT u.id, u.nombre,u.id_rol,r.nombre FROM usuario as u,rol as r  WHERE u.nombre = ? AND u.password = ? AND u.id_rol = r.id ;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nombre);
			pst.setString(2, pass);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					usuario = mapper(rs);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuario;

	}

	@Override
	public Usuario getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Usuario pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getInt("u.id"));
		usuario.setNombre(rs.getString("u.nombre"));
		usuario.getRol().setIdRol(rs.getInt("u.id_rol"));
		usuario.getRol().setClase(rs.getString("r.nombre"));
		return usuario;
	}

}
