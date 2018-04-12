package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final String USER = "admin";
	private static final String PASS = "admin";

	private static final int SESSION_EXPIRATION = 60 * 20;// 20min de sesion que tarda en expirar

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");

			if (USER.equalsIgnoreCase(usuario) && PASS.equals(password)) {
				// Enviar como atributo la lista de materiales
				MaterialDAO dao = MaterialDAO.getInstance();
				request.setAttribute("materiales", dao.getAll());

				// Guardar usuario en sesion
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(SESSION_EXPIRATION);
				session.setAttribute("usuario", usuario);

				ServletContext ctx = request.getServletContext();
				HashMap<Integer, String> nubeUsuarios = (HashMap<Integer, String>) ctx.getAttribute("nubeUsuarios");
				session.setAttribute("numUsuarios", nubeUsuarios);

				view = "backoffice/index.jsp";
				alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
			} else {

				view = "login.jsp";
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}

		} catch (Exception e) {
			e.printStackTrace();
			view = "login.jsp";
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

}
