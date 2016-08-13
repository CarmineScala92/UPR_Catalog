/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */
//
package it.unisa.upr.data.gestioneAutenticazione;
// In questa classe faccio le query relative agli account e la uso per gestire gli account collegandomi con il DB
import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe fornisce i dettagli implementativi dell'Account Manager.
 * Si occupa di tutte le operazioni relative agli account all'interno del
 * database.
 * 
 */
public class DBAccount implements IAccountManager {

	// Implementazione Design Pattern Singleton
	private static DBAccount manager;
	private static IAmministratoreManager amministratoreManager;// i tre tipi di account
	private static IResponsabileManager responsabileManager;
	private static IRicercatoreManager ricercatoreManager;

	public DBAccount() {}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBAccount getInstance() {
		if (manager == null) {
			manager = new DBAccount();
			amministratoreManager = (IAmministratoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AMMINISTRATORE);
			responsabileManager = (IResponsabileManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RESPONSABILE);
			ricercatoreManager = (IRicercatoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RICERCATORE);
		}

		return manager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Modifica un account all'interno del database in base al contenuto di acc.
	 * L'account da modificare viene selezionato in base all'id in acc.
	 * 
	 * @param acc contiene tutte le infomazioni dell'account da modificare
	 * @return true se la modifica è avvenuto con successo, altrimenti false
	 */
	public boolean modificaAccount(Account acc) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;

		try {
			connection = DBConnectionPool.getConnection();
			query = "UPDATE account SET Username='" + acc.getUsername() + "' ,Password='" + acc.getPassword() + "' ,Tipologia='" + acc.getTipologia() + "' WHERE ID_Account=" + acc.getID_Account();
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

	public String verificaTipologia(String USR, String PSS) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String Tipologia = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT Tipologia FROM " + DBNames.TABLE_ACCOUNT + " WHERE Username=\"" + USR + "\" AND Password=\"" + PSS + "\"";
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			Tipologia = rsRicerca.getString("Tipologia");

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return Tipologia;
	}

	/**
	 * Verifica che un account è presente all'interno del sistema
	 * 
	 * @param USR username
	 * @param PSS password
	 * @return true se l'account è presente, false altrimenti
	 */
	public boolean isPresentAccount(String USR, String PSS) throws SQLException {
		try {
			if (this.getAccount(USR, PSS) != null)
				return true;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		return false;

	}

	/**
	 * Carica un account dal database in base all'username e alla password
	 * 
	 * @param user username
	 * @param pass password
	 * @return ritorna l'account se è stato trovato, altrimenti null
	 */
	public Account getAccount(String user, String pass) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Account account = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " WHERE Username=\"" + user + "\" AND Password=\"" + pass + "\"";
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			int id_Account = rsRicerca.getInt("ID_Account");
			String username = rsRicerca.getString("Username");
			String password = rsRicerca.getString("Password");
			String tipologia = rsRicerca.getString("Tipologia");

			account = new Account();
			account.setID_Account(id_Account);
			account.setUsername(username);
			account.setPassword(password);
			account.setTipologia(tipologia);

			return account;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return account;
	}

	/**
	 * Elimina l'account p dal database
	 * 
	 * @param p account da eliminare
	 * @return true se l'account è stato eliminato, false altrimenti
	 */
	public boolean eliminaAccount(Account p) {

		Connection connection = null;
		Statement statement = null;
		String delete;

		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from account where ID_Account=" + p.getID_Account();

			System.out.println(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
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
	 * Inserisce l'account a all'interno del database
	 * 
	 * @param a account da inserire
	 * @return true se l'account è stato inserito, false altrimenti
	 */
	public boolean inserisciAccount(Account a) {
		Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			insert = "INSERT INTO  account (Username, Password, Tipologia ) " + "VALUES ('" + a.getUsername() + "','" + a.getPassword() + "','" + a.getTipologia() + "')";

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
	 * Cerca l'ID dell'ultimo account inserito
	 * 
	 * @return ritorna l'id dell'ultimo account inserito
	 */
	public int getUltimoIDAccount() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		int id_Account = -1;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT max(ID_Account) FROM Account";
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			id_Account = rsRicerca.getInt("max(ID_Account)");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		return id_Account;
	}

	/**
	 * Carica dal database le informazioni relative ad un account
	 * 
	 * @param iD_Account id dell'account di cui si vogliono le informazioni
	 * @return ritorna un bean Account se l'operazione si è conclusa con
	 *         successo, altrimenti null
	 */
	@Override
	public Account visualizzaAccount(int iD_Account) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Account account = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " where ID_Account=" + iD_Account;
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			if (rsRicerca.next()) {
				int id_Account = rsRicerca.getInt("ID_Account");
				String username = rsRicerca.getString("Username");
				String password = rsRicerca.getString("Password");
				String tipologia = rsRicerca.getString("Tipologia");

				account = new Account();
				account.setID_Account(id_Account);
				account.setUsername(username);
				account.setPassword(password);
				account.setTipologia(tipologia);

				return account;
			}
			else {
				return null;
			}

		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

	}

	/**
	 * Ricerca all'interno del database gli account con nome=Nome e
	 * cognome=Cognome
	 * 
	 * @param Nome nome da cercare
	 * @param Cognome cognome da cercare
	 * @return ritorna una lista con gli account trovati.
	 */
	public ArrayList<Account> ricercaAccount(String Nome, String Cognome) {
		ArrayList<Account> lista = new ArrayList<Account>();
		try {
			ArrayList<Amministratore> amm = amministratoreManager.getAmministratore(Nome,
					Cognome);
			ArrayList<Responsabile> resp = responsabileManager.getResponsabile(Nome,
					Cognome);
			ArrayList<Ricercatore> ric = ricercatoreManager.getRicercatore(Nome,
					Cognome);

			if (!amm.isEmpty())
				for (Amministratore a : amm)
					lista.add(a);
			if (!resp.isEmpty())
				for (Responsabile r : resp)
					lista.add(r);
			if (!ric.isEmpty())
				for (Ricercatore r : ric)
					lista.add(r);

			return lista;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	/**
	 * Carica dal database la lista di tutti gli account presenti
	 * 
	 * @return ritorna la lista degli account presenti nel database
	 */
	@Override
	public ArrayList<Account> getListaAccount() {
		Connection connection = null;
		PreparedStatement statement = null;
		Account account = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Account> listaAccount = new ArrayList<Account>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT;
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			while (rsRicerca.next()) {
				int id_Account = rsRicerca.getInt("ID_Account");
				String username = rsRicerca.getString("Username");
				String password = rsRicerca.getString("Password");
				String tipologia = rsRicerca.getString("Tipologia");

				account = new Account();
				account.setID_Account(id_Account);
				account.setUsername(username);
				account.setPassword(password);
				account.setTipologia(tipologia);
				listaAccount.add(account);
			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			return listaAccount;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore Lista Account");
			return null;
		}
	}

}
