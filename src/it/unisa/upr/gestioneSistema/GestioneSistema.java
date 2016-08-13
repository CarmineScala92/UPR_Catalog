/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneSistema;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.IAccountManager;
import it.unisa.upr.data.gestioneAutenticazione.IAmministratoreManager;
import it.unisa.upr.data.gestioneAutenticazione.IResponsabileManager;
import it.unisa.upr.data.gestioneAutenticazione.IRicercatoreManager;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.IProdottoManager;
import it.unisa.upr.data.gestioneProdotti.IProduzioneManager;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;
import it.unisa.upr.data.gestioneSistema.IAreaScientificaManager;
import it.unisa.upr.data.gestioneSistema.IDipartimentoManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe fa da facciata alle operazioni del sistema
 * 
 */
public class GestioneSistema implements IFacadeGestioneSistema {

	private IAccountManager accountManager;
	private IAreaScientificaManager areaScientificaManager;
	private IDipartimentoManager dipartimentoManager;
	private IAmministratoreManager amministratoreManager;
	private IRicercatoreManager ricercatoreManager;
	private IResponsabileManager responsabileManager;
	private IProduzioneManager produzioneManager;
	private IProdottoManager prodottoManager;

	private static GestioneSistema manager;

	public static GestioneSistema getInstance() {
		if (manager == null) {
			manager = new GestioneSistema();
		}
		return manager;
	}

	public GestioneSistema() {
		areaScientificaManager = (IAreaScientificaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AREA_SCIENTIFICA);
		dipartimentoManager = (IDipartimentoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_DIPARTIMENTO);
		accountManager = (IAccountManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ACCOUNT);
		amministratoreManager = (IAmministratoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AMMINISTRATORE);
		ricercatoreManager = (IRicercatoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RICERCATORE);
		responsabileManager = (IResponsabileManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RESPONSABILE);
		prodottoManager = (IProdottoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);
		produzioneManager = (IProduzioneManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PRODUZIONE);
	}

	// ************************ METODI PER SERVLET CHE RISPECCHIANO
	// FUNZIONALITA' NEL SISTEMA

	/**
	 * Elimina un account dal database
	 * 
	 * @param account account da eliminare
	 * @return true se l'account è stato eliminato, false altrimenti
	 */
	@Override
	public boolean eliminaAccount(Account account) {

		// MI PRENDO L'ACCOUNT CON TUTTI I CAMPI , PRIMA AVEVO SOLO L'ID DELLA
		// SERVLET
		try {
			account = accountManager.visualizzaAccount(account.getID_Account()); // OK
		}
		catch (SQLException e1) {
			System.out.println("NON RITORNA L'ACCOUNT CON GLI ID");
			return false;
		}

		if (account.getTipologia().equalsIgnoreCase("Ricercatore")) {

			// IN QUESTO IF SE AVESSIMO IMPLEMENTATO L RF 4 AVREMMO DOVUTO
			// ELIMINARE ANCHE IN COMPOSIZIONE
			// E NELLA TABELLA SELEZIONE DEL DATABASE !!!!!
			// PERO' SICCOME NON LO IMPLEMENTIAMO TALI TABELLE DEVONO ESSERE
			// VUOTE
			System.out.println("E' UN RICERCATORE");
			Ricercatore p = new Ricercatore();
			p.setID_Ricercatore(account.getID_Account());

			if (!produzioneManager.elimina(account)) {
				System.out.println("NON ELIMINA IN PRODUZIONE");
				return false;
			}

			if (!prodottoManager.eliminaProdotti(account)) {
				System.out.println("NON ELIMINA IN PRODOTTI");
				return false;
			}

			if (!ricercatoreManager.eliminaRicercatore(p)) {
				System.out.println("NON ELIMINA IN RICERATORE");
				return false;
			}

			if (!accountManager.eliminaAccount(account)) {
				System.out.println("NON ELIMINA IN ACCOUNT");
				return false;
			}

			return true;
		}
		else if (account.getTipologia().equalsIgnoreCase("Responsabile")) {
			System.out.println("E' UN RESPONSABILE");
			Responsabile p = new Responsabile();
			p.setID_Responsabile(account.getID_Account());

			if (!responsabileManager.eliminaResponsabile(p)) {
				return false;
			}

			if (!accountManager.eliminaAccount(account)) {
				return false;
			}

			return true;
		}
		if (account.getTipologia().equalsIgnoreCase("Amministratore")) {
			System.out.println("E' UN AMMINISTRATORE");
			Amministratore p = new Amministratore();
			p.setID_Amministratore(account.getID_Account());

			if (!amministratoreManager.eliminaAmministratore(p)) {
				System.out.println("ERRORE ELIMINA AMMINISTRATORE");
				return false;
			}

			if (!accountManager.eliminaAccount(account)) {
				System.out.println("ERRORE ELIMINA ACCOUNT");
				return false;
			}

			return true;
		}

		return true;
	}

	/**
	 * Inserisce un account nel database
	 * 
	 * @param account account da inserire
	 * @return true se l'account è stato inserito, false altrimenti
	 */
	@Override
	public synchronized boolean inserisciAccount(Account account) {
		boolean result = false;

		if (account.getTipologia().equalsIgnoreCase("Amministratore"))
			result = amministratoreManager.inserisciAmministratore((Amministratore) account);
		else if (account.getTipologia().equalsIgnoreCase("Responsabile"))
			result = responsabileManager.inserisciResponsabile((Responsabile) account);
		else if (account.getTipologia().equalsIgnoreCase("Ricercatore"))
			result = ricercatoreManager.inserisciRicercatore((Ricercatore) account);
		return result;
	}

	/**
	 * Ricerca un account nel database
	 * @param Nome nome dell'utente associato all'account
	 * @param Cognome cognome dell'utente asssociato all'account
	 * @return ritorna una lista di account che soddisfano i parametri di ricerca
	 */
	@Override
	public ArrayList<Account> ricercaAccount(String Nome, String Cognome) {
		return accountManager.ricercaAccount(Nome, Cognome);
	}

	/**
	 * Visualizza i dettagli di un account
	 * @param ID_Account id dell'account di cui si vogliono i dettagli
	 * @return ritorna l'account associato all'id
	 */
	@Override
	public Account visualizzaAccount(int ID_Account) throws SQLException {

		Account accountRitornato = accountManager.visualizzaAccount(ID_Account);
		if (accountRitornato.getTipologia().equalsIgnoreCase("Amministratore")) {
			Amministratore amministratore = amministratoreManager.getAmministratore(accountRitornato);
			return amministratore;
		}
		else if (accountRitornato.getTipologia().equalsIgnoreCase("Responsabile")) {
			Responsabile responsabile = responsabileManager.getResponsabile(accountRitornato);
			return responsabile;
		}
		else { // RICERCATORE
			Ricercatore ricercatore = ricercatoreManager.getRicercatore(accountRitornato);
			return ricercatore;
		}
	}

	/**
	 * Modifica i dettagli di un account
	 * @param account account da modificare con i dettagli modificati
	 * @return true se l'account è stato modificato, false altrimenti
	 */
	@Override
	public boolean modificaAccount(Account account) {
		if (account.getTipologia().equalsIgnoreCase("Ricercatore")) {
			return (ricercatoreManager.modificaRicercatore((Ricercatore) account) && accountManager.modificaAccount(account));

		}
		if (account.getTipologia().equalsIgnoreCase("Amministratore")) {
			return amministratoreManager.modificaAmministratore((Amministratore) account) && accountManager.modificaAccount(account);

		}
		if (account.getTipologia().equalsIgnoreCase("Responsabile") && accountManager.modificaAccount(account)) {
			return responsabileManager.modificaResponsabile((Responsabile) account);

		}
		return false;
	}

	/**
	 * Elimina un dipartimento dal database
	 * @param dipartimento dipartimento da eliminare
	 * @return true se il dipartimento è stato eliminato, false altrimenti
	 */
	@Override
	public boolean eliminaDipartimento(Dipartimento dipartimento) {
		boolean ris1 = true;
		ArrayList<Ricercatore> ricercatori = dipartimentoManager.caricaRicercatori(dipartimento);
		if (ricercatori.size() == 0) {
			try {
				ris1 = dipartimentoManager.eliminaDipartimento(dipartimento.getID_Dipartimento()) > 0;
				return ris1;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		}
		else {
			return false;
		}

	}

	/**
	 * Inserisce un dipartimento nel database
	 * @param dipartimento dipartimento da inserire
	 * @return true se il dipartimento è inserito, false altrimenti
	 */
	@Override
	public boolean inserisciDipartimento(Dipartimento dipartimento) {
		try {
			int res = dipartimentoManager.inserimentoDipartimento(dipartimento.getNome(),
					dipartimento.getTelefono(),
					dipartimento.getFax(),
					dipartimento.getSito(),
					dipartimento.getEmail());
			if (res > 0)
				return true;
			else
				return false;
		}
		catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Ricerca un dipartimento in base al nome nel database
	 * @param Nome nome del dipartimento
	 * @return ritorna la lista dei dipartimenti che soddisfano il paramtro
	 */
	@Override
	public ArrayList<Dipartimento> ricercaDipartimento(String Nome)
			throws SQLException {
		return dipartimentoManager.ricercaDipartimento(Nome);
	}

	/**
	 * Carica i dettagli di un dipartimento
	 * @param ID_Dipartimento id del dipartimento da visualizzare
	 * @return ritorna i dettagli del dipartimento
	 */
	@Override
	public Dipartimento visualizzaDipartimento(int ID_Dipartimento)
			throws SQLException {
		return dipartimentoManager.visualizzaDipartimento(ID_Dipartimento);
	}

	/**
	 * Elimina un'area scientifica dal database
	 * @param areaScientifica area scientifica da eliminare
	 * @return true se l'area è stata eliminata, false altrimenti
	 */
	@Override
	public boolean eliminaAreaScientifica(AreaScientifica areaScientifica) {
		boolean ris1 = true;
		ArrayList<Ricercatore> ricercatori = areaScientificaManager.caricaRicercatori(areaScientifica);
		if (ricercatori.size() == 0) {
			try {
				ris1 = areaScientificaManager.eliminaAreaScientifica(areaScientifica.getID_Area_Scientifica()) > 0;
				return ris1;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		}
		else {
			return false;
		}

	}

	/**
	 * Inserisce un'area scientifica nel database
	 * @param areaScientifica area scientifica da inserire
	 * @return true se l'area è stata inserita, false altrimenti
	 */
	@Override
	public boolean inserisciAreaScientifica(AreaScientifica areaScientifica) {
		try {
			int res = areaScientificaManager.inserimentoAreaScientifica(areaScientifica.getCodice(),
					areaScientifica.getNome(),
					areaScientifica.getTelefono(),
					areaScientifica.getFax(),
					areaScientifica.getSito(),
					areaScientifica.getEmail());
			if (res > 0)
				return true;
			else
				return false;
		}
		catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Ricerca un'area scientifica in base al nome
	 * @param Nome nome dell'area scientifica da cercare
	 * @return ritorna una lista contenente le aree che soddisfano il paramtro di ricerca
	 */
	@Override
	public ArrayList<AreaScientifica> ricercaAreaScientifica(String Nome)
			throws SQLException {
		return areaScientificaManager.ricercaAreaScientifica(Nome);
	}

	/**
	 * Carica i dettagli di un'area scientifica
	 * @param ID_Area_Scientifica id dell'area scientifica da visualizzare
	 * @return ritorna l'area scientifica contenente tutti i dettagli
	 */
	@Override
	public AreaScientifica visualizzaAreaScientifica(int ID_Area_Scientifica)
			throws SQLException {
		return areaScientificaManager.visualizzaAreaScientifica(ID_Area_Scientifica);
	}

	/**
	 * Modifica i dettagli di un'area scientifica
	 * @param id id dell'area da modificare
	 * @param codice nuovo codice dell'area scientifica (o attuale se non deve essere modificato)
	 * @param nome nuovo nome dell'area scientifica (o attuale se non deve essere modificato)
	 * @param telefono nuovo telefono dell'area scientifica (o attuale se non deve essere modificato)
	 * @param fax nuovo fax dell'area scientifica (o attuale se non deve essere modificato)
	 * @param sito nuovo sito dell'area scientifica (o attuale se non deve essere modificato)
	 * @param email nuova email dell'area scientifica (o attuale se non deve essere modificato)
	 */
	@Override
	public boolean modificaAreaScientifica(int id, String codice, String nome,
			String telefono, String fax, String sito, String email)
			throws SQLException {

		if (areaScientificaManager.modificaAreaScientifica(id,
				codice,
				nome,
				telefono,
				fax,
				sito,
				email) > 0)
			return true;
		else
			return false;

	}

	/**
	 * Modifica i dettagli di un dipartimento
	 * @param id id del dipartimento da modificare
	 * @param nome nuovo nome del dipartimento (o attuale se non deve essere modificato)
	 * @param telefono nuovo telefono del dipartimento (o attuale se non deve essere modificato)
	 * @param fax nuovo fax del dipartimento (o attuale se non deve essere modificato)
	 * @param sito nuovo sito del dipartimento (o attuale se non deve essere modificato)
	 * @param email nuova email del dipartimento (o attuale se non deve essere modificato)
	 */
	public boolean modificaDipartimento(int id, String nome, String telefono,
			String fax, String sito, String email) throws SQLException {

		if (dipartimentoManager.modificaDipartimento(id,
				nome,
				telefono,
				fax,
				sito,
				email) > 0)
			return true;
		else
			return false;
	}

	/**
	 * Cerca il ricercatore che è direttore del dipartimento
	 * @param dip dipartimento di cui si vuole il direttore
	 * @return ritorna il direttore del dipartimento
	 */
	@Override
	public Ricercatore getDirettoreDipartimento(Dipartimento dip) {
		return dipartimentoManager.getDirettoreDipartimento(dip);
	}

	// ************************ METODI PER SERVLET CHE RISPECCHIANO SERVIZI NEL
	// SISTEMA

}
