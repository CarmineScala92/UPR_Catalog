/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di MonografiaManager
 * @see DBMonografia
 */
public interface IMonografiaManager extends IManager {

	public Monografia visualizzaDettagliMonografia(Prodotto p);

	public boolean inserisciMonografia(Monografia mon);

	public boolean modificaMonografia(Monografia mon, String campo,
			String nuovoValore);

	public boolean eliminaMonografia(Monografia mon);
}
