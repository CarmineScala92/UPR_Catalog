/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.*;

/**
 * La classe fornisce i dettagli implementativi del CuratelaManager. Si occupa
 * di tutte le operazioni relative ai prodotti di tipologia Curatela all'interno
 * del database
 * 
 */
public class DBCuratela implements ICuratelaManager {

	// Implementazione Design Pattern Singleton
	private static DBCuratela curatelaManager;
	private IProdottoManager prodottoManager;

	private DBCuratela() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBCuratela getInstance() {
		if (curatelaManager == null)
			curatelaManager = new DBCuratela();

		return curatelaManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Carica i dettagli di un prodotto di tipo Articolo Libro dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Curatela visualizzaDettagli(Prodotto p) {
		java.sql.Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Curatela curatela = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT curatela.* FROM prodotto join curatela WHERE prodotto.ID_Prodotto=curatela.ID_Prodotto AND prodotto.ID_Prodotto="
					+ p.getID_Prodotto();
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				int ID_Prodotto = rsRicerca
						.getInt(DBNames.ATT_CURATELA_ID_PRODOTTO);
				String Nome_Rivista = rsRicerca
						.getString(DBNames.ATT_CURATELA_NOME_RIVISTA);
				String Numero_Volume = rsRicerca
						.getString(DBNames.ATT_CURATELA_NUMERO_VOLUME);
				String Autori_Volume = rsRicerca
						.getString(DBNames.ATT_CURATELA_AUTORI_VOLUME);
				String Editore = rsRicerca
						.getString(DBNames.ATT_CURATELA_EDITORE);
				String Citta_Editore = rsRicerca
						.getString(DBNames.ATT_CURATELA_CITTA_EDITORE);
				String Paese_Editore = rsRicerca
						.getString(DBNames.ATT_CURATELA_PAESE_EDITORE);
				String Pagine_Riferimento = rsRicerca
						.getString(DBNames.ATT_CURATELA_PAGINE_RIFERIMENTO);
				String ISBN = rsRicerca.getString(DBNames.ATT_CURATELA_ISBN);
				String DOI = rsRicerca.getString(DBNames.ATT_CURATELA_DOI);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_CURATELA_KEYWORDS);
				curatela = new Curatela();

				curatela.setAutori(p.getAutori());
				curatela.setTipologia(p.getTipologia());
				curatela.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				curatela.setAutori(p.getAutori());
				curatela.setAbstract(p.getAbstract());
				curatela.setURL(p.getURL());
				curatela.setNote(p.getNote());
				curatela.setID_Proprietario(p.getID_Proprietario());
				curatela.setPubblico(p.getPubblico());
				curatela.setTitolo(p.getTitolo());

				curatela.setID_Prodotto(ID_Prodotto);
				curatela.setNome_Rivista(Nome_Rivista);
				curatela.setNumero_Volume(Numero_Volume);
				curatela.setAutori_Volume(Autori_Volume);
				curatela.setEditore(Editore);
				curatela.setCitta_Editore(Citta_Editore);
				curatela.setPaese_Editore(Paese_Editore);
				curatela.setPagine_Riferimento(Pagine_Riferimento);
				curatela.setISBN(ISBN);
				curatela.setDOI(DOI);
				curatela.setKeywords(Keywords);
				curatela.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return curatela;
	}

	/**
	 * Inserisce un prodotto di tipo Curatela del database
	 * 
	 * @param cur
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciCuratela(Curatela cur) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(cur);

			// serve per dare l'id giusto al sottoprodotto per avere una
			// corrispondenza nella tabella prodotto
			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			cur.setID_Prodotto(ID_Prodotto);

			insert = "INSERT INTO curatela(ID_Prodotto,Nome_Rivista,Numero_Volume,Autori_Volume,"
					+ "Editore,Citta_Editore,Paese_Editore,Pagine_Riferimento,ISBN,DOI,Keywords)"
					+ " VALUES("
					+ ID_Prodotto
					+ ",'"
					+ cur.getNome_Rivista().replace("'", "\\'")
					+ "','"
					+ cur.getNumero_Volume()
					+ "','"
					+ cur.getAutori_Volume().replace("'", "\\'")
					+ "','"
					+ cur.getEditore().replace("'", "\\'")
					+ "','"
					+ cur.getCitta_Editore().replace("'", "\\'")
					+ "','"
					+ cur.getPaese_Editore().replace("'", "\\'")
					+ "','"
					+ cur.getPagine_Riferimento()
					+ "','"
					+ cur.getISBN()
					+ "','"
					+ cur.getDOI()
					+ "','"
					+ cur.getKeywords().replace("'", "\\'") + "')";

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
	 * Modifica un campo di un prodotto di tipo Curatela nel database
	 * 
	 * @param cur
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaCuratela(Curatela cur, String campo,
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
				result1 = prodottoManager.modificaProdotto(cur, campo,
						nuovoValore);
			} else {
				connection = DBConnectionPool.getConnection();
				update = "UPDATE curatela SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto=" + cur.getID_Prodotto();
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
	 * Elimina un prodotto Articolo Libro dal database
	 * 
	 * @param cur
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaCuratela(Curatela cur) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String del;
		try {
			connection = DBConnectionPool.getConnection();
			del = "delete from curatela where ID_Prodotto="
					+ cur.getID_Prodotto();
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
