/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import it.unisa.upr.commons.IManager;

/**
 * Operazioni associate all'interfaccia di SelezioneManager
 * @see DBSelezione
 */
public interface ISelezioneManager extends IManager {

	public boolean eliminaSelezione(Selezione c);
}
