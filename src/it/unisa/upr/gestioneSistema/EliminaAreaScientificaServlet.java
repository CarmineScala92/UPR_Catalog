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
 * Servlet implementation class EliminaAreaScientificaServlet
 * La servlet elimina un'area scientifica dal database
 */
@WebServlet("/EliminaAreaScientificaServlet")
public class EliminaAreaScientificaServlet extends HttpServlet {

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
		int ID_Area = Integer.parseInt(request.getParameter("ID_Area_Scientifica"));
		AreaScientifica area = new AreaScientifica();
		area.setID_Area_Scientifica(ID_Area);
		String toUrl = request.getContextPath() + "/gestioneAteneo.jsp";
		if (gestioneSistemaManager.eliminaAreaScientifica(area)) {
			request.getSession().setAttribute("Response",
					"Eliminazione effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/visualizzaAreaScientifica.jsp?id=" + ID_Area;
			request.getSession().setAttribute("Error",
					"Eliminazione non effettuata");
		}
		response.sendRedirect(toUrl);
	}

}
