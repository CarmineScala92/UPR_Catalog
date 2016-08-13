/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneSistema;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe fornisce l'implementazione del DipartimentoManager
 * Si occupa di tutte le operazioni relative ai dipartimenti all'interno del
 * database
 */
public class DBDipartimento implements IDipartimentoManager {

	// Implementazione Design Pattern Singleton
	private static DBDipartimento dipartimentoManager;

	private DBDipartimento() {}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBDipartimento getInstance() {
		if (dipartimentoManager == null)
			dipartimentoManager = new DBDipartimento();

		return dipartimentoManager;
	}

	/**
	 * Cerca un dipartimento all'interno del database in base all'id
	 * 
	 * @param id id del database da cercare
	 * @return ritorna il bean contenente il dipartimento se trovato, altrimenti
	 *         null
	 */
	public Dipartimento getDipartimento(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Dipartimento dipartimento = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_DIPARTIMENTO + " WHERE ID_Dipartimento = " + id;
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			int id_dipartimento = rsRicerca.getInt("ID_Dipartimento");
			String nome = rsRicerca.getString("Nome");
			String telefono = rsRicerca.getString("Telefono");
			String fax = rsRicerca.getString("Fax");
			String sito = rsRicerca.getString("Sito");
			String email = rsRicerca.getString("Email");

			dipartimento = new Dipartimento(id_dipartimento, nome, telefono, fax, sito, email);

			return dipartimento;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return dipartimento;
	}

	/**
	 * Carica la lista dei ricercatori di un dipartimento
	 * 
	 * @param dip dipartimento di cui si vuole la lista dei ricercatori
	 * @return ritorna la lista dei ricercatori del dipartimento
	 */
	public ArrayList<Ricercatore> caricaRicercatori(Dipartimento dip) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Ricercatore> listaRicercatori = new ArrayList<Ricercatore>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT *" + " FROM ricercatore,account" + " WHERE ricercatore.ID_Ricercatore=account.ID_Account" + " AND ricercatore.ID_Dipartimento=" + dip.getID_Dipartimento();

			// logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			while (rsRicerca.next()) {
				int ID_Account = rsRicerca.getInt("ID_Account");
				String username = rsRicerca.getString("Username");
				String password = rsRicerca.getString("Password");
				String tipologia = rsRicerca.getString("Tipologia");
				Account acc = new Account();
				acc.setID_Account(ID_Account);
				acc.setPassword(password);
				acc.setUsername(username);
				acc.setTipologia(tipologia);

				Ricercatore ric = new Ricercatore();
				int id_ricercatore = rsRicerca.getInt("ID_Ricercatore");
				String nome = rsRicerca.getString("Nome");
				String cognome = rsRicerca.getString("Cognome");
				String codice_fiscale = rsRicerca.getString("Codice_Fiscale");
				String data_nascita = rsRicerca.getString("Data_Nascita");
				String citta_nascita = rsRicerca.getString("Citta_Nascita");
				String provincia_nascita = rsRicerca.getString("Provincia_Nascita");
				String matricola = rsRicerca.getString("Matricola");
				String email = rsRicerca.getString("Email");
				String data_inizio_servizio = rsRicerca.getString("Data_Inizio_Servizio");
				int id_dipartimento = rsRicerca.getInt("ID_Dipartimento");
				int id_area_scientifica = rsRicerca.getInt("ID_Area_Scientifica");
				String ruolo = rsRicerca.getString("Ruolo");
				String sesso = rsRicerca.getString("Sesso");

				ric.setID_Account(acc.getID_Account());
				ric.setUsername(acc.getUsername());
				ric.setPassword(acc.getPassword());
				ric.setTipologia(acc.getTipologia());

				ric.setID_Ricercatore(id_ricercatore);
				ric.setCognome(cognome);
				ric.setNome(nome);
				ric.setCodice_Fiscale(codice_fiscale);
				ric.setData_Nascita(data_nascita);
				ric.setCitta_Nascita(citta_nascita);
				ric.setProvincia_Nascita(provincia_nascita);
				ric.setMatricola(matricola);
				ric.setEmail(email);
				ric.setData_Inizio_Servizio(data_inizio_servizio);
				ric.setID_Dipartimento(id_dipartimento);
				ric.setID_Area_Scientifica(id_area_scientifica);
				ric.setRuolo(ruolo);
				ric.setSesso(sesso);

				listaRicercatori.add(ric);
			}
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}
		catch (SQLException e) {
			// logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}

		return listaRicercatori;

	}

	/**
	 * Ricerca un dipartimento all'interno del database in base al nome
	 * 
	 * @param nome da cercare
	 * @return ritorna la lista dei dipartimenti che soddisfano la ricerca
	 */
	public ArrayList<Dipartimento> ricercaDipartimento(String nome)
			throws SQLException {
		ArrayList<Dipartimento> dipartimenti = new ArrayList<Dipartimento>();
		String query = "select * from dipartimento ";
		try {
			if (!(nome.compareTo("") == 0))
				query = query + "where Nome LIKE '%" + nome + "%'";

			Connection con = DBConnectionPool.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				dipartimenti.add(new Dipartimento(rs.getInt("ID_Dipartimento"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email")));
			rs.close();
			con.commit();
			st.close();
			DBConnectionPool.releaseConnection(con);
			return dipartimenti;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dipartimenti;
	}

	/**
	 * Inserisce un dipartimento all'interno del database
	 * 
	 * @param nome nome del dipartimento
	 * @param telefono numero di telefono del dipartimento
	 * @param fax numero di fax del dipartimento
	 * @param sito sito web del dipartimento
	 * @param email indirizzo email del dipartimento
	 * @return 0 se il dipartimento non è stato inserito, altrimenti un intero
	 *         maggiore di 0
	 */
	public int inserimentoDipartimento(String nome, String telefono,
			String fax, String sito, String email) throws SQLException {
		Connection con = DBConnectionPool.getConnection();
		String query = "";
		if (sito.compareTo("") == 0 && email.compareTo("") == 0) { // TUTTI E
																	// DUE NULLI
			query = "insert into `dipartimento` (`Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + nome + "', '" + telefono + "', '" + fax + "', " + "NULL" + ", " + "NULL" + ") ";
		}
		else {
			if (email.compareTo("") == 0) { // SIGNIFICA CHE L'EMAIL E' NULLA
				query = "insert into `dipartimento` (`Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + nome + "', '" + telefono + "', '" + fax + "', '" + sito + "', " + "NULL" + ") ";
			}
			else if (sito.compareTo("") == 0) { // SIGNIFICA CHE SITO E' NULLO
				query = "insert into `dipartimento` (`Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + nome + "', '" + telefono + "', '" + fax + "', " + "NULL" + ", '" + email + "') ";
			}
			else { // NESSUNO DEI DUE E' NULLO
				query = "insert into `dipartimento` (`Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + nome + "', '" + telefono + "', '" + fax + "', '" + sito + "', '" + email + "') ";
			}
		}
		Statement st = con.createStatement();
		int risultato = st.executeUpdate(query); // Restituisce il numero di
													// righe interessate se è
													// insert delete update, 0
													// se è una istruzione
													// DDL(CREARE E MODIFICA,
													// ELIMINA TABELLE)!!
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return (risultato);
	}

	/**
	 * Modifica un dipartimento all'interno del database
	 * 
	 * @param Id id del dipartimento da modificare
	 * @param nome nome del dipartimento
	 * @param telefono numero di telefono del dipartimento
	 * @param fax numero di fax del dipartimento
	 * @param sito sito web del dipartimento
	 * @param email indirizzo email del dipartimento
	 * @return 0 se il dipartimento non è stato modificato, altrimenti un intero
	 *         maggiore di 0
	 */
	public int modificaDipartimento(int Id, String nome, String telefono,
			String fax, String sito, String email) throws SQLException {
		String query = "";
		Connection con = DBConnectionPool.getConnection();
		if (sito.compareTo("") == 0 && email.compareTo("") == 0) { // TUTTI E
																	// DUE NULLI
			query = "update dipartimento set Nome = '" + nome + "' , Telefono = '" + telefono + "' , Fax ='" + fax + "', Sito =" + "NULL" + " ,Email =" + "NULL" + " where ID_Dipartimento =" + Id + "";
		}
		else {
			if (email.compareTo("") == 0) { // SIGNIFICA CHE L'EMAIL E' NULLA
				query = "update dipartimento set Nome = '" + nome + "' , Telefono = '" + telefono + "' ,Fax ='" + fax + "' , Sito ='" + sito + "' ,Email =" + "NULL" + " where ID_Dipartimento =" + Id + "";
			}
			else if (sito.compareTo("") == 0) { // SIGNIFICA CHE IL SITO E'
												// NULLO
				query = "update dipartimento set Nome = '" + nome + "' ,Telefono = '" + telefono + "' , Fax ='" + fax + "' , Sito =" + "NULL" + " , Email ='" + email + "' where ID_Dipartimento =" + Id + "";
			}
			else { // NESSUNO DEI DUE E' NULLO
				query = "update dipartimento set Nome = '" + nome + "' ,Telefono = '" + telefono + "',Fax ='" + fax + "', Sito ='" + sito + "', Email ='" + email + "' where ID_Dipartimento =" + Id + "";
			}
		}
		// LA QUERY DI MODIFICA LA UTILIZZO COSI PERCHE' SONO SICURO CHE
		// L'UTENTE L'ID NON PUO'CAMBIARLO, QUINDI RIMANE INVARIATO!!!
		Statement st = con.createStatement();
		int risultato = st.executeUpdate(query); // Restituisce il numero di
													// righe interessate se è
													// insert delete update, 0
													// se è una istruzione
													// DDL(CREARE E MODIFICA,
													// ELIMINA TABELLE)!!
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return risultato;
	}

	/**
	 * Elimina un dipartimento dal database
	 * 
	 * @param Id id del dipartimento da eliminare
	 * @return 0 se il dipartimento non è stato eliminato, altrimenti un intero
	 *         maggiore di 0
	 */
	public int eliminaDipartimento(int Id) throws SQLException {
		Connection con = DBConnectionPool.getConnection();
		String query = "delete from dipartimento where ID_Dipartimento =" + Id;
		Statement st = con.createStatement();
		int risultato = st.executeUpdate(query); // Restituisce il numero di
													// righe interessate se è
													// insert delete update, 0
													// se è una istruzione DDL!!
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return (risultato);
	}

	/**
	 * Carica le informazioni di un dipartimento all'interno di un bean
	 * 
	 * @param id id del dipartimento di cui si vogliono i dettagli
	 * @return ritorna il bean del dipartimento se le informazioni sono state
	 *         trovate, altrimenti null
	 */
	public Dipartimento visualizzaDipartimento(int id) throws SQLException {
		ResultSet rs = null;
		Dipartimento dipartimento = null;
		String query = "select * from dipartimento where ID_Dipartimento =" + id;
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		rs = st.executeQuery(query);
		if (rs.next())
			dipartimento = new Dipartimento(rs.getInt("ID_Dipartimento"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email"));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return dipartimento;
	}

	/**
	 * Carica la lista dei dipartimenti dal database
	 * 
	 * @return ritorna la lista contenente tutti i dipartimenti
	 */
	public ArrayList<Dipartimento> getListaDipartimenti() throws SQLException {
		String query = "select * from dipartimento";
		ArrayList<Dipartimento> dipartimenti = new ArrayList<Dipartimento>();
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
			dipartimenti.add(new Dipartimento(rs.getInt("ID_Dipartimento"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email")));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return dipartimenti;
	}

	/**
	 * Cerca il ricercatore che è direttore del dipartimento
	 * 
	 * @param dip dipartimento di cui si vuole cercare il direttore
	 * @return ritorna il ricercatore che è direttore del dipartimento
	 */
	public Ricercatore getDirettoreDipartimento(Dipartimento dip) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		Ricercatore direttore = null;
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT ricercatore.*" + " FROM dipartimento JOIN ricercatore" + " WHERE ricercatore.ID_Dipartimento= " + dip.getID_Dipartimento() + " AND ricercatore.Ruolo = 'Direttore'";

			// logger.info(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);

			if (rsRicerca.next()) {
				int id_ricercatore = rsRicerca.getInt("ID_Ricercatore");
				String nome = rsRicerca.getString("Nome");
				String cognome = rsRicerca.getString("Cognome");
				String codice_fiscale = rsRicerca.getString("Codice_Fiscale");
				String data_nascita = rsRicerca.getString("Data_Nascita");
				String citta_nascita = rsRicerca.getString("Citta_Nascita");
				String provincia_nascita = rsRicerca.getString("Provincia_Nascita");
				String matricola = rsRicerca.getString("Matricola");
				String email = rsRicerca.getString("Email");
				String data_inizio_servizio = rsRicerca.getString("Data_Inizio_Servizio");
				int id_dipartimento = rsRicerca.getInt("ID_Dipartimento");
				int id_area_scientifica = rsRicerca.getInt("ID_Area_Scientifica");
				String ruolo = rsRicerca.getString("Ruolo");
				String sesso = rsRicerca.getString("Sesso");

				direttore = new Ricercatore();

				direttore.setID_Ricercatore(id_ricercatore);
				direttore.setNome(nome);
				direttore.setCognome(cognome);
				direttore.setCodice_Fiscale(codice_fiscale);
				direttore.setData_Nascita(data_nascita);
				direttore.setCitta_Nascita(citta_nascita);
				direttore.setProvincia_Nascita(provincia_nascita);
				direttore.setMatricola(matricola);
				direttore.setEmail(email);
				direttore.setData_Inizio_Servizio(data_inizio_servizio);
				direttore.setID_Dipartimento(id_dipartimento);
				direttore.setID_Area_Scientifica(id_area_scientifica);
				direttore.setRuolo(ruolo);
				direttore.setSesso(sesso);
				statement.close();
				DBConnectionPool.releaseConnection(connection);
				return direttore;
			}
			else {
				return null;
			}

		}
		catch (SQLException e) {
			// logger.severe("probelmi con la ricerca");
			e.printStackTrace();
		}
		return direttore;
	}
}
