/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import java.sql.Date;

/**
 * La classe rappresenta il bean della tipologia di prodotto Proceeding
 * 
 */
public class Proceeding extends Prodotto {

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto nel database
	 * @param iD_Proprietario
	 *            id del propritario del dato
	 * @param titolo
	 *            titolo del prodotto
	 * @param autori
	 *            autori (non presenti nel sistema) del prodotto
	 * @param anno_Pubblicazione
	 *            anno di pubblicazione del prodotto
	 * @param abstract1
	 *            abstract del prodotto
	 * @param pubblico
	 *            indica se il prodotto è pubblico (0-privato, 1-pubblico)
	 * @param stato
	 *            stato di avanzamento del prodotto
	 * @param tipologia
	 *            tipologia del prodotto
	 * @param uRL
	 *            url del file associato il prodotto
	 * @param note
	 *            note di validazione
	 * @param relazione
	 *            relazione
	 * @param nome_Volume
	 *            nome del volume
	 * @param numero_Volume
	 *            numero del volume
	 * @param autori_Volume
	 *            autori del volume
	 * @param editore
	 *            editore del volume
	 * @param citta_Editore
	 *            città dell'editore
	 * @param paese_Editore
	 *            nazione dell'editore
	 * @param pagine_Riferimento
	 *            pagine di riferimento
	 * @param nome_Congresso
	 *            nome del congresso
	 * @param data_Congresso
	 *            data del congresso
	 * @param luogo_Congresso
	 *            luogo del congresso
	 * @param rilevanza
	 *            rilevanza
	 * @param iSBN
	 *            isbn del prodotto
	 * @param dOI
	 *            doi
	 * @param keywords
	 *            keywords
	 */
	public Proceeding(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note, String relazione, String nome_Volume,
			String numero_Volume, String autori_Volume, String editore,
			String citta_Editore, String paese_Editore,
			String pagine_Riferimento, String nome_Congresso,
			Date data_Congresso, String luogo_Congresso, String rilevanza,
			String iSBN, String dOI, String keywords) {
		super(iD_Prodotto, iD_Proprietario, titolo, autori, anno_Pubblicazione,
				abstract1, pubblico, stato, tipologia, uRL, note);
		Relazione = relazione;
		Nome_Volume = nome_Volume;
		Numero_Volume = numero_Volume;
		Autori_Volume = autori_Volume;
		Editore = editore;
		Citta_Editore = citta_Editore;
		Paese_Editore = paese_Editore;
		Pagine_Riferimento = pagine_Riferimento;
		Nome_Congresso = nome_Congresso;
		Data_Congresso = data_Congresso;
		Luogo_Congresso = luogo_Congresso;
		Rilevanza = rilevanza;
		ISBN = iSBN;
		DOI = dOI;
		Keywords = keywords;
	}

	public Proceeding() {
	}

	/**
	 * 
	 * @return ritorna la relazione
	 */
	public String getRelazione() {
		return Relazione;
	}

	/**
	 * Setta la relazione nel bean
	 * 
	 * @param relazione
	 *            relazione da settare
	 */
	public void setRelazione(String relazione) {
		Relazione = relazione;
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
	 * Setta il numero del volume nel bean
	 * 
	 * @param numero_Volume
	 *            numero del volume da settare
	 */
	public void setNumero_Volume(String numero_Volume) {
		Numero_Volume = numero_Volume;
	}

	/**
	 * 
	 * @return ritorna i nome degli autori del volume
	 */
	public String getAutori_Volume() {
		return Autori_Volume;
	}

	/**
	 * Setta i nome degli autori del volume
	 * 
	 * @param autori_Volume
	 *            autori del volume da settare
	 */
	public void setAutori_Volume(String autori_Volume) {
		Autori_Volume = autori_Volume;
	}

	/**
	 * 
	 * @return ritorna il nome dell'editore
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
	 * @return ritorna la città dell'editore
	 */
	public String getCitta_Editore() {
		return Citta_Editore;
	}

	/**
	 * Setta la città dell'editore nel bean
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
	 * @return ritorna il nome del congresso
	 */
	public String getNome_Congresso() {
		return Nome_Congresso;
	}

	/**
	 * Setta il nome del congresso nel bean
	 * 
	 * @param nome_Congresso
	 *            nome del congresso da settare
	 */
	public void setNome_Congresso(String nome_Congresso) {
		Nome_Congresso = nome_Congresso;
	}

	/**
	 * 
	 * @return ritorna la data del congresso
	 */
	public Date getData_Congresso() {
		return Data_Congresso;
	}

	/**
	 * Setta la data del congresso nel bean
	 * 
	 * @param data_Congresso
	 *            data del congresso da settare
	 */
	public void setData_Congresso(Date data_Congresso) {
		Data_Congresso = data_Congresso;
	}

	/**
	 * 
	 * @return ritorna il luogo del congresso
	 */
	public String getLuogo_Congresso() {
		return Luogo_Congresso;
	}

	/**
	 * Setta il luogo del congresso nel bean
	 * 
	 * @param luogo_Congresso
	 *            luogo del congresso da settare
	 */
	public void setLuogo_Congresso(String luogo_Congresso) {
		Luogo_Congresso = luogo_Congresso;
	}

	/**
	 * 
	 * @return ritorna la rilevanza del congresso
	 */
	public String getRilevanza() {
		return Rilevanza;
	}

	/**
	 * Setta la rilevanza del congresso nel bean
	 * 
	 * @param rilevanza
	 *            rilevanza da settare
	 */
	public void setRilevanza(String rilevanza) {
		Rilevanza = rilevanza;
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
	 *            keywords da settare
	 */
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	private String Relazione;
	private String Nome_Volume;
	private String Numero_Volume;
	private String Autori_Volume;
	private String Editore;
	private String Citta_Editore;
	private String Paese_Editore;
	private String Pagine_Riferimento;
	private String Nome_Congresso;
	private Date Data_Congresso;
	private String Luogo_Congresso;
	private String Rilevanza;
	private String ISBN;
	private String DOI;
	private String Keywords;
}
