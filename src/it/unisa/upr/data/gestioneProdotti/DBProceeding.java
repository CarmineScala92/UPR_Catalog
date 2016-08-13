/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * La classe fornisce i dettagli implementativi del ProceedingManager. Si occupa
 * di tutte le operazioni relative ai prodotti di tipologia Proceeding all'interno
 * del database
 *
 */
public class DBProceeding implements IProceedingManager {

	// Implementazione Design Pattern Singleton
	private static DBProceeding proceedingManager;
	private IProdottoManager prodottoManager;

	private DBProceeding() {
		this.prodottoManager = (IProdottoManager) RefinedAbstractManager
				.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBProceeding getInstance() {
		if (proceedingManager == null)
			proceedingManager = new DBProceeding();

		return proceedingManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Carica i dettagli di un prodotto di tipo Proceeding dal database
	 * 
	 * @param p
	 *            prodotto di cui caricare i dati
	 * @return nel caso in cui il prodotto fosse presente ritorna il bean
	 *         contenente i dettagli, altrimenti null
	 */
	public Proceeding visualizzaDettagliProceeding(Prodotto p) {
		java.sql.Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Proceeding proceeding = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT proceeding.* FROM prodotto join proceeding WHERE prodotto.ID_Prodotto=proceeding.ID_Prodotto AND prodotto.ID_Prodotto="
					+ p.getID_Prodotto();
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			while (rsRicerca.next()) {
				int ID_Prodotto = rsRicerca
						.getInt(DBNames.ATT_MONOGRAFIA_ID_PRODOTTO);
				String Relazione = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_RELAZIONE);
				String Nome_Volume = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_NOME_VOLUME);
				String Autori_Volume = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_AUTORI_VOLUME);
				String Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_EDITORE);
				String Citta_Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_CITTA_EDITORE);
				String Paese_Editore = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_PAESE_EDITORE);
				String Pagine_Riferimento = rsRicerca
						.getString(DBNames.ATT_MONOGRAFIA_PAGINE_RIFERIMENTO);
				String Nome_Congresso = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_NOME_CONGRESSO);
				Date Data_Congresso = rsRicerca
						.getDate(DBNames.ATT_PROCEEDING_DATA_CONGRESSO);
				String Luogo_Congresso = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_LUOGO_CONGRESSO);
				String Rilevanza = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_RILEVANZA);
				String ISBN = rsRicerca.getString(DBNames.ATT_PROCEEDING_ISBN);
				String DOI = rsRicerca.getString(DBNames.ATT_PROCEEDING_DOI);
				String Keywords = rsRicerca
						.getString(DBNames.ATT_PROCEEDING_KEYWORDS);

				proceeding = new Proceeding();
				proceeding.setID_Prodotto(ID_Prodotto);
				proceeding.setRelazione(Relazione);
				proceeding.setNome_Volume(Nome_Volume);
				proceeding.setAutori_Volume(Autori_Volume);
				proceeding.setEditore(Editore);
				proceeding.setCitta_Editore(Citta_Editore);
				proceeding.setPaese_Editore(Paese_Editore);
				proceeding.setPagine_Riferimento(Pagine_Riferimento);
				proceeding.setNome_Congresso(Nome_Congresso);
				proceeding.setData_Congresso(Data_Congresso);
				proceeding.setLuogo_Congresso(Luogo_Congresso);
				proceeding.setRilevanza(Rilevanza);
				proceeding.setISBN(ISBN);
				proceeding.setDOI(DOI);
				proceeding.setKeywords(Keywords);

				proceeding.setAutori(p.getAutori());
				proceeding.setTipologia(p.getTipologia());
				proceeding.setAnno_Pubblicazione(p.getAnno_Pubblicazione());
				proceeding.setAutori(p.getAutori());
				proceeding.setAbstract(p.getAbstract());
				proceeding.setURL(p.getURL());
				proceeding.setNote(p.getNote());
				proceeding.setID_Proprietario(p.getID_Proprietario());
				proceeding.setPubblico(p.getPubblico());
				proceeding.setTitolo(p.getTitolo());
				proceeding.setStato(p.getStato());

			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proceeding;
	}

	/**
	 * Inserisce un prodotto di tipo Proceeding del database
	 * 
	 * @param proc
	 *            prodotto da inserire
	 * @return true se il prodotto viene inserito, false altrimenti
	 */
	public boolean inserisciProceeding(Proceeding proc) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			prodottoManager.inserisciProdotto(proc);

			int ID_Prodotto = prodottoManager.getUltimoIDProdotto();
			proc.setID_Prodotto(ID_Prodotto);
			insert = "INSERT INTO proceeding(ID_Prodotto,Relazione,Nome_Volume,Autori_Volume,"
					+ "Editore,Citta_Editore,Paese_Editore,Pagine_Riferimento,Nome_Congresso,Data_Congresso,"
					+ "Luogo_Congresso,Rilevanza,ISBN,DOI,Keywords) VALUES("
					+ ID_Prodotto
					+ ",'"
					+ proc.getRelazione()
					+ "','"
					+ proc.getNome_Volume().replace("'", "\\'")
					+ "','"
					+ proc.getAutori_Volume().replace("'", "\\'")
					+ "','"
					+ proc.getEditore().replace("'", "\\'")
					+ "','"
					+ proc.getCitta_Editore().replace("'", "\\'")
					+ "','"
					+ proc.getPaese_Editore().replace("'", "\\'")
					+ "','"
					+ proc.getPagine_Riferimento()
					+ "','"
					+ proc.getNome_Congresso().replace("'", "\\'")
					+ "','"
					+ proc.getData_Congresso()
					+ "','"
					+ proc.getLuogo_Congresso().replace("'", "\\'")
					+ "','"
					+ proc.getRilevanza()
					+ "','"
					+ proc.getISBN()
					+ "','"
					+ proc.getDOI()
					+ "','"
					+ proc.getKeywords().replace("'", "\\'") + "')";

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
	 * Modifica un campo di un prodotto di tipo Proceeding nel database
	 * 
	 * @param proc
	 *            prodotto da modificare
	 * @param campo
	 *            campo del prodotto da modificare
	 * @param nuovoValore
	 *            valore che il campo deve assumere
	 * @return true se la modifica avviene, false altrimenti
	 */
	public boolean modificaProceeding(Proceeding proc, String campo,
			String nuovoValore) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String update;
		boolean result1 = false;
		int result = 0;
		if (!campo.equalsIgnoreCase("Data_Congresso"))
			nuovoValore = nuovoValore == null ? "" : nuovoValore;
		try {
			connection = DBConnectionPool.getConnection();
			if (campo.compareTo("Titolo") == 0
					|| campo.compareTo("Autori") == 0
					|| campo.compareTo("Anno_Pubblicazione") == 0
					|| campo.compareTo("Abstract") == 0
					|| campo.compareTo("Pubblico") == 0
					|| campo.compareTo("Tipologia") == 0
					|| campo.compareTo("Url") == 0
					|| campo.compareTo("Note") == 0
					|| campo.compareTo("Stato") == 0) {
				result1 = prodottoManager.modificaProdotto(proc, campo,
						nuovoValore);
			} else {

				update = "UPDATE proceeding SET " + campo + "='"
						+ nuovoValore.replace("'", "\\'")
						+ "' WHERE ID_Prodotto=" + proc.getID_Prodotto();
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
	 * Elimina un prodotto Proceeding dal database
	 * 
	 * @param proc
	 *            prodotto da eliminare
	 * @return true se il prodotto viene eliminato, false altrimenti
	 */
	public boolean eliminaProceeding(Proceeding proc) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String del;
		try {
			connection = DBConnectionPool.getConnection();
			del = "DELETE FROM proceeding WHERE ID_Prodotto="
					+ proc.getID_Prodotto();
			statement = connection.createStatement();
			int result = statement.executeUpdate(del); // restituisce il numero
														// di righe eliminate
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
