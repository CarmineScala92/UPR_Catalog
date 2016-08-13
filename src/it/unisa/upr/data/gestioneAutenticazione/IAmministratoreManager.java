/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di AmministratoreManager
 * @see DBAmministratore
 */
public interface IAmministratoreManager extends IManager {

	public Amministratore getAmministratore(Account account)
			throws SQLException;

	public boolean eliminaAmministratore(Amministratore p);

	boolean inserisciAmministratore(Amministratore a);

	public ArrayList<Amministratore> getAmministratore(String nome,
			String cognome) throws SQLException;

	public boolean modificaAmministratore(Amministratore account);

}
