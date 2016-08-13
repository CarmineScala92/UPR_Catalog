/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import java.util.ArrayList;

import it.unisa.upr.commons.IManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;

/**
 * Operazioni associate all'interfaccia di ProduzioneManager
 * @see DBProduzione
 */
public interface IProduzioneManager extends IManager {

	public boolean elimina(Prodotto c);

	public boolean inserisciProduzione(Produzione p);

	public ArrayList<Produzione> getProduzioni(Prodotto c);

	public boolean elimina(Account c);

	public ArrayList<Ricercatore> getRicercatoriProduzione(Prodotto c);
}
