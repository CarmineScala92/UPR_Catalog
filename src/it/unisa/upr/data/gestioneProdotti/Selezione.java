/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

/**
 * La classe rappresenta il bean dell'entità selezione
 * 
 */
public class Selezione {

	public Selezione() {

	}

	private int ID_Ricercatore;
	private int ID_Selezione;
	private int ID_Evento;
	private int ID_Responsabile;
	private String Stato;

	/**
	 * 
	 * @return ritorna l'id del ricercatore
	 */
	public int getID_Ricercatore() {
		return ID_Ricercatore;
	}

	/**
	 * Setta l'id del ricercatore nel bean
	 * 
	 * @param iD_Ricercatore
	 *            id del ricercatore da settare
	 */
	public void setID_Ricercatore(int iD_Ricercatore) {
		ID_Ricercatore = iD_Ricercatore;
	}

	/**
	 * 
	 * @return ritorna l'id della selezione
	 */
	public int getID_Selezione() {
		return ID_Selezione;
	}

	/**
	 * Setta l'id della selezione nel bean
	 * 
	 * @param iD_Selezione
	 *            id della selezione da settare
	 */
	public void setID_Selezione(int iD_Selezione) {
		ID_Selezione = iD_Selezione;
	}

	/**
	 * 
	 * @return ritorna l'id dell'evento a cui la selezione è associata
	 */
	public int getID_Evento() {
		return ID_Evento;
	}

	/**
	 * Setta l'id dell'evento nel bean
	 * 
	 * @param iD_Evento
	 *            id dell'evento da settare
	 */
	public void setID_Evento(int iD_Evento) {
		ID_Evento = iD_Evento;
	}

	/**
	 * 
	 * @return ritorna l'id del responsabile dell'evento
	 */
	public int getID_Responsabile() {
		return ID_Responsabile;
	}

	/**
	 * Setta l'id del responsabile dell'evento nel bean
	 * 
	 * @param iD_Responsabile
	 *            id del responsabile da settare
	 */
	public void setID_Responsabile(int iD_Responsabile) {
		ID_Responsabile = iD_Responsabile;
	}

	/**
	 * 
	 * @param iD_Ricercatore
	 *            id del ricercatore che ha effettuato la selezione
	 * @param iD_Selezione
	 *            id della selezione nel database
	 * @param iD_Evento
	 *            id dell'evento a cui la selezione è associata
	 * @param iD_Responsabile
	 *            id del responsabile che ha effettuato una modifica alla
	 *            selezione
	 * @param stato
	 *            stato della selezione
	 */
	public Selezione(int iD_Ricercatore, int iD_Selezione, int iD_Evento,
			int iD_Responsabile, String stato) {
		super();
		ID_Ricercatore = iD_Ricercatore;
		ID_Selezione = iD_Selezione;
		ID_Evento = iD_Evento;
		ID_Responsabile = iD_Responsabile;
		Stato = stato;
	}

	/**
	 * 
	 * @return ritorna lo stato della selezione
	 */
	public String getStato() {
		return Stato;
	}

	/**
	 * Setta lo stato della selezione nel bean
	 * 
	 * @param stato
	 *            stato da settare
	 */
	public void setStato(String stato) {
		Stato = stato;
	}

}
