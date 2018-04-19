package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Rol;
import com.ipartek.formacion.nidea.util.Utilidades;

public class RolDAO implements Persistible<Rol> {

	private static RolDAO INSTANCE = null;

	// Private constructor para que no se pueda hacer new y crear N instancias
	private RolDAO() {

	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RolDAO();
		}
	}

	public static RolDAO getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	@Override
	public ArrayList<Rol> getAll() {

		ArrayList<Rol> lista = new ArrayList<Rol>();
		String sql = "SELECT r.id,r.nombre FROM rol as r  ";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			try (ResultSet rs = pst.executeQuery();) {
				Rol m = null;
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
	 * Variable CHECK que retorna si encuentra un rol, en caso contrario retorna un
	 * null
	 * 
	 * @param recibe
	 *            el nombre y la pass para comprobarlo
	 * @param pass
	 * @return devuelve un Rol si existe y si no un null
	 */
	public ArrayList<Rol> buscar(String nombre) {

		Rol rol = null;
		ArrayList<Rol> lista = new ArrayList<Rol>();
		String sql = "SELECT id,nombre FROM rol   WHERE nombre like '%' ? '%'  ;";

		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setString(1, nombre);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					rol = mapper(rs);
					lista.add(rol);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;

	}

	@Override
	public Rol getById(int id) {
		Rol rol = null;
		String sql = "SELECT `id`, `nombre` FROM `rol` WHERE `id` = ? ;";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					rol = mapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rol;
	}

	@Override
	public boolean save(Rol pojo) {
		boolean resul = false;

		// sanear el nombre
		pojo.setClase(Utilidades.LimpiarEspacios(pojo.getClase()));

		if (pojo != null) {
			if (pojo.getIdRol() == -1) {
				resul = crear(pojo);
			} else {
				resul = modificar(pojo);
			}
		}

		return resul;
	}

	private boolean modificar(Rol pojo) {
		boolean resul = false;
		String sql = "UPDATE `rol` SET `nombre`= ? WHERE  `id`= ?;";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, pojo.getClase());
			pst.setInt(2, pojo.getIdRol());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	private boolean crear(Rol pojo) {
		boolean resul = false;
		String sql = "INSERT INTO `rol` (`nombre`) VALUES ( ? );";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, pojo.getClase());

			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				// recuperar ID generado de forma automatica
				try (ResultSet rs = pst.getGeneratedKeys()) {
					while (rs.next()) {
						pojo.setIdRol(rs.getInt(1));
						resul = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		String sql = "DELETE FROM `material` WHERE  `id`= ?;";
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}

	@Override
	public Rol mapper(ResultSet rs) throws SQLException {
		Rol rol = new Rol();
		rol.setIdRol(rs.getInt("id"));
		rol.setClase(rs.getString("nombre"));
		return rol;
	}

}
