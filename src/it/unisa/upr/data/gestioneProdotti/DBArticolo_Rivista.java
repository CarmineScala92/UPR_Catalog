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
 * La classe fornisce i dettagli implementativi dell'Articolo_RivistaManager. Si
 * occupa di tutte le operazioni relative ai prodotti di tipologia Articolo
 * Rivista all'interno del database
 * 
 */
public class DBArticolo_Rivista implements IArticolo_RivistaManager {

	static Logger logger = Logger.getLogger("global");

	// Implementazione Design Pattern Singleton
	private static DBArticolo_Rivista articoloRivistaManager;
	private IProdottoManager prodottoManager;

	private DBArticolo_Rivista() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBArticolo_Rivista getInstance() {
		if (articoloRivistaManager == null)
			articoloRivistaManager = new DBArticolo_Rivista();

		return articoloRivistaManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Inserisce un prodotto di tipo Articolo Rivista del database
	 * 
	 * @param p
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciArticolo_Rivista(Articolo_Rivista p) {
		Connection connection = null;
		Statement statement = null;
		String insert;

		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(p);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			p.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO  articolo_rivista (  ID_Prodotto,Nome_Rivista,Numero_Volume,Pagine_riferimento,DOI,Keywords) "
					+ "VALUES ("
					+ ID_Prodotto
					+ ",'"
					+ p.getNome_Rivista().replace("'", "\\'")
					+ "','"
					+ p.getNumero_Volume()
					+ "','"
					+ p.getPagine_riferimento()
					+ "','"
					+ p.getDOI()
					+ "','"
					+ p.getKeywords().replace("'", "\\'") + "')";
			logger.info(insert);
			statement = connection.createStatement();
			int result = statement.executeUpdate(insert);
			connection.commit();
			statement.close();
			// prodottoManager.eliminaProdotto(p);

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
	 * Modifica un campo di un prodotto di tipo Articolo Rivista nel database
	 * 
	 * @param p
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaArticolo_Rivista(Articolo_Rivista p, String campo,
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
				update = "UPDATE articolo_rivista SET " + campo + "='"
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
	 * Elimina un prodotto Articolo Rivista dal database
	 * 
	 * @param p
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaArticolo_Rivista(Articolo_Rivista p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from articolo_rivista where ID_Prodotto="
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
	 * Carica i dettagli di un prodotto di tipo Articolo Rivista dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Articolo_Rivista visualizzaDettagli(Prodotto p) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;

		Articolo_Rivista ar = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM articolo_rivista WHERE ID_Prodotto="
					+ p.getID_Prodotto();
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				ar = new Articolo_Rivista();
				ar.setAbstract(p.getAbstract());
				ar.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				ar.setAutori(p.getAutori());
				ar.setNote(p.getNote());
				ar.setPubblico(p.getPubblico());
				ar.setStato(p.getStato());
				ar.setTipologia(p.getTipologia());
				ar.setTitolo(p.getTitolo());
				ar.setURL(p.getURL());
				ar.setID_Prodotto(p.getID_Prodotto());
				ar.setID_Proprietario(p.getID_Proprietario());
				String Nome_Rivista = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_RIVISTA_NOME_RIVISTA);
				String Numero_Volume = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_RIVISTA_NUMERO_VOLUME);
				String Pagine_riferimento = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_RIVISTA_PAGINE_RIFERIMENTO);
				String DOI = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_RIVISTA_DOI);
				String keywords = rsRicerca
						.getString(DBNames.ATT_ARTICOLO_RIVISTA_KEYWORDS);
				ar.setDOI(DOI);
				ar.setKeywords(keywords);
				ar.setNome_Rivista(Nome_Rivista);
				ar.setNumero_Volume(Numero_Volume);
				ar.setPagine_riferimento(Pagine_riferimento);
				ar.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ar;
	}
}
