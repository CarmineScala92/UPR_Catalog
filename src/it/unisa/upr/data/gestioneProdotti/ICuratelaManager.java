/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di CuratelaManager
 * @see DBCuratela
 */
public interface ICuratelaManager extends IManager {

	public Curatela visualizzaDettagli(Prodotto p);

	public boolean inserisciCuratela(Curatela cur);

	public boolean modificaCuratela(Curatela cur, String campo,
			String nuovoValore);

	public boolean eliminaCuratela(Curatela cur);
}
