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
 * Servlet implementation class ModificaAccountServlet
 * La classe si occupa della modifica di un account del sistema
 */
@WebServlet("/ModificaAccountServlet")
public class ModificaAccountServlet extends HttpServlet {

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

		boolean err = false;
		int ID_Account = Integer.parseInt(request.getParameter("ID_Account"));
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		String Tipologia = request.getParameter("Tipologia");

		String toUrl = request.getContextPath() + "/visualizzaUtente.jsp" + "?id=" + ID_Account;
		if (Tipologia.equalsIgnoreCase("Amministratore")) {

			String Nome = request.getParameter("Nome");
			String Cognome = request.getParameter("Cognome");

			Amministratore amm = new Amministratore(ID_Account, Username, Password, Tipologia, ID_Account, Nome, Cognome);
			err = gestioneSistemaManager.modificaAccount(amm);
		}
		else if (Tipologia.equalsIgnoreCase("Responsabile")) {

			String Nome = request.getParameter("Nome");
			String Cognome = request.getParameter("Cognome");

			Responsabile resp = new Responsabile(ID_Account, Username, Password, Tipologia, ID_Account, Nome, Cognome);
			err = gestioneSistemaManager.modificaAccount(resp);
		}
		else if (Tipologia.equalsIgnoreCase("Ricercatore")) {
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

			Ricercatore r = new Ricercatore(ID_Account, Username, Password, Tipologia, ID_Account, nome, cognome, codice_Fiscale, data_Nascita, citta_Nascita, provincia_Nascita, matricola, email, data_Inizio_Servizio, iD_Dipartimento, iD_Area_Scientifica, ruolo, sesso);
			err = gestioneSistemaManager.modificaAccount(r);
		}

		if (err) { // SE ' ANDATA A BUON FINE
			request.getSession().setAttribute("Response",
					"Operazione modifica Account effettuata");
		}
		else {
			toUrl = request.getContextPath() + "/modifica_account.jsp" + "?id=" + ID_Account;
			request.getSession().setAttribute("Error",
					"Operazione modifica Account non effettuata");
		}
		response.sendRedirect(toUrl);

	}

}
