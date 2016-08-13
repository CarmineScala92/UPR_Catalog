/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.IFacade;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interfaccia di GestioneSistema
 * @see GestioneSistema
 *
 */
public interface IFacadeGestioneSistema extends IFacade {

	// ******************************** SERVLET DI FUNZIONALITA'

	public boolean eliminaAccount(Account account);

	// FACCIAMO COME L'INSERIMENTO DEL PRODOTTO , INVIAMO LO SPECIFICO
	// ACCOUNT(RICERCATORE,ADMIN) AL METODO
	// E POI IL METODO DI GESTIONE SISTEMA IN BASEA ALLA TIPOLOGIA CHIAMA LO
	// SPECIFICO MANAGER E CREA L'ACCOUNT SPECIFICO
	public boolean inserisciAccount(Account account);

	public ArrayList<Account> ricercaAccount(String Nome, String Cognome); // LA
																			// RICERCA
																			// DAL
																			// RAD
																			// DI
																			// ACCOUNT
																			// PRENDE
																			// NOME
																			// E
																			// COGNOME

	public Account visualizzaAccount(int ID_Account) throws SQLException;

	public boolean eliminaDipartimento(Dipartimento dipartimento);

	boolean inserisciDipartimento(Dipartimento dipartimento);

	public ArrayList<Dipartimento> ricercaDipartimento(String Nome)
			throws SQLException; // LA RICERCA DAL RAD PRENDE SOLO IL NOME DEL
									// DIP

	public Dipartimento visualizzaDipartimento(int ID_Dipartimento)
			throws SQLException;

	public boolean modificaDipartimento(int id, String nome, String telefono,
			String fax, String sito, String email) throws SQLException;

	public boolean eliminaAreaScientifica(AreaScientifica areaScientifica);

	public boolean inserisciAreaScientifica(AreaScientifica areaScientifica);

	public ArrayList<AreaScientifica> ricercaAreaScientifica(String Nome)
			throws SQLException; // LA RICERCA DAL RAD PRENDE SOLO IL NOME
									// DELL'AREA SC

	public AreaScientifica visualizzaAreaScientifica(int ID_Area_Scientifica)
			throws SQLException;

	boolean modificaAccount(Account account);

	boolean modificaAreaScientifica(int id, String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException;

	// ************************** METODI PER LE SERVLET DI SERVIZIO

	public Ricercatore getDirettoreDipartimento(Dipartimento dip);

}
