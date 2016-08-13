/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
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
 * Servlet implementation class RicercaProdottoServlet
 * La servlet si occupa della ricerca dei prodotti all'interno del sistema
 */
@WebServlet("/RicercaProdottiServlet")
public class RicercaProdottiServlet extends HttpServlet {

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// System.out.println("VEDETE SE VI ESCE!");
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			String titolo = request.getParameter("Titolo");

			if (titolo == null)
				titolo = "";
			String anno_pub = request.getParameter("Anno_Pubblicazione");

			if (anno_pub == null)
				anno_pub = "";

			String tipologia = request.getParameter("Tipologia");
			if (tipologia == null)
				tipologia = "";

			String nome_dip = request.getParameter("Nome_Dipartimento");
			if (nome_dip == null)
				nome_dip = "";

			String nome_area = request.getParameter("Nome_Area_Scientifica");
			if (nome_area == null)
				nome_area = "";

			String nome_autore = request.getParameter("Nome");
			if (nome_autore == null)
				nome_autore = "";

			String cognome_autore = request.getParameter("Cognome");
			if (cognome_autore == null)
				cognome_autore = "";

			/* ---- Evitare problemi nelle query sql con ' e _% ----- */
			titolo = titolo.replaceAll("_", "");
			titolo = titolo.replaceAll("%", "");
			titolo = titolo.replaceAll("'", "''");
			anno_pub = anno_pub.replaceAll("_", "");
			anno_pub = anno_pub.replaceAll("%", "");
			anno_pub = anno_pub.replaceAll("'", "''");
			tipologia = tipologia.replaceAll("_", "");
			tipologia = tipologia.replaceAll("%", "");
			tipologia = tipologia.replaceAll("'", "''");
			nome_dip = nome_dip.replaceAll("_", "");
			nome_dip = nome_dip.replaceAll("%", "");
			nome_dip = nome_dip.replaceAll("'", "''");
			nome_area = nome_area.replaceAll("_", "");
			nome_area = nome_area.replaceAll("%", "");
			nome_area = nome_area.replaceAll("'", "''");
			nome_autore = nome_autore.replaceAll("_", "");
			nome_autore = nome_autore.replaceAll("%", "");
			nome_autore = nome_autore.replaceAll("'", "''");
			cognome_autore = cognome_autore.replaceAll("_", "");
			cognome_autore = cognome_autore.replaceAll("%", "");
			cognome_autore = cognome_autore.replaceAll("'", "''");
			/* ------------------------------------------------------ */

			Element xml_root = new Element("response");
			Element xml_Prodotto;

			Element xml_ID_Prodotto;
			Element xml_ID_Proprietario;
			Element xml_Titolo;
			Element xml_Autori;
			Element xml_AnnoPubblicazione;
			Element xml_Abstract;
			Element xml_Pubblico;
			Element xml_Stato;
			Element xml_Tipologia;
			Element xml_URL;
			Element xml_Note;

			// CONTROLLO SE è UN UTENTE OPPURE UTENTE ESTERNO
			Ricercatore ric = (Ricercatore) request.getSession().getAttribute("Ricercatore");
			Amministratore amministratore = (Amministratore) request.getSession().getAttribute("Amministratore");
			if (amministratore != null) {
				listaProdotti = gestioneProdottiManager.ricercaProdottoAmministratore(titolo,
						anno_pub,
						tipologia,
						nome_dip,
						nome_area,
						nome_autore,
						cognome_autore);
				System.out.println("AMMINISTRATORE");
			}
			else {
				if (ric != null) {
					listaProdotti = gestioneProdottiManager.ricercaProdotto(titolo,
							anno_pub,
							tipologia,
							nome_dip,
							nome_area,
							nome_autore,
							cognome_autore);
					System.out.println("UTENTE RICERCATORE");
				}
				else {
					listaProdotti = gestioneProdottiManager.ricercaProdottoUe(titolo,
							anno_pub,
							tipologia,
							nome_dip,
							nome_area,
							nome_autore,
							cognome_autore);
					System.out.println("UTENTE ESTERNO");
				}

			}

			for (Prodotto p : listaProdotti) {
				xml_Prodotto = new Element("Prodotto");

				xml_ID_Prodotto = new Element("ID_Prodotto");
				xml_ID_Proprietario = new Element("ID_Proprietario");
				xml_Titolo = new Element("Titolo");
				xml_Autori = new Element("Autori");
				xml_AnnoPubblicazione = new Element("Anno_Pubblicazione");
				xml_Abstract = new Element("Abstract");
				xml_Pubblico = new Element("Pubblico");
				xml_Stato = new Element("Stato");
				xml_Tipologia = new Element("Tipologia");
				xml_URL = new Element("URL");
				xml_Note = new Element("Note");

				xml_ID_Prodotto.setText("" + p.getID_Prodotto());
				xml_ID_Proprietario.setText("" + p.getID_Proprietario());
				xml_Titolo.setText(p.getTitolo());
				xml_Autori.setText(p.getAutori());
				xml_AnnoPubblicazione.setText(p.getAnno_Pubblicazione());
				xml_Abstract.setText(p.getAbstract());
				xml_Pubblico.setText("" + p.getPubblico());
				xml_Stato.setText(p.getStato());
				xml_Tipologia.setText(p.getTipologia());
				xml_URL.setText(p.getURL());
				xml_Note.setText(p.getNote());

				xml_Prodotto.addContent(xml_ID_Prodotto);
				xml_Prodotto.addContent(xml_ID_Proprietario);
				xml_Prodotto.addContent(xml_Titolo);
				xml_Prodotto.addContent(xml_Autori);
				xml_Prodotto.addContent(xml_AnnoPubblicazione);
				xml_Prodotto.addContent(xml_Abstract);
				xml_Prodotto.addContent(xml_Pubblico);
				xml_Prodotto.addContent(xml_Stato);
				xml_Prodotto.addContent(xml_Tipologia);
				xml_Prodotto.addContent(xml_URL);
				xml_Prodotto.addContent(xml_Note);

				xml_root.addContent(xml_Prodotto);
			}

			Document doc = new Document(xml_root);

			XMLOutputter xml_out = new XMLOutputter();
			xml_out.setFormat(Format.getPrettyFormat());
			response.setContentType("text/xml");
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			PrintWriter out = response.getWriter();

			xml_out.output(doc, new FileOutputStream("prova.xml"));

			xml_out.output(doc, out);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
