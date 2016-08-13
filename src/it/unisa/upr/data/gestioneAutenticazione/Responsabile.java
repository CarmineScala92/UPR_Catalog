/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

/**
 * Una specializzazione di account, è l'account di tipo Responsabile
 * 
 */
public class Responsabile extends Account {

	/**
	 * 
	 * @param ID id dell'account
	 * @param UN username
	 * @param PSS password
	 * @param TP tipologia dell'account
	 * @param ID_RSP id del responsabile
	 * @param NM nome
	 * @param CGN cognome
	 */
	public Responsabile(int ID, String UN, String PSS, String TP, int ID_RSP,
			String NM, String CGN) {
		super(ID, UN, PSS, TP);
		ID_Responsabile = ID_RSP;
		Nome = NM;
		Cognome = CGN;
	}

	public Responsabile() {
		super();
	}

	/**
	 * 
	 * @return ritorna l'id del responsabile
	 */
	public int getID_Responsabile() {
		return ID_Responsabile;
	}

	/**
	 * Setta l'id del responsabile all'interno del bean
	 * 
	 * @param iD_Responsabile id da settare
	 */
	public void setID_Responsabile(int iD_Responsabile) {
		ID_Responsabile = iD_Responsabile;
	}

	/**
	 * 
	 * @return ritorna il nome del responsabile
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Setta il nome del responsabile all'interno del bean
	 * 
	 * @param nome nome da settare
	 */
	public void setNome(String nome) {
		Nome = nome;
	}

	/**
	 * 
	 * @return ritorna il cognome del responsabile
	 */
	public String getCognome() {
		return Cognome;
	}

	/**
	 * Setta il cognome del responsabile all'interno del bean
	 * 
	 * @param cognome cognome da settare
	 */
	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	/**
	 * ID del responsabile
	 */
	int ID_Responsabile;
	/**
	 * Nome del responsabile
	 */
	private String Nome;
	/**
	 * Cognome del responsabile
	 */
	private String Cognome;

}
