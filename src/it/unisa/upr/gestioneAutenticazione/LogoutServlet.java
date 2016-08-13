/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneAutenticazione;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestioneLogoutServlet
 */
/**
 * Il compito della servlet è quello di terminare la sessione di un utente
 * 
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneAutenticazione gestioneAutenticazioneManager;

	public void init(ServletConfig config) {
		gestioneAutenticazioneManager = GestioneAutenticazione.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		gestioneAutenticazioneManager.logout(request);
		System.out.println("Disconnessione effettuata");
		String toUrl = request.getContextPath() + "/index.jsp";
		response.sendRedirect(toUrl);
	}
}
