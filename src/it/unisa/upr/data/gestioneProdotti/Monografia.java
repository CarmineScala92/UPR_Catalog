/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean della tipologia di prodotto Monografia
 * 
 */
public class Monografia extends Prodotto {

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto nel database
	 * @param iD_Proprietario
	 *            id del proprietario del dato
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori (non nel sistema) del prodotto
	 * @param anno_Pubblicazione
	 *            anno di pubblicazione del prodotto
	 * @param abstract1
	 *            abstract del prodotto
	 * @param pubblico
	 *            indica si il prodotto è pubblico (0-privato, 1-pubblico)
	 * @param stato
	 *            stato di avanzamento del prodotto
	 * @param tipologia
	 *            tipologia del prodotto
	 * @param uRL
	 *            url del file associato al prodotto
	 * @param note
	 *            note di validazione
	 * @param editore
	 *            editore del prodotto
	 * @param citta_Editore
	 *            città dell'editore
	 * @param paese_Editore
	 *            paese dell'editore
	 * @param pagine_Riferimento
	 *            pagine di riferimento
	 * @param iSBN
	 *            isbn del prodotto
	 * @param dOI
	 *            doi del prodotto
	 * @param keywords
	 *            keywords del prodotto
	 */
	public Monografia(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note, String editore, String citta_Editore,
			String paese_Editore, String pagine_Riferimento, String iSBN,
			String dOI, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Editore = editore;
		Citta_Editore = citta_Editore;
		Paese_Editore = paese_Editore;
		Pagine_Riferimento = pagine_Riferimento;
		ISBN = iSBN;
		DOI = dOI;
		Keywords = keywords;
	}

	public Monografia() {
	}

	/**
	 * 
	 * @return ritorna il nome dell'editore del prodotto
	 */
	public String getEditore() {
		return Editore;
	}

	/**
	 * Setta il nome dell'editore nel bean
	 * 
	 * @param editore
	 *            nome dell'editore da settare
	 */
	public void setEditore(String editore) {
		Editore = editore;
	}

	/**
	 * 
	 * @return ritorna il nome della città dell'editore
	 */
	public String getCitta_Editore() {
		return Citta_Editore;
	}

	/**
	 * Setta il nome della città dell'editore nel bean
	 * 
	 * @param citta_Editore
	 *            città dell'editore da settare
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
	 * Setta la nazione dell'editore nel bean
	 * 
	 * @param paese_Editore
	 *            nazione dell'editore da settare
	 */
	public void setPaese_Editore(String paese_Editore) {
		Paese_Editore = paese_Editore;
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
	 * @return ritorna l'isbn del prodotto
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Setta l'isbn del prodotto nel bean
	 * 
	 * @param iSBN
	 *            isbn da settare
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * 
	 * @return ritorna il doi del prodotto
	 */
	public String getDOI() {
		return DOI;
	}

	/**
	 * Setta il doi nel bean
	 * 
	 * @param dOI
	 *            doi da settare
	 */
	public void setDOI(String dOI) {
		DOI = dOI;
	}

	/**
	 * 
	 * @return ritorna le keywords del prodotto
	 */
	public String getKeywords() {
		return Keywords;
	}

	/**
	 * Setta le keywords nel bean
	 * 
	 * @param keywords
	 *            keywords da setta
	 */
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	private String Editore;
	private String Citta_Editore;
	private String Paese_Editore;
	private String Pagine_Riferimento;
	private String ISBN;
	private String DOI;
	private String Keywords;
}
