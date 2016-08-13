/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.Curatela;

import it.unisa.upr.data.gestioneProdotti.Monografia;
import it.unisa.upr.data.gestioneProdotti.Proceeding;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Il ruolo della servlet è quello di eliminare un prodotto dal sistema
 */
@WebServlet("/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneProdotti gestioneProdottiManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneProdottiManager = GestioneProdotti.getInstance();
	}

	public EliminaProdottoServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		int ID_Prodotto = Integer.parseInt(request.getParameter("ID_Prodotto"));
		String Tipologia = request.getParameter("Tipologia");
		RequestDispatcher rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
		// System.out.println(ID_Prodotto+" "+Tipologia);

		if (Tipologia.equalsIgnoreCase("Monografia")) {
			Monografia prod = new Monografia();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);

			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Proceeding")) {
			Proceeding prod = new Proceeding();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Curatela")) {
			Curatela prod = new Curatela();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Altro")) {
			Altro prod = new Altro();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Articolo Libro")) {
			Articolo_Libro prod = new Articolo_Libro();
			prod.setID_Prodotto(ID_Prodotto);
			prod.setTipologia(Tipologia);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Articolo Rivista")) {
			Articolo_Rivista prod = new Articolo_Rivista();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}
		}
		else if (Tipologia.equalsIgnoreCase("Brevetto")) { // BREVETTO
			Brevetto prod = new Brevetto();
			prod.setTipologia(Tipologia);
			prod.setID_Prodotto(ID_Prodotto);
			if (gestioneProdottiManager.eliminaProdotto(prod)) {
				rd = request.getRequestDispatcher("/ric_homepage.jsp");
				request.setAttribute("Info", "eliminazione effettuata");
			}
			else {
				rd = request.getRequestDispatcher("/visualizza_prodotto.jsp?id=" + ID_Prodotto);
				request.setAttribute("error", "eliminazione non riuscita");
			}

		}
		else {
			System.out.println("Tipologia non trovata");
		}
		rd.forward(request, response);
	}
}
