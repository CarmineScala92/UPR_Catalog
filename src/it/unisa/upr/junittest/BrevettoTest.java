package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Brevetto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BrevettoTest extends TestCase{
private Brevetto brev;
	
	@Before
	public void setUp() {
		try{
			brev = new Brevetto(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "brevetto", "nuoveTecniche.pdf", "", "Descrive le nuove tecniche per creare nuovi linguaggi di programmazione usabili", "30", "44575410100797836437", "Nazionale", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		brev = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(brev.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		brev.setID_Prodotto(2);
		assertEquals(brev.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(brev.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		brev.setID_Proprietario(2);
		assertEquals(brev.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(brev.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		brev.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(brev.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(brev.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		brev.setAutori("Luigi Dell'Aglio");
		assertEquals(brev.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(brev.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		brev.setAnno_Pubblicazione("2014");
		assertEquals(brev.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(brev.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		brev.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(brev.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(brev.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		brev.setPubblico(0);
		assertEquals(brev.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(brev.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		brev.setStato("Rifiutato");
		assertEquals(brev.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(brev.getTipologia(), "brevetto");
	}
	
	@Test
	public void testSetTipologia() {
		brev.setTipologia("articolo rivista");
		assertEquals(brev.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(brev.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		brev.setURL("Tecniche.pdf");
		assertEquals(brev.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(brev.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		brev.setNote("Non è coerente con l'abstract descritto");
		assertEquals(brev.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetProprieta() {
		assertEquals(brev.getProprieta(), "Descrive le nuove tecniche per creare nuovi linguaggi di programmazione usabili");
	}

	@Test
	public void testSetProprieta() {
		brev.setProprieta("Descrive le nuove tecniche per creare nuovi linguaggi di programmazione usabili nell'ambito delle aziende");
		assertEquals(brev.getProprieta(), "Descrive le nuove tecniche per creare nuovi linguaggi di programmazione usabili nell'ambito delle aziende");
	}
	
	@Test
	public void testGetNumero_Brevetto() {
		assertEquals(brev.getNumero_Brevetto(), "30");
	}
	
	@Test
	public void testSetNumero_Brevetto() {
		brev.setNumero_Brevetto("20");
		assertEquals(brev.getNumero_Brevetto(), "20");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(brev.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		brev.setDOI("44575410100797836439");
		assertEquals(brev.getDOI(), "44575410100797836439");
	}

	@Test
	public void testGetTipo() {
		assertEquals(brev.getTipo(), "Nazionale");
	}

	@Test
	public void testSetTipo() {
		brev.setTipo("Internazionale");
		assertEquals(brev.getTipo(), "Internazionale");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(brev.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		brev.setKeywords("Informatica");
		assertEquals(brev.getKeywords(), "Informatica");
	}	
}