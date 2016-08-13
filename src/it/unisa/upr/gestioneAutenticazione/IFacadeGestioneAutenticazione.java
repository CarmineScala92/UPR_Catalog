/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneAutenticazione;

import it.unisa.upr.commons.IFacade;
import it.unisa.upr.data.gestioneAutenticazione.Account;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
/**
 * Interfaccia di GestioneAutenticazione
 * @see GestioneAutenticazione
 */
public interface IFacadeGestioneAutenticazione extends IFacade {

	/**
	 * controlla username e password e istanzia (ritorna) un ricercatore, un
	 * amministratore o un responsabile a seconda della tipologia, è richiesto
	 * un cast esplicito agli utenti del metodo dopo l'uscita dal metodo
	 * 
	 * @param username
	 * @param password
	 * @return un oggetto con tutte le credenziali
	 * @throws SQLException
	 */
	public Object login(String username, String password) throws SQLException;

	/**
	 * invalida la sessione
	 * 
	 * @param request
	 */
	public void logout(HttpServletRequest request);

	/**
	 * 
	 * @return ritorna la lista degli account del sistema
	 */
	public ArrayList<Account> getListaAccount();

}
