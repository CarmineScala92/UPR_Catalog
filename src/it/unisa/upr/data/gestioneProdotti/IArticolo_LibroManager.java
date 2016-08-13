/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di Articolo_LibroManager
 * @see DBArticolo_Libro
 */
public interface IArticolo_LibroManager extends IManager {

	public boolean inserisciArticolo_Libro(Articolo_Libro p);

	public boolean modificaArticoloLibro(Articolo_Libro p, String campo,
			String nuovoValore);

	public boolean eliminaArticoloLibro(Articolo_Libro p);

	public Articolo_Libro visualizzaDettagli(Prodotto p);
}
