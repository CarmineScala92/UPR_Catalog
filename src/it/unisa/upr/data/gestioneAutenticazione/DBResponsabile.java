/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe fornisce i dettagli implementativi del ResponsabileManager
 * Si occupa di tutte le operazioni relative agli account dei responsabili
 * all'interno del database.
 * 
 */
/**
 * @author Pino
 * 
 */
/**
 * @author Pino
 * 
 */
public class DBResponsabile implements IResponsabileManager {

	// Implementazione Design Pattern Singleton
	private static DBResponsabile manager;

	private DBResponsabile() {}

	public static synchronized DBResponsabile getInstance() {
		if (manager == null)
			manager = new DBResponsabile();

		return manager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Cerca il responsabile associato all'account all'interno del database
	 * 
	 * @param account account
	 * @return ritorna un bean Responsabile se l'account è associato ad un
	 *         responsabile, null altrimenti
	 */
	public Responsabile getResponsabile(Account account) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Responsabile responsabile = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_RESPONSABILE + " WHERE ID_Responsabile = " + account.getID_Account();
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			int id_Responsabile = rsRicerca.getInt("ID_Responsabile");
			String nome = rsRicerca.getString("Nome");
			String cognome = rsRicerca.getString("Cognome");

			responsabile = new Responsabile();

			responsabile.setID_Account(account.getID_Account());
			responsabile.setUsername(account.getUsername());
			responsabile.setPassword(account.getPassword());
			responsabile.setTipologia(account.getTipologia());

			responsabile.setID_Responsabile(id_Responsabile);
			responsabile.setCognome(cognome);
			responsabile.setNome(nome);

			return responsabile;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return responsabile;
	}

	/**
	 * Modifica i dati di un responsabile all'interno del database
	 * 
	 * @param resp responsabile da modificare
	 * @return true se la modifica è avvenuta con successo, false altrimenti
	 */
	public boolean modificaResponsabile(Responsabile resp) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;

		try {
			connection = DBConnectionPool.getConnection();
			query = "UPDATE responsabile SET Nome='" + resp.getNome() + "' ,Cognome='" + resp.getCognome() + "' WHERE ID_Responsabile=" + resp.getID_Responsabile();
			System.out.println(query);
			statement = connection.prepareStatement(query);
			int result = statement.executeUpdate(query);
			System.out.println("query fatta");
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			return result > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
			return false;
		}
	}

	/**
	 * Elimina un responsabile p dal database
	 * 
	 * @param p responsabile da eliminare
	 * @return true se il responsabile è stato eliminato con successo, false
	 *         altrimenti
	 */
	public boolean eliminaResponsabile(Responsabile p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {

			connection = DBConnectionPool.getConnection();
			delete = "delete from responsabile where ID_Responsabile=" + p.getID_Responsabile();
			// logger.info(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();

			DBConnectionPool.releaseConnection(connection);
			return result > 0;

		}
		catch (SQLException e) {
			System.out.println("ERRORE ELIMINA RESP");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Inserisce un responsabile r all'interno del database
	 * 
	 * @param r responsabile da inserire
	 * @return true se il responsabile è stato inserito, false altrimenti
	 */
	public boolean inserisciResponsabile(Responsabile r) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();

			// inserisco prima nella tabella Account poi in quella respons
			DBAccount dba = new DBAccount();
			dba.inserisciAccount(r);
			int ID = dba.getUltimoIDAccount();
			System.out.println(ID);

			insert = "INSERT INTO  responsabile (ID_Responsabile,Nome,Cognome) " + "VALUES ('" + ID + "','" + r.getNome() + "','" + r.getCognome() + "')";

			System.out.println(insert);
			statement = connection.createStatement();
			int result = statement.executeUpdate(insert);
			connection.commit();
			statement.close();

			DBConnectionPool.releaseConnection(connection);
			return result > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Cerca all'interno del database gli account dei responsabili in base ai
	 * parametri nome e cognome
	 * 
	 * @param nome nome
	 * @param cognome cognome
	 * @return ritorna la lista dei responsabili che soddisfano i parametri di
	 *         ricerca
	 */
	public ArrayList<Responsabile> getResponsabile(String nome, String cognome)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ArrayList<Responsabile> responsabile = new ArrayList<Responsabile>();
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " JOIN " + DBNames.TABLE_RESPONSABILE + " WHERE ID_Account = ID_Responsabile ";
			if (!(nome.compareTo("") == 0)) {
				query = query + "AND Nome LIKE '%" + nome + "%'";
			}
			if (!(cognome.compareTo("") == 0)) {
				query = query + "AND Cognome LIKE '%" + cognome + "%'";
			}

			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			while (rsRicerca.next())
				responsabile.add(new Responsabile(rsRicerca.getInt("ID_Account"), rsRicerca.getString("Username"), rsRicerca.getString("Password"), rsRicerca.getString("Tipologia"), rsRicerca.getInt("ID_Responsabile"), rsRicerca.getString("Nome"), rsRicerca.getString("Cognome")));

			return responsabile;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return responsabile;
	}

}
