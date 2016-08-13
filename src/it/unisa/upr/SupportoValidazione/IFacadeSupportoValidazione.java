/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.SupportoValidazione;

import it.unisa.upr.commons.IFacade;
import it.unisa.upr.data.gestioneProdotti.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfaccia di SupportoValidazione
 * @see SupportoValidazione
 */
public interface IFacadeSupportoValidazione extends IFacade {

	public boolean accettaProdotto(Integer id, String note) throws SQLException;

	public boolean rifiutaProdotto(Integer id, String note) throws SQLException;

	public boolean validaProdotto(Integer id, String note) throws SQLException;

	public ArrayList<Prodotto> caricaListaProdottiCompleti()
			throws SQLException;

	public ArrayList<Prodotto> caricaListaProdottiAccettati()
			throws SQLException;

}
