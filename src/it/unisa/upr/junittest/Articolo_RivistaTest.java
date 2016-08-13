package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Articolo_Rivista;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Articolo_RivistaTest extends TestCase{
private Articolo_Rivista artRiv;
	
	@Before
	public void setUp() {
		try{
			artRiv = new Articolo_Rivista(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "articolo rivista", "nuoveTecniche.pdf", "", "Nuove tecniche", "20", "20-30", "44575410100797836437", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		artRiv = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(artRiv.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		artRiv.setID_Prodotto(2);
		assertEquals(artRiv.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(artRiv.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		artRiv.setID_Proprietario(2);
		assertEquals(artRiv.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(artRiv.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		artRiv.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(artRiv.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(artRiv.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		artRiv.setAutori("Luigi Dell'Aglio");
		assertEquals(artRiv.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(artRiv.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		artRiv.setAnno_Pubblicazione("2014");
		assertEquals(artRiv.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(artRiv.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		artRiv.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(artRiv.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(artRiv.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		artRiv.setPubblico(0);
		assertEquals(artRiv.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(artRiv.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		artRiv.setStato("Rifiutato");
		assertEquals(artRiv.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(artRiv.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testSetTipologia() {
		artRiv.setTipologia("articolo libro");
		assertEquals(artRiv.getTipologia(), "articolo libro");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(artRiv.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		artRiv.setURL("Tecniche.pdf");
		assertEquals(artRiv.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(artRiv.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		artRiv.setNote("Non è coerente con l'abstract descritto");
		assertEquals(artRiv.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetNome_Rivista() {
		assertEquals(artRiv.getNome_Rivista(), "Nuove tecniche");
	}

	@Test
	public void testSetNome_Rivista() {
		artRiv.setNome_Rivista("Nuove tecniche informatiche");
		assertEquals(artRiv.getNome_Rivista(), "Nuove tecniche informatiche");
	}
	
	@Test
	public void testGetNumero_Volume() {
		assertEquals(artRiv.getNumero_Volume(), "20");
	}
	
	@Test
	public void testSetNumero_Volume() {
		artRiv.setNumero_Volume("30.2a");
		assertEquals(artRiv.getNumero_Volume(), "30.2a");
	}
	
	@Test
	public void testGetPagine_Riferimento() {
		assertEquals(artRiv.getPagine_riferimento(), "20-30");
	}

	@Test
	public void testSetPagine_Riferimento() {
		artRiv.setPagine_riferimento("20-25");
		assertEquals(artRiv.getPagine_riferimento(), "20-25");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(artRiv.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		artRiv.setDOI("44575410100797836439");
		assertEquals(artRiv.getDOI(), "44575410100797836439");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(artRiv.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		artRiv.setKeywords("Informatica");
		assertEquals(artRiv.getKeywords(), "Informatica");
	}
}