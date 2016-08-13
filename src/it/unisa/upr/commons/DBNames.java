/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.commons;

/**
 * La classe contiene i nomi delle tabelle e degli attributi dei prodotti
 * 
 */
public final class DBNames {

	private DBNames() {}

	public static final String TABLE_ACCOUNT = "account";
	public static final String TABLE_ALTRO = "altro";
	public static final String TABLE_AMMINISTRATORE = "amministratore";
	public static final String TABLE_AREA_SCIENTIFICA = "area_scientifica";
	public static final String TABLE_ARTICOLO_LIBRO = "articolo_libro";
	public static final String TABLE_ARTICOLO_RIVISTA = "articolo_rivista";
	public static final String TABLE_BREVETTO = "brevetto";
	public static final String TABLE_COMPOSIZIONE = "composizione";
	public static final String TABLE_CURATELA = "curatela";
	public static final String TABLE_DIPARTIMENTO = "dipartimento";
	public static final String TABLE_EVENTO = "evento";
	public static final String TABLE_MONOGRAFIA = "monografia";
	public static final String TABLE_PROCEEDING = "proceeding";
	public static final String TABLE_PRODOTTO = "prodotto";
	public static final String TABLE_PRODUZIONE = "produzione";
	public static final String TABLE_RESPONSABILE = "responsabile";
	public static final String TABLE_RICERCATORE = "ricercatore";
	public static final String TABLE_SELEZIONE = "selezione";

	// ATTRIBUTI DELLA TABELLA PRODOTTO
	public static final String ATT_PRODOTTO_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_PRODOTTO_ID_PROPRIETARIO = "ID_Proprietario";
	public static final String ATT_PRODOTTO_TITOLO = "Titolo";
	public static final String ATT_PRODOTTO_AUTORI = "Autori";
	public static final String ATT_PRODOTTO_ANNO_PUBBLICAZIONE = "Anno_Pubblicazione";
	public static final String ATT_PRODOTTO_ABSTRACT = "Abstract";
	public static final String ATT_PRODOTTO_PUBBLICO = "Pubblico";
	public static final String ATT_PRODOTTO_STATO = "Stato";
	public static final String ATT_PRODOTTO_TIPOLOGIA = "Tipologia";
	public static final String ATT_PRODOTTO_URL = "URL";
	public static final String ATT_PRODOTTO_NOTE = "Note";

	// ATTRIBUTI DELLA TABELLA ARTICOLO_RIVISTA

	public static final String ATT_ARTICOLO_RIVISTA_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_ARTICOLO_RIVISTA_NOME_RIVISTA = "Nome_Rivista";
	public static final String ATT_ARTICOLO_RIVISTA_NUMERO_VOLUME = "Numero_Volume";
	public static final String ATT_ARTICOLO_RIVISTA_PAGINE_RIFERIMENTO = "Pagine_riferimento";
	public static final String ATT_ARTICOLO_RIVISTA_DOI = "DOI";
	public static final String ATT_ARTICOLO_RIVISTA_KEYWORDS = "Keywords";

	// ATTRIBUTI PRESENTI IN ARTICOLO LIBRO

	public static final String ATT_ARTICOLO_LIBRO_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_ARTICOLO_LIBRO_NOME_VOLUME = "Nome_Volume";
	public static final String ATT_ARTICOLO_LIBRO_AUTORI_VOLUME = "Autori_Volume";
	public static final String ATT_ARTICOLO_LIBRO_EDITORE = "Editore";
	public static final String ATT_ARTICOLO_LIBRO_CITTA_EDITORE = "Citta_Editore";
	public static final String ATT_ARTICOLO_LIBRO_PAESE_EDITORE = "Paese_Editore";
	public static final String ATT_ARTICOLO_LIBRO_ISBN = "ISBN";
	public static final String ATT_ARTICOLO_LIBRO_PAGINE_RIFERIMENTO = "Pagine_Riferimento";
	public static final String ATT_ARTICOLO_LIBRO_DOI = "DOI";
	public static final String ATT_ARTICOLO_LIBRO_KEYWORDS = "Keywords";

	// ATTRIBUTI IN BREVETTO

	public static final String ATT_BREVETTO_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_BREVETTO_PROPRIETA = "Proprieta";
	public static final String ATT_BREVETTO_NUMERO_BREVETTO = "Numero_Brevetto";
	public static final String ATT_BREVETTO_DOI = "DOI";
	public static final String ATT_BREVETTO_TIPO = "Tipo";
	public static final String ATT_BREVETTO_KEYWORDS = "Keywords";

	// ATTRIBUTI IN COMPOSIZIONE
	public static final String ATT_COMPOSIZIONE_ID_RICERCATORE = "ID_Ricercatore";
	public static final String ATT_COMPOSIZIONE_ID_PRODOTTO = "ID_Prodotto";

	// ATTRIBUTI IN CURATELA

	public static final String ATT_CURATELA_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_CURATELA_NOME_RIVISTA = "Nome_Rivista";
	public static final String ATT_CURATELA_NUMERO_VOLUME = "Numero_Volume";
	public static final String ATT_CURATELA_AUTORI_VOLUME = "Autori_Volume";
	public static final String ATT_CURATELA_EDITORE = "Editore";
	public static final String ATT_CURATELA_CITTA_EDITORE = "Citta_Editore";
	public static final String ATT_CURATELA_PAESE_EDITORE = "Paese_Editore";
	public static final String ATT_CURATELA_PAGINE_RIFERIMENTO = "Pagine_Riferimento";
	public static final String ATT_CURATELA_ISBN = "ISBN";
	public static final String ATT_CURATELA_DOI = "DOI";
	public static final String ATT_CURATELA_KEYWORDS = "Keywords";

	// ATTRIBUTI IN MONOGRAFIA

	public static final String ATT_MONOGRAFIA_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_MONOGRAFIA_EDITORE = "Editore";
	public static final String ATT_MONOGRAFIA_CITTA_EDITORE = "Citta_Editore";
	public static final String ATT_MONOGRAFIA_PAESE_EDITORE = "Paese_Editore";
	public static final String ATT_MONOGRAFIA_PAGINE_RIFERIMENTO = "Pagine_Riferimento";
	public static final String ATT_MONOGRAFIA_ISBN = "ISBN";
	public static final String ATT_MONOGRAFIA_DOI = "DOI";
	public static final String ATT_MONOGRAFIA_KEYWORDS = "Keywords";

	// ATTRIBUTI IN PROCEEDING

	public static final String ATT_PROCEEDING_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_PROCEEDING_RELAZIONE = "Relazione";
	public static final String ATT_PROCEEDING_NOME_VOLUME = "Nome_Volume";
	public static final String ATT_PROCEEDING_AUTORI_VOLUME = "Autori_Volume";
	public static final String ATT_PROCEEDING_EDITORE = "Editore";
	public static final String ATT_PROCEEDING_CITTA_EDITORE = "Citta_Editore";
	public static final String ATT_PROCEEDING_PAESE_EDITORE = "Paese_Editore";
	public static final String ATT_PROCEEDING_PAGINE_RIFERIMENTO = "Pagine_Riferimento";
	public static final String ATT_PROCEEDING_NOME_CONGRESSO = "Nome_Congresso";
	public static final String ATT_PROCEEDING_DATA_CONGRESSO = "Data_Congresso";
	public static final String ATT_PROCEEDING_LUOGO_CONGRESSO = "Luogo_Congresso";
	public static final String ATT_PROCEEDING_RILEVANZA = "Rilevanza";
	public static final String ATT_PROCEEDING_ISBN = "ISBN";
	public static final String ATT_PROCEEDING_DOI = "DOI";
	public static final String ATT_PROCEEDING_KEYWORDS = "Keywords";

	// ATTRIBUTI IN ALTRO

	public static final String ATT_ALTRO_ID_PRODOTTO = "ID_Prodotto";
	public static final String ATT_ALTRO_NOME_VOLUME = "Nome_Volume";
	public static final String ATT_ALTRO_NUMERO_VOLUME = "Numero_Volume";
	public static final String ATT_ALTRO_PAGINE_RIFERIMENTO = "Pagine_Riferimento";
	public static final String ATT_ALTRO_ISBN = "ISBN";
	public static final String ATT_ALTRO_KEYWORDS = "Keywords";
}
