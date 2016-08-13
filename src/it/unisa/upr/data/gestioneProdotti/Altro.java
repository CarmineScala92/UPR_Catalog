/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean della tipologia di prodotto Altro
 * 
 */
public class Altro extends Prodotto {

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto
	 * @param iD_Proprietario
	 *            id del proprietario
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori (che non appartengono all'ateneo) del prodotto
	 * @param anno_Pubblicazione
	 *            anno di pubblicazione
	 * @param abstract1
	 *            abstract del prodotto
	 * @param pubblico
	 *            indica se il prodotto è pubblico
	 * @param stato
	 *            stato di avanzamento del prodotto
	 * @param tipologia
	 *            tipologia del prodotto
	 * @param uRL
	 *            url del file associato al prodotto
	 * @param note
	 *            note di validazione
	 * @param nome_Volume
	 *            nome del volume
	 * @param numero_Volume
	 *            numero del volume
	 * @param pagine_Riferimento
	 *            pagine di riferimento
	 * @param iSBN
	 *            isbn
	 * @param keywords
	 *            keywords
	 */
	public Altro(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note, String nome_Volume, String numero_Volume,
			String pagine_Riferimento, String iSBN, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Nome_Volume = nome_Volume;
		Numero_Volume = numero_Volume;
		Pagine_Riferimento = pagine_Riferimento;
		ISBN = iSBN;
		Keywords = keywords;
	}

	public Altro() {
	}

	/**
	 * @return ritorna l'id del prodotto
	 */
	public int getID_Prodotto() {
		return super.getID_Prodotto();
	}

	/**
	 * 
	 * @return ritorna il nome del volume
	 */
	public String getNome_Volume() {
		return Nome_Volume;
	}

	/**
	 * Setta il nome del volume nel bean
	 * 
	 * @param nome_Volume
	 *            nome del volume da settare
	 */
	public void setNome_Volume(String nome_Volume) {
		Nome_Volume = nome_Volume;
	}

	/**
	 * 
	 * @return ritorna il numero del volume
	 */
	public String getNumero_Volume() {
		return Numero_Volume;
	}

	/**
	 * Setta il numero del volume
	 * 
	 * @param numero_Volume
	 *            numero del volume da settare
	 */
	public void setNumero_Volume(String numero_Volume) {
		Numero_Volume = numero_Volume;
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
	 * Setta l'isbn nel bean
	 * 
	 * @param iSBN
	 *            isbn da settare
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
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

	private String Nome_Volume;
	private String Numero_Volume;
	private String Pagine_Riferimento;
	private String ISBN;
	private String Keywords;
}
