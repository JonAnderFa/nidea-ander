package com.ipartek.formacion.nidea.backoffice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;

/**
 * Servlet implementation class BackofficeMaterialesController
 */
@WebServlet("/backoffice/materiales")
public class BackofficeMaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW_INDEX = "materiales/index.jsp";
	private static final String VIEW_FORM = "materiales/form.jsp";
	private static final String VIEW_INICIO = "index.jsp";

	public static final int OP_MOSTRAR_FORMULARIO = 1;
	public static final int OP_BUSQUEDA = 2;
	public static final int OP_ELIMINAR = 3;
	public static final int OP_GUARDAR = 4;
	public static final int OP_INDEX = 5;

	private RequestDispatcher dispacher;

	private MaterialDAO dao;
	private Alert alert;

	// PARAMETROS COMUNES
	private String search;// para el buscador por nombre material
	private int op; // operacion a realizar

	// PARAMETOS DEL MATERIAL
	private int id;
	private String nombre;
	private float precio;

	// Se ejecuta una unica vez con la primera peticion que se realiza
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = MaterialDAO.getInstance();

	}

	// Se ejecuta cuando Paramos el servidor de aplicaciones
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		dao = null;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Antes de Ejecutar doGET o doPost");
		super.service(request, response);
		System.out.println("Despues de Ejecutar doGet o doPost");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			recogerParametros(request);

			switch (op) {
			case OP_MOSTRAR_FORMULARIO:
				mostrarFormulario(request);

				break;
			case OP_ELIMINAR:
				eliminar(request);

				break;
			case OP_BUSQUEDA:
				buscar(request);

				break;
			case OP_GUARDAR:
				guardar(request);

				break;
			case OP_INDEX:
				index(request);

				break;
			default:
				listar(request);
				break;
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			dispacher = request.getRequestDispatcher(VIEW_INDEX);
			alert = new Alert();
			request.setAttribute("alert", alert);

		} finally {

			dispacher.forward(request, response);
			// Reseteamos variables locales
			op = 0;
			id = -1;
			alert = null;

		}

	}

	private void index(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.seach(search);
		request.setAttribute("materiales", materiales);
		dispacher = request.getRequestDispatcher(VIEW_INICIO);

	}

	private void mostrarFormulario(HttpServletRequest request) {
		Material material = new Material();
		if (id > 0) {
			material = dao.seachid(id);
		}
		request.setAttribute("material", material);
		dispacher = request.getRequestDispatcher(VIEW_FORM);

	}

	private void eliminar(HttpServletRequest request) {
		if (dao.delete(id)) {
			alert = new Alert("Material Eliminado id " + id, Alert.TIPO_PRIMARY);
		} else {
			alert = new Alert("Error Eliminando, sentimos las molestias ", Alert.TIPO_WARNING);
		}
		listar(request);
		request.setAttribute("alert", alert);

	}

	private void guardar(HttpServletRequest request) {

		Material material = new Material();
		if (id == -1) {
			alert = new Alert("Creado Nuevo Material ", Alert.TIPO_PRIMARY);
			dao.create(nombre, precio);
		} else {
			alert = new Alert("Modificado Material id: " + id, Alert.TIPO_PRIMARY);
			dao.update(id, nombre, precio);
		}

		request.setAttribute("material", material);
		listar(request);
		request.setAttribute("alert", alert);

	}

	private void buscar(HttpServletRequest request) {

		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.seach(search);
		request.setAttribute("materiales", materiales);
		dispacher = request.getRequestDispatcher(VIEW_INDEX);

	}

	private void listar(HttpServletRequest request) {
		ArrayList<Material> materiales = new ArrayList<Material>();
		materiales = dao.getAll();
		request.setAttribute("materiales", materiales);
		dispacher = request.getRequestDispatcher(VIEW_INDEX);

	}

	/**
	 * Recogemos e inicializamos todos los posibles parametros que nos pueden mandar
	 * 
	 * @param request
	 */
	private void recogerParametros(HttpServletRequest request) {

		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		}

		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		if (request.getParameter("nombre") != null) {
			nombre = request.getParameter("nombre");

		}
		if (request.getParameter("precio") != null) {
			precio = Float.parseFloat(request.getParameter("precio"));

		}
	}

}
