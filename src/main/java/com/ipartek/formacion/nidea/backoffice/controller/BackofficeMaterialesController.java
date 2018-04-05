package com.ipartek.formacion.nidea.backoffice.controller;

import java.io.IOException;
import java.util.ArrayList;

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
	private String view = "";
	private Alert alert = new Alert();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Material> materiales = new ArrayList<Material>();
		try {
			String search = request.getParameter("search");

			// Enviar como atributo la lista de materiales
			MaterialDAO dao = MaterialDAO.getInstance();
			if (search != null) {
				materiales = dao.seach(search);
			} else {
				materiales = dao.getAll();
			}

			request.setAttribute("materiales", materiales);

			view = "materiales/index.jsp";

		} catch (

		Exception e) {
			e.printStackTrace();
			view = "/index.jsp";
			alert = new Alert();
			request.setAttribute("alert", alert);

		} finally {

			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
