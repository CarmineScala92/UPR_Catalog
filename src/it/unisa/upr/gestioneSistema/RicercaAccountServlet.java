/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Account;

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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class RicercaAccountServlet
 * La servlet è adibita alla ricerca degli account del sistema
 */
@WebServlet("/RicercaAccountServlet")
public class RicercaAccountServlet extends HttpServlet {

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
		ArrayList<Account> lista = new ArrayList<Account>();
		Document doc;

		String nome = request.getParameter("Nome");
		if (nome == null)
			nome = "";

		String cognome = request.getParameter("Cognome");
		if (cognome == null)
			cognome = "";

		lista = gestioneSistemaManager.ricercaAccount(nome, cognome);

		if (!lista.isEmpty()) {
			System.out.println("Lista non vuota");
			if (request.getSession().getAttribute("Amministratore") != null) {
				System.out.println("E' LOGGATO UN AMMINISTRATORE");
				Account amm = (Account) request.getSession().getAttribute("Amministratore");
				ArrayList<Account> nuovoAccount = new ArrayList<Account>();
				for (Account a : lista) {
					if (!a.getUsername().equalsIgnoreCase(amm.getUsername())) {
						nuovoAccount.add(a);
					}
				}
				lista = nuovoAccount;
			}

			doc = XMLConverter.caricaAccount(lista);

			XMLOutputter xml_out = new XMLOutputter();
			xml_out.setFormat(Format.getPrettyFormat());
			response.setContentType("text/xml");
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			PrintWriter out = response.getWriter();

			xml_out.output(doc, new FileOutputStream("prova.xml"));

			xml_out.output(doc, out);
		}
	}
}
