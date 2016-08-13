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
 * La classe fornisce i dettagli implementativi dell' AmministratoreManager
 * Si occupa di tutte le operazioni relative agli account degli amministratori
 * all'interno del database.
 */
public class DBAmministratore implements IAmministratoreManager {

	// Implementazione Design Pattern Singleton
	private static DBAmministratore manager;

	private DBAmministratore() {}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBAmministratore getInstance() {
		if (manager == null)
			manager = new DBAmministratore();

		return manager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Modifica un account amministratore in base ai dettagli contenuti in amm
	 * 
	 * @param amm amministratore da modificare
	 * @return true se le modifiche sono avvenute con successo, false altrimenti
	 */
	public boolean modificaAmministratore(Amministratore amm) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;

		try {
			connection = DBConnectionPool.getConnection();
			query = "UPDATE amministratore SET Nome='" + amm.getNome() + "' ,Cognome='" + amm.getCognome() + "' WHERE ID_Amministratore=" + amm.getID_Amministratore();
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
	 * Cerca all'interno del database un amministratore con account = account
	 * 
	 * @param account account da cercare
	 * @return ritorna l'amministratore a cui è associato l'account, altrimenti
	 *         null
	 */
	public Amministratore getAmministratore(Account account)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Amministratore amministratore = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_AMMINISTRATORE + " WHERE ID_Amministratore = " + account.getID_Account();
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			int id_Amministratore = rsRicerca.getInt("ID_Amministratore");
			String nome = rsRicerca.getString("Nome");
			String cognome = rsRicerca.getString("Cognome");

			amministratore = new Amministratore();

			amministratore.setID_Account(account.getID_Account());
			amministratore.setUsername(account.getUsername());
			amministratore.setPassword(account.getPassword());
			amministratore.setTipologia(account.getTipologia());

			amministratore.setID_Amministratore(id_Amministratore);
			amministratore.setNome(nome);
			amministratore.setCognome(cognome);

			return amministratore;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return amministratore;
	}

	/**
	 * Elimina l'amministratore p dal database
	 * 
	 * @param p amministratore da eliminare
	 * @return true se l'amministratore è stato eliminato, false altrimenti
	 */
	public boolean eliminaAmministratore(Amministratore p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {

			connection = DBConnectionPool.getConnection();
			delete = "delete from amministratore where ID_Amministratore=" + p.getID_Amministratore();
			// logger.info(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();

			DBConnectionPool.releaseConnection(connection);

			return result > 0;

		}
		catch (SQLException e) {
			System.out.println("ERRORE ELIMINA AMM");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Inserisce un amministratore all'interno del database
	 * 
	 * @param a amministratore da inserire
	 * @return true se l'amministratore è stato inserito, false altrimenti
	 */
	@Override
	public boolean inserisciAmministratore(Amministratore a) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();

			// inserisco prima nella tabella Account poi in quella
			// amministratore
			DBAccount dba = new DBAccount();
			dba.inserisciAccount(a);
			int ID = dba.getUltimoIDAccount();
			System.out.println(ID);

			insert = "INSERT INTO  amministratore (ID_Amministratore,Nome,Cognome) " + "VALUES ('" + ID + "','" + a.getNome() + "','" + a.getCognome() + "')";

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
	 * Cerca all'interno del database gli amministratore in base al nome e al
	 * cognome
	 * 
	 * @param nome nome
	 * @param cognome cognome
	 * @return ritorna la lista degli amministratori che soddisfano i parametri
	 *         di ricerca
	 */
	public ArrayList<Amministratore> getAmministratore(String nome,
			String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ArrayList<Amministratore> amministratore = new ArrayList<Amministratore>();
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " JOIN " + DBNames.TABLE_AMMINISTRATORE + " WHERE ID_Account = ID_Amministratore ";
			if (!(nome.compareTo("") == 0)) {
				query = query + "AND Nome LIKE '%" + nome + "%'";
			}
			if (!(cognome.compareTo("") == 0)) {
				query = query + "AND Cognome LIKE '%" + cognome + "%'";
			}

			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			// System.out.println("query fatta");
			while (rsRicerca.next())
				amministratore.add(new Amministratore(rsRicerca.getInt("ID_Account"), rsRicerca.getString("Username"), rsRicerca.getString("Password"), rsRicerca.getString("Tipologia"), rsRicerca.getInt("ID_Amministratore"), rsRicerca.getString("Nome"), rsRicerca.getString("Cognome")));

			return amministratore;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return amministratore;
	}
}
