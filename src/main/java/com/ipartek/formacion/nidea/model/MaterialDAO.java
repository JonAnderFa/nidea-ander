package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;

public class MaterialDAO implements Persistible<Material> {

	private static MaterialDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N instancias
	private MaterialDAO() {

	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MaterialDAO();
		}
	}

	public static MaterialDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	/**
	 * Recupera todos los materiales de la BBDD ordenados por id descendente
	 * 
	 * @return ArrayList<Material> si no existen registros new ArrayList<Material>()
	 */
	public ArrayList<Material> getAll() {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT m.id, m.nombre, precio, u.nombre FROM material as m INNER JOIN usuario as u ON m.id_usuario = u.id;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Material m = null;
				while (rs.next()) {
					m = new Material();
					m.setId(rs.getInt("m.id"));
					m.setNombre(rs.getString("m.nombre"));
					m.setPrecio(rs.getFloat("precio"));
					// TODO m.setRol(rs.getString("u.nombre"));
					lista.add(m);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

	}

	/**
	 * Devuelve un arrayList de materiales filtrando por el nombre
	 * 
	 * @param buscar
	 * @return
	 */
	public ArrayList<Material> seach(String buscar) {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT id,nombre,precio FROM material WHERE nombre like '%' ? '%' ORDER BY id DESC LIMIT 500;";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, buscar);
			try (ResultSet rs = pst.executeQuery();) {
				Material m = null;
				while (rs.next()) {
					m = new Material();
					m.setId(rs.getInt("id"));
					m.setNombre(rs.getString("nombre"));
					m.setPrecio(rs.getFloat("precio"));
					lista.add(m);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	/**
	 * Busca por id y devuelve un material de dicho id
	 * 
	 * @param buscar
	 * @return
	 */
	public Material getById(int buscar) {

		Material material = new Material();
		String sql = "SELECT  `id`,`nombre`,`precio` FROM material \n" + "WHERE id = ?\n" + ";";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, buscar);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					material.setId(rs.getInt("id"));
					material.setNombre(rs.getString("nombre"));
					material.setPrecio(rs.getFloat("precio"));
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return material;
	}

	/**
	 * Borra un registro material de la bbdd cuyo id sea igual al que recibe
	 * devuelve un boolean
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `material` WHERE  `id`= ?;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	public boolean update(int id, String nombre, float precio)
			throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "UPDATE `material` SET `nombre`=? , `precio`=? WHERE  `id`=?;";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.setInt(3, id);

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		}
		return resul;
	}

	public boolean create(String nombre, float precio) throws SQLIntegrityConstraintViolationException, Exception {
		boolean resul = false;
		String sql = "INSERT INTO `material` (`nombre`, `precio`) VALUES (?, ?);";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.executeUpdate();
			resul = true;

		}
		return resul;
	}

	@Override
	public boolean save(Material pojo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Material mapper(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
