/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;
/**
 * Operazioni associate all'interfaccia del ResponsabileManager
 * @see DBResponsabile
 */
public interface IResponsabileManager extends IManager {

	public Responsabile getResponsabile(Account account) throws SQLException;

	public boolean eliminaResponsabile(Responsabile p);

	boolean inserisciResponsabile(Responsabile r);

	public ArrayList<Responsabile> getResponsabile(String nome, String cognome)
			throws SQLException;

	public boolean modificaResponsabile(Responsabile account);
}
