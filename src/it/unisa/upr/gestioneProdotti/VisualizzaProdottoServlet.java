/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneProdotti.Prodotto;

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
 * Servlet implementation class VisualizzaProdottoServlet
 * La servlet è adibita a trovare i dettagli di un prodotto. Il risultato è
 * visualizzato in un file XML
 */
@WebServlet("/VisualizzaProdottoServlet")
public class VisualizzaProdottoServlet extends HttpServlet {

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
		int id = Integer.parseInt(request.getParameter("Id"));
		Prodotto prodottoVisualizzare = null;
		Document doc = null;
		try {

			prodottoVisualizzare = gestioneProdottiManager.visualizzaProdotto(id);
			if (prodottoVisualizzare != null) {

				if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Curatela") == 0) {
					// converto la Curatela in Document xml
					doc = XMLConverter.caricaProdottoCuratela(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Brevetto") == 0) {
					// converto la Brevetto in Document xml
					doc = XMLConverter.caricaProdottoBrevetto(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Articolo Libro") == 0) {
					// converto la lista p in Document xml
					doc = XMLConverter.caricaProdottoArticoloLibro(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Articolo Rivista") == 0) {
					// converto la lista prodotti in Document xml
					doc = XMLConverter.caricaProdottoArticoloRivista(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Altro") == 0) {
					// converto la lista prodotti in Document xml
					doc = XMLConverter.caricaProdottoAltro(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else if ((prodottoVisualizzare.getTipologia()).compareToIgnoreCase("Monografia") == 0) {
					// converto la lista prodotti in Document xml
					doc = XMLConverter.caricaProdottoMonografia(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}
				else { // +++++++++++++++++++++++++++ PROCEEDING
						// converto la lista prodotti in Document xml
					doc = XMLConverter.caricaProdottoProceeding(prodottoVisualizzare,
							gestioneProdottiManager.getProduzioni(prodottoVisualizzare),
							gestioneProdottiManager.getRicercatoriProduzione(prodottoVisualizzare));
				}

				// Risponde al client
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
			else {
				System.out.println("Prodotto ritornato null");
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
