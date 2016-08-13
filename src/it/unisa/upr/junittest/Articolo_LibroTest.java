package it.unisa.upr.junittest;

import junit.framework.TestCase;
import it.unisa.upr.data.gestioneProdotti.Articolo_Libro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Articolo_LibroTest extends TestCase{
	private Articolo_Libro artLib;
	
	@Before
	public void setUp() {
		try{
			artLib = new Articolo_Libro(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "articolo libro", "nuoveTecniche.pdf", "", "Nuove tecniche", "Gianni Vivo, Giuseppe Bianchi", "Marco Dodero", "", "", "289302938223", "20-30", "44575410100797836437", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		artLib = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(artLib.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		artLib.setID_Prodotto(2);
		assertEquals(artLib.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(artLib.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		artLib.setID_Proprietario(2);
		assertEquals(artLib.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(artLib.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		artLib.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(artLib.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(artLib.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		artLib.setAutori("Luigi Dell'Aglio");
		assertEquals(artLib.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(artLib.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		artLib.setAnno_Pubblicazione("2014");
		assertEquals(artLib.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(artLib.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		artLib.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(artLib.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(artLib.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		artLib.setPubblico(0);
		assertEquals(artLib.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(artLib.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		artLib.setStato("Rifiutato");
		assertEquals(artLib.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(artLib.getTipologia(), "articolo libro");
	}
	
	@Test
	public void testSetTipologia() {
		artLib.setTipologia("articolo rivista");
		assertEquals(artLib.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(artLib.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		artLib.setURL("Tecniche.pdf");
		assertEquals(artLib.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(artLib.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		artLib.setNote("Non è coerente con l'abstract descritto");
		assertEquals(artLib.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetNome_Volume() {
		assertEquals(artLib.getNome_Volume(), "Nuove tecniche");
	}

	@Test
	public void testSetNome_Volume() {
		artLib.setNome_Volume("Nuove tecniche informatiche");
		assertEquals(artLib.getNome_Volume(), "Nuove tecniche informatiche");
	}
	
	@Test
	public void testGetAutori_Volume() {
		assertEquals(artLib.getAutori_Volume(), "Gianni Vivo, Giuseppe Bianchi");
	}
	
	@Test
	public void testSetAutori_Volume() {
		artLib.setAutori_Volume("Gianni Vivo");
		assertEquals(artLib.getAutori_Volume(), "Gianni Vivo");
	}
	
	@Test
	public void testGetEditore() {
		assertEquals(artLib.getEditore(), "Marco Dodero");
	}
	
	@Test
	public void testSetEditore() {
		artLib.setEditore("Mirco Dodero");
		assertEquals(artLib.getEditore(), "Mirco Dodero");
	}
	
	@Test
	public void testGetCitta_Editore() {
		assertEquals(artLib.getCitta_Editore(), "");
	}
	
	@Test
	public void testSetCitta_Editore() {
		artLib.setCitta_Editore("Milano");
		assertEquals(artLib.getCitta_Editore(), "Milano");
	}
	
	@Test
	public void testGetPaese_Editore() {
		assertEquals(artLib.getPaese_Editore(), "");
	}

	@Test
	public void testSetPaese_Editore() {
		artLib.setPaese_Editore("Italia");
		assertEquals(artLib.getPaese_Editore(), "Italia");
	}
	
	@Test
	public void testGetISBN() {
		assertEquals(artLib.getISBN(), "289302938223");
	}

	@Test
	public void testSetISBN() {
		artLib.setISBN("289302938225");
		assertEquals(artLib.getISBN(), "289302938225");
	}
	
	@Test
	public void testGetPagine_Riferimento() {
		assertEquals(artLib.getPagine_Riferimento(), "20-30");
	}

	@Test
	public void testSetPagine_Riferimento() {
		artLib.setPagine_Riferimento("20-25");
		assertEquals(artLib.getPagine_Riferimento(), "20-25");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(artLib.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		artLib.setDOI("44575410100797836439");
		assertEquals(artLib.getDOI(), "44575410100797836439");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(artLib.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		artLib.setKeywords("Informatica");
		assertEquals(artLib.getKeywords(), "Informatica");
	}
}