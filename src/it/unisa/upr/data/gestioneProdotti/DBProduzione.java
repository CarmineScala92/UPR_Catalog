/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBProduzione implements IProduzioneManager {

	// Implementazione Design Pattern Singleton
	private static DBProduzione produzioneManager;

	private DBProduzione() {
	}

	public static synchronized DBProduzione getInstance() {
		if (produzioneManager == null)
			produzioneManager = new DBProduzione();

		return produzioneManager;
	}

	// Fine implementazione Design Pattern Singleton

	@Override
	public boolean elimina(Prodotto c) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from produzione where ID_Prodotto="
					+ c.getID_Prodotto();
			// logger.info(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			/*
			 * if(result==0) return false;
			 */
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean elimina(Account c) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from produzione where ID_Ricercatore="
					+ c.getID_Account();
			// logger.info(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			/*
			 * if(result==0) return false;
			 */
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean inserisciProduzione(Produzione p) {
		java.sql.Connection connection = null;
		Statement statement = null;
		String insert;
		try {
			connection = DBConnectionPool.getConnection();
			insert = "INSERT INTO produzione(ID_Ricercatore,ID_Prodotto)"
					+ " VALUES(" + p.getID_Ricercatore() + ","
					+ p.getID_Prodotto() + ")";

			statement = connection.createStatement();
			int result = statement.executeUpdate(insert); // restituisce il
															// numero di righe
															// inserite
			System.out.println(insert);
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

	public ArrayList<Produzione> getProduzioni(Prodotto c) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DBConnectionPool.getConnection();

			String query = ("select * from produzione where ID_Prodotto=" + c
					.getID_Prodotto());
			Statement statement = connection.createStatement();
			ResultSet rsRicerca = statement.executeQuery(query);
			ArrayList<Produzione> lista = new ArrayList<Produzione>();
			Produzione comp;
			while (rsRicerca.next()) {
				comp = new Produzione(rsRicerca.getInt("ID_Prodotto"),
						rsRicerca.getInt("ID_Ricercatore"));
				lista.add(comp);
			}
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);

			return lista;
		} catch (SQLException e) {
			System.out.println("Sto nell catch");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Ricercatore> getRicercatoriProduzione(Prodotto c) {
		try {
			Connection connection = DBConnectionPool.getConnection();

			String query = ("select R1.Nome , R1.Cognome "
					+ " from prodotto P1 join produzione PR1 on (P1.ID_Prodotto=PR1.ID_Prodotto)  "
					+ " join ricercatore R1 on (R1.ID_Ricercatore=PR1.ID_Ricercatore) "
					+ " where P1.ID_Prodotto=" + c.getID_Prodotto());
			Statement statement = connection.createStatement();
			ResultSet rsRicerca = statement.executeQuery(query);
			ArrayList<Ricercatore> lista = new ArrayList<Ricercatore>();
			Ricercatore ric;
			while (rsRicerca.next()) {
				ric = new Ricercatore();
				ric.setNome(rsRicerca.getString("Nome"));
				System.out.println(rsRicerca.getString("Nome"));
				ric.setCognome(rsRicerca.getString("Cognome"));
				System.out.println(rsRicerca.getString("Cognome"));
				lista.add(ric);
			}
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			System.out.println(lista.size());
			return lista;
		} catch (SQLException e) {
			System.out.println("Sto nell catch");
			e.printStackTrace();
			return null;
		}
	}

}
