/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneSistema;

/**
 * La classe è un bean dell'entità Area Scientifica
 * 
 */
public class AreaScientifica {

	public AreaScientifica() {

	}

	/**
	 * 
	 * @param iDArea id dell'area all'interno del database
	 * @param codice codice dell'area scientifica
	 * @param nome nome dell'area scientifica
	 * @param telefono numero telefonico dell'area scientifica
	 * @param fax numero di fax dell'area scientifica
	 * @param sito sito web dell'area scientifica
	 * @param email indirizzo email dell'area scientifica
	 */
	public AreaScientifica(int iDArea, String codice, String nome,
			String telefono, String fax, String sito, String email) {
		this.ID_Area_Scientifica = iDArea;
		this.Codice = codice;
		this.Nome = nome;
		this.Telefono = telefono;
		this.Fax = fax;
		this.Sito = sito;
		this.Email = email;
	}

	/**
	 * 
	 * @return ritorna l'id del database dell'area scientifica
	 */
	public int getID_Area_Scientifica() {
		return ID_Area_Scientifica;
	}

	/**
	 * Setta l'id dell'area scientifica all'interno del bean
	 * 
	 * @param iD_Area_Scientifica id da settare
	 */
	public void setID_Area_Scientifica(int iD_Area_Scientifica) {
		this.ID_Area_Scientifica = iD_Area_Scientifica;
	}

	/**
	 * 
	 * @return ritorna il codice dell'area scientifica
	 */
	public String getCodice() {
		return Codice;
	}

	/**
	 * Setta il codice dell'area all'interno del bean
	 * 
	 * @param codice codice da settare
	 */
	public void setCodice(String codice) {
		this.Codice = codice;
	}

	/**
	 * 
	 * @return ritorna il nome dell'area scientifica
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Setta il nome dell'area scientifica all'interno del bean
	 * 
	 * @param nome nome da settare
	 */
	public void setNome(String nome) {
		this.Nome = nome;
	}

	/**
	 * 
	 * @return ritorna il numero telefonico dell'area scientifica
	 */
	public String getTelefono() {
		return Telefono;
	}

	/**
	 * Setta il numero di telefono dell'area scientifica all'interno del bean
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}

	/**
	 * 
	 * @return ritorna il numero di fax dell'area scientifica
	 */
	public String getFax() {
		return Fax;
	}

	/**
	 * Setta il numero di fax dell'area scientifica all'interno del bean
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		this.Fax = fax;
	}

	/**
	 * 
	 * @return ritorna il sito web dell'area scientifica
	 */
	public String getSito() {
		return Sito;
	}

	/**
	 * Setta l'indirizzo del sito web dell'area scientifica all'interno del bean
	 * 
	 * @param sito sito web da settare
	 */
	public void setSito(String sito) {
		this.Sito = sito;
	}

	/**
	 * 
	 * @return ritorna l'indirizzo email dell'area scientifica
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Setta l'indirizzo email dell'area scientifica
	 * 
	 * @param email indirizzo email da settare
	 */
	public void setEmail(String email) {
		this.Email = email;
	}

	private int ID_Area_Scientifica;
	private String Codice;
	private String Nome;
	private String Telefono;
	private String Fax;
	private String Sito;
	private String Email;
}
