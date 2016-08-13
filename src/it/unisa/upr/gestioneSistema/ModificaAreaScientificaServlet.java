/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneSistema.AreaScientifica;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaAreaScientificaServlet
 * La servlet si occupa della modifica dei dettagli di un'area scientifica
 */
@WebServlet("/ModificaAreaScientificaServlet")
public class ModificaAreaScientificaServlet extends HttpServlet {

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

		int ID_Area = Integer.parseInt(request.getParameter("ID_Area_Scientifica"));
		String nome = request.getParameter("Nome");
		String telefono = request.getParameter("Telefono");
		String fax = request.getParameter("Fax");
		String email = request.getParameter("Email");
		String sito = request.getParameter("Sito");
		String codice = request.getParameter("Codice");

		String toUrl = request.getContextPath() + "/visualizzaAreaScientifica.jsp" + "?id=" + ID_Area;
		AreaScientifica area = new AreaScientifica();
		area.setID_Area_Scientifica(ID_Area);
		try {
			if (gestioneSistemaManager.modificaAreaScientifica(ID_Area,
					codice,
					nome,
					telefono,
					fax,
					sito,
					email)) {

				request.getSession().setAttribute("Response",
						"Modifica dell'Area Scientifica effettuata");
			}
			else {
				toUrl = request.getContextPath() + "/modifica_Area_Scientifica.jsp" + "?id=" + ID_Area;
				request.getSession().setAttribute("Error",
						"Errore - Modifica dell'Area Scientifica non effettuata");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(toUrl);
	}

}
