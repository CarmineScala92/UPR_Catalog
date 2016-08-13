/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.DBConstants;
import it.unisa.upr.commons.DBNames;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
/**
 * La classe fornisce i dettagli implementativi del ProdottoManager. Si occupa
 * di tutte le operazioni relative ai prodotti generici all'interno
 * del database
 * 
 */
public class DBProdotto implements IProdottoManager {

	static final Logger logger = Logger.getLogger("loggerDBProdotto");

	// Implementazione Design Pattern Singleton
	private static DBProdotto prodottoManager;

	private DBProdotto() {
	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBProdotto getInstance() {
		if (prodottoManager == null)
			prodottoManager = new DBProdotto();

		return prodottoManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Carica i prodotti di cui un ricercatore è proprietario
	 * @param acc account del ricercatore
	 * @return ritorna la lista dei prodotti associati all'account
	 */
	public ArrayList<Prodotto> caricaProdottiProprietario(Account acc) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,ricercatore"
					+ " WHERE prodotto.ID_Proprietario=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.ID_Ricercatore=" + acc.getID_Account();

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;
	}

	/**
	 * Elimina i prodotti associati ad un account
	 * @param acc account del ricercatore
	 * @return true se i prodotti sono stati cancellati, false altrimenti
	 */
	public boolean eliminaProdotti(Account acc) {
		DBProduzione produ = DBProduzione.getInstance();
		ArrayList<Prodotto> prodotti = this.caricaProdottiProprietario(acc);
		System.out.println("NUMERO PRODOTTI: " + prodotti.size());
		for (int i = 0; i < prodotti.size(); i++) {

			if (prodotti.get(i).getTipologia().equalsIgnoreCase("Brevetto")) {

				DBBrevetto x = DBBrevetto.getInstance();
				Brevetto prod = new Brevetto();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaBrevetto(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}

			}
			if (prodotti.get(i).getTipologia().equalsIgnoreCase("Proceeding")) {

				DBProceeding x = DBProceeding.getInstance();
				Proceeding prod = new Proceeding();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaProceeding(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}
			if (prodotti.get(i).getTipologia()
					.equalsIgnoreCase("Articolo Libro")) {

				DBArticolo_Libro x = DBArticolo_Libro.getInstance();
				Articolo_Libro prod = new Articolo_Libro();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaArticoloLibro(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}
			if (prodotti.get(i).getTipologia()
					.equalsIgnoreCase("Articolo Rivista")) {

				DBArticolo_Rivista x = DBArticolo_Rivista.getInstance();
				Articolo_Rivista prod = new Articolo_Rivista();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaArticolo_Rivista(prod)) {

					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}
			if (prodotti.get(i).getTipologia().equalsIgnoreCase("Altro")) {
				DBAltro x = DBAltro.getInstance();
				Altro prod = new Altro();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaAltro(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}
			if (prodotti.get(i).getTipologia().equalsIgnoreCase("Monografia")) {

				DBMonografia x = DBMonografia.getInstance();
				Monografia prod = new Monografia();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaMonografia(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}

			if (prodotti.get(i).getTipologia().equalsIgnoreCase("Curatela")) {

				DBCuratela x = DBCuratela.getInstance();
				Curatela prod = new Curatela();
				prod.setID_Prodotto(prodotti.get(i).getID_Prodotto());
				if (!x.eliminaCuratela(prod)) {
					return false;
				}
				if (!produ.elimina(prodotti.get(i))) {
					return false;
				}
				if (!this.eliminaProdotto(prodotti.get(i))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Carica la lista dei prodotti associati ad un account
	 * @param acc account di cui cercare l'associazione
	 * @return ritorna la lista dei prodotti a cui l'account è associato
	 */
	public ArrayList<Prodotto> caricaProdotti(Account acc) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.ID_Ricercatore=" + acc.getID_Account();

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);
			int i = listaProdotti.size();
			for (int j = i - 1; j > 0; j--) {
				Prodotto p = listaProdotti.get(j);
				if (p.getID_Proprietario() != acc.getID_Account()
						&& p.getStato().equalsIgnoreCase("draft"))
					listaProdotti.remove(j);
			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;

	}

	/**
	 * Carica la lista di tutti i prodotti completi
	 * @return ritorna la lista di tutti i prodotti completi
	 */
	public ArrayList<Prodotto> caricaProdottiCompleti() {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND prodotto.Stato='completo'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;

	}

	/**
	 * Carica la lista di tutti i prodotti accettati
	 * @return ritorna la lista dei prodotti accettati
	 */
	public ArrayList<Prodotto> caricaProdottiAccettati() {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND prodotto.Stato='accettato'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;

	}

	/**
	 * Ricerca un prodotto nel database (Utenti registrati)
	 * @param titolo titolo del prodotto
	 * @param anno_pubblicazione anno di pubblicazione
	 * @param tipologia tipologia del prodotto
	 * @param nome_dipart nome del dipartimento
	 * @param nome_area nome dell'area
	 * @param nome_prop nome del proprietario
	 * @param cognome_prop cognome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano i parametri di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdotto(String titolo,
			String anno_pubblicazione, String tipologia, String nome_dipart,
			String nome_area, String nome_prop, String cognome_prop) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsRicerca = null;
		String query;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();

			query = "select * from prodotto,dipartimento, ricercatore, area_scientifica,produzione "
					+ " where ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento "
					+ "AND area_scientifica.ID_Area_Scientifica=ricercatore.ID_Area_Scientifica "
					+ "AND (prodotto.Stato = 'validato' OR prodotto.Stato='inviato') "
					+ "AND produzione.ID_Prodotto=prodotto.ID_Prodotto ";

			if (!(titolo.compareTo("") == 0))
				query = query + "AND prodotto.Titolo like '%" + titolo + "%' ";

			if (!(anno_pubblicazione.compareTo("") == 0))
				query = query + "and prodotto.Anno_Pubblicazione ='"
						+ anno_pubblicazione + "' ";

			if (!(tipologia.compareTo("") == 0))
				query = query + "and prodotto.Tipologia like '%" + tipologia
						+ "%' ";

			if (!(nome_area.compareTo("") == 0))
				query = query + "and area_scientifica.ID_Area_Scientifica ='"
						+ nome_area + "' ";

			if (!(nome_dipart.compareTo("") == 0))
				query = query + "and dipartimento.ID_Dipartimento=\""
						+ nome_dipart + "\" ";

			if (!(nome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Nome like '%" + nome_prop + "%') ";

			if (!(cognome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Cognome like '%" + cognome_prop + "%') ";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;
	}

	/**
	 * Ricerca un prodotto nel database (Utenti esterni)
	 * @param titolo titolo del prodotto
	 * @param anno_pubblicazione anno di pubblicazione
	 * @param tipologia tipologia del prodotto
	 * @param nome_dipart nome del dipartimento
	 * @param nome_area nome dell'area
	 * @param nome_prop nome del proprietario
	 * @param cognome_prop cognome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano i parametri di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoUe(String titolo,
			String anno_pubblicazione, String tipologia, String nome_dipart,
			String nome_area, String nome_prop, String cognome_prop) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsRicerca = null;
		String query;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();

			query = "select * from prodotto,dipartimento, ricercatore, area_scientifica,produzione "
					+ " where ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento "
					+ "AND area_scientifica.ID_Area_Scientifica=ricercatore.ID_Area_Scientifica "
					+ "AND (prodotto.Stato = 'validato' OR prodotto.Stato='inviato') AND prodotto.Pubblico='1' "
					+ "AND produzione.ID_Prodotto=prodotto.ID_Prodotto ";

			if (!(titolo.compareTo("") == 0))
				query = query + "AND prodotto.Titolo like '%" + titolo + "%' ";

			if (!(anno_pubblicazione.compareTo("") == 0))
				query = query + "and prodotto.Anno_Pubblicazione ='"
						+ anno_pubblicazione + "' ";

			if (!(tipologia.compareTo("") == 0))
				query = query + "and prodotto.Tipologia like '%" + tipologia
						+ "%' ";

			if (!(nome_area.compareTo("") == 0))
				query = query + "and area_scientifica.ID_Area_Scientifica ='"
						+ nome_area + "' ";

			if (!(nome_dipart.compareTo("") == 0))
				query = query + "and dipartimento.ID_Dipartimento=\""
						+ nome_dipart + "\" ";

			if (!(nome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Nome like '%" + nome_prop + "%') ";

			if (!(cognome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Cognome like '%" + cognome_prop + "%') ";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return listaProdotti;
	}

	// serve a riempire l'ArrayList di prodotti ricercati
	/**
	 * Converte il risultato della ricerca dei prodotti nel database in una lista
	 * @param rsRicerca risultati della ricerca dei prodotti
	 * @return ritorna la lista dei prodotti presenti in rsRicerca
	 * @throws SQLException
	 */
	public static ArrayList<Prodotto> trova(ResultSet rsRicerca)
			throws SQLException {
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		while (rsRicerca.next()) {
			int ID_Prodotto = rsRicerca
					.getInt(DBNames.ATT_PRODOTTO_ID_PRODOTTO);
			int ID_Proprietario = rsRicerca
					.getInt(DBNames.ATT_PRODOTTO_ID_PROPRIETARIO);
			String Titolo = rsRicerca.getString(DBNames.ATT_PRODOTTO_TITOLO);
			String Autori = rsRicerca.getString(DBNames.ATT_PRODOTTO_AUTORI);
			String Anno_Pubblicazione = rsRicerca
					.getString(DBNames.ATT_PRODOTTO_ANNO_PUBBLICAZIONE);
			String Abstract = rsRicerca
					.getString(DBNames.ATT_PRODOTTO_ABSTRACT);
			int Pubblico = rsRicerca.getInt(DBNames.ATT_PRODOTTO_PUBBLICO);
			String Stato = rsRicerca.getString(DBNames.ATT_PRODOTTO_STATO);
			String Tipologia = rsRicerca
					.getString(DBNames.ATT_PRODOTTO_TIPOLOGIA);
			String URL = rsRicerca.getString(DBNames.ATT_PRODOTTO_URL);
			String Note = rsRicerca.getString(DBNames.ATT_PRODOTTO_NOTE);
			Prodotto prodotto = new Prodotto();
			prodotto.setID_Prodotto(ID_Prodotto);
			prodotto.setID_Proprietario(ID_Proprietario);
			prodotto.setAbstract(Abstract);
			prodotto.setAnno_Pubblicazione(Anno_Pubblicazione);
			prodotto.setAutori(Autori);
			prodotto.setNote(Note);
			prodotto.setPubblico(Pubblico);
			prodotto.setStato(Stato);
			prodotto.setTipologia(Tipologia);
			prodotto.setTitolo(Titolo);
			prodotto.setURL(URL);
			boolean copie = false;
			for (int i = 0; i < listaProdotti.size(); i++) {
				if (listaProdotti.get(i).getID_Prodotto() == prodotto
						.getID_Prodotto())
					copie = true;
			}
			if (!copie)
				listaProdotti.add(prodotto);
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al titolo (Utente registrato)
	 * @param titolo titolo da cercare
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTitolo(String titolo) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_TITOLO + "=\"" + titolo
					+ "\" AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT
					+ "'";
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = this.trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("probelmi con la ricerca");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al titolo (Utente esterno)
	 * @param titolo titolo da cercare
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTitoloUe(String titolo) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_TITOLO + "=\"" + titolo
					+ "\" AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT
					+ "'" + "AND PUBBLICO="
					+ DBConstants.PRODOTTO_STATO_PUBBLICO;
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base all'anno di produzione (Utente registrato)
	 * @param anno anno di produzione
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerAnno(String anno) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_ANNO_PUBBLICAZIONE + "='" + anno
					+ "'AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT + "'";
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base all'anno di produzione (Utente esterno)
	 * @param anno anno di produzione
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerAnnoUe(String anno) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_ANNO_PUBBLICAZIONE + "='" + anno
					+ "'AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT + "'"
					+ "AND " + DBNames.ATT_PRODOTTO_PUBBLICO + "="
					+ DBConstants.PRODOTTO_STATO_PUBBLICO;
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/*
	 * SELECT * FROM prodotto WHERE Tipologia=’articolo libro’ AND
	 * Stato<>'draft'
	 */
	/**
	 * Ricerca dei prodotti in base alla tipologia (Utente registrato)
	 * @param tipologia tipologia dei prodotti
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTipologia(String tipologia) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_TIPOLOGIA + "='" + tipologia
					+ "'AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT + "'";
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base alla tipologia (Utente esterno)
	 * @param tipologia tipologia dei prodotti
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerTipologiaUe(String tipologia) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM prodotto WHERE "
					+ DBNames.ATT_PRODOTTO_TIPOLOGIA + "='" + tipologia
					+ "'AND Stato<>'" + DBConstants.PRODOTTO_STATO_DRAFT + "'"
					+ "AND " + DBNames.ATT_PRODOTTO_PUBBLICO + "="
					+ DBConstants.PRODOTTO_STATO_PUBBLICO;
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/*
	 * SELECT prodotto.* FROM prodotto,ricercatore,dipartimento WHERE
	 * ricercatore.ID_Ricercatore=prodotto.ID_Proprietario AND
	 * dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento AND
	 * dipartimento.Nome=”chimica e biologia” AND Stato<>'draft'
	 */
	/**
	 * Ricerca dei prodotti in base al dipartimento (Utente registrato)
	 * @param nomeDip nome del dipartimento
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeDipartimento(String nomeDip) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.* "
					+ "FROM prodotto,ricercatore,dipartimento"
					+ " WHERE ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento "
					+ "AND dipartimento.Nome=\"" + nomeDip
					+ "\" AND Stato<>'draft'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al dipartimento (Utente esterno)
	 * @param nomeDip nome del dipartimento
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeDipartimentoUe(
			String nomeDip) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.* "
					+ "FROM prodotto,ricercatore,dipartimento"
					+ " WHERE ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento "
					+ "AND dipartimento.Nome=\"" + nomeDip
					+ "\" AND Stato<>'draft'" + " AND prodotto.Pubblico=1";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base all'area scientifica (Utente registrato)
	 * @param nomeArea nome dell'area scientifica
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeAreaScientifica(
			String nomeArea) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.* "
					+ "FROM prodotto,ricercatore,area_scientifica"
					+ " WHERE ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND area_scientifica.ID_Area_Scientifica=ricercatore.ID_Area_Scientifica"
					+ " AND area_scientifica.Nome='" + nomeArea
					+ "' AND Stato<>'draft'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base all'area scientifica (Utente esterno)
	 * @param nomeArea nome dell'area scientifica
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeAreaScientificaUe(
			String nomeArea) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.* "
					+ "FROM prodotto,ricercatore,area_scientifica"
					+ " WHERE ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ "AND area_scientifica.ID_Area_Scientifica=ricercatore.ID_Area_Scientifica"
					+ " AND area_scientifica.Nome='" + nomeArea
					+ "' AND Stato<>'draft'" + " AND prodotto.Pubblico=1";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/*
	 * SELECT prodotto.* FROM prodotto,produzione,ricercatore WHERE
	 * prodotto.ID_Prodotto=produzione.ID_Prodotto AND
	 * produzione.ID_Ricercatore=ricercatore.ID_Ricercatore AND
	 * ricercatore.Nome="Mario" AND prodotto.Stato<>'draft'
	 */
	/**
	 * Ricerca dei prodotti in base al nome del proprietario (Utente registrato)
	 * @param nomeProp nome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeProprietario(
			String nomeProp) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.Nome=\"" + nomeProp
					+ "\" AND prodotto.Stato<>'draft'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al nome del proprietario (Utente esterno)
	 * @param nomeProp nome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerNomeProprietarioUe(
			String nomeProp) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.Nome=\"" + nomeProp
					+ "\" AND prodotto.Stato<>'draft'"
					+ "AND prodotto.Pubblico=1";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al cognome del proprietario (Utente registrato)
	 * @param CognomeProp cognome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerCognomeProprietario(
			String CognomeProp) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.Cognome=\"" + CognomeProp
					+ "\" AND prodotto.Stato<>'draft'";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Ricerca dei prodotti in base al cognome del proprietario (Utente esterno)
	 * @param CognomeProp cognome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano il parametro di ricerca
	 */
	public ArrayList<Prodotto> ricercaProdottoPerCognomeProprietarioUe(
			String CognomeProp) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT prodotto.*"
					+ " FROM prodotto,produzione,ricercatore"
					+ " WHERE prodotto.ID_Prodotto=produzione.ID_Prodotto"
					+ " AND produzione.ID_Ricercatore=ricercatore.ID_Ricercatore"
					+ " AND ricercatore.Cognome=\"" + CognomeProp
					+ "\" AND prodotto.Stato<>'draft'"
					+ "AND prodotto.Pubblico=1";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);

		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe("errore in ricerca prodotto");
		}
		return listaProdotti;
	}

	/**
	 * Inserisce un prodotto generico nel database
	 * @param p prodotto da inserire
	 * return true se il prodotto è stato inserito, false altrimenti
	 */
	public boolean inserisciProdotto(Prodotto p) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			insert = "INSERT INTO  prodotto (ID_Proprietario, Titolo,Autori,Anno_Pubblicazione,Abstract,Pubblico,"
					+ "Stato,Tipologia,URL,Note) "
					+ "VALUES ("
					+ p.getID_Proprietario()
					+ ",'"
					+ p.getTitolo().replace("'", "\\'")
					+ "','"
					+ p.getAutori().replace("'", "\\'")
					+ "','"
					+ p.getAnno_Pubblicazione()
					+ "','"
					+ p.getAbstract().replace("'", "\\'")
					+ "','"
					+ p.getPubblico()
					+ "','"
					+ p.getStato()
					+ "','"
					+ p.getTipologia()
					+ "','"
					+ p.getURL().replace("'", "\\'")
					+ "','" + p.getNote().replace("'", "\\'") + "')";
			logger.info(insert);
			statement = connection.createStatement();
			int result = statement.executeUpdate(insert);
			connection.commit();
			statement.close();

			DBConnectionPool.releaseConnection(connection);
			return result >= 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Modifica un campo di un prodotto generico.
	 * @param p prodotto da modificare
	 * @param campo campo da modificare
	 * @param nuovoValore valore che il campo deve assumere
	 * @return true se il prodotto è stato modificato, false altrimenti
	 */
	public boolean modificaProdotto(Prodotto p, String campo, String nuovoValore) {
		Connection connection = null;
		Statement statement = null;
		String update;
		nuovoValore = nuovoValore == null ? "" : nuovoValore;
		try {
			connection = DBConnectionPool.getConnection();
			update = "UPDATE prodotto SET " + campo + "='"
					+ nuovoValore.replace("'", "\\'") + "' WHERE ID_Prodotto="
					+ p.getID_Prodotto();
			logger.info(update);
			statement = connection.prepareStatement(update);
			int result = statement.executeUpdate(update);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			if (result == 0) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Elimina un prodotto dal database
	 * @param p prodotto da eliminare
	 * @return true se il prodotto è stato eliminato, false altrimenti
	 */
	public boolean eliminaProdotto(Prodotto p) {

		Connection connection = null;
		Statement statement = null;
		String delete;

		try {
			connection = DBConnectionPool.getConnection();

			delete = "delete from prodotto where ID_Prodotto="
					+ p.getID_Prodotto();
			logger.info(delete);
			statement = connection.prepareStatement(delete);

			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Carica dal database i dettagli di un prodotto in un bean
	 * @param id id del prodotto di cui si vogliono i dettagli
	 * @return ritorna il bean del prodotto se il prodotto è presente, null in caso di errore
	 */
	@Override
	public Prodotto visualizzaProdotto(int id) throws SQLException {
		String query = "select * from prodotto where ID_Prodotto=" + id;
		Prodotto prodotto = null;
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if (rs.next())
			prodotto = new Prodotto(rs.getInt("ID_Prodotto"),
					rs.getInt("ID_Proprietario"), rs.getString("Titolo"),
					rs.getString("Autori"), rs.getString("Anno_Pubblicazione"),
					rs.getString("Abstract"), rs.getInt("Pubblico"),
					rs.getString("Stato"), rs.getString("Tipologia"),
					rs.getString("URL"), rs.getString("Note"));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return prodotto;
	}

	/**
	 * @return ritorna l'id dell'ultimo prodotto inserito
	 */
	@Override
	public int getUltimoIDProdotto() throws SQLException {
		String query = "select max(ID_Prodotto) as 'ID_Ultimo' from prodotto";
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int id = -1;
		if (rs.next())
			id = rs.getInt("ID_Ultimo");
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return id;
	}

	/**
	 * Verifica l'esistenza di un prodotto in base a titolo, tipologia e anno di pubblicazione
	 * @param titolo titolo del prodotto da cercare
	 * @param tipologia tipologia del prodotto da cercare
	 * @param anno anno del prodotto da cercare
	 * @return true se il proddo è presente, false altrimenti
	 */
	@Override
	public boolean exists(String titolo, String tipologia, String anno)
			throws SQLException {
		String query = "SELECT * FROM prodotto WHERE Titolo='"
				+ titolo.replace("'", "\\'") + "' AND Tipologia='"
				+ tipologia.replace("'", "\\'") + "' AND Anno_Pubblicazione='"
				+ anno.replace("'", "\\'") + "'";
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean exists = rs.next();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return exists;
	}

	/**
	 * Carica i prodotti che l'amministratore può visualizzare (tutti)
	 * @return ritorna la lista dei prodotti
	 */
	@Override
	public ArrayList<Prodotto> ricercaProdottiAmministratore() { // PRODOTTI
																	// HOMEPAGE
																	// AMMINISTRATORE
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsRicerca = null;
		String query;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "select * from prodotto ";
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			listaProdotti = trova(rsRicerca);
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("Problemi con la ricerca di prodotti per l'amministratore");
			e.printStackTrace();
		}
		return listaProdotti;
	}

	/**
	 * Ricerca un prodotto (Utente amministratore)
	 * @param titolo titolo del prodotto da cercare
	 * @param anno_pubblicazione anno di pubblicazione 
	 * @param tipologia tipologia del prodotto
	 * @param nome_dipart nome del dipartimento
	 * @param nome_area nome dell'area
	 * @param nome_prop nome del proprietario
	 * @param cognome_prop cognome del proprietario
	 * @return ritorna la lista dei prodotti che soddisfano i parametri di ricerca
	 */
	@Override
	// METODO CHE RITORNA I RISULTATI DELLA RICERCA DEI PRODOTTI
	// DELL'AMMINISTRATORE
	public ArrayList<Prodotto> ricercaProdottoAmministratore(String titolo,
			String anno_pubblicazione, String tipologia, String nome_dipart,
			String nome_area, String nome_prop, String cognome_prop) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsRicerca = null;
		String query;
		ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
		try {
			connection = DBConnectionPool.getConnection();

			query = "select * from prodotto,dipartimento, ricercatore, area_scientifica,produzione "
					+ " where ricercatore.ID_Ricercatore=prodotto.ID_Proprietario "
					+ " AND dipartimento.ID_Dipartimento=ricercatore.ID_Dipartimento "
					+ " AND area_scientifica.ID_Area_Scientifica=ricercatore.ID_Area_Scientifica "
					+ " AND produzione.ID_Prodotto=prodotto.ID_Prodotto ";

			if (!(titolo.compareTo("") == 0))
				query = query + "AND prodotto.Titolo like '%" + titolo + "%' ";

			if (!(anno_pubblicazione.compareTo("") == 0))
				query = query + " and prodotto.Anno_Pubblicazione ='"
						+ anno_pubblicazione + "' ";

			if (!(tipologia.compareTo("") == 0))
				query = query + " and prodotto.Tipologia like '%" + tipologia
						+ "%' ";

			if (!(nome_area.compareTo("") == 0))
				query = query + " and area_scientifica.ID_Area_Scientifica ='"
						+ nome_area + "' ";

			if (!(nome_dipart.compareTo("") == 0))
				query = query + "and dipartimento.ID_Dipartimento=\""
						+ nome_dipart + "\" ";

			if (!(nome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Nome like '%" + nome_prop + "%') ";

			if (!(cognome_prop.compareTo("") == 0))
				query = query
						+ "and produzione.ID_Ricercatore in (SELECT ID_Ricercatore from Ricercatore"
						+ " where Cognome like '%" + cognome_prop + "%') ";

			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			listaProdotti = trova(rsRicerca);

			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			logger.severe("Problemi con ricerca prodotti amministratore");
			e.printStackTrace();
		}
		return listaProdotti;
	}

}
