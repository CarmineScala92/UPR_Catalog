/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.SupportoValidazione;

import it.unisa.upr.gestioneProdotti.IFacadeGestioneProdotti;

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
 * Servlet implementation class AccettaProdottiServlet
 * La servlet è adibita all'accettazione dei prodotti
 */
@WebServlet("/AccettaProdottiServlet")
public class AccettaProdottiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeSupportoValidazione gestioneValidazioneManager;
	private IFacadeGestioneProdotti gestioneProdottiManager;

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
		String[] listaID = request.getParameterValues("ID_Prodotto");
		String stato = request.getParameter("Stato");
		String Note = request.getParameter("Note");
		System.out.println(stato);
		int i;
		if (stato.equals("accettato")) {
			for (i = 0; i < listaID.length; i++) {
				// Prodotto p =
				// gestioneProdottiManager.visualizzaProdotto(Integer.parseInt(listaID[i]));
				Integer id_prodotto = Integer.parseInt(listaID[i]);
				try {
					gestioneValidazioneManager.accettaProdotto(id_prodotto,
							Note);
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					rd = request.getRequestDispatcher("/validazione_homepage.jsp");
					request.setAttribute("err",
							"Accettazione della selezione non riuscita, provare in seguito");
					rd.forward(request, response);
				}
			}
		}

		rd = request.getRequestDispatcher("/validazione_homepage.jsp");
		request.setAttribute("response", "Selezione Accettata");
		rd.forward(request, response);
		rd = request.getRequestDispatcher("/CaricaListaProdottiCompletiServlet");

	}

}
