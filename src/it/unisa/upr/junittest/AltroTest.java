package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Altro;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AltroTest extends TestCase{
	private Altro altro;
	
	@Before
	public void setUp() {
		try{
			altro = new Altro(1, 2, "Le origini dell'Universo", "Nello Polidoro, Luigi Dell'Aglio", "2010", "Notazioni sull'origine", 0, "validato", "Altro", "Origini.pdf", "nessuna", "Focus", "39-c", "1-5", "192836425261", "scienze, astronomia");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		altro = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(altro.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		altro.setID_Prodotto(2);
		assertEquals(altro.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(altro.getID_Proprietario(), 2);
	}

	@Test
	public void testSetID_Proprietario() {
		altro.setID_Proprietario(3);
		assertEquals(altro.getID_Proprietario(), 3);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(altro.getTitolo(), "Le origini dell'Universo");
	}
	
	@Test
	public void testSetTitolo() {
		altro.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(altro.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(altro.getAutori(), "Nello Polidoro, Luigi Dell'Aglio");
	}
	
	@Test
	public void testSetAutori() {
		altro.setAutori("Luigi Dell'Aglio");
		assertEquals(altro.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(altro.getAnno_Pubblicazione(), "2010");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		altro.setAnno_Pubblicazione("2014");
		assertEquals(altro.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(altro.getAbstract(), "Notazioni sull'origine");
	}
	
	@Test
	public void testSetAbstract() {
		altro.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(altro.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(altro.getPubblico(), 0);
	}

	@Test
	public void testSetPubblico() {
		altro.setPubblico(1);
		assertEquals(altro.getPubblico(), 1);
	}

	@Test
	public void testGetStato() {
		assertEquals(altro.getStato(), "validato");
	}

	@Test
	public void testSetStato() {
		altro.setStato("Rifiutato");
		assertEquals(altro.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(altro.getTipologia(), "Altro");
	}
	
	@Test
	public void testSetTipologia() {
		altro.setTipologia("articolo rivista");
		assertEquals(altro.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(altro.getURL(), "Origini.pdf");
	}

	@Test
	public void testSetURL() {
		altro.setURL("Tecniche.pdf");
		assertEquals(altro.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(altro.getNote(), "nessuna");
	}
	
	@Test
	public void testSetNote() {
		altro.setNote("Non è coerente con l'abstract descritto");
		assertEquals(altro.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetNome_Volume() {
		assertEquals(altro.getNome_Volume(),"Focus");
	}
	
	@Test
	public void testSetNome_Volume() {
		altro.setNome_Volume("PCMania");
		assertEquals(altro.getNome_Volume(), "PCMania");
	}
	
	@Test
	public void testGetNumero_Volume() {
		assertEquals(altro.getNumero_Volume(), "39-c");
	}
	
	@Test
	public void testSetNumero_Volume() {
		altro.setNumero_Volume("30-c");
		assertEquals(altro.getNumero_Volume(), "30-c");
	}
	
	@Test
	public void testGetPagineRiferimento() {
		assertEquals(altro.getPagine_Riferimento(), "1-5");
	}
	
	@Test
	public void testSetPagineRiferimento() {
		altro.setPagine_Riferimento("10-20");
		assertEquals(altro.getPagine_Riferimento(), "10-20");
	}
	
	@Test
	public void testGetISBN() {
		assertEquals(altro.getISBN(), "192836425261");
	}
	
	@Test
	public void testSetISBN() {
		altro.setISBN("192836425269");
		assertEquals(altro.getISBN(), "192836425269");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(altro.getKeywords(), "scienze, astronomia");
	}

	@Test
	public void testSetKeywords() {
		altro.setKeywords("informatica");
		assertEquals(altro.getKeywords(), "informatica");
	}
}