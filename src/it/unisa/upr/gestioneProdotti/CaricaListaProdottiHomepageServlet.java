/*
 * �2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Prodotto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class CaricamentoProdottiHomepageServlet
 * viene chiamata quando l'homepage di un account deve caricare i prodotti
 * dell'utente
 */
@WebServlet("/CaricaListaProdottiHomepageServlet")
public class CaricaListaProdottiHomepageServlet extends HttpServlet {

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

		Ricercatore ric = (Ricercatore) request.getSession().getAttribute("Ricercatore");

		/* ----------- stub ----------------- */
		// ric=new Ricercatore();
		// ric.setID_Account(2);
		/*-----------------------------------*/

		ArrayList<Prodotto> listaProdotti = gestioneProdottiManager.caricaListaProdottiHomepage(ric);

		// converto la lista prodotti in Document xml
		Document doc = XMLConverter.caricaListaProdottiHomepage(listaProdotti);

		// risponde al client
		XMLOutputter xml_out = new XMLOutputter();
		xml_out.setFormat(Format.getPrettyFormat());
		response.setContentType("text/xml");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		PrintWriter out = response.getWriter();

		// esporto su file il documento creato
		// xml_out.output(doc, new FileOutputStream("prova.xml"));
		xml_out.output(doc, out);

	}

}
