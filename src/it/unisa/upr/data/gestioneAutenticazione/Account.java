/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

/**
 * La classe è la rappresentazione di un Account del sistema.
 * E' un JavaBean
 * 
 */
public class Account {

	public Account() {

	}

	/**
	 * 
	 * @param ID id all'interno del database
	 * @param UN username
	 * @param PSS password
	 * @param TP tipologia di account
	 */
	public Account(int ID, String UN, String PSS, String TP) {
		ID_Account = ID;
		Username = UN;
		Password = PSS;
		Tipologia = TP;
	}

	/**
	 * 
	 * @return Ritorna l'id dell'account
	 */
	public int getID_Account() {
		return ID_Account;
	}

	/**
	 * Setta l'id dell'account all'interno del bean
	 * 
	 * @param iD_Account id da settare
	 */
	public void setID_Account(int iD_Account) {
		ID_Account = iD_Account;
	}

	/**
	 * 
	 * @return ritorna l'username associato all'account
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * Setta l'username all'interno del bean
	 * 
	 * @param username username da settare
	 */
	public void setUsername(String username) {
		Username = username;
	}

	/**
	 * 
	 * @return ritorna la password associata all'account
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * Setta la password all'interno del bean
	 * 
	 * @param password password da settare
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * 
	 * @return ritorna la tipologia dell'account
	 */
	public String getTipologia() {
		return Tipologia;
	}

	/**
	 * Setta la tipologia all'interno del bean
	 * 
	 * @param tipologia tipologia da settare
	 */
	public void setTipologia(String tipologia) {
		Tipologia = tipologia;
	}

	/**
	 * ID dell'account
	 */
	int ID_Account;
	/**
	 * Username dell'account
	 */
	private String Username;
	/**
	 * Password dell'account
	 */
	private String Password;
	/**
	 * Tipologia dell'account
	 */
	private String Tipologia;

}
