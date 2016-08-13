/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

public class Prodotto {

	private int ID_Prodotto;
	private int ID_Proprietario;
	private String Titolo;
	private String Autori;
	private String Anno_Pubblicazione;
	private String Abstract;
	private int Pubblico;
	private String Stato;
	private String Tipologia;
	private String URL;
	private String Note;

	public Prodotto() {

	}

	public Prodotto(int iD_Prodotto, int iD_Proprietario, String titolo,
			String autori, String anno_Pubblicazione, String abstract1,
			int pubblico, String stato, String tipologia, String uRL,
			String note) {

		ID_Prodotto = iD_Prodotto;
		ID_Proprietario = iD_Proprietario;
		Titolo = titolo;
		Autori = autori;
		Anno_Pubblicazione = anno_Pubblicazione;
		Abstract = abstract1;
		Pubblico = pubblico;
		Stato = stato;
		Tipologia = tipologia;
		URL = uRL;
		Note = note;
	}

	@Override
	public String toString() {
		return "Prodotto [ID_Prodotto=" + ID_Prodotto + ", ID_Proprietario="
				+ ID_Proprietario + ", Titolo=" + Titolo + ", Autori=" + Autori
				+ ", Anno_Pubblicazione=" + Anno_Pubblicazione + ", Abstract="
				+ Abstract + ", Pubblico=" + Pubblico + ", Stato=" + Stato
				+ ", Tipologia=" + Tipologia + ", URL=" + URL + ", Note="
				+ Note + "]";
	}

	public int getID_Prodotto() {
		return ID_Prodotto;
	}

	public void setID_Prodotto(int iD_Prodotto) {
		ID_Prodotto = iD_Prodotto;
	}

	public int getID_Proprietario() {
		return ID_Proprietario;
	}

	public void setID_Proprietario(int iD_Proprietario) {
		ID_Proprietario = iD_Proprietario;
	}

	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getAutori() {
		return Autori;
	}

	public void setAutori(String autori) {
		Autori = autori;
	}

	public String getAnno_Pubblicazione() {
		return Anno_Pubblicazione;
	}

	public void setAnno_Pubblicazione(String anno_Pubblicazione) {
		Anno_Pubblicazione = anno_Pubblicazione;
	}

	public String getAbstract() {
		return Abstract;
	}

	public void setAbstract(String abstract1) {
		Abstract = abstract1;
	}

	public int getPubblico() {
		return Pubblico;
	}

	public void setPubblico(int pubblico) {
		Pubblico = pubblico;
	}

	public String getStato() {
		return Stato;
	}

	public void setStato(String stato) {
		Stato = stato;
	}

	public String getTipologia() {
		return Tipologia;
	}

	public void setTipologia(String tipologia) {
		Tipologia = tipologia;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

}
