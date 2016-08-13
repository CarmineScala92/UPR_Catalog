/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneSistema;

/**
 * La classe è un bean dell'entità Dipartimento
 *
 */
public class Dipartimento {

	public Dipartimento() {

	}

	/**
	 * 
	 * @param idDipartimento id del dipartimento all'interno del database
	 * @param nome nome del dipartimento
	 * @param telefono numero di telefono del dipartimento
	 * @param fax numero di fax del dipartimento
	 * @param sito sito web del dipartimento
	 * @param email indirizzo email del dipartimento
	 */
	public Dipartimento(int idDipartimento, String nome, String telefono,
			String fax, String sito, String email) {
		this.ID_Dipartimento = idDipartimento;
		this.Nome = nome;
		this.Telefono = telefono;
		this.Fax = fax;
		this.Sito = sito;
		this.Email = email;
	}

	/**
	 * 
	 * @return ritorna il nome del dipartimento
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Setta il nome del dipartimento all'interno del bean
	 * @param nome nome da settare
	 */
	public void setNome(String nome) {
		this.Nome = nome;
	}

	/**
	 * 
	 * @return ritorna il numero di telefono del dipartimento
	 */
	public String getTelefono() {
		return Telefono;
	}

	/**
	 * Setta il numero telefonico del dipartimento all'interno del bean
	 * @param telefono numero da settare
	 */
	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}

	/**
	 * 
	 * @return ritorna il numero di fax del dipartimento
	 */
	public String getFax() {
		return Fax;
	}

	/**
	 * Setta il numero di fax del dipartimento nel bean
	 * @param fax numero di fax da settare
	 */
	public void setFax(String fax) {
		this.Fax = fax;
	}

	/**
	 * 
	 * @return ritorna l'indirizzo del sito web del dipartimento 
	 */
	public String getSito() {
		return Sito;
	}

	/**
	 * Setta l'indirizzo del sito web nel bean
	 * @param sito indirizzo del sito web da settare
	 */
	public void setSito(String sito) {
		this.Sito = sito;
	}

	/**
	 * 
	 * @return ritorna l'indirizzo email del dipartimento
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Setta l'indirizzo email nel bean
	 * @param email indirizzo email da settare
	 */
	public void setEmail(String email) {
		this.Email = email;
	}

	/**
	 * 
	 * @return ritorna l'id del database
	 */
	public int getID_Dipartimento() {
		return ID_Dipartimento;
	}

	/**
	 * Setta l'id del database nel bean
	 * @param iD_Dipartimento id da settare
	 */
	public void setID_Dipartimento(int iD_Dipartimento) {
		this.ID_Dipartimento = iD_Dipartimento;
	}

	private int ID_Dipartimento;
	private String Nome;
	private String Telefono;
	private String Fax;
	private String Sito;
	private String Email;
}
