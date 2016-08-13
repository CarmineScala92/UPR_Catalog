/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneSistema;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
/**
 * Intefaccia del DipartimentoManager
 * @see DBDipartimento
 *
 */
public interface IDipartimentoManager extends IManager {

	public Dipartimento getDipartimento(int id) throws SQLException;

	public ArrayList<Dipartimento> ricercaDipartimento(String nome)
			throws SQLException;

	public int inserimentoDipartimento(String nome, String telefono,
			String fax, String sito, String email) throws SQLException;

	public int modificaDipartimento(int Id, String nome, String telefono,
			String fax, String sito, String email) throws SQLException;

	public int eliminaDipartimento(int Id) throws SQLException;

	public Dipartimento visualizzaDipartimento(int id) throws SQLException;

	public ArrayList<Dipartimento> getListaDipartimenti() throws SQLException;

	public ArrayList<Ricercatore> caricaRicercatori(Dipartimento dipartimento);

	public Ricercatore getDirettoreDipartimento(Dipartimento dip);
}
