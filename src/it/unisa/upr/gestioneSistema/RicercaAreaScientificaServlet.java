/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;

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
 * Servlet implementation class RicercaAreaScientificaServlet
 * La servlet è adibita alla ricerca di aree scientifiche nel sistema
 */
@WebServlet("/RicercaAreaScientificaServlet")
public class RicercaAreaScientificaServlet extends HttpServlet {

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
		ArrayList<AreaScientifica> lista = new ArrayList<AreaScientifica>();
		Document doc;
		String nome = request.getParameter("Nome");
		if (nome == null)
			nome = "";
		try {
			lista = gestioneSistemaManager.ricercaAreaScientifica(nome);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		if (!lista.isEmpty()) {
			doc = XMLConverter.caricaAreeScientifiche(lista);

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
