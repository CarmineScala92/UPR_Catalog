/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneAutenticazione;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GestioneLoginServlet
 */
/**
 * Il compito della servlet è l'autenticazione di un utente nel sistema
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		String ricordami = request.getParameter("Ricordami");
		Object account = null;

		/* stub per test */
		if (Username.equalsIgnoreCase("a") && Password.equalsIgnoreCase("a")) {
			Username = "mario";
			Password = "mario.rossi";
		}
		/*------------------------------------*/

		// restituisci un oggetto di tipo Ricercatore, Amministratore o
		// Responsabile dal DB
		try {
			account = gestioneAutenticazioneManager.login(Username, Password);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (account == null) {
			request.getSession().setAttribute("error",
					"Le credenziali di accesso non sono corrette.");
			String toUrl = request.getContextPath() + "/index.jsp";
			System.out.println("Account non trovato");
			response.sendRedirect(toUrl);
		}
		else if (account instanceof Ricercatore) {
			Ricercatore ric = (Ricercatore) account;
			request.getSession().setAttribute("success",
					"Credenziali di accesso valide.");
			request.getSession().setAttribute("Ricercatore", ric);
			int id = ric.getID_Ricercatore();
			if (ricordami != null && ricordami.compareToIgnoreCase("yes") == 0) {
				System.out.println("Ricordami");
				// request.getSession().setMaxInactiveInterval(-1); //La
				// sessione resta attiva un giorno
			}
			request.getSession().setAttribute("ID_Ricercatore", id);
			System.out.println("Accesso effettuato da: " + ric.getNome() + " " + ric.getCognome());
			String toUrl = request.getContextPath() + "/ric_homepage.jsp";
			request.getSession().setMaxInactiveInterval(1 * 60 * 60); // DURA 1
																		// ORA
																		// LA
																		// SESSIONE
			response.sendRedirect(toUrl);
		}
		else if (account instanceof Responsabile) {
			Responsabile res = (Responsabile) account;
			request.getSession().setAttribute("success",
					"Credenziali di accesso valide.");
			request.getSession().setAttribute("Responsabile", res);
			if (ricordami != null && ricordami.compareToIgnoreCase("yes") == 0) {
				System.out.println("Ricordami");
				// request.getSession().setMaxInactiveInterval(-1); //La
				// sessione resta attiva un giorno
			}
			request.getSession().setAttribute("ID_Responsabile",
					res.getID_Responsabile());
			System.out.println("Accesso effettuato da: " + res.getNome() + " " + res.getCognome());
			String toUrl = request.getContextPath() + "/res_homepage.jsp";
			request.getSession().setMaxInactiveInterval(1 * 60 * 60); // DURA 1
																		// ORA
																		// LA
																		// SESSIONE
			response.sendRedirect(toUrl);
		}
		else if (account instanceof Amministratore) {
			Amministratore amm = (Amministratore) account;
			request.getSession().setAttribute("success",
					"Credenziali di accesso valide.");
			request.getSession().setAttribute("Amministratore", amm);
			if (ricordami != null && ricordami.compareToIgnoreCase("yes") == 0) {
				System.out.println("Ricordami");
				// request.getSession().setMaxInactiveInterval(60*60*24); //La
				// sessione resta attiva un giorno
			}
			request.getSession().setAttribute("ID_Amministratore",
					amm.getID_Amministratore());
			System.out.println("Accesso effettuato da: " + amm.getNome() + " " + amm.getCognome());
			String toUrl = request.getContextPath() + "/gestioneUtenti.jsp";
			request.getSession().setMaxInactiveInterval(1 * 60 * 60); // DURA 1
																		// ORA
																		// LA
																		// SESSIONE
			response.sendRedirect(toUrl);
		}

	}

}
