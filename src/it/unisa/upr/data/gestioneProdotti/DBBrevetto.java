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
 * La classe fornisce i dettagli implementativi dell'BrevettoManager. Si occupa
 * di tutte le operazioni relative ai prodotti di tipologia Brevetto all'interno
 * del database
 * 
 */
public class DBBrevetto implements IBrevettoManager {

	static Logger logger = Logger.getLogger("global");

	private IProdottoManager prodottoManager;

	// Implementazione Design Pattern Singleton
	private static DBBrevetto brevettoManager;

	private DBBrevetto() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBBrevetto getInstance() {
		if (brevettoManager == null)
			brevettoManager = new DBBrevetto();

		return brevettoManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Inserisce un prodotto di tipo Brevetto del database
	 * 
	 * @param p
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciBrevetto(Brevetto p) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(p);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();

			p.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO brevetto (ID_Prodotto, Proprieta, Numero_Brevetto, DOI, Tipo, Keywords) "
					+ "VALUES ("
					+ ID_Prodotto
					+ ",'"
					+ p.getProprieta().replace("'", "\\'")
					+ "','"
					+ p.getNumero_Brevetto()
					+ "','"
					+ p.getDOI()
					+ "','"
					+ p.getTipo()
					+ "','"
					+ p.getKeywords().replace("'", "\\'")
					+ "')";
			logger.info(insert);
			statement = connection.createStatement();
			int result = statement.executeUpdate(insert);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			// if(result)
			// return true;
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Modifica un campo di un prodotto di tipo Brevetto nel database
	 * 
	 * @param p
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaBrevetto(Brevetto p, String campo, String nuovoValore) {
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
				update = "UPDATE brevetto SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto=" + p.getID_Prodotto();
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
	 * Elimina un prodotto Brevetto dal database
	 * 
	 * @param p
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaBrevetto(Brevetto p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from brevetto where ID_Prodotto="
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
	 * Carica i dettagli di un prodotto di tipo Brevetto dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Brevetto visualizzaDettagli(Prodotto p) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;

		Brevetto br = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM brevetto WHERE ID_Prodotto="
					+ p.getID_Prodotto();
			logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				br = new Brevetto();
				br.setAbstract(p.getAbstract());
				br.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				br.setAutori(p.getAutori());
				br.setNote(p.getNote());
				br.setPubblico(p.getPubblico());
				br.setStato(p.getStato());
				br.setTipologia(p.getTipologia());
				br.setTitolo(p.getTitolo());
				br.setURL(p.getURL());
				br.setID_Prodotto(p.getID_Prodotto());
				br.setID_Proprietario(p.getID_Proprietario());
				String Proprieta = rsRicerca
						.getString(DBNames.ATT_BREVETTO_PROPRIETA);
				String Numero_Brevetto = rsRicerca
						.getString(DBNames.ATT_BREVETTO_NUMERO_BREVETTO);
				String DOI = rsRicerca.getString(DBNames.ATT_BREVETTO_DOI);
				String Tipo = rsRicerca.getString(DBNames.ATT_BREVETTO_TIPO);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_BREVETTO_KEYWORDS);
				br.setDOI(DOI);
				br.setKeywords(Keywords);
				br.setNumero_Brevetto(Numero_Brevetto);
				br.setProprieta(Proprieta);
				br.setTipo(Tipo);
				br.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}
}
