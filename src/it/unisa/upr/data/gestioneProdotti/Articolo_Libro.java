/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean della tipologia di prodotto Articolo su libro
 * 
 */
public class Articolo_Libro extends Prodotto {

	private String Nome_Volume;
	private String Autori_Volume;
	private String Editore;
	private String Citta_Editore;
	private String Paese_Editore;
	private String ISBN;
	private String Pagine_Riferimento;
	private String DOI;
	private String Keywords;

	public Articolo_Libro() {
		super();

	}

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto all'interno del database
	 * @param iD_Proprietario
	 *            id del proprietario
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori (non presenti nel sistema) del prodotto
	 * @param anno_Pubblicazione
	 *            anno di pubblicazione
	 * @param abstract1
	 *            abstract del prodotto
	 * @param pubblico
	 *            indica se il prodotto è pubblico (0-privato 1-pubblico)
	 * @param stato
	 *            stato di avanzamento del prodotto (draft, completo, validato,
	 *            ...)
	 * @param tipologia
	 *            tipologia del prodotto (articolo_libro, articolo_rivista,
	 *            brevetto, ...)
	 * @param uRL
	 *            url del file associato al prodotto
	 * @param note
	 *            note di validazione
	 * @param nome_Volume
	 *            nome del volume sul quale è stato pubblicato il prodotto
	 * @param autori_Volume
	 *            autori del volume
	 * @param editore
	 *            editore del volume
	 * @param citta_Editore
	 *            città dell'editore
	 * @param paese_Editore
	 *            nazione dell'editore
	 * @param iSBN
	 *            isbn del volume
	 * @param pagine_Riferimento
	 *            pagine di riferimento
	 * @param dOI
	 *            doi
	 * @param keywords
	 *            keywords
	 */
	public Articolo_Libro(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note, String nome_Volume, String autori_Volume,
			String editore, String citta_Editore, String paese_Editore,
			String iSBN, String pagine_Riferimento, String dOI, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Nome_Volume = nome_Volume;
		Autori_Volume = autori_Volume;
		Editore = editore;
		Citta_Editore = citta_Editore;
		Paese_Editore = paese_Editore;
		ISBN = iSBN;
		Pagine_Riferimento = pagine_Riferimento;
		DOI = dOI;
		Keywords = keywords;
	}

	public String toString() {
		return super.toString() + ", ArticoloLibro [ Nome_Volume="
				+ Nome_Volume + ", Autori_Volume=" + Autori_Volume
				+ ", Editore=" + Editore + ", Citta_Editore=" + Citta_Editore
				+ ", Paese_Editore=" + Paese_Editore + ", ISBN=" + ISBN
				+ ", Pagine_Riferimento=" + Pagine_Riferimento + ", DOI=" + DOI
				+ ", Keywords=" + Keywords + "]";
	}

	/**
	 * 
	 * @return ritorna il nome del volume
	 */
	public String getNome_Volume() {
		return Nome_Volume;
	}

	/**
	 * Setta il nome del volume all'interno del bean
	 * 
	 * @param nome_Volume
	 *            nome del volume da settare
	 */
	public void setNome_Volume(String nome_Volume) {
		Nome_Volume = nome_Volume;
	}

	/**
	 * 
	 * @return ritorna i nomi degli autori del volume
	 */
	public String getAutori_Volume() {
		return Autori_Volume;
	}

	/**
	 * Setta il nome degli autori del volume all'interno del bean
	 * 
	 * @param autori_Volume
	 *            nome degli autori del volume da settare
	 */
	public void setAutori_Volume(String autori_Volume) {
		Autori_Volume = autori_Volume;
	}

	/**
	 * 
	 * @return ritorna il nome dell'editore del volume
	 */
	public String getEditore() {
		return Editore;
	}

	/**
	 * Setta il nome dell'editore all'interno del bean
	 * 
	 * @param editore
	 *            nome dell'editore da settare
	 */
	public void setEditore(String editore) {
		Editore = editore;
	}

	/**
	 * 
	 * @return ritorna la città dell'editore
	 */
	public String getCitta_Editore() {
		return Citta_Editore;
	}

	/**
	 * Setta il nome della città dell'editore all'interno del bean
	 * 
	 * @param citta_Editore
	 *            nome della città da settare
	 */
	public void setCitta_Editore(String citta_Editore) {
		Citta_Editore = citta_Editore;
	}

	/**
	 * 
	 * @return ritorna la nazione dell'editore
	 */
	public String getPaese_Editore() {
		return Paese_Editore;
	}

	/**
	 * Setta la nazione dell'editore
	 * 
	 * @param paese_Editore
	 *            nazione dell'editore da settare
	 */
	public void setPaese_Editore(String paese_Editore) {
		Paese_Editore = paese_Editore;
	}

	/**
	 * 
	 * @return ritorna l'isbn del volume
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Setta l'isbn del volume nel bean
	 * 
	 * @param iSBN
	 *            isbn da settare
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * 
	 * @return ritorna le pagine di riferimento
	 */
	public String getPagine_Riferimento() {
		return Pagine_Riferimento;
	}

	/**
	 * Setta le pagine di riferimento nel bean
	 * 
	 * @param pagine_Riferimento
	 *            pagine di riferimento da settare
	 */
	public void setPagine_Riferimento(String pagine_Riferimento) {
		Pagine_Riferimento = pagine_Riferimento;
	}

	/**
	 * 
	 * @return ritorna il DOI del prodotto
	 */
	public String getDOI() {
		return DOI;
	}

	/**
	 * Setta il DOI nel bean
	 * 
	 * @param dOI
	 *            DOI da settare
	 */
	public void setDOI(String dOI) {
		DOI = dOI;
	}

	/**
	 * 
	 * @return ritorna le keywords
	 */
	public String getKeywords() {
		return Keywords;
	}

	/**
	 * Setta le keywords nel bean
	 * 
	 * @param keywords
	 *            keywords da settare
	 */
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

}
