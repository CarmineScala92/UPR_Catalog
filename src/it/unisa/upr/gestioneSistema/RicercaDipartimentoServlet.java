/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.DBDipartimento;
import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * Servlet implementation class RicercaDipartimentoServlet
 * La servlet è adibita alla ricerca di dipartimenti nel database
 */
@WebServlet("/RicercaDipartimentoServlet")
public class RicercaDipartimentoServlet extends HttpServlet {

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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Dipartimento> lista = new ArrayList<Dipartimento>();
		Document doc;
		String nome = request.getParameter("Nome");
		if (nome == null)
			nome = "";
		try {
			lista = gestioneSistemaManager.ricercaDipartimento(nome);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if (!lista.isEmpty()) {
			doc = XMLConverter.caricaDipartimenti(lista);

			XMLOutputter xml_out = new XMLOutputter();
			xml_out.setFormat(Format.getPrettyFormat());
			response.setContentType("text/xml");
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			PrintWriter out = response.getWriter();

			xml_out.output(doc, new FileOutputStream("prova.xml"));

			xml_out.output(doc, out);
		}
		else
			System.out.println("La lista è vuota");
	}
}
