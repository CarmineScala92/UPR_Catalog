/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

import it.unisa.upr.commons.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Operazioni associate all'interfaccia dell'AccountManager
 * @see DBAccount
 */
public interface IAccountManager extends IManager {

	public String verificaTipologia(String USR, String PSS) throws SQLException;

	public boolean isPresentAccount(String USR, String PSS) throws SQLException;

	public Account getAccount(String user, String pass) throws SQLException;

	public boolean eliminaAccount(Account p);

	boolean inserisciAccount(Account a);

	public int getUltimoIDAccount() throws SQLException;

	public Account visualizzaAccount(int iD_Account) throws SQLException;

	public ArrayList<Account> ricercaAccount(String nome, String cognome);

	public boolean modificaAccount(Account account);

	public ArrayList<Account> getListaAccount();

}
