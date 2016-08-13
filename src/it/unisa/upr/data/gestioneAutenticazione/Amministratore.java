/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

/**
 * Una specializzazione di account, è l'account di tipo Amministratore
 * 
 */
public class Amministratore extends Account {
	/**
	 *
	 * @param ID id nella tabella account
	 * @param UN username
	 * @param PSS password
	 * @param TP tipologia
	 * @param ID_AMN id nella tabella amministratore
	 * @param NM nome
	 * @param CGN cognome
	 */
	public Amministratore(int ID, String UN, String PSS, String TP, int ID_AMN,
			String NM, String CGN) {
		super(ID, UN, PSS, TP);
		ID_Amministratore = ID_AMN;
		Nome = NM;
		Cognome = CGN;
	}

	public Amministratore() {
		super();
	}
	
	/**
	 * 
	 * @return ritorna l'id dell'amministratore
	 */
	public int getID_Amministratore() {
		return ID_Amministratore;
	}

	/**
	 * setta l'id amministratore nel bean
	 * @param iD_Amministratore id da settare
	 */
	public void setID_Amministratore(int iD_Amministratore) {
		ID_Amministratore = iD_Amministratore;
	}

	/**
	 * 
	 * @return ritorna il nome dell'amministratore
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * setta il nome dell'amministratore all'interno del bean
	 * @param nome nome da settare
	 */
	public void setNome(String nome) {
		Nome = nome;
	}

	/**
	 * 
	 * @return ritorna il cognome dell'amministratore
	 */
	public String getCognome() {
		return Cognome;
	}

	/**
	 * setta il cognome dell'amministratore all'interno del bean
	 * @param cognome cognome da settare
	 */
	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	/**
	 * ID all'interno della tabella amministratore
	 */
	int ID_Amministratore;
	/**
	 * Nome dell'amministratore
	 */
	private String Nome;
	/**
	 * Cognome dell'amministratore
	 */
	private String Cognome;

}
