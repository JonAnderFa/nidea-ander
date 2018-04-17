package com.ipartek.formacion.nidea.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class
 * UsuariosConectadosListener
 *
 */
@WebListener
public class UsuariosConectadosListener implements HttpSessionListener, HttpSessionAttributeListener {
	private String comprobar = "uPublic";

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// Comprobar que sea atributo .equals uPublic
		if (se.getSession().getAttribute("uPublic") != null) {
			Usuario user = (Usuario) se.getSession().getAttribute("uPublic");
			ServletContext ctx = se.getSession().getServletContext();
			HashMap<Integer, String> nubeUsuarios = null;
			nubeUsuarios = (HashMap<Integer, String>) ctx.getAttribute("nubeUsuarios");

			nubeUsuarios.remove(user.getId());

			// guardar hasmap en contexto Servlets
			ctx.setAttribute("nubeUsuarios", nubeUsuarios);
		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {

		// Comprobar que sea atributo .equals uPublic
		String nombre = event.getName();

		if (comprobar.equals(nombre)) {
			ServletContext ctx = event.getSession().getServletContext();
			HashMap<Integer, String> nubeUsuarios = null;

			// Recogemos el parametro Usuario
			Usuario user = (Usuario) event.getValue();

			// Si no existe lo creo y si no lo cargo de en el atributo local
			if (ctx.getAttribute("nubeUsuarios") == null) {
				nubeUsuarios = new HashMap<Integer, String>();
			} else {
				nubeUsuarios = (HashMap<Integer, String>) ctx.getAttribute("nubeUsuarios");
			}
			// guardar usuario
			nubeUsuarios.put(user.getId(), user.getNombre());
			// guardar hasmap en contexto Servlets
			ctx.setAttribute("nubeUsuarios", nubeUsuarios);

		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {

	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

}
