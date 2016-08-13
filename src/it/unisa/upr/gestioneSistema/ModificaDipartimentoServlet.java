/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaDipartimentoServlet
 * La servlet si occupa della modifica dei dettagli di un dipartimento
 */
@WebServlet("/ModificaDipartimentoServlet")
public class ModificaDipartimentoServlet extends HttpServlet {

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
		// TODO Auto-generated method stub

		int ID_Dip = Integer.parseInt(request.getParameter("ID_Dipartimento"));
		String nome = request.getParameter("Nome");
		String telefono = request.getParameter("Telefono");
		String fax = request.getParameter("Fax");
		String email = request.getParameter("Email");
		String sito = request.getParameter("Sito");

		String toUrl = request.getContextPath() + "/visualizza_dipartimento.jsp" + "?id=" + ID_Dip;
		Dipartimento dip = new Dipartimento();
		dip.setID_Dipartimento(ID_Dip);
		try {
			if (gestioneSistemaManager.modificaDipartimento(ID_Dip,
					nome,
					telefono,
					fax,
					sito,
					email)) {
				request.getSession().setAttribute("Response",
						"Modifica del Dipartimento effettuata");
			}
			else {
				toUrl = request.getContextPath() + "/Modifica_Dipartimento.jsp" + "?id=" + ID_Dip;
				request.getSession().setAttribute("Errore",
						"Errore - Modifica del Dipartimento non effettuata");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(toUrl);
	}

}
