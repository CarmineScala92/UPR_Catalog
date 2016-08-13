/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean della tipologia di prodotto Brevetto
 * 
 */
public class Brevetto extends Prodotto {

	public Brevetto() {
	}

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto nel database
	 * @param iD_Proprietario
	 *            id del proprietario del dato
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori
	 * @param anno_Pubblicazione
	 * @param abstract1
	 * @param pubblico
	 * @param stato
	 * @param tipologia
	 * @param uRL
	 * @param note
	 * @param proprieta
	 * @param numero_Brevetto
	 * @param dOI
	 * @param tipo
	 * @param keywords
	 */
	public Brevetto(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note, String proprieta, String numero_Brevetto, String dOI,
			String tipo, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Proprieta = proprieta;
		Numero_Brevetto = numero_Brevetto;
		DOI = dOI;
		Tipo = tipo;
		Keywords = keywords;
	}

	/**
	 * 
	 * @return ritorna le proprietà del brevetto
	 */
	public String getProprieta() {
		return Proprieta;
	}

	/**
	 * Setta le proprietà del brevetto nel bean
	 * 
	 * @param proprieta
	 *            proprietà del brevetto da settare
	 */
	public void setProprieta(String proprieta) {
		Proprieta = proprieta;
	}

	/**
	 * 
	 * @return ritorna il numero del brevetto
	 */
	public String getNumero_Brevetto() {
		return Numero_Brevetto;
	}

	/**
	 * Setta il numero del brevetto nel bean
	 * 
	 * @param numero_Brevetto
	 *            numero del brevetto da settare
	 */
	public void setNumero_Brevetto(String numero_Brevetto) {
		Numero_Brevetto = numero_Brevetto;
	}

	/**
	 * 
	 * @return ritorna il doi del brevetto
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
	 * @return ritorna il tipo di brevetto
	 */
	public String getTipo() {
		return Tipo;
	}

	/**
	 * Setta il tipo di brevetto nel bean
	 * 
	 * @param tipo
	 *            tipo di brevetto da settare
	 */
	public void setTipo(String tipo) {
		Tipo = tipo;
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

	private String Proprieta;
	private String Numero_Brevetto;
	private String DOI;
	private String Tipo;
	private String Keywords;
}
