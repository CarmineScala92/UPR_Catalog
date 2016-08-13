/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.SupportoValidazione;

import it.unisa.upr.commons.DBConstants;
import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneProdotti.IProdottoManager;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.gestioneProdotti.GestioneProdotti;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupportoValidazione implements IFacadeSupportoValidazione {

	private IProdottoManager ipm;
	private static SupportoValidazione supportoValidazioneManager;
	private static GestioneProdotti gestioneProdottiManager;

	public static SupportoValidazione getInstance() {
		if (supportoValidazioneManager == null) {
			supportoValidazioneManager = new SupportoValidazione();
		}
		return supportoValidazioneManager;
	}

	public SupportoValidazione() {
		ipm = (IProdottoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_PRODOTTO);

	}

	/**
	 * Accetta un prodotto, modificandone lo stato in Accettato
	 * @param id id del prodotto da accettare
	 * @param Note note da aggiungere per l'accettazione
	 * @return true se il prodotto è stato modificato, false altrimenti
	 */
	@Override
	public boolean accettaProdotto(Integer id, String Note) throws SQLException {
		GestioneProdotti gest = gestioneProdottiManager.getInstance();
		Prodotto p = gest.visualizzaProdotto(id);
		if (p.getStato().equals(DBConstants.PRODOTTO_STATO_COMPLETO)) {
			boolean r1 = ipm.modificaProdotto(p,
					"stato",
					DBConstants.PRODOTTO_STATO_ACCETTATO);
			return r1 && ipm.modificaProdotto(p, "note", Note);
		}
		else
			return false;
	}

	/**
	 * Rifiuta un prodotto e lo riporta in stato Draft
	 * @param id id del prodotto da rifiutare
	 * @param note motivo del rifiuto
	 * @return true se il prodotto è stato modificato, false altrimenti
	 */
	@Override
	public boolean rifiutaProdotto(Integer id, String note) throws SQLException {
		// TODO Auto-generated method stub

		GestioneProdotti gest = gestioneProdottiManager.getInstance();
		Prodotto p = gest.visualizzaProdotto(id);
		if (p.getStato().equals(DBConstants.PRODOTTO_STATO_COMPLETO) || p.getStato().equals(DBConstants.PRODOTTO_STATO_ACCETTATO)) {
			boolean r1 = ipm.modificaProdotto(p,
					"stato",
					DBConstants.PRODOTTO_STATO_DRAFT);
			return r1 && ipm.modificaProdotto(p, "note", note);
		}

		else
			return false;
	}

	/**
	 * Valida un prodotto modificandone lo stato in Validato
	 * @param id id del prodotto da validare
	 * @param Note Note di validazione
	 * @return true se il prodotto è stato modificato, false altrimenti
	 */
	@Override
	public boolean validaProdotto(Integer id, String Note) throws SQLException {
		// TODO Auto-generated method stub

		GestioneProdotti gest = gestioneProdottiManager.getInstance();
		Prodotto p = gest.visualizzaProdotto(id);
		if (p.getStato().equals(DBConstants.PRODOTTO_STATO_ACCETTATO)) {
			boolean r1 = ipm.modificaProdotto(p,
					"stato",
					DBConstants.PRODOTTO_STATO_VALIDATO);
			return r1 && ipm.modificaProdotto(p, "note", Note);
		}
		else
			return false;
	}

	/**
	 * Carica la lista di tutti i prodotti completi
	 * @return ritorna la lista dei prodotti completi
	 */
	public ArrayList<Prodotto> caricaListaProdottiCompleti()
			throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Prodotto> listacompleti = ipm.caricaProdottiCompleti();
		return listacompleti;
	}

	/**
	 * Carica la lista dei prodotti in stato Accettato
	 * @return ritorna la lista dei prodotti accettati
	 */
	@Override
	public ArrayList<Prodotto> caricaListaProdottiAccettati()
			throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Prodotto> listacompleti = ipm.caricaProdottiAccettati();
		return listacompleti;
	}
}
