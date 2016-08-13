/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.Curatela;
import it.unisa.upr.data.gestioneProdotti.Monografia;
import it.unisa.upr.data.gestioneProdotti.Proceeding;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaProdottoServlet
 * La servlet si occupa della modifica di un prodotto
 */
@WebServlet("/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneProdotti gestioneProdottiManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneProdottiManager = GestioneProdotti.getInstance();
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
		Ricercatore ric = (Ricercatore) request.getSession().getAttribute("Ricercatore");
		Amministratore amm = (Amministratore) request.getSession().getAttribute("Amministratore");

		if (ric == null && amm == null) {
			response.sendRedirect("./index.jsp");
			return;
		}

		boolean isOK = false;

		/*
		 * Enumeration<String> params=request.getParameterNames();
		 * while(params.hasMoreElements()){
		 * System.out.println(params.nextElement()); }
		 */
		int ID_Proprietario=0;
		if(ric!=null)
			ID_Proprietario=ric.getID_Account();
		else if(amm!=null){
			try {
				ID_Proprietario=Integer.parseInt(request.getParameter("ID_Proprietario"));
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				RequestDispatcher rd=request.getRequestDispatcher("./index.jsp");
				rd.forward(request, response);
				return;
			}
		}

		System.out.println("ID_Proprietario: " + ID_Proprietario);

		int[] id_proprietari = null;
		if (request.getParameterValues("ID_Ricercatore") != null) {
			String[] id_ricercatori = new String[request.getParameterValues("ID_Ricercatore").length + 1];
			System.arraycopy(request.getParameterValues("ID_Ricercatore"),
					0,
					id_ricercatori,
					0,
					id_ricercatori.length - 1);
			id_proprietari = new int[id_ricercatori.length + 1];
			for (int i = 0; i < id_ricercatori.length - 1; i++) {
				// System.out.println(id_ricercatori[i]);
				id_proprietari[i] = Integer.parseInt(id_ricercatori[i]);
			}
			id_proprietari[id_proprietari.length - 1] = ID_Proprietario;
		}
		else {
			// System.out.println("Non sono stati inseriti altri ricercatori autori");
			id_proprietari = new int[1];
			id_proprietari[0] = ID_Proprietario;
		}

		// NON POSSIAMO EDITARE LA TIPOLOGIA NEL MODIFICA
		// POSSIAMO MODIFICARE IN ALCUNI CASI LO STATO

		String Autori = request.getParameter("Autori") == null ? "" : request.getParameter("Autori");
		String Abstract = request.getParameter("Abstract") == null ? "" : request.getParameter("Abstract");
		int Pubblico = Integer.parseInt(request.getParameter("Pubblico"));
		String Stato = request.getParameter("Stato");
		String Tipologia = request.getParameter("Tipologia");
		String Id_Prodotto = request.getParameter("ID_Prodotto");

		System.out.println("Tipologia:" + Tipologia);
		if (Tipologia.equalsIgnoreCase("Articolo Libro")) {
			String Nome_Volume = request.getParameter("Nome_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore");
			String ISBN = request.getParameter("ISBN");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords");

			Articolo_Libro prodottoGenerico = new Articolo_Libro();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var8 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Volume",
					Nome_Volume);
			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori_Volume",
					Autori_Volume);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Editore",
					Editore);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Citta_Editore",
					Citta_Editore);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Paese_Editore",
					Paese_Editore);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"ISBN",
					ISBN);
			boolean var15 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_Riferimento);
			boolean var16 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var17 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);

			boolean var18 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var8 && var9 && var10 && var11 && var12 && var13 && var14 && var15 && var16 && var17 && var18)
				isOK = true;
			else
				isOK = false;

		}
		else if (Tipologia.equalsIgnoreCase("Articolo Rivista")) {

			String Nome_Rivista = request.getParameter("Nome_Rivista");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Pagine_riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords");

			Articolo_Rivista prodottoGenerico = new Articolo_Rivista();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var6 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Tipologia",
					Tipologia);
			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Rivista",
					Nome_Rivista);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Numero_Volume",
					Numero_Volume);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_riferimento);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);

			boolean var15 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var6 && var9 && var10 && var11 && var12 && var13 && var14 && var15)
				isOK = true;
			else
				isOK = false;

		}
		else if (Tipologia.equalsIgnoreCase("Brevetto")) {
			String Proprieta = request.getParameter("Proprieta");
			String Numero_Brevetto = request.getParameter("Numero_Brevetto");
			String DOI = request.getParameter("DOI");
			String Tipo = request.getParameter("Tipo");
			String Keywords = request.getParameter("Keywords");

			Brevetto prodottoGenerico = new Brevetto();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var6 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Tipologia",
					Tipologia);
			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Proprieta",
					Proprieta);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Numero_Brevetto",
					Numero_Brevetto);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Tipo",
					Tipo);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);

			boolean var15 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);
			System.out.println("var :" + var2 + "\n" + "var :" + var4 + "\n" + "var :" + var5 + "\n" + "var :" + var6 + "\n" + "var :" + var9 + "\n" + "var :" + var10 + "\n" + "var :" + var11 + "\n" + "var :" + var12 + "\n" + "var :" + var13 + "\n" + "var :" + var14 + "\n" + "var :" + var15 + "\n");
			if (var2 && var4 && var5 && var6 && var9 && var10 && var11 && var12 && var13 && var14 && var15)
				isOK = true;
			else
				isOK = false;

		}
		else if (Tipologia.equalsIgnoreCase("Curatela")) {

			String Nome_Rivista = request.getParameter("Nome_Rivista");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords");

			Curatela prodottoGenerico = new Curatela();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var8 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Rivista",
					Nome_Rivista);
			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Numero_Volume",
					Numero_Volume);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori_Volume",
					Autori_Volume);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Editore",
					Editore);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Citta_Editore",
					Citta_Editore);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Paese_Editore",
					Paese_Editore);
			boolean var15 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_Riferimento);
			boolean var16 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"ISBN",
					ISBN);
			boolean var17 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var18 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);

			boolean var19 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var8 && var9 && var10 && var11 && var12 && var13 && var14 && var15 && var16 && var17 && var18 && var19)
				isOK = true;
			else
				isOK = false;

		}
		else if (Tipologia.equalsIgnoreCase("Monografia")) {

			String Editore = request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords");

			Monografia prodottoGenerico = new Monografia();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var8 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Editore",
					Editore);
			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Citta_Editore",
					Citta_Editore);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Paese_Editore",
					Paese_Editore);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_Riferimento);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"ISBN",
					ISBN);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var15 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);

			boolean var16 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var8 && var9 && var10 && var11 && var12 && var13 && var14 && var15 && var16)
				isOK = true;
			else
				isOK = false;
		}
		else if (Tipologia.equalsIgnoreCase("Proceeding")) {
			String Nome_Volume = request.getParameter("Nome_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore");
			String ISBN = request.getParameter("ISBN");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords");
			String Relazione = request.getParameter("Relazione");
			String Nome_Congresso = request.getParameter("Nome_Congresso");
			String data_s = request.getParameter("Data_Congresso");
			Date Data_Congresso = null;
			if (data_s != null && !data_s.isEmpty() && data_s.contains("/")) {
				System.out.println("Sono nell'if della data");
				String[] data_string = data_s.split("/");
				System.out.println("Lunghezza split:" + data_string.length);
				if (data_string.length == 3) {
					try {
						GregorianCalendar data_gc = new GregorianCalendar(Integer.parseInt(data_string[2]), Integer.parseInt(data_string[1]) - 1, Integer.parseInt(data_string[0]));
						Data_Congresso = new Date(data_gc.getTimeInMillis());
					}
					catch (NumberFormatException e) {
						Data_Congresso = new Date(0);
					}
				}
				else
					Data_Congresso = new Date(0);
			}
			else
				Data_Congresso = new Date(0);
			// Date Data_Congresso =
			// Date.valueOf((request.getParameter("Data_Congresso")));
			String Luogo_Congresso = request.getParameter("Luogo_Congresso");
			String Rilevanza = request.getParameter("Rilevanza");

			Proceeding prodottoGenerico = new Proceeding();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var8 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Volume",
					Nome_Volume);
			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori_Volume",
					Autori_Volume);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Editore",
					Editore);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Citta_Editore",
					Citta_Editore);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Paese_Editore",
					Paese_Editore);
			boolean var14 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"ISBN",
					ISBN);
			boolean var15 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_Riferimento);
			boolean var16 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"DOI",
					DOI);
			boolean var17 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);
			boolean var18 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Relazione",
					Relazione);
			boolean var20 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Congresso",
					Nome_Congresso);
			String data = (Data_Congresso.toString());
			boolean var21 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Data_Congresso",
					data);
			boolean var22 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Luogo_Congresso",
					Luogo_Congresso);
			boolean var23 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Rilevanza",
					Rilevanza);
			boolean var24 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var8 && var9 && var10 && var11 && var12 && var13 && var14 && var15 && var16 && var17 && var18 && var20 && var21 && var22 && var23 && var24)
				isOK = true;
			else
				isOK = false;

		}
		else if (Tipologia.equalsIgnoreCase("Altro")) {
			String Nome_Volume = request.getParameter("Nome_Volume");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String Keywords = request.getParameter("Keywords");

			Altro prodottoGenerico = new Altro();
			prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));

			boolean var2 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Autori",
					Autori);
			boolean var4 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Abstract",
					Abstract);
			boolean var5 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pubblico",
					Integer.toString(Pubblico));
			boolean var8 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Stato",
					Stato);

			boolean var9 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Nome_Volume",
					Nome_Volume);
			boolean var10 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Numero_Volume",
					Numero_Volume);
			boolean var11 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Pagine_Riferimento",
					Pagine_Riferimento);
			boolean var12 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"ISBN",
					ISBN);
			boolean var13 = gestioneProdottiManager.modificaProdotto(prodottoGenerico,
					"Keywords",
					Keywords);
			boolean var14 = gestioneProdottiManager.inserisciRicercatoriProduzione(prodottoGenerico,
					null,
					id_proprietari);

			if (var2 && var4 && var5 && var8 && var9 && var10 && var11 && var12 && var13 && var14)
				isOK = true;
			else
				isOK = false;

		}
		else {
			// QUESTO E' UN CASO IMPOSSIBILE CIOE' CHE IL PRODOTTO NON HA
			// PROPRIO TIPO
			// CASOMAI VA A MALE LA MODIFICA LO FACCIO RITORNARE ALLA PAGINA DI
			// VISUALIZZA PRODOTTO!!!!!
			// rd = request.getRequestDispatcher("/VisualizzaProdotto.jsp");
			request.setAttribute("Error",
					"Errore nella modifica del prodotto!!!");
			// DA VISUALIZZA CONTROLLIAMO SE LA MODIFICA E' ANDATA TUTTO OK CON
			// UNA JSP!!!
			// SE NON VA BENE SETTIAMO L'ATTRIBUTO ERRORE CHE CONTROLLIAMO NELLA
			// JSP
			// SE VA BENE SETTIAMO L'ATTRIBUTO RESPONSE CHE CONTROLLIAMO SEMPRE
			// NELLA JSP
			// MAGARI TUTTE E DUE POSSIAMO DARLE IN OUTPUT CON UN ALERT,
			// FINESTRA DI ERRORE!!

		}

		response.getWriter().write(isOK ? "OK" : "error");
		/*
		 * System.out.println("Modifica Prodotto Servlet"); Ricercatore ric=
		 * (Ricercatore) request.getSession().getAttribute("Ricercatore"); int
		 * ID_Proprietario= ric.getID_Ricercatore();
		 * 
		 * String[]
		 * Autori_per_produzione=request.getParameterValues("ID_Ricercatore");
		 * // RITORNA ARRAY DI ID DEI RICERCATORI AUTORI
		 * 
		 * String [] Autori_per_produzione_nuovo= new
		 * String[Autori_per_produzione.length+1]; for (int i = 0; i <
		 * Autori_per_produzione.length; i++) {
		 * Autori_per_produzione_nuovo[i]=Autori_per_produzione[i]; }
		 * Autori_per_produzione_nuovo
		 * [Autori_per_produzione.length]=Integer.toString(ID_Proprietario); int
		 * [] id_proprietari= new int [Autori_per_produzione.length+1]; // NON
		 * POSSIAMO EDITARE LA TIPOLOGIA NEL MODIFICA // POSSIAMO MODIFICARE IN
		 * ALCUNI CASI LO STATO
		 * 
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/modifica_prodotto.jsp");
		 * 
		 * String Id_Prodotto = request.getParameter("ID_Prodotto"); String
		 * Titolo= request.getParameter("Titolo"); String Tipologia =
		 * request.getParameter("Tipologia"); // mi serve solo per gli if String
		 * Autori=request.getParameter("Autori"); String Anno_Pubblicazione=
		 * request.getParameter("Anno_Pubblicazione"); String Abstract=
		 * request.getParameter("Abstract"); int
		 * Pubblico=Integer.parseInt(request.getParameter("Pubblico")); String
		 * Url=request.getParameter("Url"); String
		 * Note=request.getParameter("Note"); String
		 * Stato=request.getParameter("Stato");
		 * 
		 * if(Tipologia.equalsIgnoreCase("Articolo Libro")) { String
		 * Nome_Volume= request.getParameter("Nome_Volume"); String
		 * Autori_Volume=request.getParameter("Autori_Volume"); String Editore=
		 * request.getParameter("Editore"); String
		 * Citta_Editore=request.getParameter("Città_Editore"); String
		 * Paese_Editore= request.getParameter("Paese_Editore"); String
		 * ISBN=request.getParameter("ISBN"); String Pagine_Riferimento=
		 * request.getParameter("Pagine_Riferimento"); String
		 * DOI=request.getParameter("DOI"); String Keywords =
		 * request.getParameter("Keywords");
		 * 
		 * 
		 * Articolo_Libro prodottoGenerico= new Articolo_Libro();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * boolean
		 * var1=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Url",Url); boolean
		 * var7=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Note",Note); boolean
		 * var8=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * 
		 * boolean
		 * var9=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Nome_Volume",Nome_Volume); boolean
		 * var10=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Autori_Volume",Autori_Volume); boolean
		 * var11=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Editore",Editore); boolean
		 * var12=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Citta_Editore",Citta_Editore); boolean
		 * var13=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Paese_Editore",Paese_Editore); boolean
		 * var14=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "ISBN",ISBN); boolean
		 * var15=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Pagine_Riferimento",Pagine_Riferimento); boolean
		 * var16=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "DOI",DOI); boolean
		 * var17=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Keywords",Keywords);
		 * 
		 * boolean var18=gestioneProdottiManager.inserisciRicercatoriProduzione(
		 * prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15&& var16 &&
		 * var17&& var18 )
		 * request.setAttribute("Response","Operazione completata con successo"
		 * ); else
		 * request.setAttribute("Error","Errore nella modifica del prodotto!!!"
		 * );
		 * 
		 * 
		 * } else if(Tipologia.equals("Articolo Rivista")){
		 * 
		 * String Nome_Rivista= request.getParameter("Nome_Rivista"); String
		 * Numero_Volume= request.getParameter("Numero_Volume"); String
		 * Pagine_riferimento= request.getParameter("Pagine_Riferimento");
		 * String DOI= request.getParameter("DOI"); String Keywords=
		 * request.getParameter("Keywords");
		 * 
		 * 
		 * Articolo_Rivista prodottoGenerico= new Articolo_Rivista();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * 
		 * boolean var1=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Tipologia",Tipologia); boolean
		 * var7=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Url",Url); boolean var8=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Note",Note); boolean
		 * var9=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean var10=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Nome_Rivista",Nome_Rivista); boolean
		 * var11=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Numero_Volume",Numero_Volume); boolean
		 * var12=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pagine_Riferimento",Pagine_riferimento); boolean
		 * var13=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "DOI",DOI); boolean var14=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Keywords",Keywords);
		 * 
		 * boolean var15=gestioneProdottiManager.inserisciRicercatoriProduzione(
		 * prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15 )
		 * request.setAttribute
		 * ("Response","Operazione completata con successo"); else
		 * request.setAttribute
		 * ("Error","Errore nella modifica del prodotto!!!");
		 * 
		 * 
		 * } else if(Tipologia.equals("Brevetto")){ String Proprieta=
		 * request.getParameter("Proprieta"); String Numero_Brevetto=
		 * request.getParameter("Numero_Brevetto"); String DOI=
		 * request.getParameter("DOI"); String Tipo=
		 * request.getParameter("Tipo"); String Keywords=
		 * request.getParameter("Keywords");
		 * 
		 * Brevetto prodottoGenerico= new Brevetto();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * boolean
		 * var1=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Tipologia",Tipologia); boolean
		 * var7=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Url",Url); boolean
		 * var8=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Note",Note); boolean
		 * var9=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean
		 * var10=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Proprieta",Proprieta); boolean
		 * var11=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Numero_Brevetto",Numero_Brevetto); boolean
		 * var12=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "DOI",DOI); boolean
		 * var13=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Tipo",Tipo); boolean
		 * var14=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Keywords",Keywords);
		 * 
		 * boolean var15=gestioneProdottiManager.inserisciRicercatoriProduzione(
		 * prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15 )
		 * request.setAttribute
		 * ("Response","Operazione completata con successo"); else
		 * request.setAttribute
		 * ("Error","Errore nella modifica del prodotto!!!");
		 * 
		 * }else if(Tipologia.equals("Curatela")){
		 * 
		 * String Nome_Rivista=request.getParameter("Nome_Rivista"); String
		 * Numero_Volume=request.getParameter("Numero_Volume"); String
		 * Autori_Volume=request.getParameter("Autori_Volume"); String
		 * Editore=request.getParameter("Editore"); String
		 * Citta_Editore=request.getParameter("Città_Editore"); String
		 * Paese_Editore=request.getParameter("Paese_Editore"); String
		 * Pagine_Riferimento=request.getParameter("Pagine_Riferimento"); String
		 * ISBN=request.getParameter("ISBN"); String
		 * DOI=request.getParameter("DOI"); String
		 * Keywords=request.getParameter("Keywords");
		 * 
		 * Curatela prodottoGenerico= new Curatela();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * boolean
		 * var1=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Url",Url); boolean
		 * var7=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Note",Note); boolean
		 * var8=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean
		 * var9=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Nome_Rivista",Nome_Rivista); boolean
		 * var10=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Numero_Volume",Numero_Volume); boolean
		 * var11=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Autori_Volume",Autori_Volume); boolean
		 * var12=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Editore",Editore); boolean
		 * var13=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Citta_Editore",Citta_Editore); boolean
		 * var14=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Paese_Editore",Paese_Editore); boolean
		 * var15=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Pagine_Riferimento",Pagine_Riferimento); boolean
		 * var16=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "ISBN",ISBN); boolean
		 * var17=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "DOI",DOI); boolean
		 * var18=gestioneProdottiManager.modificaProdotto(prodottoGenerico,
		 * "Keywords",Keywords);
		 * 
		 * boolean var19=gestioneProdottiManager.inserisciRicercatoriProduzione(
		 * prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15 && var16 &&
		 * var17 && var18 && var19 )
		 * request.setAttribute("Response","Operazione completata con successo"
		 * ); else
		 * request.setAttribute("Error","Errore nella modifica del prodotto!!!"
		 * );
		 * 
		 * 
		 * 
		 * }else if(Tipologia.equalsIgnoreCase("Monografia")) {
		 * 
		 * String Editore=request.getParameter("Editore"); String
		 * Citta_Editore=request.getParameter("Città_Editore"); String
		 * Paese_Editore=request.getParameter("Paese_Editore"); String
		 * Pagine_Riferimento=request.getParameter("Pagine_Riferimento"); String
		 * ISBN=request.getParameter("ISBN"); String
		 * DOI=request.getParameter("DOI"); String
		 * Keywords=request.getParameter("Keywords");
		 * 
		 * Monografia prodottoGenerico= new Monografia();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * boolean var1=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Url",Url); boolean var7=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Note",Note); boolean
		 * var8=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean var9=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Editore",Editore); boolean
		 * var10=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Citta_Editore",Citta_Editore); boolean
		 * var11=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Paese_Editore",Paese_Editore); boolean
		 * var12=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pagine_Riferimento",Pagine_Riferimento); boolean
		 * var13=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "ISBN",ISBN); boolean var14=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "DOI",DOI); boolean
		 * var15=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Keywords",Keywords);
		 * 
		 * boolean var16=gestioneProdottiManager.inserisciRicercatoriProduzione(
		 * prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15 && var16)
		 * request
		 * .setAttribute("Response","Operazione completata con successo"); else
		 * request
		 * .setAttribute("Error","Errore nella modifica del prodotto!!!");
		 * 
		 * }else if(Tipologia.equalsIgnoreCase("Proceeding")) { String
		 * Nome_Volume= request.getParameter("Nome_Volume"); String
		 * Autori_Volume=request.getParameter("Autori_Volume"); String Editore=
		 * request.getParameter("Editore"); String
		 * Citta_Editore=request.getParameter("Città_Editore"); String
		 * Paese_Editore= request.getParameter("Paese_Editore"); String
		 * ISBN=request.getParameter("ISBN"); String Pagine_Riferimento=
		 * request.getParameter("Pagine_Riferimento"); String
		 * DOI=request.getParameter("DOI"); String Keywords =
		 * request.getParameter("Keywords"); String Relazione=
		 * request.getParameter("Relazione"); String
		 * Numero_Volume=request.getParameter("Numero_Volume"); String
		 * Nome_Congresso=request.getParameter("Nome_Congresso"); Date
		 * Data_Congresso
		 * =Date.valueOf((request.getParameter("Data_Congresso"))); String
		 * Luogo_Congresso=request.getParameter("Luogo_Congresso"); String
		 * Rilevanza=request.getParameter("Rilevanza");
		 * 
		 * Proceeding prodottoGenerico= new Proceeding();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * 
		 * boolean var1=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Url",Url); boolean var7=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Note",Note); boolean
		 * var8=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean var9=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Nome_Volume",Nome_Volume); boolean
		 * var10=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Autori_Volume",Autori_Volume); boolean
		 * var11=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Editore",Editore); boolean
		 * var12=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Citta_Editore",Citta_Editore); boolean
		 * var13=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Paese_Editore",Paese_Editore); boolean
		 * var14=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "ISBN",ISBN); boolean var15=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Pagine_Riferimento",Pagine_Riferimento); boolean
		 * var16=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "DOI",DOI); boolean var17=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Keywords",Keywords); boolean
		 * var18=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Relazione",Relazione); boolean
		 * var19=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Numero_Volume",Numero_Volume); boolean
		 * var20=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Nome_Congresso",Nome_Congresso); String
		 * data=(Data_Congresso.toString()); boolean
		 * var21=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Data_Congresso",data); boolean
		 * var22=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Luogo_Congresso",Luogo_Congresso); boolean
		 * var23=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Rilevanza",Rilevanza); boolean
		 * var24=gestioneProdottiManager.inserisciRicercatoriProduzione
		 * (prodottoGenerico, Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 && var15&& var16 &&
		 * var17&& var18 && var19&& var20&& var21&& var22&& var23&& var24 )
		 * request
		 * .setAttribute("Response","Operazione completata con successo"); else
		 * request
		 * .setAttribute("Error","Errore nella modifica del prodotto!!!");
		 * 
		 * 
		 * }else if(Tipologia.equalsIgnoreCase("Altro")) { String Nome_Volume=
		 * request.getParameter("Nome_Volume"); String
		 * Numero_Volume=request.getParameter("Numero_Volume"); String
		 * Pagine_Riferimento= request.getParameter("Pagine_Riferimento");
		 * String ISBN=request.getParameter("ISBN"); String Keywords =
		 * request.getParameter("Keywords");
		 * 
		 * Altro prodottoGenerico= new Altro();
		 * prodottoGenerico.setID_Prodotto(Integer.parseInt(Id_Prodotto));
		 * 
		 * boolean var1=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Titolo",Titolo); boolean
		 * var2=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Autori",Autori); boolean
		 * var3=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Anno_Pubblicazione",Anno_Pubblicazione); boolean
		 * var4=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Abstract",Abstract); boolean
		 * var5=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pubblico",Integer.toString(Pubblico)); boolean
		 * var6=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Url",Url); boolean var7=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Note",Note); boolean
		 * var8=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Stato",Stato);
		 * 
		 * boolean var9=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Nome_Volume",Nome_Volume); boolean
		 * var10=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Numero_Volume",Numero_Volume); boolean
		 * var11=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "Pagine_Riferimento",Pagine_Riferimento); boolean
		 * var12=gestioneProdottiManager.modificaProdotto( prodottoGenerico,
		 * "ISBN",ISBN); boolean var13=gestioneProdottiManager.modificaProdotto(
		 * prodottoGenerico, "Keywords",Keywords); boolean
		 * var14=gestioneProdottiManager
		 * .inserisciRicercatoriProduzione(prodottoGenerico,
		 * Autori_per_produzione_nuovo, id_proprietari);
		 * 
		 * 
		 * if( var1 && var2 && var3 && var4 && var5 && var6 && var7 && var8 &&
		 * var9 && var10 && var11 && var12 && var13&& var14 )
		 * request.setAttribute
		 * ("Response","Operazione completata con successo"); else
		 * request.setAttribute
		 * ("Error","Errore nella modifica del prodotto!!!");
		 * 
		 * 
		 * }else { // QUESTO E' UN CASO IMPOSSIBILE CIOE' CHE IL PRODOTTO NON HA
		 * PROPRIO TIPO //CASOMAI VA A MALE LA MODIFICA LO FACCIO RITORNARE ALLA
		 * PAGINA DI VISUALIZZA PRODOTTO!!!!! //rd =
		 * request.getRequestDispatcher("/VisualizzaProdotto.jsp");
		 * request.setAttribute
		 * ("Error","Errore nella modifica del prodotto!!!"); // DA VISUALIZZA
		 * CONTROLLIAMO SE LA MODIFICA E' ANDATA TUTTO OK CON UNA JSP!!! // SE
		 * NON VA BENE SETTIAMO L'ATTRIBUTO ERRORE CHE CONTROLLIAMO NELLA JSP //
		 * SE VA BENE SETTIAMO L'ATTRIBUTO RESPONSE CHE CONTROLLIAMO SEMPRE
		 * NELLA JSP // MAGARI TUTTE E DUE POSSIAMO DARLE IN OUTPUT CON UN
		 * ALERT, FINESTRA DI ERRORE!!
		 * 
		 * } rd.forward(request, response);
		 */
	}

}
