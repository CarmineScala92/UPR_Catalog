/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.data.gestioneAutenticazione;

/**
 * La classe rappresenta una specializzazione di account, l'account di un
 * Ricercatore.
 * 
 */
public class Ricercatore extends Account {

	public Ricercatore() {
		super();
	}

	/**
	 * 
	 * @param ID id dell'account
	 * @param UN username
	 * @param PSS password
	 * @param TP tipologia dell'account
	 * @param iD_Ricercatore id del ricercatore
	 * @param nome nome del ricercatore
	 * @param cognome cognome del ricercatore
	 * @param codice_Fiscale codice fiscale
	 * @param data_Nascita data di nascita
	 * @param citta_Nascita cittaà di nascita
	 * @param provincia_Nascita provincia di nascita
	 * @param matricola matricola del ricercatore
	 * @param email email del ricercatore
	 * @param data_Inizio_Servizio data di inizio servizio
	 * @param iD_Dipartimento id del ripartimento al quale il ricercatore
	 *            appartiene
	 * @param iD_Area_Scientifica id dell'area scientifica di appartenenza
	 * @param ruolo ruolo del ricercatore
	 * @param sesso sesso del ricercatore
	 */
	public Ricercatore(int ID, String UN, String PSS, String TP,
			int iD_Ricercatore, String nome, String cognome,
			String codice_Fiscale, String data_Nascita, String citta_Nascita,
			String provincia_Nascita, String matricola, String email,
			String data_Inizio_Servizio, int iD_Dipartimento,
			int iD_Area_Scientifica, String ruolo, String sesso) {
		super(ID, UN, PSS, TP);
		ID_Ricercatore = ID;
		Nome = nome;
		Cognome = cognome;
		Codice_Fiscale = codice_Fiscale;
		Data_Nascita = data_Nascita;
		Citta_Nascita = citta_Nascita;
		Provincia_Nascita = provincia_Nascita;
		Matricola = matricola;
		Email = email;
		Data_Inizio_Servizio = data_Inizio_Servizio;
		ID_Dipartimento = iD_Dipartimento;
		ID_Area_Scientifica = iD_Area_Scientifica;
		Sesso = sesso;
		Ruolo = ruolo;

	}

	/**
	 * 
	 * @return ritorna l'id del ricercatore
	 */
	public int getID_Ricercatore() {
		return ID_Ricercatore;
	}

	/**
	 * Setta l'id del ricercatore all'interno del bean
	 * 
	 * @param iD_Ricercatore id del ricercatore da settare
	 */
	public void setID_Ricercatore(int iD_Ricercatore) {
		ID_Ricercatore = iD_Ricercatore;
	}

	/**
	 * 
	 * @return ritorna il nome del ricercatore
	 */
	public String getNome() {
		return Nome;
	}

	/**
	 * Setta il nome del ricercatore all'interno del bean
	 * 
	 * @param nome nome da settare
	 */
	public void setNome(String nome) {
		Nome = nome;
	}

	/**
	 * 
	 * @return ritorna il cognome del ricercatore
	 */
	public String getCognome() {
		return Cognome;
	}

	/**
	 * Setta il cognome all'interno del bean
	 * 
	 * @param cognome cognome da settare
	 */
	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	/**
	 * 
	 * @return ritorna il codice fiscale del ricercatore
	 */
	public String getCodice_Fiscale() {
		return Codice_Fiscale;
	}

	/**
	 * Setta il codice fiscale del ricercatore all'interno del bean
	 * 
	 * @param codice_Fiscale codice fiscale da settare
	 */
	public void setCodice_Fiscale(String codice_Fiscale) {
		Codice_Fiscale = codice_Fiscale;
	}

	/**
	 * 
	 * @return ritorna la data di nascita del ricercatore
	 */
	public String getData_Nascita() {
		return Data_Nascita;
	}

	/**
	 * Setta la data di nascita del ricercatore all'interno del bean
	 * 
	 * @param data_Nascita data di nascita da settare
	 */
	public void setData_Nascita(String data_Nascita) {
		Data_Nascita = data_Nascita;
	}

	/**
	 * 
	 * @return ritorna la città di nascita del ricercatore
	 */
	public String getCitta_Nascita() {
		return Citta_Nascita;
	}

	/**
	 * Setta la città di nascita del ricercatore
	 * 
	 * @param citta_Nascita citta di nascita da settare
	 */
	public void setCitta_Nascita(String citta_Nascita) {
		Citta_Nascita = citta_Nascita;
	}

	/**
	 * 
	 * @return ritorna la provincia di nascita del ricercatore
	 */
	public String getProvincia_Nascita() {
		return Provincia_Nascita;
	}

	/**
	 * Setta la provincia di nascita del ricercatore all'interno del bean
	 * 
	 * @param provincia_Nascita provincia di nascita da settare
	 */
	public void setProvincia_Nascita(String provincia_Nascita) {
		Provincia_Nascita = provincia_Nascita;
	}

	/**
	 * 
	 * @return ritorna la matricola del ricercatore
	 */
	public String getMatricola() {
		return Matricola;
	}

	/**
	 * Setta la matricola del ricercatore all'interno del bean
	 * 
	 * @param matricola matricola da settare
	 */
	public void setMatricola(String matricola) {
		Matricola = matricola;
	}

	/**
	 * 
	 * @return ritorna l'email del ricercatore
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Setta l'email del ricercatore all'interno del bean
	 * 
	 * @param email email da settare
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * 
	 * @return ritorna la data di inizio servizio del ricercatore
	 */
	public String getData_Inizio_Servizio() {
		return Data_Inizio_Servizio;
	}

	/**
	 * Setta la data di inizio servizio all'interno del bean
	 * 
	 * @param data_inizio_servizio2 data di inizio servizio da settare
	 */
	public void setData_Inizio_Servizio(String data_inizio_servizio2) {
		Data_Inizio_Servizio = data_inizio_servizio2;
	}

	/**
	 * 
	 * @return ritorna l'id del dipartimento al quale è associato il ricercatore
	 */
	public int getID_Dipartimento() {
		return ID_Dipartimento;
	}

	/**
	 * Setta l'id del dipartimento a cui è associato il ricercatore all'interno
	 * del bean
	 * 
	 * @param iD_Dipartimento id del dipartimento da settare
	 */
	public void setID_Dipartimento(int iD_Dipartimento) {
		ID_Dipartimento = iD_Dipartimento;
	}

	/**
	 * 
	 * @return Ritorna l'id dell'area scientifica a cui è associato il
	 *         ricercatore
	 */
	public int getID_Area_Scientifica() {
		return ID_Area_Scientifica;
	}

	/**
	 * Setta l'id dell'area scientifica all'interno del bean
	 * 
	 * @param iD_Area_Scientifica id dell'area scientifica da settare
	 */
	public void setID_Area_Scientifica(int iD_Area_Scientifica) {
		ID_Area_Scientifica = iD_Area_Scientifica;
	}

	/**
	 * 
	 * @return ritorna il sesso del ricercatore
	 */
	public String getSesso() {
		return Sesso;
	}

	/**
	 * Setta il sesso del ricercatore all'interno del bean
	 * 
	 * @param sesso sesso da settare
	 */
	public void setSesso(String sesso) {
		Sesso = sesso;
	}

	/**
	 * 
	 * @return ritorna il ruolo del ricercatore
	 */
	public String getRuolo() {
		return Ruolo;
	}

	/**
	 * Setta il ruolo del ricercatore all'interno del bean
	 * 
	 * @param ruolo ruolo da settare
	 */
	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}

	int ID_Ricercatore;
	private String Nome;
	private String Cognome;
	private String Codice_Fiscale;
	private String Data_Nascita;
	private String Citta_Nascita;
	private String Provincia_Nascita;
	private String Matricola;
	private String Email;
	private String Data_Inizio_Servizio;
	private int ID_Dipartimento;
	private int ID_Area_Scientifica;
	private String Ruolo;
	private String Sesso;
}
