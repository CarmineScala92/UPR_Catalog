/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class VisualizzaAccountServlet
 * La servlet ricerca i dettagli di un account e li restituisce in formato XML
 */
@WebServlet("/VisualizzaAccountServlet")
public class VisualizzaAccountServlet extends HttpServlet {

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

		int id = Integer.parseInt(request.getParameter("Id"));
		Account accountVisualizzare = null;
		Document doc = null;

		try {
			accountVisualizzare = gestioneSistemaManager.visualizzaAccount(id);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (accountVisualizzare != null) {
			// CONVERTO ACCOUNT IN XML
			Ricercatore ricercatore = null;
			if (accountVisualizzare instanceof Ricercatore) {
				ricercatore = (Ricercatore) accountVisualizzare;
				String nomeDip = null;
				try {
					nomeDip = gestioneSistemaManager.visualizzaDipartimento(ricercatore.getID_Dipartimento()).getNome();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String nomeArea = null;
				try {
					nomeArea = gestioneSistemaManager.visualizzaAreaScientifica(ricercatore.getID_Area_Scientifica()).getNome();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				doc = XMLConverter.caricaAccount(accountVisualizzare,
						nomeDip,
						nomeArea);
			}
			else
				doc = XMLConverter.caricaAccount(accountVisualizzare,
						null,
						null);

			// Risponde al client
			XMLOutputter xml_out = new XMLOutputter();
			xml_out.setFormat(Format.getPrettyFormat());
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			PrintWriter out = response.getWriter();

			// esporto su file il documento creato
			// xml_out.output(doc, new FileOutputStream("prova.xml"));
			xml_out.output(doc, out);
		}
		else {
			System.out.println("Prodotto ritornato null");
		}

	}

}
