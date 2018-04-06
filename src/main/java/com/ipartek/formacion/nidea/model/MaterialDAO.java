package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;

public class MaterialDAO {

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
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			/*
			 * Class.forName("com.mysql.jdbc.Driver"); final String URL =
			 * "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno"; con =
			 * DriverManager.getConnection(URL);
			 */
			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM material;";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			/*
			 * Class.forName("com.mysql.jdbc.Driver"); final String URL =
			 * "jdbc:mysql://192.168.0.42/spoty?user=alumno&password=alumno"; con =
			 * DriverManager.getConnection(URL);
			 */
			con = ConnectionManager.getConnection();
			String sql = "SELECT id,nombre,precio FROM material WHERE nombre like '%' ? '%' ORDER BY id DESC LIMIT 500;";

			pst = con.prepareStatement(sql);
			pst.setString(1, buscar);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	/**
	 * Busca por id y devuelve un material de dicho id
	 * 
	 * @param buscar
	 * @return
	 */
	public Material seachid(int buscar) {

		Material material = new Material();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			con = ConnectionManager.getConnection();
			String sql = "SELECT id,nombre,precio FROM material \n" + "WHERE id = ?\n" + ";";

			pst = con.prepareStatement(sql);
			pst.setInt(1, buscar);
			rs = pst.executeQuery();
			while (rs.next()) {
				material.setId(rs.getInt("id"));
				material.setNombre(rs.getString("nombre"));
				material.setPrecio(rs.getFloat("precio"));
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		Connection con = null;
		PreparedStatement pst = null;
		try {

			con = ConnectionManager.getConnection();
			String sql = "DELETE FROM `material` WHERE  `id`= ?;";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resul;
	}

	public boolean update(int id, String nombre, float precio) {
		boolean resul = false;
		Connection con = null;
		PreparedStatement pst = null;
		try {

			con = ConnectionManager.getConnection();
			String sql = "UPDATE `material` SET `nombre`=? , `precio`=? WHERE  `id`=?;";

			pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.setInt(3, id);

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resul;
	}

	public boolean create(String nombre, float precio) {
		boolean resul = false;
		Connection con = null;
		PreparedStatement pst = null;
		try {

			con = ConnectionManager.getConnection();
			// String sql = "INSERT INTO `material` (`nombre`, `precio`) VALUES ('" + nombre
			// + "', '" + precio + "');";
			String sql = "INSERT INTO `material` (`nombre`, `precio`) VALUES (?, ?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.executeUpdate();

			sql = "SELECT LAST_INSERT_ID();";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();

			sql = "SELECT `id`, `nombre`, `precio` FROM `material` WHERE  `id`=?;";

			int affetedRows = pst.executeUpdate();

			if (affetedRows == 1) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resul;
	}

}
