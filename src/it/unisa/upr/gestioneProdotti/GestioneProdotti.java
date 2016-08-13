/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.IRicercatoreManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.Curatela;
import it.unisa.upr.data.gestioneProdotti.IAltroManager;
import it.unisa.upr.data.gestioneProdotti.IArticolo_LibroManager;
import it.unisa.upr.data.gestioneProdotti.IArticolo_RivistaManager;
import it.unisa.upr.data.gestioneProdotti.IBrevettoManager;
import it.unisa.upr.data.gestioneProdotti.ICuratelaManager;
import it.unisa.upr.data.gestioneProdotti.IMonografiaManager;
import it.unisa.upr.data.gestioneProdotti.IProceedingManager;
import it.unisa.upr.data.gestioneProdotti.IProdottoManager;
import it.unisa.upr.data.gestioneProdotti.IProduzioneManager;
import it.unisa.upr.data.gestioneProdotti.Monografia;
import it.unisa.upr.data.gestioneProdotti.Proceeding;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.data.gestioneProdotti.Produzione;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;
import it.unisa.upr.data.gestioneSistema.IAreaScientificaManager;
import it.unisa.upr.data.gestioneSistema.IDipartimentoManager;

/**
 * La classe fa da facciata tra le operazioni dei manager dei prodotti e le
 * servlet
 * 
 */
public class GestioneProdotti implements IFacadeGestioneProdotti {

	private IAreaScientificaManager areaScientificaManager;
	private IDipartimentoManager dipartimentoManager;
	private IProdottoManager prodottoManager;
	private IProduzioneManager produzioneManager;
	private IRicercatoreManager ricercatoreManager;
	private ICuratelaManager curatelaManager;
	private IBrevettoManager brevettoManager;
	private IArticolo_LibroManager articolo_libroManager;
	private IArticolo_RivistaManager articolo_rivistaManager;
	private IAltroManager altroManager;
	private IMonografiaManager monografiaManager;
	private IProceedingManager proceedingManager;

	private static GestioneProdotti manager;

	/**
	 * @return ritorna l'istanza del manager
	 */
	public static GestioneProdotti getInstance() {
		if (manager == null) {
			manager = new GestioneProdotti();
		}
		return manager;
	}

	public GestioneProdotti() {

		areaScientificaManager = (IAreaScientificaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AREA_SCIENTIFICA);
		dipartimentoManager = (IDipartimentoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_DIPARTIMENTO);
		prodottoManager = (IProdottoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);
		ricercatoreManager = (IRicercatoreManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_RICERCATORE);
		produzioneManager = (IProduzioneManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PRODUZIONE);

		curatelaManager = (ICuratelaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_CURATELA);
		brevettoManager = (IBrevettoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_BREVETTO);
		articolo_libroManager = (IArticolo_LibroManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ARTICOLO_LIBRO);
		articolo_rivistaManager = (IArticolo_RivistaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ARTICOLO_RIVISTA);
		altroManager = (IAltroManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_ALTRO);
		monografiaManager = (IMonografiaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_MONOGRAFIA);
		proceedingManager = (IProceedingManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PROCEEDING);

	}

	// METODI PER LE SERVLET DI SERVIZIO

	/**
	 * Carica la lista delle aree scientifiche
	 * 
	 * @return ritorna la lista delle aree scientifiche
	 */
	@Override
	public ArrayList<AreaScientifica> caricaListaAreeScientifiche()
			throws SQLException {
		return areaScientificaManager.getListaAreeScientifiche();
	}

	/**
	 * Carica la lista dei dipartimenti
	 * 
	 * @return ritorna la lista dei dipartimenti
	 */
	@Override
	public ArrayList<Dipartimento> caricaListaDipartimenti()
			throws SQLException {
		return dipartimentoManager.getListaDipartimenti();
	}

	/**
	 * Carica la lista dei ricercatori
	 * 
	 * @return ritorna la lista dei ricercatori
	 */
	@Override
	public ArrayList<Ricercatore> getListaRicercatori() throws SQLException {
		return ricercatoreManager.getListaRicercatori();
	}

	/**
	 * Carica la lista dei prodotti associati ad un account
	 * 
	 * @param account account di cui si vogliono i prodotti associati
	 * @return ritorna la lista dei prodotti associati all'account
	 */
	@Override
	public ArrayList<Prodotto> caricaListaProdottiHomepage(Account account) {
		return prodottoManager.caricaProdotti(account);
	}

	/**
	 * Carica i ricercatori associati ad un prodotto
	 * 
	 * @param prod prodotto di interesse
	 * @return ritorna una lista di produzioni contenente i ricercatori
	 *         associati al prodotto
	 */
	@Override
	public ArrayList<Produzione> getProduzioni(Prodotto prod) {
		return produzioneManager.getProduzioni(prod);
	}

	// METODI PER SERVLET DI FUNZIONALITA'

	/**
	 * Elimina un prodotto dal database
	 * 
	 * @param prod prodotto da eliminare
	 * @return true se il prodotto è stato eliminato, false altrimenti
	 */
	@Override
	public boolean eliminaProdotto(Prodotto prod) {
		// BISOGNA ELIMINARE IN TABELLA PRODOTTO, IN TABELLA TIPO DEL PRODOTTO,
		// E IN TABELLA PRODUZIONE
		boolean risProd;
		boolean risTipo;
		boolean risProduzione;
		if (prod.getTipologia().equalsIgnoreCase("Curatela")) {
			risTipo = curatelaManager.eliminaCuratela((Curatela) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else if (prod.getTipologia().equalsIgnoreCase("Brevetto")) {
			risTipo = brevettoManager.eliminaBrevetto((Brevetto) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else if (prod.getTipologia().equalsIgnoreCase("Articolo Libro")) {
			risTipo = articolo_libroManager.eliminaArticoloLibro((Articolo_Libro) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else if (prod.getTipologia().equalsIgnoreCase("Articolo Rivista")) {
			risTipo = articolo_rivistaManager.eliminaArticolo_Rivista((Articolo_Rivista) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else if (prod.getTipologia().equalsIgnoreCase("Altro")) {
			risTipo = altroManager.eliminaAltro((Altro) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else if (prod.getTipologia().equalsIgnoreCase("Monografia")) {
			risTipo = monografiaManager.eliminaMonografia((Monografia) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
		else { // E' PROCEEDING ALLORA!!
			risTipo = proceedingManager.eliminaProceeding((Proceeding) prod);
			risProduzione = produzioneManager.elimina(prod);
			risProd = prodottoManager.eliminaProdotto(prod);
			return risProd && risTipo && risProduzione;
		}
	}

	/**
	 * Inserisce un prodotto nel database
	 * 
	 * @param prod prodotto da inserire
	 * @param Autori_per_produzione id degli autori in formato String
	 * @param id_proprietari id degli autori interi
	 * @return true se il prodotto è stato inserito, false altrimenti
	 */
	@Override
	public synchronized boolean inserisciProdotto(Prodotto prod,
			String[] Autori_per_produzione, int[] id_proprietari)
			throws SQLException {

		boolean risultato = false;

		// prodottoManager.inserisciProdotto(prod);
		// PRENDERE L'ID
		// id=prodottoManager.getUltimoIDProdotto();
		// SETTARLO AL PRODOTTO SPECIFICO
		// prod.setID_Prodotto(id);

		if (prod.getTipologia().equalsIgnoreCase("Curatela")) {
			risultato = curatelaManager.inserisciCuratela((Curatela) prod);
		}
		else if (prod.getTipologia().equalsIgnoreCase("Brevetto")) {
			risultato = brevettoManager.inserisciBrevetto((Brevetto) prod);
		}
		else if (prod.getTipologia().equalsIgnoreCase("Articolo Libro")) {
			risultato = articolo_libroManager.inserisciArticolo_Libro((Articolo_Libro) prod);
		}
		else if (prod.getTipologia().equalsIgnoreCase("Articolo Rivista")) {
			risultato = articolo_rivistaManager.inserisciArticolo_Rivista((Articolo_Rivista) prod);
		}
		else if (prod.getTipologia().equalsIgnoreCase("Altro")) {
			risultato = altroManager.inserisciAltro((Altro) prod);
		}
		else if (prod.getTipologia().equalsIgnoreCase("Monografia")) {
			risultato = monografiaManager.inserisciMonografia((Monografia) prod);
		}
		else { // PROCEEDING
			risultato = proceedingManager.inserisciProceeding((Proceeding) prod);
		}
		// System.out.println("result="+risultato);
		if (risultato) {
			System.out.println("result=" + risultato);
			for (int i = 0; i < id_proprietari.length; i++) {
				Produzione produzione = new Produzione(prod.getID_Prodotto(), id_proprietari[i]);
				produzioneManager.inserisciProduzione(produzione);
			}
			/*
			 * for(int i=0;i<Autori_per_produzione.length;i++) // SERVE PER
			 * INSERIRE NELLA TABELLA ----> PRODUZIONE
			 * {
			 * id_proprietari[i]=Integer.parseInt(Autori_per_produzione[i]);
			 * System.out.println(id_proprietari[i]);
			 * Produzione produzione=new Produzione(prod.getID_Prodotto(),
			 * id_proprietari[i]);
			 * produzioneManager.inserisciProduzione(produzione);
			 * }
			 */
			System.out.println(id_proprietari[id_proprietari.length - 1]);
			Produzione produzione = new Produzione(prod.getID_Prodotto(), id_proprietari[id_proprietari.length - 1]);
			produzioneManager.inserisciProduzione(produzione);

		}
		return risultato;
	}

	/**
	 * Ricerca dei prodotti nel database
	 * 
	 * @param titolo titolo del prodotto da cercare
	 * @param anno_pubblicazione anno di pubblicazione
	 * @param tipologia tipologia del prodotto
	 * @param nome_area nome dell'area scientifica
	 * @param nome_dipart nome del dipartimento
	 * @param nome_prop nome del proprietario
	 * @param cognome_prop cognome del proprietario
	 * @return ritorna una lista di prodotti che soddisfano i parametri
	 */
	@Override
	public ArrayList<Prodotto> ricercaProdotto(String titolo,
			String anno_pubblicazione, String tipologia, String nome_area,
			String nome_dipart, String nome_prop, String cognome_prop) {
		return prodottoManager.ricercaProdotto(titolo,
				anno_pubblicazione,
				tipologia,
				nome_area,
				nome_dipart,
				nome_prop,
				cognome_prop);
	}

	/**
	 * Cerca i dettagli di un prodotto
	 * 
	 * @param ID_Prod id del prodotto di cui si vogliono i dettagli
	 * @return ritorna il prodotto contenente tutti i dettagli
	 */
	@Override
	public Prodotto visualizzaProdotto(int ID_Prod) throws SQLException {

		Prodotto prodottoRitornato = prodottoManager.visualizzaProdotto(ID_Prod);
		if (prodottoRitornato.getTipologia().equalsIgnoreCase("Curatela")) {
			Curatela curatela = curatelaManager.visualizzaDettagli(prodottoRitornato);
			return curatela;
		}
		else if (prodottoRitornato.getTipologia().equalsIgnoreCase("Brevetto")) {
			Brevetto brevetto = brevettoManager.visualizzaDettagli(prodottoRitornato);
			return brevetto;
		}
		else if (prodottoRitornato.getTipologia().equalsIgnoreCase("Articolo Libro")) {
			Articolo_Libro articoloLibro = articolo_libroManager.visualizzaDettagli(prodottoRitornato);
			return articoloLibro;
		}
		else if (prodottoRitornato.getTipologia().equalsIgnoreCase("Articolo Rivista")) {
			Articolo_Rivista articoloRivista = articolo_rivistaManager.visualizzaDettagli(prodottoRitornato);
			return articoloRivista;
		}
		else if (prodottoRitornato.getTipologia().equalsIgnoreCase("Altro")) {
			Altro altro = altroManager.visualizzaDettagliAltro(prodottoRitornato);
			return altro;
		}
		else if (prodottoRitornato.getTipologia().equalsIgnoreCase("Monografia")) {
			Monografia monografia = monografiaManager.visualizzaDettagliMonografia(prodottoRitornato);
			return monografia;
		}
		else { // PROCEEDING
			Proceeding proceeding = proceedingManager.visualizzaDettagliProceeding(prodottoRitornato);
			return proceeding;
		}
	}

	@Override
	public String stampaProdotto(Prodotto prod) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Modifica un campo di un prodotto
	 * 
	 * @param prod prodotto che si vuole modificare
	 * @param campo campo da modificare
	 * @param valoreCampo valore che il campo dovrà assumere
	 * @return true se il prodotto è stato modificato, false altrimenti
	 */
	@Override
	public boolean modificaProdotto(Prodotto prod, String campo,
			String valoreCampo) {
		switch (campo) {
			case "Titolo":
			case "Autori":
			case "Anno_Pubblicazione":
			case "Abstract":
			case "Pubblico":
			case "Stato":
			case "URL":
			case "Note":
				return prodottoManager.modificaProdotto(prod,
						campo,
						valoreCampo);
		}
		if (prod instanceof Curatela) {
			return curatelaManager.modificaCuratela((Curatela) prod,
					campo,
					valoreCampo);
		}
		else if (prod instanceof Brevetto) {
			return brevettoManager.modificaBrevetto((Brevetto) prod,
					campo,
					valoreCampo);
		}
		else if (prod instanceof Articolo_Libro) {
			return articolo_libroManager.modificaArticoloLibro((Articolo_Libro) prod,
					campo,
					valoreCampo);
		}
		else if (prod instanceof Articolo_Rivista) {
			return articolo_rivistaManager.modificaArticolo_Rivista((Articolo_Rivista) prod,
					campo,
					valoreCampo);
		}
		else if (prod instanceof Altro) {
			return altroManager.modificaAltro((Altro) prod, campo, valoreCampo);
		}
		else if (prod instanceof Monografia) {
			return monografiaManager.modificaMonografia((Monografia) prod,
					campo,
					valoreCampo);
		}
		else { // PROCEEDING
			return proceedingManager.modificaProceeding((Proceeding) prod,
					campo,
					valoreCampo);
		}
	}

	/**
	 * Associa dei ricercatori ad un prodotto
	 * 
	 * @param prod prodotto interessato
	 * @param Autori_per_produzione id degli autori in forma di stringa
	 * @param id_proprietari id degli autori interi
	 * @return true se gli id sono stati aggiunti, false altrimenti
	 */
	@Override
	public boolean inserisciRicercatoriProduzione(Prodotto prod,
			String[] Autori_per_produzione, int[] id_proprietari) {
		// ELIMINA TUTTE LE TUPLE DELLA TABELLA PRODUZIONE RIGUARDANTE IL
		// PRODOTTO PROD
		boolean var1 = produzioneManager.elimina(prod);

		int inseriti = 0;
		// INSERIAMO NUOVAMENTE NELLA TABELLA PRODUZIONE TUTTI I RICERCATORI
		for (int i = 0; i < id_proprietari.length; i++) {
			// System.out.println(id_proprietari[i]);
			Produzione produzione = new Produzione(prod.getID_Prodotto(), id_proprietari[i]);
			if (produzioneManager.inserisciProduzione(produzione))
				inseriti++;
		}
		return var1; // per questioni di implementazione del database
	}

	/**
	 * Ricerca dei prodotti per un utente esterno
	 * 
	 * @param titolo titolo del prodotto da cercare
	 * @param anno_pub anno di pubblicazione
	 * @param tipologia tipologia del prodotto
	 * @param nome_dip nome del dipartimento
	 * @param nome_area nome dell'area scientifica
	 * @param nome_autore nome dell'autore
	 * @param cognome_autore cognome dell'autore
	 * @return ritorna la lista dei prodotti che soddisfano i parametri
	 */
	@Override
	public ArrayList<Prodotto> ricercaProdottoUe(String titolo,
			String anno_pub, String tipologia, String nome_dip,
			String nome_area, String nome_autore, String cognome_autore) {
		// TODO Auto-generated method stub
		return prodottoManager.ricercaProdottoUe(titolo,
				anno_pub,
				tipologia,
				nome_area,
				nome_dip,
				nome_autore,
				cognome_autore);
	}

	/**
	 * Verifica se un prodotto è già presente nel database
	 * 
	 * @param titolo titolo del prodotto da cercare
	 * @param tipologia tipologia del prodotto
	 * @param anno anno di pubblicazione
	 * @return true se il prodotto esiste, false altrimenti
	 */
	public boolean exists(String titolo, String tipologia, String anno)
			throws SQLException {
		return prodottoManager.exists(titolo, tipologia, anno);
	}

	/**
	 * @return ritorna la lista dei prodotti che l'amministratore può visualizzare
	 */
	@Override
	public ArrayList<Prodotto> ricercaProdottiAmministratore() {
		return prodottoManager.ricercaProdottiAmministratore();
	}

	/**
	 * @return ritorna la lista dei ricercatori associati ad un prodotto
	 */
	@Override
	public ArrayList<Ricercatore> getRicercatoriProduzione(Prodotto prod) {
		return produzioneManager.getRicercatoriProduzione(prod);
	}

	/**
	 * Ricerca un prodotto per l'amministratore
	 * @param titolo titolo del prodotto
	 * @param anno_pub anno di pubblicazione
	 * @param tipologia tipologia
	 * @param nome_dip nome del dipartimento
	 * @param nome_area nome dell'area scientifica
	 * @param nome_autore nome dell'autore
	 * @param cognome_autore cognome dell'autore
	 */
	@Override
	public ArrayList<Prodotto> ricercaProdottoAmministratore(String titolo,
			String anno_pub, String tipologia, String nome_dip,
			String nome_area, String nome_autore, String cognome_autore) {
		return prodottoManager.ricercaProdottoAmministratore(titolo,
				anno_pub,
				tipologia,
				nome_dip,
				nome_area,
				nome_autore,
				cognome_autore);
	}

}
