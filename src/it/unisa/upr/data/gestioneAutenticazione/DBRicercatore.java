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

public class DBRicercatore implements IRicercatoreManager {

	// Implementazione Design Pattern Singleton
	private static DBRicercatore manager;

	private DBRicercatore() {}

	public static synchronized DBRicercatore getInstance() {
		if (manager == null)
			manager = new DBRicercatore();

		return manager;
	}

	// Fine implementazione Design Pattern Singleton

	public Ricercatore getRicercatore(Account acc) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Ricercatore ricercatore = null;
		String query;
		ResultSet rsRicerca = null;

		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_RICERCATORE + " WHERE ID_Ricercatore = " + acc.getID_Account();
			System.out.println(query);
			statement = connection.prepareStatement(query);
			rsRicerca = statement.executeQuery(query);
			System.out.println("query fatta");
			rsRicerca.next();
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

			ricercatore = new Ricercatore();

			ricercatore.setID_Account(acc.getID_Account());
			ricercatore.setUsername(acc.getUsername());
			ricercatore.setPassword(acc.getPassword());
			ricercatore.setTipologia(acc.getTipologia());

			ricercatore.setID_Ricercatore(id_ricercatore);
			ricercatore.setCognome(cognome);
			ricercatore.setNome(nome);
			ricercatore.setCodice_Fiscale(codice_fiscale);
			ricercatore.setData_Nascita(data_nascita);
			ricercatore.setCitta_Nascita(citta_nascita);
			ricercatore.setProvincia_Nascita(provincia_nascita);
			ricercatore.setMatricola(matricola);
			ricercatore.setEmail(email);
			ricercatore.setData_Inizio_Servizio(data_inizio_servizio);
			ricercatore.setID_Dipartimento(id_dipartimento);
			ricercatore.setID_Area_Scientifica(id_area_scientifica);
			ricercatore.setRuolo(ruolo);
			ricercatore.setSesso(sesso);

			return ricercatore;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return ricercatore;
	}

	public boolean modificaRicercatore(Ricercatore ric)

	{
		Connection connection = null;
		PreparedStatement statement = null;
		String query;

		try {
			connection = DBConnectionPool.getConnection();
			query = "UPDATE ricercatore SET Nome='" + ric.getNome() + "' ,Cognome='" + ric.getCognome() + "' ,Codice_Fiscale='" + ric.getCodice_Fiscale() + "' ,Data_Nascita='" + ric.getData_Nascita() + "' ,Citta_Nascita='" + ric.getCitta_Nascita() + "' ,Provincia_Nascita='" + ric.getProvincia_Nascita() + "' ,Email='" + ric.getEmail() + "' ,Data_Inizio_Servizio='" + ric.getData_Inizio_Servizio() + "' ,ID_Dipartimento='" + ric.getID_Dipartimento() + "' ,ID_Area_Scientifica='" + ric.getID_Area_Scientifica() + "' ,Sesso='" + ric.getSesso() + "' ,Ruolo='" + ric.getRuolo() + "' WHERE ID_Ricercatore=" + ric.getID_Ricercatore();
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

	public ArrayList<Ricercatore> getListaRicercatori() throws SQLException {

		String query = "select * from ricercatore R1 join account A1 on (R1.ID_Ricercatore = A1.ID_Account)";
		ArrayList<Ricercatore> ricercatori = new ArrayList<Ricercatore>();
		Connection con = DBConnectionPool.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
			ricercatori.add(new Ricercatore(rs.getInt("ID_Account"), rs.getString("Username"), rs.getString("Password"), rs.getString("Tipologia"), rs.getInt("ID_Ricercatore"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Codice_Fiscale"), rs.getString("Data_Nascita"), rs.getString("Citta_Nascita"), rs.getString("Provincia_Nascita"), rs.getString("Matricola"), rs.getString("Email"), rs.getString("Data_Inizio_Servizio"), rs.getInt("ID_Dipartimento"), rs.getInt("ID_Area_Scientifica"), rs.getString("Ruolo"), rs.getString("Sesso")));
		rs.close();
		con.commit();
		st.close();
		DBConnectionPool.releaseConnection(con);
		return ricercatori;
	}

	public boolean eliminaRicercatore(Ricercatore p) {
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {

			connection = DBConnectionPool.getConnection();
			delete = "delete from ricercatore where ID_Ricercatore=" + p.getID_Ricercatore();
			System.out.println(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();

			DBConnectionPool.releaseConnection(connection);

			return result > 0;

		}
		catch (SQLException e) {
			System.out.println("ERRORE ELIMINA RICERCATORE");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean inserisciRicercatore(Ricercatore r) {
		// TODO Auto-generated method stubConnection connection=null;
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

			insert = "INSERT INTO  ricercatore (ID_Ricercatore, Nome, Cognome, Codice_Fiscale, Data_Nascita," + " Citta_Nascita, Provincia_Nascita, Matricola, Sesso, Email, Data_Inizio_Servizio, ID_Dipartimento, ID_Area_Scientifica, Ruolo) " + "VALUES ('" + ID + "','" + r.getNome() + "','" + r.getCognome() + "','" + r.getCodice_Fiscale() + "','" + r.getData_Nascita() + "','" + r.getCitta_Nascita() + "" + "','" + r.getProvincia_Nascita() + "','" + r.getMatricola() + "','" + r.getSesso() + "','" + r.getEmail() + "','" + "" + r.getData_Inizio_Servizio() + "','" + r.getID_Dipartimento() + "','" + r.getID_Area_Scientifica() + "','" + "" + r.getRuolo() + "')";

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

	public ArrayList<Ricercatore> getRicercatore(String nome, String cognome)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String query;
		ResultSet rsRicerca = null;
		ArrayList<Ricercatore> ricercatori = new ArrayList<Ricercatore>();
		try {
			connection = DBConnectionPool.getConnection();
			query = "SELECT * FROM " + DBNames.TABLE_ACCOUNT + " JOIN " + DBNames.TABLE_RICERCATORE + " WHERE ID_Account = ID_Ricercatore ";
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
				ricercatori.add(new Ricercatore(rsRicerca.getInt("ID_Account"), rsRicerca.getString("Username"), rsRicerca.getString("Password"), rsRicerca.getString("Tipologia"), rsRicerca.getInt("ID_Ricercatore"), rsRicerca.getString("Nome"), rsRicerca.getString("Cognome"), rsRicerca.getString("Codice_Fiscale"), rsRicerca.getString("Data_Nascita"), rsRicerca.getString("Citta_Nascita"), rsRicerca.getString("Provincia_Nascita"), rsRicerca.getString("Matricola"), rsRicerca.getString("Email"), rsRicerca.getString("Data_Inizio_Servizio"), rsRicerca.getInt("ID_Dipartimento"), rsRicerca.getInt("ID_Area_Scientifica"), rsRicerca.getString("Ruolo"), rsRicerca.getString("Sesso")));

			return ricercatori;

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Errore");
		}
		finally {
			statement.close();
			DBConnectionPool.releaseConnection(connection);
		}

		return ricercatori;
	}

}
