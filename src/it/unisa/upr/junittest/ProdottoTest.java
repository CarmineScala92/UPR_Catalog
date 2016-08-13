package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProdottoTest extends TestCase{
	private Prodotto prod;
	
	@Before
	public void setUp() {
		try{
			prod = new Prodotto(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "articolo libro", "nuoveTecniche.pdf", "");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		prod = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(prod.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		prod.setID_Prodotto(2);
		assertEquals(prod.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(prod.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		prod.setID_Proprietario(2);
		assertEquals(prod.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(prod.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		prod.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(prod.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(prod.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		prod.setAutori("Luigi Dell'Aglio");
		assertEquals(prod.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(prod.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		prod.setAnno_Pubblicazione("2014");
		assertEquals(prod.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(prod.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		prod.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(prod.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(prod.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		prod.setPubblico(0);
		assertEquals(prod.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(prod.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		prod.setStato("Rifiutato");
		assertEquals(prod.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(prod.getTipologia(), "articolo libro");
	}
	
	@Test
	public void testSetTipologia() {
		prod.setTipologia("articolo rivista");
		assertEquals(prod.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(prod.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		prod.setURL("Tecniche.pdf");
		assertEquals(prod.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(prod.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		prod.setNote("Non è coerente con l'abstract descritto");
		assertEquals(prod.getNote(), "Non è coerente con l'abstract descritto");
	}
}