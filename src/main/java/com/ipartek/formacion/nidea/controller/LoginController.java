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
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final int ADMIN = 1;
	private static final int USUARIO = 2;
	private static final String BACKOFFICE = "backoffice/index.jsp";
	private static final String FRONTOFFICE = "index.jsp";
	private static final String LOGIN = "login.jsp";

	private UsuarioDAO u;
	private MaterialDAO dao;

	private static final int SESSION_EXPIRATION = 60 * 20;// 20min de sesion que tarda en expirar

	@Override
	public void init() throws ServletException {
		u = UsuarioDAO.getInstance();
		// Enviar como atributo la lista de materiales
		dao = MaterialDAO.getInstance();
		super.init();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(LOGIN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String usuario = request.getParameter("usuario");
			String pas = request.getParameter("password");
			Usuario usu = u.buscar(usuario, pas);

			if (usu != null) {

				request.setAttribute("materiales", dao.getAll());

				// Guardar usuario en sesion
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(SESSION_EXPIRATION);
				session.setAttribute("usuario", usu);

				if (usu.getRol().getIdRol() == 1) {
					request.setAttribute("listUsuarios", u.getAll());
					ServletContext ctx = request.getServletContext();
					HashMap<Integer, String> nubeUsuarios = (HashMap<Integer, String>) ctx.getAttribute("nubeUsuarios");
					session.setAttribute("nubeUsuarios", nubeUsuarios);

					view = BACKOFFICE;
					alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
				} else if (usu.getRol().getIdRol() == 2) {

					view = FRONTOFFICE;
					alert = new Alert("Bienvenido al Front: " + usu.getNombre(), alert.TIPO_PRIMARY);
				}
			} else {

				view = LOGIN;
				alert = new Alert("Credenciales incorrectas, prueba de nuevo", alert.TIPO_DANGER);
			}

		} catch (Exception e) {
			e.printStackTrace();
			view = LOGIN;
			alert = new Alert();

		} finally {
			request.setAttribute("alert", alert);
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

}
