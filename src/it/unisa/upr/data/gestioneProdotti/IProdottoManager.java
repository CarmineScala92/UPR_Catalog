/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneProdotti;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.commons.IManager;
import it.unisa.upr.data.gestioneAutenticazione.Account;

/**
 * Operazioni associate all'interfaccia di ProdottoManager
 * @see DBProdotto
 */
public interface IProdottoManager extends IManager {

	public ArrayList<Prodotto> ricercaProdotto(String titolo,
			String anno_pubblicazione, String tipologia, String nome_area,
			String nome_dipart, String nome_prop, String cognome_prop);

	public ArrayList<Prodotto> ricercaProdottoUe(String titolo,
			String anno_pubblicazione, String tipologia, String nome_area,
			String nome_dipart, String nome_prop, String cognome_prop);

	public ArrayList<Prodotto> caricaProdotti(Account acc);

	public boolean inserisciProdotto(Prodotto p);

	public boolean modificaProdotto(Prodotto p, String campo, String nuovoValore);

	public boolean eliminaProdotto(Prodotto p);

	public Prodotto visualizzaProdotto(int id) throws SQLException;

	public int getUltimoIDProdotto() throws SQLException;

	public ArrayList<Prodotto> caricaProdottiCompleti() throws SQLException;

	public ArrayList<Prodotto> caricaProdottiAccettati() throws SQLException;

	public boolean eliminaProdotti(Account account);

	public boolean exists(String titolo, String tipologia, String anno)
			throws SQLException;

	public ArrayList<Prodotto> ricercaProdottiAmministratore();

	public ArrayList<Prodotto> ricercaProdottoAmministratore(String titolo,
			String anno_pub, String tipologia, String nome_dip,
			String nome_area, String nome_autore, String cognome_autore);
}
