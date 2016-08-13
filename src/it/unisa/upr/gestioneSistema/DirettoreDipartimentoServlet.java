/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class DirettoreDipartimentoServlet
 * La servlet ricerca il direttore associato ad un dipartimento
 */
@WebServlet("/DirettoreDipartimentoServlet")
public class DirettoreDipartimentoServlet extends HttpServlet {

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
		int id_Dip = Integer.parseInt(request.getParameter("ID_Dipartimento"));
		Dipartimento dip = new Dipartimento();
		dip.setID_Dipartimento(id_Dip);
		Ricercatore direttoreDipartimento = null;

		direttoreDipartimento = gestioneSistemaManager.getDirettoreDipartimento(dip);

		// CONVERTO ACCOUNT IN XML
		Document doc = XMLConverter.caricaDirettoreDipartimento(direttoreDipartimento);
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

}
