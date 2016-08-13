package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneProdotti.Curatela;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuratelaTest extends TestCase{
private Curatela cur;
	
	@Before
	public void setUp() {
		try{
			cur = new Curatela(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "curatela", "nuoveTecniche.pdf", "", "PCMania", "30", "Gianni Vivo, Giuseppe Bianchi", "Marco Dodero", "", "","20-30", "289302938223", "44575410100797836437", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		cur = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(cur.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		cur.setID_Prodotto(2);
		assertEquals(cur.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(cur.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		cur.setID_Proprietario(2);
		assertEquals(cur.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(cur.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		cur.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(cur.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(cur.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		cur.setAutori("Luigi Dell'Aglio");
		assertEquals(cur.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(cur.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		cur.setAnno_Pubblicazione("2014");
		assertEquals(cur.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(cur.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		cur.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(cur.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(cur.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		cur.setPubblico(0);
		assertEquals(cur.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(cur.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		cur.setStato("Rifiutato");
		assertEquals(cur.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(cur.getTipologia(), "curatela");
	}
	
	@Test
	public void testSetTipologia() {
		cur.setTipologia("articolo rivista");
		assertEquals(cur.getTipologia(), "articolo rivista");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(cur.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		cur.setURL("Tecniche.pdf");
		assertEquals(cur.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(cur.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		cur.setNote("Non è coerente con l'abstract descritto");
		assertEquals(cur.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetNome_Rivista() {
		assertEquals(cur.getNome_Rivista(), "PCMania");
	}

	@Test
	public void testSetNome_Rivista() {
		cur.setNome_Rivista("Nuove tecniche informatiche");
		assertEquals(cur.getNome_Rivista(), "Nuove tecniche informatiche");
	}
	
	@Test
	public void testGetNumero_Volume() {
		assertEquals(cur.getNumero_Volume(), "30");
	}
	
	@Test
	public void testSetNumero_Volume() {
		cur.setNumero_Volume("20");
		assertEquals(cur.getNumero_Volume(), "20");
	}
	
	@Test
	public void testGetAutori_Volume() {
		assertEquals(cur.getAutori_Volume(), "Gianni Vivo, Giuseppe Bianchi");
	}
	
	@Test
	public void testSetAutori_Volume() {
		cur.setAutori_Volume("Gianni Vivo");
		assertEquals(cur.getAutori_Volume(), "Gianni Vivo");
	}
	
	@Test
	public void testGetEditore() {
		assertEquals(cur.getEditore(), "Marco Dodero");
	}
	
	@Test
	public void testSetEditore() {
		cur.setEditore("Mirco Dodero");
		assertEquals(cur.getEditore(), "Mirco Dodero");
	}
	
	@Test
	public void testGetCitta_Editore() {
		assertEquals(cur.getCitta_Editore(), "");
	}
	
	@Test
	public void testSetCitta_Editore() {
		cur.setCitta_Editore("Milano");
		assertEquals(cur.getCitta_Editore(), "Milano");
	}
	
	@Test
	public void testGetPaese_Editore() {
		assertEquals(cur.getPaese_Editore(), "");
	}

	@Test
	public void testSetPaese_Editore() {
		cur.setPaese_Editore("Italia");
		assertEquals(cur.getPaese_Editore(), "Italia");
	}
	
	@Test
	public void testGetPagine_Riferimento() {
		assertEquals(cur.getPagine_Riferimento(), "20-30");
	}

	@Test
	public void testSetPagine_Riferimento() {
		cur.setPagine_Riferimento("20-25");
		assertEquals(cur.getPagine_Riferimento(), "20-25");
	}
	
	@Test
	public void testGetISBN() {
		assertEquals(cur.getISBN(), "289302938223");
	}

	@Test
	public void testSetISBN() {
		cur.setISBN("289302938225");
		assertEquals(cur.getISBN(), "289302938225");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(cur.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		cur.setDOI("44575410100797836439");
		assertEquals(cur.getDOI(), "44575410100797836439");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(cur.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		cur.setKeywords("Informatica");
		assertEquals(cur.getKeywords(), "Informatica");
	}

}
