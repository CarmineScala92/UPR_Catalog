/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * La classe fornisce i dettagli implementativi dell'Articolo_LibroManager. Si
 * occupa di tutte le operazioni relative ai prodotti di tipologia Articolo
 * Libro all'interno del database
 * 
 */
public class DBArticolo_Libro implements IArticolo_LibroManager {

	static Logger logger = Logger.getLogger("global");

	// Implementazione Design PAttern Singleton
	private static DBArticolo_Libro articoloLibroManager;
	private IProdottoManager prodottoManager;

	private DBArticolo_Libro() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);
	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBArticolo_Libro getInstance() {
		if (articoloLibroManager == null)
			articoloLibroManager = new DBArticolo_Libro();

		return articoloLibroManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Inserisce un prodotto di tipo Articolo Libro del database
	 * 
	 * @param p
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciArticolo_Libro(Articolo_Libro p) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(p);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			p.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO  articolo_libro (  ID_Prodotto,Nome_Volume,Autori_Volume,Editore,Citta_Editore,"
					+ "Paese_Editore,ISBN,Pagine_Riferimento,DOI,Keywords) "
					+ "VALUES ("
					+ ID_Prodotto
					+ ",'"
					+ p.getNome_Volume().replace("'", "\\'")
					+ "','"
					+ p.getAutori_Volume().replace("'", "\\'")
					+ "','"
					+ p.getEditore().replace("'", "\\'")
					+ "','"
					+ p.getCitta_Editore().replace("'", "\\'")
					+ "','"
					+ p.getPaese_Editore().replace("'", "\\'")
					+ "','"
					+ p.getISBN()
					+ "','"
					+ p.getPagine_Riferimento()
					+ "','"
					+ p.getDOI()
					+ "','"
					+ p.getKeywords().replace("'", "\\'")
					+ "')";
			logger.info(insert);
			statement = connection.createStatement();
			int result = statement.executeUpdate(insert);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			if (result > 0)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Modifica un campo di un prodotto di tipo Articolo Libro nel database
	 * 
	 * @param p
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaArticoloLibro(Articolo_Libro p, String campo,
			String nuovoValore) {
		Connection connection = null;
		Statement statement = null;
		String update;
		boolean result1 = false;
		int result = 0;
		nuovoValore = nuovoValore == null ? "" : nuovoValore;
		try {
			if (campo.compareTo("Titolo") == 0
					|| campo.compareTo("Autori") == 0
					|| campo.compareTo("Anno_Pubblicazione") == 0
					|| campo.compareTo("Abstract") == 0
					|| campo.compareTo("Pubblico") == 0
					|| campo.compareTo("Tipologia") == 0
					|| campo.compareTo("Url") == 0
					|| campo.compareTo("Note") == 0
					|| campo.compareTo("Stato") == 0) {
				return prodottoManager.modificaProdotto(p, campo, nuovoValore);
			} else {
				connection = DBConnectionPool.getConnection();
				update = "UPDATE articolo_libro SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto =" + p.getID_Prodotto();
				logger.info(update);
				statement = connection.prepareStatement(update);
				result = statement.executeUpdate(update);
				connection.commit();
				statement.close();
				DBConnectionPool.releaseConnection(connection);
			}
			if (result == 0 && !result1)
				return false;
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Elimina un prodotto Articolo Libro dal database
	 * 
	 * @param p
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaArticoloLibro(Articolo_Libro p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {

			connection = DBConnectionPool.getConnection();
			delete = "delete from articolo_libro where ID_Prodotto="
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
	 * Carica i dettagli di un prodotto di tipo Articolo Libro dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Articolo_Libro visualizzaDettagli(Prodotto p) {

		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Articolo_Libro al = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM articolo_libro WHERE ID_Prodotto="
					+ p.getID_Prodotto();
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				al = new Articolo_Libro();
				al.setAbstract(p.getAbstract());
				al.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				al.setAutori(p.getAutori());
				al.setNote(p.getNote());
				al.setPubblico(p.getPubblico());
				al.setStato(p.getStato());
				al.setTipologia(p.getTipologia());
				al.setTitolo(p.getTitolo());
				al.setURL(p.getURL());
				al.setID_Prodotto(p.getID_Prodotto());
				al.setID_Proprietario(p.getID_Proprietario());

				// System.out.println("dentro qhile di vis dett");
				int ID_Prodotto = rsRicerca
						.getInt(DBNames.ATT_ARTICOLO_LIBRO_ID_PRODOTTO);
				System.out.println("ID_PRODOTTO:" + ID_Prodotto);
				String Nome_Volume = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_NOME_VOLUME);
				String Autori_Volume = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_AUTORI_VOLUME);
				String Editore = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_EDITORE);
				String Citta_Editore = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_CITTA_EDITORE);
				String Paese_Editore = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_PAESE_EDITORE);
				String ISBN = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_ISBN);
				String Pagine_Riferimento = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_PAGINE_RIFERIMENTO);
				String DOI = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_DOI);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_LIBRO_KEYWORDS);

				al.setAutori_Volume(Autori_Volume);
				al.setCitta_Editore(Citta_Editore);
				al.setDOI(DOI);
				al.setEditore(Editore);
				al.setID_Prodotto(ID_Prodotto);
				al.setISBN(ISBN);
				al.setNome_Volume(Nome_Volume);
				al.setPaese_Editore(Paese_Editore);
				al.setPagine_Riferimento(Pagine_Riferimento);
				al.setKeywords(Keywords);
				al.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
}
