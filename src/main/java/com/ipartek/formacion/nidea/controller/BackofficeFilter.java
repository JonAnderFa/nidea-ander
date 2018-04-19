package com.ipartek.formacion.nidea.controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet Filter implementation class BackofficeFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, description = "Deja pasar solo a los usuarios logueados", urlPatterns = {
				"/backoffice/*" })
public class BackofficeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public BackofficeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (null != usuario && usuario.getRol().getIdRol() == Usuario.ROL_ADMIN) {
			chain.doFilter(req, res);
		} else {
			informacionPeticion(req);
			res.sendRedirect(req.getContextPath() + "/login");
		}

	}

	/**
	 * Mostramos por pantalla toda la informacion del usuario == Req
	 * 
	 * @param req
	 */
	private void informacionPeticion(HttpServletRequest req) {
		System.out.println("***************** ACCESO NO PERMITIDO *********************");
		System.out.println("IP= " + req.getLocalAddr());
		System.out.println("PORT= " + req.getRemotePort());
		System.out.println("URI= " + req.getRequestURI());
		System.out.println("URL= " + req.getRequestURL());
		System.out.println("**** Encabezados ****");
		System.out.println("navegador" + req.getHeader("user-agent"));
		System.out.println("***********************************************************");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init");
	}

}
