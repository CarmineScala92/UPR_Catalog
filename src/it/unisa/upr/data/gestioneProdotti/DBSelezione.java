/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.storage.DBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelezione implements ISelezioneManager {

	@Override
	public boolean eliminaSelezione(Selezione c) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		String delete;
		try {
			connection = DBConnectionPool.getConnection();
			delete = "delete from selezione where ID_Selezione="
					+ c.getID_Selezione();
			// logger.info(delete);
			statement = connection.prepareStatement(delete);
			int result = statement.executeUpdate(delete);
			connection.commit();
			statement.close();
			DBConnectionPool.releaseConnection(connection);
			if (result == 0)
				return false;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
