/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneSistema.AreaScientifica;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisciAreaScientificaServlet
 * La servlet si occupa dell'inserimento di una area scientifica nel sistema
 */
@WebServlet("/InserisciAreaScientificaServlet")
public class InserisciAreaScientificaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneSistema gestioneSistemaManager; // VIENE USATA NEL DO
															// POST

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneSistemaManager = GestioneSistema.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("Nome");
		String codice = request.getParameter("Codice");
		String telefono = request.getParameter("Telefono");
		String fax = request.getParameter("Fax");
		String sito = request.getParameter("Sito");
		String email = request.getParameter("Email");
		AreaScientifica area = new AreaScientifica(1, codice, nome, telefono, fax, sito, email);

		boolean err = gestioneSistemaManager.inserisciAreaScientifica(area);
		String toUrl = request.getContextPath() + "/gestioneAteneo.jsp";;
		if (err) {
			request.getSession().setAttribute("Response",
					"Operazione inserimento Area Scientifica effettuta");
		}
		else {
			toUrl = request.getContextPath() + "/InserimentoAreaScientifica.jsp";;
			request.getSession().setAttribute("Error",
					"Operazione inserimento Area Scientifica non effettuata");
		}
		response.sendRedirect(toUrl);

	}

}
