package it.unisa.upr.junittest;

import junit.framework.TestCase;
import it.unisa.upr.data.gestioneProdotti.Monografia;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MonografiaTest extends TestCase{

private Monografia mon;
	
	@Before
	public void setUp() {
		try{
			mon = new Monografia(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "monografia", "nuoveTecniche.pdf", "", "Marco Dodero", "", "", "20-30", "289302938223", "44575410100797836437", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}

	@After
	public void tearDown() {
		mon = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(mon.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		mon.setID_Prodotto(2);
		assertEquals(mon.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(mon.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		mon.setID_Proprietario(2);
		assertEquals(mon.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(mon.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		mon.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(mon.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(mon.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		mon.setAutori("Luigi Dell'Aglio");
		assertEquals(mon.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(mon.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		mon.setAnno_Pubblicazione("2014");
		assertEquals(mon.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(mon.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		mon.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(mon.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(mon.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		mon.setPubblico(0);
		assertEquals(mon.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(mon.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		mon.setStato("Rifiutato");
		assertEquals(mon.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(mon.getTipologia(), "monografia");
	}
	
	@Test
	public void testSetTipologia() {
		mon.setTipologia("articolo rivista");
		assertEquals(mon.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(mon.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		mon.setURL("Tecniche.pdf");
		assertEquals(mon.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(mon.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		mon.setNote("Non è coerente con l'abstract descritto");
		assertEquals(mon.getNote(), "Non è coerente con l'abstract descritto");
	}
		
	@Test
	public void testGetEditore() {
		assertEquals(mon.getEditore(), "Marco Dodero");
	}
	
	@Test
	public void testSetEditore() {
		mon.setEditore("Mirco Dodero");
		assertEquals(mon.getEditore(), "Mirco Dodero");
	}
	
	@Test
	public void testGetCitta_Editore() {
		assertEquals(mon.getCitta_Editore(), "");
	}
	
	@Test
	public void testSetCitta_Editore() {
		mon.setCitta_Editore("Milano");
		assertEquals(mon.getCitta_Editore(), "Milano");
	}
	
	@Test
	public void testGetPaese_Editore() {
		assertEquals(mon.getPaese_Editore(), "");
	}

	@Test
	public void testSetPaese_Editore() {
		mon.setPaese_Editore("Italia");
		assertEquals(mon.getPaese_Editore(), "Italia");
	}
	
	@Test
	public void testGetPagine_Riferimento() {
		assertEquals(mon.getPagine_Riferimento(), "20-30");
	}

	@Test
	public void testSetPagine_Riferimento() {
		mon.setPagine_Riferimento("20-25");
		assertEquals(mon.getPagine_Riferimento(), "20-25");
	}
	
	@Test
	public void testGetISBN() {
		assertEquals(mon.getISBN(), "289302938223");
	}

	@Test
	public void testSetISBN() {
		mon.setISBN("289302938225");
		assertEquals(mon.getISBN(), "289302938225");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(mon.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		mon.setDOI("44575410100797836439");
		assertEquals(mon.getDOI(), "44575410100797836439");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(mon.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		mon.setKeywords("Informatica");
		assertEquals(mon.getKeywords(), "Informatica");
	}
}