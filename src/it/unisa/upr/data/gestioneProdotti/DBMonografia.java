/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * La classe fornisce i dettagli implementativi del MonografiaManager. Si occupa
 * di tutte le operazioni relative ai prodotti di tipologia Monografia all'interno
 * del database
 * 
 */
public class DBMonografia implements IMonografiaManager {

	// Implementazione Design Pattern Singleton
	private static DBMonografia monografiaManager;
	private IProdottoManager prodottoManager;

	private DBMonografia() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBMonografia getInstance() {
		if (monografiaManager == null)
			monografiaManager = new DBMonografia();

		return monografiaManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Carica i dettagli di un prodotto di tipo Monografia dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Monografia visualizzaDettagliMonografia(Prodotto p) {
		java.sql.Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Monografia monografia = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT monografia.* FROM prodotto join monografia WHERE prodotto.ID_Prodotto=monografia.ID_Prodotto AND prodotto.ID_Prodotto="
					+ p.getID_Prodotto();
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				int ID_Prodotto = rsRicerca
						.getInt(DBNames.ATT_MONOGRAFIA_ID_PRODOTTO);
				String Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_EDITORE);
				String Citta_Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_CITTA_EDITORE);
				String Paese_Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_PAESE_EDITORE);
				String Pagine_Riferimento = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_PAGINE_RIFERIMENTO);
				String ISBN = rsRicerca.getString(DBNames.ATT_MONOGRAFIA_ISBN);
				String DOI = rsRicerca.getString(DBNames.ATT_MONOGRAFIA_DOI);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_KEYWORDS);
				monografia = new Monografia();
				monografia.setID_Prodotto(ID_Prodotto);
				monografia.setEditore(Editore);
				monografia.setCitta_Editore(Citta_Editore);
				monografia.setPaese_Editore(Paese_Editore);
				monografia.setPagine_Riferimento(Pagine_Riferimento);
				monografia.setISBN(ISBN);
				monografia.setDOI(DOI);
				monografia.setKeywords(Keywords);
				monografia.setStato(p.getStato());

				monografia.setAutori(p.getAutori());
				monografia.setTipologia(p.getTipologia());
				monografia.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				monografia.setAutori(p.getAutori());
				monografia.setAbstract(p.getAbstract());
				monografia.setURL(p.getURL());
				monografia.setNote(p.getNote());
				monografia.setID_Proprietario(p.getID_Proprietario());
				monografia.setPubblico(p.getPubblico());
				monografia.setTitolo(p.getTitolo());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (Exception e) {

		}
		return monografia;
	}

	/**
	 * Inserisce un prodotto di tipo Monografia del database
	 * 
	 * @param mon
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciMonografia(Monografia mon) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(mon);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			mon.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO monografia(ID_Prodotto,Editore,Citta_Editore,Paese_Editore,Pagine_Riferimento,ISBN,DOI,Keywords) VALUES("
					+ ID_Prodotto
					+ ",'"
					+ mon.getEditore().replace("'", "\\'")
					+ "','"
					+ mon.getCitta_Editore().replace("'", "\\'")
					+ "','"
					+ mon.getPaese_Editore().replace("'", "\\'")
					+ "','"
					+ mon.getPagine_Riferimento()
					+ "','"
					+ mon.getISBN()
					+ "','"
					+ mon.getDOI()
					+ "','"
					+ mon.getKeywords().replace("'", "\\'") + "')";

			statement = connection.createStatement();
			int result = statement.executeUpdate(insert); // restituisce il
															// numero di righe
															// inserite
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			if (result == 0)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Modifica un campo di un prodotto di tipo Monografia nel database
	 * 
	 * @param mon
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaMonografia(Monografia mon, String campo,
			String nuovoValore) {
		java.sql.Connection connection = null;
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
				return prodottoManager
						.modificaProdotto(mon, campo, nuovoValore);
			} else {
				connection = DBConnectionPool.getConnection();
				update = "UPDATE monografia SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto=" + mon.getID_Prodotto();
				statement = connection.createStatement();
				result = statement.executeUpdate(update); // restituisce il
															// numero di righe
															// modificate
				connection.commit();
				statement.close();
				DBConnectionPool.releaseConnection(connection);
			}
			if (result == 0 && !result1)
				return false;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un prodotto Monografia dal database
	 * 
	 * @param mon
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaMonografia(Monografia mon) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String del;
		try {
			connection = DBConnectionPool.getConnection();
			del = "DELETE FROM monografia WHERE ID_Prodotto="
					+ mon.getID_Prodotto();
			statement = connection.createStatement();
			int result = statement.executeUpdate(del); // restituisce il numero
														// di righe eliminate
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
