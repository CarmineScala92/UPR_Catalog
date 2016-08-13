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
 * La classe fornisce i dettagli implementativi dell'AltroManager. Si occupa di
 * tutte le operazioni relative ai prodotti di tipologia Altro all'interno del
 * database
 * 
 */
public class DBAltro implements IAltroManager {

	// Implementazione Design Pattern Singleton
	private static DBAltro altroManager;
	private IProdottoManager prodottoManager;

	private DBAltro() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);
	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBAltro getInstance() {
		if (altroManager == null)
			altroManager = new DBAltro();

		return altroManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Carica dal database i dettagli di un prodotto di tipo Altro in un bean
	 * 
	 * @param p
	 *            prodotto di interesse
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Altro visualizzaDettagliAltro(Prodotto p) {
		java.sql.Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Altro altro = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT altro.* FROM prodotto join altro WHERE prodotto.ID_Prodotto=altro.ID_Prodotto AND prodotto.ID_Prodotto="
					+ p.getID_Prodotto();
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				int ID_Prodotto = rsRicerca
						.getInt(DBNames.ATT_ALTRO_ID_PRODOTTO);
				String Nome_Volume = rsRicerca
						.getString(DBNames.ATT_ALTRO_NOME_VOLUME);
				String Numero_Volume = rsRicerca
						.getString(DBNames.ATT_ALTRO_NUMERO_VOLUME);
				String Pagine_Riferimento = rsRicerca
						.getString(DBNames.ATT_ALTRO_PAGINE_RIFERIMENTO);
				String ISBN = rsRicerca.getString(DBNames.ATT_ALTRO_ISBN);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_ALTRO_KEYWORDS);
				altro = new Altro();
				altro.setID_Prodotto(ID_Prodotto);
				altro.setNome_Volume(Nome_Volume);
				altro.setNumero_Volume(Numero_Volume);
				altro.setPagine_Riferimento(Pagine_Riferimento);
				altro.setISBN(ISBN);
				altro.setKeywords(Keywords);

				altro.setAutori(p.getAutori());
				altro.setTipologia(p.getTipologia());
				altro.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				altro.setAutori(p.getAutori());
				altro.setAbstract(p.getAbstract());
				altro.setURL(p.getURL());
				altro.setNote(p.getNote());
				altro.setID_Proprietario(p.getID_Proprietario());
				altro.setPubblico(p.getPubblico());
				altro.setTitolo(p.getTitolo());
				altro.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return altro;
	}

	/**
	 * Inserisce un prodotto Altro nel database
	 * 
	 * @param altro
	 *            prodotto da inserire
	 * @return true se il prodotto è stato inserito, altrimenti false
	 */
	public boolean inserisciAltro(Altro altro) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(altro);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			altro.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO altro(ID_Prodotto,Nome_Volume,Numero_Volume,Pagine_Riferimento,ISBN,Keywords) VALUES("
					+ ID_Prodotto
					+ ",'"
					+ altro.getNome_Volume().replace("'", "\\'")
					+ "','"
					+ altro.getNumero_Volume()
					+ "','"
					+ altro.getPagine_Riferimento()
					+ "','"
					+ altro.getISBN()
					+ "','" + altro.getKeywords().replace("'", "\\'") + "')";

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
	 * Modifica un campo di un prodotto di tipo Altro
	 * 
	 * @param altro
	 *            prodotto da modificare
	 * @param campo
	 *            campo del database da modificare
	 * @param nuovoValore
	 *            valore da assegnare al campo
	 * @return ritorna true se la modifica è avvenuta con successo, altrimenti
	 *         false
	 */
	public boolean modificaAltro(Altro altro, String campo, String nuovoValore) {
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
				result1 = prodottoManager.modificaProdotto(altro, campo,
						nuovoValore);
			} else {
				connection = DBConnectionPool.getConnection();
				update = "UPDATE altro SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto=" + altro.getID_Prodotto();

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
	 * Elimina un prodotto di tipo altro dal database
	 * 
	 * @param altro
	 *            prodotto da eliminare
	 * @return ritorna true se il prodotto è stato eliminato, false altrimenti
	 */
	public boolean eliminaAltro(Altro altro) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String del;
		try {
			connection = DBConnectionPool.getConnection();
			del = "DELETE FROM altro WHERE ID_Prodotto="
					+ altro.getID_Prodotto();

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
