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
 * Servlet implementation class EliminaDipartimentoServlet
 * La servlet elimina un dipartimento dal database
 */
@WebServlet("/EliminaDipartimentoServlet")
public class EliminaDipartimentoServlet extends HttpServlet {

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
		String toUrl = request.getContextPath() + "/gestioneAteneo.jsp";
		int ID_Dipartimento = Integer.parseInt(request.getParameter("ID_Dipartimento"));
		Dipartimento dip = new Dipartimento();
		dip.setID_Dipartimento(ID_Dipartimento);

		if (gestioneSistemaManager.eliminaDipartimento(dip)) {
			request.getSession().setAttribute("Response",
					"Eliminazione effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/visualizza_dipartimento.jsp?id=" + ID_Dipartimento;
			request.getSession().setAttribute("Error",
					"Eliminazione non effettuata");
		}
		response.sendRedirect(toUrl);
	}

}
