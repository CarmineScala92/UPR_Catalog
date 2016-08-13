/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.commons;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.Curatela;
import it.unisa.upr.data.gestioneProdotti.Monografia;
import it.unisa.upr.data.gestioneProdotti.Proceeding;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.data.gestioneProdotti.Produzione;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.DBAreaScientifica;
import it.unisa.upr.data.gestioneSistema.DBDipartimento;
import it.unisa.upr.data.gestioneSistema.Dipartimento;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;

/**
 * La classe è predisposta per la conversione degli oggetti beans in formato XML
 * 
 */
public class XMLConverter {

	/**
	 * Si occupa di convertire una lista di prodotti in XML
	 * I prodotti verranno trattati come prodotti generici.
	 * Formato XML:
	 * 
	 * <response>
	 * <prodotto>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * </prodotto>
	 * 
	 * <prodotto>
	 * .
	 * .
	 * .
	 * </prodotto>
	 * </response>
	 * 
	 * @param listaProdotti lista dei prodotti da includere nel file xml
	 * @return Il documento XML risultante
	 */
	public static Document caricaListaProdottiHomepage(ArrayList<Prodotto> listaProdotti) {
		Element xml_root = new Element("response");
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;
		Element xml_Prodotto;

		for (Prodotto p : listaProdotti) {

			xml_Prodotto = new Element("prodotto");
			xml_ID_Prodotto = new Element("ID_Prodotto");
			xml_ID_Proprietario = new Element("ID_Proprietario");
			xml_Titolo = new Element("Titolo");
			xml_Autori = new Element("Autori");
			xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
			xml_Abstract = new Element("Abstract");
			xml_Pubblico = new Element("Pubblico");
			xml_Stato = new Element("Stato");
			xml_Tipologia = new Element("Tipologia");
			xml_URL = new Element("URL");
			xml_Note = new Element("Note");

			xml_ID_Prodotto.setText("" + p.getID_Prodotto());
			xml_ID_Proprietario.setText("" + p.getID_Proprietario());
			xml_Abstract.setText(p.getAbstract());
			xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
			xml_Autori.setText(p.getAutori());
			xml_Note.setText(p.getNote());
			xml_Pubblico.setText("" + p.getPubblico());
			xml_Stato.setText(p.getStato());
			xml_Tipologia.setText(p.getTipologia());
			xml_Titolo.setText(p.getTitolo());
			xml_URL.setText(p.getURL());

			xml_Prodotto.addContent(xml_ID_Prodotto);
			xml_Prodotto.addContent(xml_ID_Proprietario);
			xml_Prodotto.addContent(xml_Titolo);
			xml_Prodotto.addContent(xml_Autori);
			xml_Prodotto.addContent(xml_Anno_Pubblicazione);
			xml_Prodotto.addContent(xml_Abstract);
			xml_Prodotto.addContent(xml_Pubblico);
			xml_Prodotto.addContent(xml_Stato);
			xml_Prodotto.addContent(xml_Tipologia);
			xml_Prodotto.addContent(xml_URL);
			xml_Prodotto.addContent(xml_Note);

			xml_root.addContent(xml_Prodotto);
		}
		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Si occupa di creare una lista di ricercatori suddivisi per dipartimento,
	 * in formato XML
	 * Formato XML:
	 * 
	 * <Dipartimenti>
	 * 
	 * <Dipartimento>
	 * <Nome_Dipartimento />
	 * <Ricercatori>
	 * <Ricercatore>
	 * <Nome_Ricercatore />
	 * <Cognome_Ricercatore />
	 * <ID_Ricercatore />
	 * </Ricercatore>
	 * <Ricercatore>
	 * .
	 * .
	 * .
	 * </Ricercatore>
	 * </Ricercatori>
	 * </Dipartimento>
	 * 
	 * <Dipartimento>
	 * .
	 * .
	 * .
	 * </Dipartimento>
	 * </Dipartimenti>
	 * 
	 * @param listaRicercatori lista di tutti i ricercatori dell'Ateneo
	 * @param listaDipartimenti lista di tutti i dipartimenti dell'Ateneo
	 * @return Il documento XML risultante
	 * 
	 */
	public static Document caricaListaRicercatoriDipartimento(
			ArrayList<Ricercatore> listaRicercatori,
			ArrayList<Dipartimento> listaDipartimenti) {
		Element xml_root = new Element("Dipartimenti");

		Element xml_Dipartimento;

		Element xml_NomeDipartimento;

		Element xml_Ricercatori;
		Element xml_Ricercatore; // CONTENITORE
		Element xml_Nome;
		Element xml_Cognome;
		Element xml_ID_Ricercatore; // SERVE PER LA OPTIONS DELLA SELECT

		for (Dipartimento d : listaDipartimenti) {
			xml_Dipartimento = new Element("Dipartimento");

			xml_NomeDipartimento = new Element("Nome_Dipartimento");
			xml_NomeDipartimento.setText(d.getNome());

			xml_Dipartimento.addContent(xml_NomeDipartimento);
			xml_Ricercatori = new Element("Ricercatori");
			for (Ricercatore r : listaRicercatori) {

				if (r.getID_Dipartimento() == d.getID_Dipartimento()) {

					xml_Ricercatore = new Element("Ricercatore");

					xml_Nome = new Element("Nome_Ricercatore");
					xml_Cognome = new Element("Cognome_Ricercatore");
					xml_ID_Ricercatore = new Element("ID_Ricercatore");

					xml_ID_Ricercatore.setText("" + r.getID_Ricercatore());
					xml_Nome.setText(r.getNome());
					xml_Cognome.setText(r.getCognome());

					xml_Ricercatore.addContent(xml_Nome);
					xml_Ricercatore.addContent(xml_Cognome);
					xml_Ricercatore.addContent(xml_ID_Ricercatore);

					xml_Ricercatori.addContent(xml_Ricercatore);
				}
			}
			xml_Dipartimento.addContent(xml_Ricercatori);
			xml_root.addContent(xml_Dipartimento);
		}

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte una lista di aree scientifiche in un documento xml
	 * Formato XML:
	 * 
	 * <response>
	 * <areascientifica>
	 * <nome />
	 * <id />
	 * </areascientifica>
	 * </response>
	 * 
	 * @param listaAreeScientifiche lista delle aree scientifiche da inserire
	 *            nel documento
	 * @return Il documento XML risultante
	 */
	public static Document caricaListaAreeScientifiche(
			ArrayList<AreaScientifica> listaAreeScientifiche) {
		Element xml_root = new Element("response");
		Element xml_areascientifica;
		Element xml_nome;
		Element xml_id;

		for (AreaScientifica e : listaAreeScientifiche) {
			xml_areascientifica = new Element("areascientifica");

			xml_nome = new Element("nome");
			xml_nome.setText(e.getNome());

			xml_id = new Element("id");
			xml_id.setText(Integer.toString(e.getID_Area_Scientifica()));

			xml_areascientifica.addContent(xml_nome);
			xml_areascientifica.addContent(xml_id);

			xml_root.addContent(xml_areascientifica);
		}

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte una lista di dipartimenti in un documento xml
	 * Formato XML:
	 * 
	 * <response>
	 * <dipartimento>
	 * <nome />
	 * <id />
	 * </dipartimento>
	 * </response>
	 * 
	 * @param listaDipartimenti lista dei dipartimenti da inserire nel documento
	 * @return Il documento XML risultante
	 */
	public static Document caricaListaDipartimenti(
			ArrayList<Dipartimento> listaDipartimenti) {

		Element xml_root = new Element("response");
		Element xml_dipartimento;
		Element xml_nome;
		Element xml_id;

		for (Dipartimento e : listaDipartimenti) {

			xml_dipartimento = new Element("dipartimento");

			xml_nome = new Element("nome");
			xml_nome.setText(e.getNome());

			xml_id = new Element("id");
			xml_id.setText(Integer.toString(e.getID_Dipartimento()));

			xml_dipartimento.addContent(xml_nome);
			xml_dipartimento.addContent(xml_id);

			xml_root.addContent(xml_dipartimento);
		}

		Document doc = new Document(xml_root);
		return doc;

	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Articolo Libro
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Nome_Volume />
	 * <Pagine_Riferimento />
	 * <ISBN />
	 * <Keywords />
	 * <Autori_Volume />
	 * <Editore />
	 * <Citta_Editore />
	 * <Paese_Editore />
	 * <DOI />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoArticoloLibro(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {

		Articolo_Libro p = (Articolo_Libro) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;

		Element xml_Nome_Volume;
		Element xml_Autori_Volume;
		Element xml_Pagine_Riferimento;
		Element xml_ISBN;
		Element xml_Keywords;
		Element xml_Editore;
		Element xml_Citta_Editore;
		Element xml_Paese_Editore;
		Element xml_DOI;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");
		xml_Nome_Volume = new Element("Nome_Volume");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_ISBN = new Element("ISBN");
		xml_Keywords = new Element("Keywords");
		xml_Autori_Volume = new Element("Autori_Volume");
		xml_Editore = new Element("Editore");
		xml_Citta_Editore = new Element("Citta_Editore");
		xml_Paese_Editore = new Element("Paese_Editore");
		xml_DOI = new Element("DOI");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());
		xml_Nome_Volume.setText(p.getNome_Volume());
		xml_Pagine_Riferimento.setText(p.getPagine_Riferimento());
		xml_ISBN.setText(p.getISBN());
		xml_Keywords.setText(p.getKeywords());
		xml_Autori_Volume.setText(p.getAutori_Volume());
		xml_Editore.setText(p.getEditore());
		xml_Citta_Editore.setText(p.getCitta_Editore());
		xml_Paese_Editore.setText(p.getPaese_Editore());
		xml_DOI.setText(p.getDOI());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Nome_Volume);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_ISBN);
		xml_ID_Account.addContent(xml_Keywords);
		xml_ID_Account.addContent(xml_Autori_Volume);
		xml_ID_Account.addContent(xml_Editore);
		xml_ID_Account.addContent(xml_Citta_Editore);
		xml_ID_Account.addContent(xml_Paese_Editore);
		xml_ID_Account.addContent(xml_DOI);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);
		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;

	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Brevetto
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Numero_Brevetto />
	 * <Proprieta />
	 * <Tipo />
	 * <DOI />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoBrevetto(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {
		Brevetto p = (Brevetto) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;

		Element xml_Numero_Brevetto;
		Element xml_Proprieta;
		Element xml_Tipo;
		Element xml_Keywords;
		Element xml_DOI;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");

		xml_Numero_Brevetto = new Element("Numero_Brevetto");
		xml_Proprieta = new Element("Proprieta");
		xml_Tipo = new Element("Tipo");;
		xml_Keywords = new Element("Keywords");
		xml_DOI = new Element("DOI");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());

		xml_Numero_Brevetto.setText(p.getNumero_Brevetto());
		xml_Proprieta.setText(p.getProprieta());
		xml_Tipo.setText(p.getTipo());
		xml_Keywords.setText(p.getKeywords());
		xml_DOI.setText(p.getDOI());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Numero_Brevetto);
		xml_ID_Account.addContent(xml_Proprieta);
		xml_ID_Account.addContent(xml_Tipo);
		xml_ID_Account.addContent(xml_Keywords);
		xml_ID_Account.addContent(xml_DOI);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Articolo Rivista
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Nome_Rivista />
	 * <Numero_Volume />
	 * <Pagine_Riferimento />
	 * <DOI />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * 
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoArticoloRivista(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {
		Articolo_Rivista p = (Articolo_Rivista) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;

		Element xml_Nome_Rivista;
		Element xml_Numero_Volume;
		Element xml_Pagine_Riferimento;
		Element xml_DOI;
		Element xml_Keywords;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");

		xml_Nome_Rivista = new Element("Nome_Rivista");
		xml_Numero_Volume = new Element("Numero_Volume");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_DOI = new Element("DOI");
		xml_Keywords = new Element("Keywords");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());
		xml_Nome_Rivista.setText(p.getNome_Rivista());
		xml_Numero_Volume.setText(p.getNumero_Volume());
		xml_Pagine_Riferimento.setText(p.getPagine_riferimento());
		xml_DOI.setText(p.getDOI());
		xml_Keywords.setText(p.getKeywords());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Nome_Rivista);
		xml_ID_Account.addContent(xml_Numero_Volume);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_DOI);
		xml_ID_Account.addContent(xml_Keywords);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Curatela
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Nome_Rivista />
	 * <Numero_Volume />
	 * <Editore />
	 * <Citta_Editore />
	 * <Paese_Editore />
	 * <Pagine_Riferimento />
	 * <ISBN />
	 * <Autori_Volume />
	 * <DOI />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoCuratela(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {
		Curatela p = (Curatela) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;

		Element xml_Nome_Rivista;
		Element xml_Numero_Volume;
		Element xml_Autori_Volume;
		Element xml_Editore;
		Element xml_Citta_Editore;
		Element xml_Paese_Editore;
		Element xml_Pagine_Riferimento;
		Element xml_DOI;
		Element xml_ISBN;
		Element xml_Keywords;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");

		xml_Numero_Volume = new Element("Numero_Volume");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_ISBN = new Element("ISBN");
		xml_Keywords = new Element("Keywords");
		xml_Autori_Volume = new Element("Autori_Volume");
		xml_Editore = new Element("Editore");
		xml_Citta_Editore = new Element("Citta_Editore");
		xml_Paese_Editore = new Element("Paese_Editore");
		xml_Nome_Rivista = new Element("Nome_Rivista");
		xml_DOI = new Element("DOI");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());

		xml_Nome_Rivista.setText(p.getNome_Rivista());
		xml_Numero_Volume.setText(p.getNumero_Volume());
		xml_Pagine_Riferimento.setText(p.getPagine_Riferimento());
		xml_ISBN.setText(p.getISBN());
		xml_Keywords.setText(p.getKeywords());
		xml_Autori_Volume.setText(p.getAutori_Volume());
		xml_Editore.setText(p.getEditore());
		xml_Citta_Editore.setText(p.getCitta_Editore());
		xml_Paese_Editore.setText(p.getPaese_Editore());
		xml_DOI.setText(p.getDOI());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);

		xml_ID_Account.addContent(xml_Numero_Volume);
		xml_ID_Account.addContent(xml_Nome_Rivista);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_ISBN);
		xml_ID_Account.addContent(xml_Keywords);
		xml_ID_Account.addContent(xml_Autori_Volume);
		xml_ID_Account.addContent(xml_Editore);
		xml_ID_Account.addContent(xml_Citta_Editore);
		xml_ID_Account.addContent(xml_Paese_Editore);
		xml_ID_Account.addContent(xml_DOI);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Altro
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Nome_Volume />
	 * <Numero_Volume />
	 * <Pagine_Riferimento />
	 * <ISBN />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoAltro(Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {

		Altro p = (Altro) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;
		Element xml_Nome_Volume;
		Element xml_Numero_Volume;
		Element xml_Pagine_Riferimento;
		Element xml_ISBN;
		Element xml_Keywords;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");
		xml_Nome_Volume = new Element("Nome_Volume");
		xml_Numero_Volume = new Element("Numero_Volume");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_ISBN = new Element("ISBN");
		xml_Keywords = new Element("Keywords");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());
		xml_Nome_Volume.setText(p.getNome_Volume());
		xml_Numero_Volume.setText(p.getNumero_Volume());
		xml_Pagine_Riferimento.setText(p.getPagine_Riferimento());
		xml_ISBN.setText(p.getISBN());
		xml_Keywords.setText(p.getKeywords());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Nome_Volume);
		xml_ID_Account.addContent(xml_Numero_Volume);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_ISBN);
		xml_ID_Account.addContent(xml_Keywords);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Monografia
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Editore />
	 * <Citta_Editore />
	 * <Paese_Editore />
	 * <ISBN />
	 * <DOI />
	 * <Pagine_Riferimento />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoMonografia(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {

		Monografia p = (Monografia) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;

		Element xml_Pagine_Riferimento;
		Element xml_Keywords;
		Element xml_Editore;
		Element xml_Citta_Editore;
		Element xml_Paese_Editore;
		Element xml_DOI;
		Element xml_ISBN;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_ISBN = new Element("ISBN");
		xml_Keywords = new Element("Keywords");
		xml_Editore = new Element("Editore");
		xml_Citta_Editore = new Element("Citta_Editore");
		xml_Paese_Editore = new Element("Paese_Editore");
		xml_DOI = new Element("DOI");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());
		xml_Pagine_Riferimento.setText(p.getPagine_Riferimento());
		xml_ISBN.setText(p.getISBN());
		xml_Keywords.setText(p.getKeywords());
		xml_Editore.setText(p.getEditore());
		xml_Citta_Editore.setText(p.getCitta_Editore());
		xml_Paese_Editore.setText(p.getPaese_Editore());
		xml_DOI.setText(p.getDOI());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_ISBN);
		xml_ID_Account.addContent(xml_Keywords);
		xml_ID_Account.addContent(xml_Editore);
		xml_ID_Account.addContent(xml_Citta_Editore);
		xml_ID_Account.addContent(xml_Paese_Editore);
		xml_ID_Account.addContent(xml_DOI);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un prodotto nel formato XML
	 * Il prodotto include anche i campi della tipologia Proceeding
	 * 
	 * Formato XML:
	 * <Response>
	 * <ID_Prodotto />
	 * <ID_Proprietario />
	 * <Titolo />
	 * <Autori />
	 * <Anno_Pubblicazione />
	 * <Abstract />
	 * <Pubblico />
	 * <Stato />
	 * <Tipologia />
	 * <URL />
	 * <Note />
	 * <Editore />
	 * <Citta_Editore />
	 * <Paese_Editore />
	 * <ISBN />
	 * <DOI />
	 * <Pagine_Riferimento />
	 * <Nome_Volume />
	 * <Autori_Volume />
	 * <Rilevanza />
	 * <Relazione />
	 * <Data_Congresso />
	 * <Luogo_Congresso />
	 * <Nome_Congresso />
	 * <Keywords />
	 * <Autori_Ricercatori />
	 * <Id_Ricercatori >
	 * <id />
	 * .
	 * </Id_Ricercatori>
	 * </Response>
	 * 
	 * @param prodotto_visualizzare Prodotto da convertire
	 * @param iD_Ricercatori elenco di id dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @param ricercatoriProduzione nomi dei ricercatori che hanno partecipato
	 *            alla produzione
	 * @return Il documento XML risultante
	 */
	public static Document caricaProdottoProceeding(
			Prodotto prodotto_visualizzare,
			ArrayList<Produzione> iD_Ricercatori,
			ArrayList<Ricercatore> ricercatoriProduzione) {
		Proceeding p = (Proceeding) prodotto_visualizzare;

		Element xml_root = new Element("Response");
		Element xml_ID_Account;
		Element xml_ID_Prodotto;
		Element xml_ID_Proprietario;
		Element xml_Titolo;
		Element xml_Autori;
		Element xml_Anno_Pubblicazione;
		Element xml_Abstract;
		Element xml_Pubblico;
		Element xml_Stato;
		Element xml_Tipologia;
		Element xml_URL;
		Element xml_Note;
		Element xml_Nome_Volume;
		Element xml_Pagine_Riferimento;
		Element xml_ISBN;
		Element xml_Keywords;
		Element xml_Autori_Volume;
		Element xml_Editore;
		Element xml_Citta_Editore;
		Element xml_Paese_Editore;
		Element xml_Nome_Congresso;
		Element xml_Data_Congresso;
		Element xml_Luogo_Congresso;
		Element xml_Rilevanza;
		Element xml_Relazione;
		Element xml_DOI;

		xml_ID_Account = new Element("Prodotto");
		xml_ID_Prodotto = new Element("ID_Prodotto");
		xml_ID_Proprietario = new Element("ID_Proprietario");
		xml_Titolo = new Element("Titolo");
		xml_Autori = new Element("Autori");
		xml_Anno_Pubblicazione = new Element("Anno_Pubblicazione");
		xml_Abstract = new Element("Abstract");
		xml_Pubblico = new Element("Pubblico");
		xml_Stato = new Element("Stato");
		xml_Tipologia = new Element("Tipologia");
		xml_URL = new Element("URL");
		xml_Note = new Element("Note");
		xml_Nome_Volume = new Element("Nome_Volume");
		xml_Pagine_Riferimento = new Element("Pagine_Riferimento");
		xml_ISBN = new Element("ISBN");
		xml_Keywords = new Element("Keywords");
		xml_Autori_Volume = new Element("Autori_Volume");
		xml_Editore = new Element("Editore");
		xml_Citta_Editore = new Element("Citta_Editore");
		xml_Paese_Editore = new Element("Paese_Editore");
		xml_Nome_Congresso = new Element("Nome_Congresso");
		xml_Data_Congresso = new Element("Data_Congresso");
		xml_Luogo_Congresso = new Element("Luogo_Congresso");
		xml_Rilevanza = new Element("Rilevanza");
		xml_Relazione = new Element("Relazione");
		xml_DOI = new Element("DOI");

		xml_ID_Prodotto.setText("" + p.getID_Prodotto());
		xml_ID_Proprietario.setText("" + p.getID_Proprietario());
		xml_Abstract.setText(p.getAbstract());
		xml_Anno_Pubblicazione.setText(p.getAnno_Pubblicazione());
		xml_Autori.setText(p.getAutori());
		xml_Note.setText(p.getNote());
		xml_Pubblico.setText("" + p.getPubblico());
		xml_Stato.setText(p.getStato());
		xml_Tipologia.setText(p.getTipologia());
		xml_Titolo.setText(p.getTitolo());
		xml_URL.setText(p.getURL());
		xml_Nome_Volume.setText(p.getNome_Volume());
		xml_Pagine_Riferimento.setText(p.getPagine_Riferimento());
		xml_ISBN.setText(p.getISBN());
		xml_Keywords.setText(p.getKeywords());
		xml_Autori_Volume.setText(p.getAutori_Volume());
		xml_Editore.setText(p.getEditore());
		xml_Citta_Editore.setText(p.getCitta_Editore());
		xml_Paese_Editore.setText(p.getPaese_Editore());
		xml_Nome_Congresso.setText(p.getNome_Congresso());
		xml_Data_Congresso.setText(p.getData_Congresso().toString());
		xml_Luogo_Congresso.setText(p.getLuogo_Congresso());
		xml_Rilevanza.setText(p.getRilevanza());
		xml_Relazione.setText(p.getRelazione());
		xml_DOI.setText(p.getDOI());

		xml_ID_Account.addContent(xml_ID_Prodotto);
		xml_ID_Account.addContent(xml_ID_Proprietario);
		xml_ID_Account.addContent(xml_Titolo);
		xml_ID_Account.addContent(xml_Autori);
		xml_ID_Account.addContent(xml_Anno_Pubblicazione);
		xml_ID_Account.addContent(xml_Abstract);
		xml_ID_Account.addContent(xml_Pubblico);
		xml_ID_Account.addContent(xml_Stato);
		xml_ID_Account.addContent(xml_Tipologia);
		xml_ID_Account.addContent(xml_URL);
		xml_ID_Account.addContent(xml_Note);
		xml_ID_Account.addContent(xml_Nome_Volume);
		xml_ID_Account.addContent(xml_Pagine_Riferimento);
		xml_ID_Account.addContent(xml_ISBN);
		xml_ID_Account.addContent(xml_Keywords);
		xml_ID_Account.addContent(xml_Autori_Volume);
		xml_ID_Account.addContent(xml_Editore);
		xml_ID_Account.addContent(xml_Citta_Editore);
		xml_ID_Account.addContent(xml_Paese_Editore);
		xml_ID_Account.addContent(xml_Nome_Congresso);
		xml_ID_Account.addContent(xml_Data_Congresso);
		xml_ID_Account.addContent(xml_Luogo_Congresso);
		xml_ID_Account.addContent(xml_Rilevanza);
		xml_ID_Account.addContent(xml_Relazione);
		xml_ID_Account.addContent(xml_DOI);

		Element xml_Autori_Ricercatori = new Element("Autori_Ricercatori");
		String roots = "";
		for (int i = 0; i < ricercatoriProduzione.size(); i++) {
			String nomeCognome = "" + ricercatoriProduzione.get(i).getNome() + " " + ricercatoriProduzione.get(i).getCognome() + ",";
			roots += nomeCognome;
		}
		roots = roots.substring(0, roots.length() - 1);

		xml_Autori_Ricercatori.setText(roots);
		xml_ID_Account.addContent(xml_Autori_Ricercatori);

		Element xml_Ricercatori_Autori = new Element("Id_Ricercatori");
		Element id;
		for (int i = 0; i < iD_Ricercatori.size(); i++) {
			id = new Element("Id");
			id.setText(Integer.toString(iD_Ricercatori.get(i).getID_Ricercatore()));
			xml_Ricercatori_Autori.addContent(id);
		}
		xml_ID_Account.addContent(xml_Ricercatori_Autori);

		xml_root.addContent(xml_ID_Account);

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte un account in un documento XML
	 * Mostra tutti i dettagli.
	 * 
	 * @param a account da convertire
	 * @param nome_Dip nome del dipartimento di appartenenza
	 * @param nome_Area nome dell'area di appartenenza
	 * @return Il documento XML risultante
	 */
	public static Document caricaAccount(Account a, String nome_Dip,
			String nome_Area) {
		Element xml_root = new Element("response");

		Element xml_Account = new Element("Account");
		Element xml_ID_Account;
		Element xml_Username;
		Element xml_Password;
		Element xml_Tipologia;

		xml_ID_Account = new Element("ID_Account");
		xml_Username = new Element("Username");
		xml_Password = new Element("Password");
		xml_Tipologia = new Element("Tipologia");

		xml_ID_Account.setText("" + a.getID_Account());
		xml_Username.setText(a.getUsername());
		xml_Password.setText(a.getPassword());
		xml_Tipologia.setText(a.getTipologia());

		if (a.getTipologia().equalsIgnoreCase("Amministratore")) {
			Amministratore admin = (Amministratore) a;

			xml_Account.addContent(xml_ID_Account);
			xml_Account.addContent(xml_Username);
			xml_Account.addContent(xml_Password);
			xml_Account.addContent(xml_Tipologia);

			Element xml_ID_Amministratore = new Element("ID_Amministratore");
			Element xml_Nome = new Element("Nome");
			Element xml_Cognome = new Element("Cognome");

			xml_ID_Amministratore.setText("" + admin.getID_Amministratore());
			xml_Nome.setText(admin.getNome());
			xml_Cognome.setText(admin.getCognome());

			xml_Account.addContent(xml_ID_Amministratore);
			xml_Account.addContent(xml_Nome);
			xml_Account.addContent(xml_Cognome);

		}
		else if (a.getTipologia().equalsIgnoreCase("Responsabile")) {
			Responsabile resp = (Responsabile) a;

			xml_Account.addContent(xml_ID_Account);
			xml_Account.addContent(xml_Username);
			xml_Account.addContent(xml_Password);
			xml_Account.addContent(xml_Tipologia);

			Element xml_ID_Responsabile = new Element("ID_Responsabile");
			Element xml_Nome = new Element("Nome");
			Element xml_Cognome = new Element("Cognome");

			xml_ID_Responsabile.setText("" + resp.getID_Responsabile());
			xml_Nome.setText(resp.getNome());
			xml_Cognome.setText(resp.getCognome());

			xml_Account.addContent(xml_ID_Responsabile);
			xml_Account.addContent(xml_Nome);
			xml_Account.addContent(xml_Cognome);

		}
		else { // RICERCATORE E'
			Ricercatore ric = (Ricercatore) a;

			xml_Account.addContent(xml_ID_Account);
			xml_Account.addContent(xml_Username);
			xml_Account.addContent(xml_Password);
			xml_Account.addContent(xml_Tipologia);

			Element xml_ID_Ricercatore = new Element("ID_Ricercatore");
			Element xml_Nome = new Element("Nome");
			Element xml_Cognome = new Element("Cognome");
			Element xml_Codice_Fiscale = new Element("Codice_Fiscale");
			Element xml_Data_Nascita = new Element("Data_Nascita");
			Element xml_Citta_Nascita = new Element("Citta_Nascita");
			Element xml_Provincia_Nascita = new Element("Provincia_Nascita");
			Element xml_Matricola = new Element("Matricola");
			Element xml_Sesso = new Element("Sesso");
			Element xml_Email = new Element("Email");
			Element xml_Data_Inizio_Servizio = new Element("Data_Inizio_Servizio");
			Element xml_Nome_Dipartimento = new Element("Nome_Dipartimento");
			Element xml_ID_Dipartimento = new Element("ID_Dipartimento");
			Element xml_Nome_Area_Scientifica = new Element("Nome_Area_Scientifica");
			Element xml_ID_Area_Scientifica = new Element("ID_Area_Scientifica");
			Element xml_Ruolo = new Element("Ruolo");

			xml_ID_Ricercatore.setText("" + ric.getID_Ricercatore());
			xml_Nome.setText(ric.getNome());
			xml_Cognome.setText(ric.getCognome());
			xml_Codice_Fiscale.setText(ric.getCodice_Fiscale());
			xml_Data_Nascita.setText(ric.getData_Nascita());
			xml_Citta_Nascita.setText(ric.getCitta_Nascita());
			xml_Provincia_Nascita.setText(ric.getProvincia_Nascita());
			xml_Matricola.setText(ric.getMatricola());
			xml_Sesso.setText(ric.getSesso());
			xml_Email.setText(ric.getEmail());
			xml_Data_Inizio_Servizio.setText(ric.getData_Inizio_Servizio());
			xml_Nome_Dipartimento.setText(nome_Dip);
			xml_ID_Dipartimento.setText("" + ric.getID_Dipartimento());
			xml_Nome_Area_Scientifica.setText(nome_Area);
			xml_ID_Area_Scientifica.setText("" + ric.getID_Area_Scientifica());
			xml_Ruolo.setText(ric.getRuolo());

			// System.out.println("-------->"+nome_Area);

			xml_Account.addContent(xml_ID_Ricercatore);
			xml_Account.addContent(xml_Nome);
			xml_Account.addContent(xml_Cognome);
			xml_Account.addContent(xml_Codice_Fiscale);
			xml_Account.addContent(xml_Data_Nascita);
			xml_Account.addContent(xml_Citta_Nascita);
			xml_Account.addContent(xml_Provincia_Nascita);
			xml_Account.addContent(xml_Matricola);
			xml_Account.addContent(xml_Sesso);
			xml_Account.addContent(xml_Email);
			xml_Account.addContent(xml_Data_Inizio_Servizio);
			xml_Account.addContent(xml_Nome_Dipartimento);
			xml_Account.addContent(xml_ID_Dipartimento);
			xml_Account.addContent(xml_Nome_Area_Scientifica);
			xml_Account.addContent(xml_ID_Area_Scientifica);
			xml_Account.addContent(xml_Ruolo);

		}
		xml_root.addContent(xml_Account);
		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Visualizza i dati di un'area scientifica in formato XML
	 * 
	 * @param areaScientifica area scientifica da visualizzare
	 * @return Il documento XML risultante
	 */
	public static Document
			caricaAreaScientifica(AreaScientifica areaScientifica) {

		Element xml_root = new Element("Response");
		Element xml_AreaSc = new Element("Area_Scientifica");

		Element xml_ID_Area_Scientifica;
		Element xml_Codice;
		Element xml_Nome;
		Element xml_Telefono;
		Element xml_Fax;
		Element xml_Sito;
		Element xml_Email;

		xml_ID_Area_Scientifica = new Element("ID_Area_Scientifica");
		xml_Codice = new Element("Codice");
		xml_Nome = new Element("Nome");
		xml_Telefono = new Element("Telefono");
		xml_Fax = new Element("Fax");
		xml_Sito = new Element("Sito");
		xml_Email = new Element("Email");

		xml_ID_Area_Scientifica.setText("" + areaScientifica.getID_Area_Scientifica());
		xml_Codice.setText("" + areaScientifica.getCodice());
		xml_Nome.setText(areaScientifica.getNome());
		xml_Telefono.setText(areaScientifica.getTelefono());
		xml_Fax.setText(areaScientifica.getFax());
		xml_Sito.setText(areaScientifica.getSito());
		xml_Email.setText(areaScientifica.getEmail());

		xml_AreaSc.addContent(xml_ID_Area_Scientifica);
		xml_AreaSc.addContent(xml_Codice);
		xml_AreaSc.addContent(xml_Nome);
		xml_AreaSc.addContent(xml_Telefono);
		xml_AreaSc.addContent(xml_Fax);
		xml_AreaSc.addContent(xml_Sito);
		xml_AreaSc.addContent(xml_Email);

		xml_root.addContent(xml_AreaSc);
		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Visualizza un dipartimento in formato XML
	 * 
	 * @param dipartimento dipartimento da visualizzare
	 * @return Il documento XML risultante
	 */
	public static Document caricaDipartimento(Dipartimento dipartimento) {

		Element xml_root = new Element("Response");
		Element xml_Dip = new Element("Dipartimento");

		Element xml_ID_Dipartimento;
		Element xml_Nome;
		Element xml_Telefono;
		Element xml_Fax;
		Element xml_Sito;
		Element xml_Email;

		xml_ID_Dipartimento = new Element("ID_Dipartimento");
		xml_Nome = new Element("Nome");
		xml_Telefono = new Element("Telefono");
		xml_Fax = new Element("Fax");
		xml_Sito = new Element("Sito");
		xml_Email = new Element("Email");

		xml_ID_Dipartimento.setText("" + dipartimento.getID_Dipartimento());
		xml_Nome.setText(dipartimento.getNome());
		xml_Telefono.setText(dipartimento.getTelefono());
		xml_Fax.setText(dipartimento.getFax());
		xml_Sito.setText(dipartimento.getSito());
		xml_Email.setText(dipartimento.getEmail());

		xml_Dip.addContent(xml_ID_Dipartimento);
		xml_Dip.addContent(xml_Nome);
		xml_Dip.addContent(xml_Telefono);
		xml_Dip.addContent(xml_Fax);
		xml_Dip.addContent(xml_Sito);
		xml_Dip.addContent(xml_Email);

		xml_root.addContent(xml_Dip);
		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte una lista di account in formato XML
	 * 
	 * @param lista lista di account da convertire
	 * @return Il documento XML risultante
	 */
	public static Document caricaAccount(ArrayList<Account> lista) {
		Element xml_root = new Element("Response");
		Element xml_account;
		Element xml_id_account;
		Element xml_username;
		Element xml_password;
		Element xml_tipologia;
		Element xml_nome;
		Element xml_cognome;
		Element xml_codice_fiscale;
		Element xml_data_nascita;
		Element xml_citta_nascita;
		Element xml_provincia_nascita;
		Element xml_matricola;
		Element xml_email;
		Element xml_data_inizio_servizio;
		Element xml_id_dipartimento;
		Element xml_id_area_scientifica;
		Element xml_ruolo;
		Element xml_sesso;
		Element xml_nome_dipartimento;
		Element xml_nome_area;

		for (Account a : lista) {
			xml_account = new Element("Account");

			xml_id_account = new Element("ID_Account");
			xml_username = new Element("Username");
			xml_password = new Element("Password");
			xml_tipologia = new Element("Tipologia");
			xml_nome = new Element("Nome");
			xml_cognome = new Element("Cognome");
			xml_codice_fiscale = new Element("Codice_Fiscale");
			xml_data_nascita = new Element("Data_Nascita");
			xml_citta_nascita = new Element("Citta_Nascita");
			xml_provincia_nascita = new Element("Provincia_Nascita");
			xml_matricola = new Element("Matricola");
			xml_email = new Element("Email");
			xml_data_inizio_servizio = new Element("Data_Inizio_Servizio");
			xml_id_dipartimento = new Element("ID_Dipartimento");
			xml_id_area_scientifica = new Element("ID_Area_Scientifica");
			xml_ruolo = new Element("Ruolo");
			xml_sesso = new Element("Sesso");
			xml_nome_dipartimento = new Element("Dipartimento");
			xml_nome_area = new Element("Area_Scientifica");

			if (a.getTipologia().equalsIgnoreCase("Ricercatore")) {
				xml_id_account.setText("" + a.getID_Account());
				xml_username.setText(a.getUsername());
				xml_password.setText(a.getPassword());
				xml_tipologia.setText(a.getTipologia());
				xml_nome.setText(((Ricercatore) a).getNome());
				xml_cognome.setText(((Ricercatore) a).getCognome());
				xml_codice_fiscale.setText(((Ricercatore) a).getCodice_Fiscale());
				xml_data_nascita.setText(((Ricercatore) a).getData_Nascita());
				xml_citta_nascita.setText(((Ricercatore) a).getCitta_Nascita());
				xml_provincia_nascita.setText(((Ricercatore) a).getProvincia_Nascita());
				xml_matricola.setText(((Ricercatore) a).getMatricola());
				xml_email.setText(((Ricercatore) a).getEmail());
				xml_data_inizio_servizio.setText(((Ricercatore) a).getData_Inizio_Servizio());
				xml_id_dipartimento.setText("" + ((Ricercatore) a).getID_Dipartimento());
				xml_id_area_scientifica.setText("" + ((Ricercatore) a).getID_Area_Scientifica());
				xml_ruolo.setText(((Ricercatore) a).getRuolo());
				xml_sesso.setText(((Ricercatore) a).getSesso());

				try {
					Dipartimento dip = DBDipartimento.getInstance().getDipartimento(((Ricercatore) a).getID_Dipartimento());
					xml_nome_dipartimento.setText(dip.getNome());
				}
				catch (SQLException e) {
					System.out.println("Errore in getDipartimento");
					e.printStackTrace();
				}

				try {
					AreaScientifica area = DBAreaScientifica.getInstance().getAreaScientifica(((Ricercatore) a).getID_Area_Scientifica());
					xml_nome_area.setText(area.getNome());
				}
				catch (SQLException e) {
					e.printStackTrace();
				}

				xml_account.addContent(xml_id_account);
				xml_account.addContent(xml_username);
				xml_account.addContent(xml_password);
				xml_account.addContent(xml_tipologia);
				xml_account.addContent(xml_nome);
				xml_account.addContent(xml_cognome);
				xml_account.addContent(xml_codice_fiscale);
				xml_account.addContent(xml_data_nascita);
				xml_account.addContent(xml_citta_nascita);
				xml_account.addContent(xml_provincia_nascita);
				xml_account.addContent(xml_matricola);
				xml_account.addContent(xml_email);
				xml_account.addContent(xml_data_inizio_servizio);
				xml_account.addContent(xml_id_dipartimento);
				xml_account.addContent(xml_nome_dipartimento);
				xml_account.addContent(xml_id_area_scientifica);
				xml_account.addContent(xml_nome_area);
				xml_account.addContent(xml_ruolo);
				xml_account.addContent(xml_sesso);
			}
			else if (a.getTipologia().equalsIgnoreCase("Amministratore")) {
				xml_id_account.setText("" + a.getID_Account());
				xml_username.setText(a.getUsername());
				xml_password.setText(a.getPassword());
				xml_tipologia.setText(a.getTipologia());
				xml_nome.setText(((Amministratore) a).getNome());
				xml_cognome.setText(((Amministratore) a).getCognome());

				xml_account.addContent(xml_id_account);
				xml_account.addContent(xml_username);
				xml_account.addContent(xml_password);
				xml_account.addContent(xml_tipologia);
				xml_account.addContent(xml_nome);
				xml_account.addContent(xml_cognome);
			}
			else if (a.getTipologia().equalsIgnoreCase("Responsabile")) {
				xml_id_account.setText("" + a.getID_Account());
				xml_username.setText(a.getUsername());
				xml_password.setText(a.getPassword());
				xml_tipologia.setText(a.getTipologia());
				xml_nome.setText(((Responsabile) a).getNome());
				xml_cognome.setText(((Responsabile) a).getCognome());

				xml_account.addContent(xml_id_account);
				xml_account.addContent(xml_username);
				xml_account.addContent(xml_password);
				xml_account.addContent(xml_tipologia);
				xml_account.addContent(xml_nome);
				xml_account.addContent(xml_cognome);
			}

			xml_root.addContent(xml_account);
		}

		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte una lista di dipartimenti in un documento XML
	 * 
	 * @param lista lista dei dipartimenti da visualizzare
	 * @return Il documento XML risultante
	 */
	public static Document caricaDipartimenti(ArrayList<Dipartimento> lista) {
		Element xml_root = new Element("Response");
		Element xml_dipartimento;
		Element xml_id_dipartimento;
		Element xml_nome;
		Element xml_telefono;
		Element xml_fax;
		Element xml_sito;
		Element xml_email;
		Element xml_nome_direttore;
		Element xml_cognome_direttore;

		for (Dipartimento a : lista) {
			xml_dipartimento = new Element("Dipartimento");
			xml_id_dipartimento = new Element("ID_Dipartimento");
			xml_nome = new Element("Nome");
			xml_telefono = new Element("Telefono");
			xml_fax = new Element("Fax");
			xml_sito = new Element("Sito");
			xml_email = new Element("Email");
			xml_nome_direttore = new Element("Nome_Direttore");
			xml_cognome_direttore = new Element("Cognome_Direttore");

			xml_id_dipartimento.setText("" + a.getID_Dipartimento());
			xml_nome.setText(a.getNome());
			xml_telefono.setText(a.getTelefono());
			xml_fax.setText(a.getFax());
			xml_sito.setText(a.getSito());
			xml_email.setText(a.getEmail());

			Ricercatore dir = null;
			dir = DBDipartimento.getInstance().getDirettoreDipartimento(a);

			if (dir == null) {
				xml_nome_direttore.setText(" - - - - - ");
				xml_cognome_direttore.setText(" - - - - -");
			}
			else {
				xml_nome_direttore.setText(dir.getNome());
				xml_cognome_direttore.setText(dir.getCognome());
			}

			xml_dipartimento.addContent(xml_id_dipartimento);
			xml_dipartimento.addContent(xml_nome);
			xml_dipartimento.addContent(xml_telefono);
			xml_dipartimento.addContent(xml_fax);
			xml_dipartimento.addContent(xml_sito);
			xml_dipartimento.addContent(xml_email);
			xml_dipartimento.addContent(xml_nome_direttore);
			xml_dipartimento.addContent(xml_cognome_direttore);

			xml_root.addContent(xml_dipartimento);
		}
		Document doc = new Document(xml_root);
		return doc;
	}

	/**
	 * Converte una lista di aree scientifiche in un documento XML
	 * 
	 * @param lista Lista di aree scientifiche da visualizzare
	 * @return Il documento XML risultante
	 */
	public static Document caricaAreeScientifiche(
			ArrayList<AreaScientifica> lista) {
		Element xml_root = new Element("Response");
		Element xml_area_scientifica;
		Element xml_id_area;
		Element xml_codice;
		Element xml_nome;
		Element xml_telefono;
		Element xml_fax;
		Element xml_sito;
		Element xml_email;

		for (AreaScientifica a : lista) {
			xml_area_scientifica = new Element("Area_Scientifica");
			xml_id_area = new Element("ID_Area_Scientifica");
			xml_codice = new Element("Codice");
			xml_nome = new Element("Nome");
			xml_telefono = new Element("Telefono");
			xml_fax = new Element("Fax");
			xml_sito = new Element("Sito");
			xml_email = new Element("Email");

			xml_id_area.setText("" + a.getID_Area_Scientifica());
			xml_codice.setText(a.getCodice());
			xml_nome.setText(a.getNome());
			xml_telefono.setText(a.getTelefono());
			xml_fax.setText(a.getFax());
			xml_sito.setText(a.getSito());
			xml_email.setText(a.getEmail());

			xml_area_scientifica.addContent(xml_id_area);
			xml_area_scientifica.addContent(xml_codice);
			xml_area_scientifica.addContent(xml_nome);
			xml_area_scientifica.addContent(xml_telefono);
			xml_area_scientifica.addContent(xml_fax);
			xml_area_scientifica.addContent(xml_sito);
			xml_area_scientifica.addContent(xml_email);

			xml_root.addContent(xml_area_scientifica);
		}
		Document doc = new Document(xml_root);

		return doc;
	}

	/**
	 * Converte una lista di account in un documento XML
	 * 
	 * @param listaAccount lista di account da visualizzare
	 * @return Il documento XML risultante
	 */
	public static Document caricaListaAccount(ArrayList<Account> listaAccount) {

		Element xml_root = new Element("Response");
		Element xml_Account;
		Element xml_ID_Account;
		Element xml_Username;
		Element xml_Password;
		Element xml_Tipologia;

		for (Account a : listaAccount) {
			xml_Account = new Element("Account");
			xml_ID_Account = new Element("ID_Account");
			xml_Username = new Element("Username");
			xml_Password = new Element("Password");
			xml_Tipologia = new Element("Tipologia");

			xml_ID_Account.setText("" + a.getID_Account());
			xml_Username.setText(a.getUsername());
			xml_Password.setText(a.getPassword());
			xml_Tipologia.setText(a.getTipologia());

			xml_Account.addContent(xml_ID_Account);
			xml_Account.addContent(xml_Username);
			xml_Account.addContent(xml_Password);
			xml_Account.addContent(xml_Tipologia);

			xml_root.addContent(xml_Account);
		}
		Document doc = new Document(xml_root);

		return doc;

	}

	/**
	 * Visualizza i dettagli di un ricercatore che è direttore di dipartimento,
	 * in XML
	 * 
	 * @param r Bean del direttore di dipartimento
	 * @return Il documento XML risultante
	 */
	public static Document caricaDirettoreDipartimento(Ricercatore r) {
		Element xml_root = new Element("Response");

		if (r != null) {
			Element xml_Ricercatore; // CONTENITORE
			Element xml_Nome;
			Element xml_Cognome;
			Element xml_ID_Ricercatore; // SERVE PER LA OPTIONS DELLA SELECT

			xml_Ricercatore = new Element("Direttore");

			xml_Nome = new Element("Nome_Ricercatore");
			xml_Cognome = new Element("Cognome_Ricercatore");
			xml_ID_Ricercatore = new Element("ID_Ricercatore");

			xml_ID_Ricercatore.setText("" + r.getID_Ricercatore());
			xml_Nome.setText(r.getNome());
			xml_Cognome.setText(r.getCognome());

			xml_Ricercatore.addContent(xml_Nome);
			xml_Ricercatore.addContent(xml_Cognome);
			xml_Ricercatore.addContent(xml_ID_Ricercatore);
			xml_root.addContent(xml_Ricercatore);
		}

		Document doc = new Document(xml_root);
		return doc;
	}

}
