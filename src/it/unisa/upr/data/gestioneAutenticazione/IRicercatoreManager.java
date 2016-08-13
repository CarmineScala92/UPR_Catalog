/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia del RicercatoreManager
 * @see DBRicercatore
 */
public interface IRicercatoreManager extends IManager {

	public Ricercatore getRicercatore(Account acc) throws SQLException;

	public ArrayList<Ricercatore> getListaRicercatori() throws SQLException;

	public boolean eliminaRicercatore(Ricercatore p);

	boolean inserisciRicercatore(Ricercatore r);

	public ArrayList<Ricercatore> getRicercatore(String nome, String cognome)
			throws SQLException;

	public boolean modificaRicercatore(Ricercatore ric);
}
