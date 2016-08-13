/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisciAccountServlet
 * La servlet si occupa delle operazioni per inserire un nuovo account nel
 * sistema
 */
@WebServlet("/InserisciAccountServlet")
public class InserisciAccountServlet extends HttpServlet {

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

		String toUrl = request.getContextPath() + "/gestioneUtenti.jsp";
		boolean err = false;
		String username = request.getParameter("Username");
		String passwword = request.getParameter("Password");
		String tipologia = request.getParameter("Tipologia");

		if (tipologia.equalsIgnoreCase("Amministratore")) {

			String Nome = request.getParameter("Nome");
			String Cognome = request.getParameter("Cognome");

			Amministratore amm = new Amministratore(1, username, passwword, tipologia, 1, Nome, Cognome);
			err = gestioneSistemaManager.inserisciAccount(amm);
		}
		else if (tipologia.equalsIgnoreCase("Responsabile")) {

			String Nome = request.getParameter("Nome");
			String Cognome = request.getParameter("Cognome");

			Responsabile amm = new Responsabile(1, username, passwword, tipologia, 1, Nome, Cognome);
			err = gestioneSistemaManager.inserisciAccount(amm);
		}
		else if (tipologia.equalsIgnoreCase("Ricercatore")) {
			String nome = request.getParameter("Nome");
			String cognome = request.getParameter("Cognome");
			String codice_Fiscale = request.getParameter("Codice_Fiscale");
			String data_Nascita = request.getParameter("Data_Nascita");
			String citta_Nascita = request.getParameter("Citta_Nascita");
			String provincia_Nascita = request.getParameter("Provincia_Nascita");
			String matricola = request.getParameter("Matricola");
			String email = request.getParameter("Email");
			String data_Inizio_Servizio = request.getParameter("Data_Inizio_Servizio");
			int iD_Dipartimento = Integer.parseInt(request.getParameter("ID_Dipartimento"));
			int iD_Area_Scientifica = Integer.parseInt(request.getParameter("ID_Area_Scientifica"));
			String ruolo = request.getParameter("Ruolo");
			String sesso = request.getParameter("Sesso");

			Ricercatore r = new Ricercatore(1, username, passwword, tipologia, 1, nome, cognome, codice_Fiscale, data_Nascita, citta_Nascita, provincia_Nascita, matricola, email, data_Inizio_Servizio, iD_Dipartimento, iD_Area_Scientifica, ruolo, sesso);
			err = gestioneSistemaManager.inserisciAccount(r);
		}
		if (err) {
			request.getSession().setAttribute("Response",
					"Operazione inserimento Account effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/Inserisci_utente.jsp";
			request.getSession().setAttribute("Error",
					"Operazione inserimento Account non effettuata");
		}
		response.sendRedirect(toUrl);

	}

}
