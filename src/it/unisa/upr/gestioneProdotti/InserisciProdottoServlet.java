/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class InserisciProdottoServlet
 * La servlet si occupa dell'inserimento del prodotto nel database e dell'upload
 * del file associato
 */
@WebServlet("/InserisciProdottoServlet")
@MultipartConfig
public class InserisciProdottoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IFacadeGestioneProdotti gestioneProdottiManager;

	/**
	 * @see HttpServlet#init(ServletConfig config)
	 */
	public void init(ServletConfig config) {
		gestioneProdottiManager = GestioneProdotti.getInstance();
		/*
		 * File filesDir = (File)
		 * getServletContext().getAttribute("FILES_DIR_FILE");
		 * filesDir.mkdirs();
		 */
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
		Ricercatore ric = (Ricercatore) request.getSession().getAttribute("Ricercatore");
		Amministratore amm = (Amministratore) request.getSession().getAttribute("Amministratore");
		RequestDispatcher rd = request.getRequestDispatcher("./inserimento_prodotto.jsp");

		if (ric == null && amm == null) {
			rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
			return;
		}

		boolean err = false;

		/*
		 * Enumeration<String> params=request.getParameterNames();
		 * while(params.hasMoreElements()){
		 * System.out.println(params.nextElement());
		 * }
		 */
		int ID_Proprietario = 0;
		if (ric != null)
			ID_Proprietario = ric.getID_Ricercatore();
		else if (amm != null)
			try {
				ID_Proprietario = Integer.parseInt(request.getParameter("Autore_Proprietario"));
			}
			catch (NumberFormatException e) {
				request.getRequestDispatcher("./index.jsp");
				rd.forward(request, response);
				return;
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

		String Titolo = request.getParameter("Titolo");
		String Autori = request.getParameter("Autori") == null ? "" : request.getParameter("Autori");
		String Anno_Pubblicazione = request.getParameter("Anno_Pubblicazione") == null ? "" : request.getParameter("Anno_Pubblicazione");
		String Abstract = request.getParameter("Abstract") == null ? "" : request.getParameter("Abstract");
		int Pubblico = Integer.parseInt(request.getParameter("Pubblico"));
		String Stato = request.getParameter("Stato");
		String Tipologia = request.getParameter("Tipologia");
		String Url = request.getParameter("URL") == null ? "" : request.getParameter("URL");
		String Note = "";

		/*
		 * try {
		 * if(gestioneProdottiManager.exists(Titolo, Tipologia,
		 * Anno_Pubblicazione)){
		 * System.out.println("Prodotto già esistente");
		 * request.setAttribute("Response",
		 * "Non è stato possibile inserire il prodotto perché già presente.");
		 * response.sendRedirect("./inserimento_prodotto.jsp?response=1");
		 * return;
		 * }
		 * }
		 * catch (SQLException e1) {
		 * e1.printStackTrace();
		 * }
		 */
		int id_prodotto = 0;
		Prodotto prod = null;
		if (Tipologia.equalsIgnoreCase("Articolo Libro")) {
			String Nome_Volume = request.getParameter("Nome_Volume") == null ? "" : request.getParameter("Nome_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume") == null ? "" : request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore") == null ? "" : request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore") == null ? "" : request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore") == null ? "" : request.getParameter("Paese_Editore");
			String ISBN = request.getParameter("ISBN");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");

			prod = new Articolo_Libro(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Nome_Volume, Autori_Volume, Editore, Citta_Editore, Paese_Editore, ISBN, Pagine_Riferimento, DOI, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Articolo Rivista")) {
			String Nome_Rivista = request.getParameter("Nome_Rivista") == null ? "" : request.getParameter("Nome_Rivista");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Pagine_riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");

			prod = new Articolo_Rivista(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Nome_Rivista, Numero_Volume, Pagine_riferimento, DOI, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Brevetto")) {
			String Proprieta = request.getParameter("Proprieta") == null ? "" : request.getParameter("Proprieta");
			String Numero_Brevetto = request.getParameter("Numero_Brevetto");
			String DOI = request.getParameter("DOI");
			String Tipo = request.getParameter("Tipo");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");

			prod = new Brevetto(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Proprieta, Numero_Brevetto, DOI, Tipo, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Curatela")) {

			String Nome_Rivista = request.getParameter("Nome_Rivista") == null ? "" : request.getParameter("Nome_Rivista");
			String Numero_Volume = request.getParameter("Numero_Volume") == null ? "" : request.getParameter("Numero_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume") == null ? "" : request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore") == null ? "" : request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore") == null ? "" : request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore") == null ? "" : request.getParameter("Paese_Editore");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");

			prod = new Curatela(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Nome_Rivista, Numero_Volume, Autori_Volume, Editore, Citta_Editore, Paese_Editore, Pagine_Riferimento, ISBN, DOI, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Monografia")) {

			String Editore = request.getParameter("Editore") == null ? "" : request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore") == null ? "" : request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore") == null ? "" : request.getParameter("Paese_Editore");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");

			prod = new Monografia(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Editore, Citta_Editore, Paese_Editore, Pagine_Riferimento, ISBN, DOI, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Proceeding")) {
			String Nome_Volume = request.getParameter("Nome_Volume") == null ? "" : request.getParameter("Nome_Volume");
			String Autori_Volume = request.getParameter("Autori_Volume") == null ? "" : request.getParameter("Autori_Volume");
			String Editore = request.getParameter("Editore") == null ? "" : request.getParameter("Editore");
			String Citta_Editore = request.getParameter("Citta_Editore") == null ? "" : request.getParameter("Citta_Editore");
			String Paese_Editore = request.getParameter("Paese_Editore") == null ? "" : request.getParameter("Paese_Editore");
			String ISBN = request.getParameter("ISBN");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String DOI = request.getParameter("DOI");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");
			String Relazione = request.getParameter("Relazione");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Nome_Congresso = request.getParameter("Nome_Congresso") == null ? "" : request.getParameter("Nome_Congresso");
			String data = request.getParameter("Data_Congresso");
			System.out.println("Data: " + data);
			Date Data_Congresso = null;
			if (data != null && !data.isEmpty() && data.contains("/")) {
				System.out.println("Sono nell'if della data");
				String[] data_string = data.split("/");
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
			String Luogo_Congresso = request.getParameter("Luogo_Congresso") == null ? "" : request.getParameter("Luogo_Congresso");
			String Rilevanza = request.getParameter("Rilevanza");

			prod = new Proceeding(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Relazione, Nome_Volume, Numero_Volume, Autori_Volume, Editore, Citta_Editore, Paese_Editore, Pagine_Riferimento, Nome_Congresso, Data_Congresso, Luogo_Congresso, Rilevanza, ISBN, DOI, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (Tipologia.equalsIgnoreCase("Altro")) {
			String Nome_Volume = request.getParameter("Nome_Volume") == null ? "" : request.getParameter("Nome_Volume");
			String Numero_Volume = request.getParameter("Numero_Volume");
			String Pagine_Riferimento = request.getParameter("Pagine_Riferimento");
			String ISBN = request.getParameter("ISBN");
			String Keywords = request.getParameter("Keywords") == null ? "" : request.getParameter("Keywords");
			prod = new Altro(1, ID_Proprietario, Titolo, Autori, Anno_Pubblicazione, Abstract, Pubblico, Stato, Tipologia, Url, Note, Nome_Volume, Numero_Volume, Pagine_Riferimento, ISBN, Keywords);
			try {
				err = gestioneProdottiManager.inserisciProdotto(prod,
						null,
						id_proprietari);
				if (err)
					id_prodotto = prod.getID_Prodotto();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * ridondanza. il controllo è già stato effettuato sopra
		 * // SETTOVARIABILE DI SESSIONE -> "Duplicato" QUANDO esiste già uno
		 * stesso prodotto con stesso titolo Anno e tipologia
		 * if( gestioneProdottiManager.cercaDuplicato(Titolo,Anno_Pubblicazione,
		 * Tipologia) > 0){
		 * request.getSession().setAttribute("Duplicato",
		 * "Prodotto con titolo,Anno Pubblicazione e Tipologia già' esistente");
		 * }
		 */
		if (err) {
			String urlFile = upload(request, response);
			if (urlFile != null) {
				if (gestioneProdottiManager.modificaProdotto(prod,
						"URL",
						urlFile)) {
					response.sendRedirect("./visualizza_prodotto.jsp?id=" + id_prodotto);
					return;
				}
				else {
					// TODO si dovrebbe eliminare anche il file
					gestioneProdottiManager.eliminaProdotto(prod);
					response.sendRedirect("./inserimento_prodotto.jsp?response=2");
					return;
				}
			}
			else {
				response.sendRedirect("./inserimento_prodotto.jsp?response=2");
				return;
			}
		}
		else {
			response.sendRedirect("./inserimento_prodotto.jsp?response=2");
			return;
		}
	}

	private static String letters = "0123456789abcdefghijklmnopqrstuvzABCDEFGHIJKLMNOPQRSTUVZ";
	private static Random rand = new Random(System.currentTimeMillis());

	private String upload(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Richiesta di upload avviata");
		System.out.println("Mi accingo a fare l'upload");
		OutputStream out = null;
		InputStream filecontent = null;

		try {
			final Part filePart = req.getPart("fileName");

			if (filePart == null) {
				System.out.println("Il file è null");
				return null;
			}

			File file = null;
			String nomefile = "";
			do {
				nomefile = generaStringaCasuale(filePart);
				file = new File(req.getServletContext().getAttribute("FILES_DIR") + File.separator + nomefile);
			} while (file.exists());
			// file.mkdirs();
			System.out.println(file.getAbsolutePath());
			out = new FileOutputStream(file);
			filecontent = filePart.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[4096];
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			return nomefile;
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (ServletException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (filecontent != null) {
				try {
					filecontent.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String generaStringaCasuale(Part file) {
		String randomString = "";
		for (int i = 0; i < 17; i++)
			randomString += letters.charAt(rand.nextInt(letters.length()));
		return randomString + getEstensione(file);
	}

	private String getEstensione(Part file) {
		// content-disposition
		String infosFile = file.getHeader("content-disposition");
		String[] details = infosFile.split(";");
		for (int i = 0; i < details.length; i++) {
			String info = details[i].trim();
			if (info.startsWith("filename=")) {
				info = info.replace("filename=", "").replace("\"", "");
				if (info.contains("."))
					return info.substring(info.lastIndexOf("."));
			}
		}
		return "";
	}
}
