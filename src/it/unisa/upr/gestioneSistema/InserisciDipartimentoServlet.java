/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisciDipartimentoServlet
 * La classe si occupa dell'inserimento di un nuovo dipartimento nel sistema
 */
@WebServlet("/InserisciDipartimentoServlet")
public class InserisciDipartimentoServlet extends HttpServlet {

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
		String telefono = request.getParameter("Telefono");
		String fax = request.getParameter("Fax");
		String sito = request.getParameter("Sito");
		String email = request.getParameter("Email");
		Dipartimento dip = new Dipartimento(1, nome, telefono, fax, sito, email);

		boolean err = gestioneSistemaManager.inserisciDipartimento(dip);
		String toUrl = request.getContextPath() + "/gestioneAteneo.jsp";;
		if (err) {
			request.getSession().setAttribute("Response",
					"Operazione inserimento dipartimento effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/InserimentoDipartimento.jsp";;
			request.getSession().setAttribute("Error",
					"Operazione inserimento dipartimento non effettuato");
		}
		response.sendRedirect(toUrl);

	}

}
