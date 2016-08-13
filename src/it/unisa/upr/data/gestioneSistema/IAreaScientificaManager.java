/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneSistema;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
/**
 * Intefaccia dell'AreaScientificaManager
 * @see DBAreaScientifica
 *
 */
public interface IAreaScientificaManager extends IManager {

	public AreaScientifica getAreaScientifica(int id) throws SQLException;

	public ArrayList<AreaScientifica> ricercaAreaScientifica(String nome)
			throws SQLException;

	public int inserimentoAreaScientifica(String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException;

	public int modificaAreaScientifica(int Id, String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException;

	public int eliminaAreaScientifica(int Id) throws SQLException;

	public AreaScientifica visualizzaAreaScientifica(int id)
			throws SQLException;

	public ArrayList<AreaScientifica> getListaAreeScientifiche()
			throws SQLException;

	public ArrayList<Ricercatore> caricaRicercatori(
			AreaScientifica areaScientifica);
}
