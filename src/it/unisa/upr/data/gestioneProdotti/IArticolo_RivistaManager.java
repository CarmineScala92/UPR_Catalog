/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di Articolo_RivistaManager
 * @see DBArticolo_Rivista
 */
public interface IArticolo_RivistaManager extends IManager {

	public boolean inserisciArticolo_Rivista(Articolo_Rivista p);

	public boolean modificaArticolo_Rivista(Articolo_Rivista p, String campo,
			String nuovoValore);

	public boolean eliminaArticolo_Rivista(Articolo_Rivista p);

	public Articolo_Rivista visualizzaDettagli(Prodotto p);
}
