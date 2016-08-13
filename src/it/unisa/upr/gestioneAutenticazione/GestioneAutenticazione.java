/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneAutenticazione;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.unisa.upr.commons.DBConstants;
import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.*;

/**
 * La classe fa da facciata alle operazioni di login/logout
 * 
 */
public class GestioneAutenticazione implements IFacadeGestioneAutenticazione {

	private IAccountManager accountManager;
	private IAmministratoreManager amministratoreManager;
	private IResponsabileManager responsabileManager;
	private IRicercatoreManager ricercatoreManager;
	private static GestioneAutenticazione manager;

	public static GestioneAutenticazione getInstance() {
		if (manager == null) {
			manager = new GestioneAutenticazione();
		}
		return manager;
	}

	public GestioneAutenticazione() {
		accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
		amministratoreManager = (IAmministratoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AMMINISTRATORE);
		responsabileManager = (IResponsabileManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RESPONSABILE);
		ricercatoreManager = (IRicercatoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RICERCATORE);
	}

	/**
	 * Logga un account nel sistema, verificandone le credenziali
	 * 
	 * @param Username username
	 * @param Password password
	 * @return ritorna un oggetto di tipo account, in base alla tipologia
	 *         dell'utente loggato
	 */
	@Override
	public Object login(String Username, String Password) throws SQLException {
		// controlla se le credenziali di accesso rispettano il formato definito
		// nel RAD
		if (!controllaCredenziali(Username, Password))
			return null;

		Account account = accountManager.getAccount(Username, Password);

		if (account == null) {
			return null;
		}

		String tipologia = account.getTipologia();

		if (tipologia.equalsIgnoreCase(DBConstants.ACCOUNT_TIPOLOGIA_RICERCATORE)) {
			Ricercatore ricercatore = ricercatoreManager.getRicercatore(account);
			return ricercatore;
		}

		if (tipologia.equalsIgnoreCase(DBConstants.ACCOUNT_TIPOLOGIA_AMMINISTRATORE)) {
			Amministratore amministratore = amministratoreManager.getAmministratore(account);
			return amministratore;
		}

		if (tipologia.equalsIgnoreCase(DBConstants.ACCOUNT_TIPOLOGIA_RESPONSABILE)) {
			Responsabile responsabile = responsabileManager.getResponsabile(account);
			return responsabile;
		}

		return null;
	}

	/**
	 * Controlla che le credenziali inserire siano nel formato corretto
	 * @param Username username
	 * @param Password password
	 * @return true se il formato è corretto, false altrimenti
	 */
	private static boolean
			controllaCredenziali(String Username, String Password) {
		if (Username.length() < 2 || Username.length() > 20 || Password.length() < 2 || Password.length() > 20)
			return false;
		return true;
	}

	/**
	 * Invalida la sessione di un utente, sloggandolo dal sistema
	 * @param request sessione dell'utente
	 */
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	/**
	 * Carica la lista degli account del sistema
	 * @return ritorna la lista degli account
	 */
	@Override
	public ArrayList<Account> getListaAccount() {
		return accountManager.getListaAccount();
	}

}
