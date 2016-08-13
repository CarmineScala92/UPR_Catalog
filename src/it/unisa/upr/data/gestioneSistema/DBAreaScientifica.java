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
 * La classe fornisce l'implementazione dell'AreaScientificaManager
 * Si occupa di tutte le operazioni relative alle aree scientifiche all'interno
 * del database
 * 
 */
public class DBAreaScientifica implements IAreaScientificaManager {

	// Implementazione Design Pattern Singleton
	private static DBAreaScientifica areaManager;

	private DBAreaScientifica() {}

	/**
	 * 
	 * @return ritorna l'istanza del manager
	 */
	public static synchronized DBAreaScientifica getInstance() {
		if (areaManager == null)
			areaManager = new DBAreaScientifica();

		return areaManager;
	}

	// Fine implementazione Design Pattern Singleton

	/**
	 * Cerca un area scientifica in base all'id all'interno del database
	 * 
	 * @param id id dell'area da cercare
	 * @return ritorna l'area scientifica se è stata trovata, null altrimenti
	 */
	public AreaScientifica getAreaScientifica(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		AreaScientifica area = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_AREA_SCIENTIFICA + " WHERE ID_Area_Scientifica = " + id;
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
			int id_area_scientifica = rsRicerca.getInt("ID_Area_Scientifica");
			String codice = rsRicerca.getString("Codice");
			String nome = rsRicerca.getString("Nome");
			String telefono = rsRicerca.getString("Telefono");
			String fax = rsRicerca.getString("Fax");
			String sito = rsRicerca.getString("Sito");
			String email = rsRicerca.getString("Email");

			area = new AreaScientifica(id_area_scientifica, codice, nome, telefono, fax, sito, email);

			return area;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return area;
	}

	/**
	 * Inserisce un'area scientica all'interno del database
	 * 
	 * @param codice codice dell'area da inserire
	 * @param nome nome dell'area scientifica
	 * @param telefono numero di telefono dell'area scientifica
	 * @param fax numero di fax dell'area scientifica
	 * @param sito indirizzo del sito web dell'area scientifica
	 * @param email indirizzo email dell'area scientifica
	 * @return 0 se l'inserimento non è avvenuto, un numero maggiore di 0 se
	 *         l'inserimento è stato effettuato
	 */
	public int inserimentoAreaScientifica(String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException {
		Connection con = DBConnectionPool.getConnection();
		String query = "";
		if (sito.compareTo("") == 0 && email.compareTo("") == 0) { // TUTTI E
																	// DUE NULLI
			query = "insert into area_scientifica ( `Codice` , `Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + codice + "', '" + nome + "', '" + telefono + "', '" + fax + "', " + "NULL" + ", " + "NULL" + ") ";
		}
		else {
			if (email.compareTo("") == 0) { // SIGNIFICA CHE L'EMAIL E' NULLA
				query = "insert into area_scientifica (`Codice` , `Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + codice + "', '" + nome + "', '" + telefono + "', '" + fax + "', '" + sito + "', " + "NULL" + ") ";
			}
			else if (sito.compareTo("") == 0) { // SIGNIFICA CHE SITO E' NULLO
				query = "insert into area_scientifica (`Codice` , `Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + codice + "', '" + nome + "', '" + telefono + "', '" + fax + "', " + "NULL" + ", '" + email + "') ";
			}
			else { // NESSUNO DEI DUE E' NULLO
				query = "insert into area_scientifica (`Codice` , `Nome`, `Telefono`, `Fax`, `Sito`, `Email`) values " + "('" + codice + "', '" + nome + "', '" + telefono + "', '" + fax + "', '" + sito + "', '" + email + "') ";
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
	 * Modifica un'area scientica all'interno del database
	 * 
	 * @param Id id dell'area scientifica da modificare
	 * @param codice codice dell'area da inserire
	 * @param nome nome dell'area scientifica
	 * @param telefono numero di telefono dell'area scientifica
	 * @param fax numero di fax dell'area scientifica
	 * @param sito indirizzo del sito web dell'area scientifica
	 * @param email indirizzo email dell'area scientifica
	 * @return 0 se la modifica non è avvenuta, un numero maggiore di 0 se la
	 *         modifica è stato effettuata
	 */
	public int modificaAreaScientifica(int Id, String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException {
		String query = "";
		Connection con = DBConnectionPool.getConnection();
		if (sito.compareTo("") == 0 && email.compareTo("") == 0) { // TUTTI E
																	// DUE NULLI
			query = "update area_scientifica set Codice = '" + codice + "' , Nome = '" + nome + "' , Telefono = '" + telefono + "' , Fax ='" + fax + "', Sito =" + "NULL" + " ,Email =" + "NULL" + " where ID_Area_Scientifica =" + Id + "";
		}
		else {
			if (email.compareTo("") == 0) { // SIGNIFICA CHE L'EMAIL E' NULLA
				query = "update area_scientifica set Codice = '" + codice + "' , Nome = '" + nome + "' , Telefono = '" + telefono + "' ,Fax ='" + fax + "' , Sito ='" + sito + "' ,Email =" + "NULL" + " where ID_Area_Scientifica =" + Id + "";
			}
			else if (sito.compareTo("") == 0) { // SIGNIFICA CHE IL SITO E'
												// NULLO
				query = "update area_scientifica set Codice = '" + codice + "' , Nome = '" + nome + "' , Telefono = '" + telefono + "' ,Fax ='" + fax + "' , Sito =" + "NULL" + " ,Email ='" + email + "' where ID_Area_Scientifica =" + Id + "";
			}
			else { // NESSUNO DEI DUE E' NULLO
				query = "update area_scientifica set Codice = '" + codice + "' , Nome = '" + nome + "' , Telefono = '" + telefono + "' ,Fax ='" + fax + "' , Sito ='" + sito + "' ,Email ='" + email + "' where ID_Area_Scientifica =" + Id + "";
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
	 * Elimina un'area scientifica dal database
	 * 
	 * @param Id id dell'area scientifica da eliminare
	 * @return 0 se l'eliminazione non è stata effettuata, altrimenti un numero
	 *         maggiore di 0
	 */
	public int eliminaAreaScientifica(int Id) throws SQLException {
		Connection con = DBConnectionPool.getConnection();
		String query = "delete from area_scientifica where ID_Area_Scientifica ='" + Id + "'";
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
	 * Carica la lista di ricercatori che appartengono all'area
	 * 
	 * @param area area scientifica di cui si vuole la lista dei ricercatori
	 * @return ritorna la lista dei ricercatori che appartengono all'area
	 *         scientifica
	 */
	public ArrayList<Ricercatore> caricaRicercatori(AreaScientifica area) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Ricercatore> listaRicercatori = new ArrayList<Ricercatore>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT *" + " FROM area_scientifica,ricercatore,account" + " WHERE ricercatore.ID_Ricercatore=account.ID_Account" + " AND ricercatore.ID_Area_Scientifica=" + area.getID_Area_Scientifica();

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
	 * Carica le informazioni relative ad un area scientifica all'interno di un
	 * bean
	 * 
	 * @param id id dell'area scientifica di cui si vogliono le informazioni
	 * @return ritorna il bean contenente le informazioni dell'area scientifica,
	 *         null se l'area non è presente
	 */
	public AreaScientifica visualizzaAreaScientifica(int id)
			throws SQLException {
		AreaScientifica AreaScientifica = null;
		String query = "select * from  area_scientifica where ID_Area_Scientifica = '" + id + "'";
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if (rs.next())
			AreaScientifica = (new AreaScientifica(rs.getInt("ID_Area_Scientifica"), rs.getString("Codice"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email")));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return AreaScientifica;
	}

	/**
	 * Carica la lista delle aree scientifiche presenti
	 * 
	 * @return ritorna la lista contenente tutte le aree scientifiche presenti
	 *         nel database
	 */
	public ArrayList<AreaScientifica> getListaAreeScientifiche()
			throws SQLException {
		String query = "select * from area_scientifica";
		ArrayList<AreaScientifica> areeScientifiche = new ArrayList<AreaScientifica>();
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
			areeScientifiche.add(new AreaScientifica(rs.getInt("ID_Area_Scientifica"), rs.getString("Codice"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email")));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return areeScientifiche;
	}

	/**
	 * Ricerca un'area scientifica in base al nome
	 * 
	 * @param nome nome dell'area scientifica da cercare
	 * @return ritorna la lista delle aree scientifiche che soddisfano i
	 *         parametri di ricerca
	 */
	@Override
	public ArrayList<AreaScientifica> ricercaAreaScientifica(String nome) {
		ArrayList<AreaScientifica> areeScientifiche = new ArrayList<AreaScientifica>();
		String query = "select * from area_scientifica ";
		if (!(nome.compareTo("") == 0))
			query = query + "where Nome LIKE '%" + nome + "%'";

		try {
			Connection con = DBConnectionPool.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				areeScientifiche.add(new AreaScientifica(rs.getInt("ID_Area_Scientifica"), rs.getString("Codice"), rs.getString("Nome"), rs.getString("Telefono"), rs.getString("Fax"), rs.getString("Sito"), rs.getString("Email")));
			rs.close();
			con.commit();
			st.close();
			DBConnectionPool.releaseConnection(con);
			return areeScientifiche;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return areeScientifiche;
	}
}
