/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.SupportoValidazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RifiutaProdottiServlet
 * La servlet è adibita a rifiutare un prodotto e a riportarlo in stato bozza
 */
@WebServlet("/RifiutaProdottiServlet")
public class RifiutaProdottiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeSupportoValidazione gestioneValidazioneManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneValidazioneManager = SupportoValidazione.getInstance();
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
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/validazione_homepage.jsp");
		String stato = request.getParameter("Stato");
		String id_prodotto = request.getParameter("ID_Prodotto");
		String note = request.getParameter("Note");
		try {
			gestioneValidazioneManager.rifiutaProdotto(Integer.parseInt(id_prodotto),
					note);
		}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("err",
					"Non è stato possibile rifiutare il prodotto");
			rd.forward(request, response);
		}
		rd = request.getRequestDispatcher("/validazione_homepage.jsp");
		request.setAttribute("response", "Prodotto rifiutato");
		rd.forward(request, response);
	}

}
