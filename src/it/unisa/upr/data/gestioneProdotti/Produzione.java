/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean dell'entità produzione
 * 
 */
public class Produzione {

	public Produzione() {

	}

	/**
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto
	 * @param iD_ricercatore
	 *            id del ricercatore associato al prodotto
	 */
	public Produzione(int iD_Prodotto, int iD_ricercatore) {
		ID_Prodotto = iD_Prodotto;
		ID_Ricercatore = iD_ricercatore;
	}

	private int ID_Prodotto;
	private int ID_Ricercatore;

	/**
	 * 
	 * @return ritorna l'id del prodotto
	 */
	public int getID_Prodotto() {
		return ID_Prodotto;
	}

	/**
	 * Setta l'id del prodotto nel bean
	 * 
	 * @param iD_Prodotto
	 *            id del prodotto da settare
	 */
	public void setID_Prodotto(int iD_Prodotto) {
		ID_Prodotto = iD_Prodotto;
	}

	/**
	 * 
	 * @return ritorna l'id del ricercatore associato
	 */
	public int getID_Ricercatore() {
		return ID_Ricercatore;
	}

	/**
	 * Setta l'id del ricercatore associato nel bean
	 * 
	 * @param iD_Ricercatore
	 *            id del ricercatore da settare
	 */
	public void setID_Ricercatore(int iD_Ricercatore) {
		ID_Ricercatore = iD_Ricercatore;
	}

}
