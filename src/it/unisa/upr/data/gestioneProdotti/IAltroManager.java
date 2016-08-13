/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di AltroManager
 * @see DBAltro
 */
public interface IAltroManager extends IManager {

	public Altro visualizzaDettagliAltro(Prodotto p);

	public boolean inserisciAltro(Altro altro);

	public boolean modificaAltro(Altro altro, String campo, String nuovoValore);

	public boolean eliminaAltro(Altro altro);
}
