/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * La servlet verifica se un prodotto è già presente all'interno del database
 */
@WebServlet("/VerificaEsistenzaProdottoServlet")
public class VerificaEsistenzaProdottoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneProdotti gestioneProdottiManager;

	public void init(ServletConfig config) {
		gestioneProdottiManager = GestioneProdotti.getInstance();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws IOException {
		Ricercatore ric = (Ricercatore) req.getSession().getAttribute("Ricercatore");
		Amministratore amm = (Amministratore) req.getSession().getAttribute("Amministratore");
		Document doc;
		if (ric != null || amm!=null) {
			String titolo = req.getParameter("Titolo");
			String anno = req.getParameter("Anno_Pubblicazione");
			String tipo = req.getParameter("Tipologia");

			if(titolo==null || anno==null || tipo==null){
				doc=createXML(4);
			}
			else {
				try {
					if (gestioneProdottiManager.exists(titolo, tipo, anno)) {
						doc = createXML(1);
					}
					else
						doc = createXML(0);
				}
				catch (SQLException e1) {
					doc = createXML(2);
				}
			}
		}
		else
			doc = createXML(3);

		XMLOutputter xml_out = new XMLOutputter();
		xml_out.setFormat(Format.getPrettyFormat());
		resp.setContentType("text/xml");
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		PrintWriter out = resp.getWriter();
		xml_out.output(doc, out);
	}

	private Document createXML(int response) {

		Element xml_root = new Element("root");
		Element xml_response = new Element("response");
		switch (response) {
			case 0:
				xml_response.setText("false");
				break;
			case 1:
				xml_response.setText("true");
				break;
			case 2:
				xml_response.setText("error");
				break;
			case 3:
				xml_response.setText("logout");
				break;
			case 4:
				xml_response.setText("dati_malformati");
				break;
		}
		xml_root.addContent(xml_response);
		Document doc = new Document(xml_root);
		return doc;
	}
}
