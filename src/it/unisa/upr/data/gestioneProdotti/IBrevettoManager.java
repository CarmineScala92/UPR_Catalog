/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di BrevettoManager
 * @see DBBrevetto
 */
public interface IBrevettoManager extends IManager {

	public boolean inserisciBrevetto(Brevetto p);

	public boolean modificaBrevetto(Brevetto p, String campo, String nuovoValore);

	public boolean eliminaBrevetto(Brevetto p);

	public Brevetto visualizzaDettagli(Prodotto p);
}
