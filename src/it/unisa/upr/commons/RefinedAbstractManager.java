/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.commons;

import it.unisa.upr.data.gestioneAutenticazione.DBAccount;
import it.unisa.upr.data.gestioneAutenticazione.DBAmministratore;
import it.unisa.upr.data.gestioneAutenticazione.DBResponsabile;
import it.unisa.upr.data.gestioneAutenticazione.DBRicercatore;
import it.unisa.upr.data.gestioneProdotti.DBAltro;
import it.unisa.upr.data.gestioneProdotti.DBArticolo_Libro;
import it.unisa.upr.data.gestioneProdotti.DBArticolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.DBBrevetto;
import it.unisa.upr.data.gestioneProdotti.DBCuratela;
import it.unisa.upr.data.gestioneProdotti.DBMonografia;
import it.unisa.upr.data.gestioneProdotti.DBProceeding;
import it.unisa.upr.data.gestioneProdotti.DBProdotto;
import it.unisa.upr.data.gestioneProdotti.DBProduzione;
import it.unisa.upr.data.gestioneSistema.DBAreaScientifica;
import it.unisa.upr.data.gestioneSistema.DBDipartimento;

/**
 * La classe fornisce l'accesso ai vari sottosistemi attraverso il pattern
 * Bridge
 * 
 */
public class RefinedAbstractManager extends AbstractManager<IManager> {

	// Singleton Design Pattern's implementation
	private static RefinedAbstractManager refinedManager;

	private RefinedAbstractManager() {}

	/**
	 * 
	 * @return Ritorna l'istanza del manager. Si occupa di allocarlo se non è
	 *         ancora presente
	 */
	public static RefinedAbstractManager getInstance() {
		if (refinedManager == null) {
			refinedManager = new RefinedAbstractManager();
		}
		return refinedManager;
	}

	// end of Singleton Design Pattern's implementation

	/**
	 * @param pManagerType Il valore letterale della funzionalità alla quale si
	 *            vuole accedere
	 * @return Ritorna il manager della funzionalità
	 */
	public IManager getManagerImplementor(String pManagerType) {
		if (pManagerType.equals(DBNames.TABLE_ACCOUNT)) {
			this.imp = DBAccount.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_RICERCATORE)) {
			this.imp = DBRicercatore.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_RESPONSABILE)) {
			this.imp = DBResponsabile.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_AMMINISTRATORE)) {
			this.imp = DBAmministratore.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_AREA_SCIENTIFICA)) {
			this.imp = DBAreaScientifica.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_DIPARTIMENTO)) {
			this.imp = DBDipartimento.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_PRODOTTO)) {
			this.imp = DBProdotto.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_ARTICOLO_RIVISTA)) {
			this.imp = DBArticolo_Rivista.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_ARTICOLO_LIBRO)) {
			this.imp = DBArticolo_Libro.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_MONOGRAFIA)) {
			this.imp = DBMonografia.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_CURATELA)) {
			this.imp = DBCuratela.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_PROCEEDING)) {
			this.imp = DBProceeding.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_BREVETTO)) {
			this.imp = DBBrevetto.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_ALTRO)) {
			this.imp = DBAltro.getInstance();
		}
		else if (pManagerType.equals(DBNames.TABLE_PRODUZIONE)) {
			this.imp = DBProduzione.getInstance();
		}

		return this.imp;
	}
}
