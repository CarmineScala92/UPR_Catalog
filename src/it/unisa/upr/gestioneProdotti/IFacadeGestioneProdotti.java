/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.commons.IFacade;
import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.data.gestioneProdotti.Produzione;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interfaccia di GestioneProdotti
 * @see GestioneProdotti
 *
 */
public interface IFacadeGestioneProdotti extends IFacade {

	// ********************* PER LE SERVLET DI SERVIZIO

	public ArrayList<AreaScientifica> caricaListaAreeScientifiche()
			throws SQLException;

	public ArrayList<Dipartimento> caricaListaDipartimenti()
			throws SQLException;

	// L'HO DOVUTO AGGIUNGERE PERCHè SERVE NELLA SERVLE
	// CARICALISTARICERCATORIDIPARTIMENTI
	public ArrayList<Ricercatore> getListaRicercatori() throws SQLException;

	// TALE METODO L'HO DOVUTO AGGIUNGERE IO A IPRODOTTOMANAGER NELL'INTERFACCIA
	// PERCHE' NON C'ERA E NELLA
	// CLASSE DB RELATIVA A TALE METODO (DB PRODOTTO) HO GENERALIZZATO IL METODO
	// E LEVATO CLAUSOLA STATIC
	public ArrayList<Prodotto> caricaListaProdottiHomepage(Account a);

	public ArrayList<Produzione> getProduzioni(Prodotto prod);

	public boolean inserisciRicercatoriProduzione(Prodotto prod,
			String[] Autori_per_produzione, int[] id_proprietari);

	public ArrayList<Ricercatore> getRicercatoriProduzione(Prodotto prod);

	// ******************************* PER LE SERVLET DELLE FUNZIONALITA

	public boolean eliminaProdotto(Prodotto prod);

	public boolean inserisciProdotto(Prodotto prod,
			String[] Autori_per_produzione, int[] id_proprietari)
			throws SQLException;

	public boolean modificaProdotto(Prodotto prod, String campo,
			String valoreCampo);

	// TALE METODO L'HO DOVUTO AGGIUNGERE IO A IPRODOTTOMANAGER NELL'INTERFACCIA
	// PERCHE' NON C'ERA E NELLA
	// CLASSE DB RELATIVA A TALE METODO (DB PRODOTTO) HO CREATO IL METODO
	public Prodotto visualizzaProdotto(int ID_Prod) throws SQLException;

	public ArrayList<Prodotto> ricercaProdotto(String titolo,
			String anno_pubblicazione, String tipologia, String nome_area,
			String nome_dipart, String nome_prop, String cognome_prop);

	public String stampaProdotto(Prodotto prod); // RITORNA URL DOVE E'
													// DISPONIBILE SCARICARLO

	public ArrayList<Prodotto> ricercaProdottoUe(String titolo,
			String anno_pub, String tipologia, String nome_dip,
			String nome_area, String nome_autore, String cognome_autore);

	public boolean exists(String titolo, String tipologia, String anno)
			throws SQLException;

	public ArrayList<Prodotto> ricercaProdottiAmministratore();

	public ArrayList<Prodotto> ricercaProdottoAmministratore(String titolo,
			String anno_pub, String tipologia, String nome_dip,
			String nome_area, String nome_autore, String cognome_autore);

}
