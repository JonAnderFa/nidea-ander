package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.pojo.Alert;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet(description = "loginUser", urlPatterns = { "/loginUser" })
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String view = "";
	private Alert alert = new Alert();

	private static final int SESSION_EXPIRATION = 60 * 20;// 20min de sesion que tarda en expirar

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("loginUser.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			if (request.getParameter("id") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				if (request.getParameter("nombre") != null) {
					String nombre = request.getParameter("nombre");

					// recoger los usuarios conectados
					ServletContext ctx = request.getServletContext();
					HashMap<Integer, String> nubeUsuarios = null;

					if (ctx.getAttribute("nubeUsuarios") == null) {
						nubeUsuarios = new HashMap<Integer, String>();
					} else {
						nubeUsuarios = (HashMap<Integer, String>) ctx.getAttribute("nubeUsuarios");
					}
					// guardar usuario
					nubeUsuarios.put(id, nombre);
					// guardar hasmap en contexto Servlets
					ctx.setAttribute("nubeUsuarios", nubeUsuarios);

					view = "index.jsp";
					alert = new Alert("Ongi Etorri '" + nombre + "'", Alert.TIPO_PRIMARY);
				}

			} else {

				view = "loginUser.jsp";
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			view = "loginUser.jsp";
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);

		}

	}

}