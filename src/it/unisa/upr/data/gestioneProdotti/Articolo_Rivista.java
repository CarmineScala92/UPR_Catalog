/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean della tipologia di prodotto Articolo su rivista
 * 
 */
public class Articolo_Rivista extends Prodotto {

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto all'interno del database
	 * @param iD_Proprietario
	 *            id del ricercatore proprietario del dato
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori del prodotto (non appartenenti al sistema)
	 * @param anno_Pubblicazione
	 *            anno di pubblicazione dell'articolo
	 * @param abstract1
	 *            abstract del prodotto
	 * @param pubblico
	 *            indica se il prodotto è pubblico o privato (0-privato
	 *            1-pubblico)
	 * @param stato
	 *            stato di avanzamento del prodotto
	 * @param tipologia
	 *            tipologia del prodotto
	 * @param uRL
	 *            url del file associato al prodotto
	 * @param note
	 *            note di validazione
	 * @param nome_Rivista
	 *            nome della rivista su cui è stato pubblicato l'articolo
	 * @param numero_Volume
	 *            numero del volume
	 * @param pagine_riferimento
	 *            pagine di riferimento
	 * @param dOI
	 *            doi
	 * @param keywords
	 *            keywords
	 */
	public Articolo_Rivista(int iD_Prodotto, int iD_Proprietario,
			String titolo, String autori, String anno_Pubblicazione,
			String abstract1, int pubblico, String stato, String tipologia,
			String uRL, String note, String nome_Rivista, String numero_Volume,
			String pagine_riferimento, String dOI, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Nome_Rivista = nome_Rivista;
		Numero_Volume = numero_Volume;
		Pagine_riferimento = pagine_riferimento;
		DOI = dOI;
		Keywords = keywords;
	}

	public Articolo_Rivista() {
		super();
	}

	@Override
	public String toString() {
		return "ArticoloRivista [Nome_Rivista=" + Nome_Rivista
				+ ", Numero_Volume=" + Numero_Volume + ", Pagine_riferimento="
				+ Pagine_riferimento + ", DOI=" + DOI + ", Keywords="
				+ Keywords + "]";
	}

	/**
	 * 
	 * @return ritorna il nome della rivista di pubblicazione
	 */
	public String getNome_Rivista() {
		return Nome_Rivista;
	}

	/**
	 * Setta il nome della rivista all'interno del bean
	 * 
	 * @param nome_Rivista
	 *            nome della rivista da settare
	 */
	public void setNome_Rivista(String nome_Rivista) {
		Nome_Rivista = nome_Rivista;
	}

	/**
	 * 
	 * @return ritorna il numero del volume
	 */
	public String getNumero_Volume() {
		return Numero_Volume;
	}

	/**
	 * Setta il numero del volume all'interno del bean
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
	public String getPagine_riferimento() {
		return Pagine_riferimento;
	}

	/**
	 * Setta le pagine di riferimento nel bean
	 * 
	 * @param pagine_riferimento
	 *            pagine di riferimento da settare
	 */
	public void setPagine_riferimento(String pagine_riferimento) {
		Pagine_riferimento = pagine_riferimento;
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
	 *            keywords da settare
	 */
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	private String Nome_Rivista;
	private String Numero_Volume;
	private String Pagine_riferimento;
	private String DOI;
	private String Keywords;
}
