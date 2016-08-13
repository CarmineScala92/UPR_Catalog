/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneAutenticazione.Account;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EliminaAccountServlet
 * La servlet è adibita all'eliminazione di un account dal database
 */
@WebServlet("/EliminaAccountServlet")
public class EliminaAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IFacadeGestioneSistema gestioneSistemaManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneSistemaManager = GestioneSistema.getInstance();
	}

	public EliminaAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int ID_Account = Integer.parseInt(request.getParameter("ID_Account"));
		Account acc = new Account();
		acc.setID_Account(ID_Account);
		String toUrl = request.getContextPath() + "/gestioneUtenti.jsp";
		if (gestioneSistemaManager.eliminaAccount(acc)) {
			request.getSession().setAttribute("Response",
					"Eliminazione Account effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/visualizzaUtente.jsp" + "?id=" + ID_Account;
			request.getSession().setAttribute("Error",
					"Eliminazione Account non effettuata");
		}
		response.sendRedirect(toUrl);

	}
}
