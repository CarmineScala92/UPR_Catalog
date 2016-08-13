/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneProdotti.Prodotto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class VisualizzaProdottiAmministratoreServlet
 * La servlet restituisce un file XML contenente tutti i prodotti visualizzabili
 * dall'amministratore
 */
@WebServlet("/VisualizzaProdottiAmministratoreServlet")
public class VisualizzaProdottiAmministratoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneProdotti gestioneProdottiManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneProdottiManager = GestioneProdotti.getInstance();
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
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		// CONTROLLO SE E' LOGGATO L'AMMINISTRATORE
		Amministratore amministratore = (Amministratore) request.getSession().getAttribute("Amministratore");
		if (amministratore != null) {
			listaProdotti = gestioneProdottiManager.ricercaProdottiAmministratore();
			System.out.println("AMMINISTRATORE");
		}
		else {
			System.out.println("AMMINISTRATORE NON LOGGATO");
		}
		Document doc = XMLConverter.caricaListaProdottiHomepage(listaProdotti);
		XMLOutputter xml_out = new XMLOutputter();
		xml_out.setFormat(Format.getPrettyFormat());
		response.setContentType("text/xml");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		PrintWriter out = response.getWriter();
		// xml_out.output(doc, new FileOutputStream("prova.xml"));

		xml_out.output(doc, out);

	}
}
