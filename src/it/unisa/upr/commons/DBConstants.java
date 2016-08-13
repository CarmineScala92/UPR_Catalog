/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.commons;

/**
 * La classe contiene le costanti del database
 * 
 */
public final class DBConstants {

	private DBConstants() {}

	public static final String PRODOTTO_STATO_DRAFT = "draft";
	public static final String PRODOTTO_STATO_COMPLETO = "completo";
	public static final String PRODOTTO_STATO_ACCETTATO = "accettato";
	public static final String PRODOTTO_STATO_VALIDATO = "validato";
	public static final String PRODOTTO_STATO_RIFIUTATO = "rifiutato";
	public static final String PRODOTTO_STATO_INVIATO = "inviato";

	public static final int PRODOTTO_STATO_PUBBLICO = 0;
	public static final int PRODOTTO_STATO_PRIVATO = 1;

	public static final String SELEZIONE_STATO_COMPLETA = "completa";
	public static final String SELEZIONE_STATO_ACCETTATA = "accettata";
	public static final String SELEZIONE_STATO_VALIDATA = "validata";

	public static final String ACCOUNT_TIPOLOGIA_RICERCATORE = "Ricercatore";
	public static final String ACCOUNT_TIPOLOGIA_AMMINISTRATORE = "Amministratore";
	public static final String ACCOUNT_TIPOLOGIA_RESPONSABILE = "Responsabile";

	public static final String RICERCATORE_RUOLO_RICERCATORE = "Ricercatore";
	public static final String RICERCATORE_RUOLO_DIRETTORE = "Direttore";
	public static final String RICERCATORE_RUOLO_MEMBRO = "Membro";

}
