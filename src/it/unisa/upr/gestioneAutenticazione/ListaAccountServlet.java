/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneAutenticazione;

import it.unisa.upr.commons.XMLConverter;
import it.unisa.upr.data.gestioneAutenticazione.Account;

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
 * Servlet implementation class ListaUsernameServlet
 */
/**
 * Il compito della servlet è quello di recuperare gli account del sistema e
 * comunicarli in formato XML
 * 
 */
@WebServlet("/ListaAccountServlet")
public class ListaAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneAutenticazione gestioneAutenticazioneManager;

	public void init(ServletConfig config) {
		gestioneAutenticazioneManager = GestioneAutenticazione.getInstance();
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

		ArrayList<Account> listaAccount = gestioneAutenticazioneManager.getListaAccount();

		Document doc = XMLConverter.caricaListaAccount(listaAccount);
		XMLOutputter xml_out = new XMLOutputter();
		xml_out.setFormat(Format.getPrettyFormat());
		response.setContentType("text/xml");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		PrintWriter out = response.getWriter();
		xml_out.output(doc, out);

	}

}
