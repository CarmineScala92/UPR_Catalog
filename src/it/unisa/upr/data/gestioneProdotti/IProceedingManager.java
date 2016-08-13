/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di ProceedingManager
 * @see DBProceeding
 */
public interface IProceedingManager extends IManager {

	public Proceeding visualizzaDettagliProceeding(Prodotto p);

	public boolean inserisciProceeding(Proceeding proc);

	public boolean modificaProceeding(Proceeding proc, String campo,
			String nuovoValore);

	public boolean eliminaProceeding(Proceeding proc);
}
