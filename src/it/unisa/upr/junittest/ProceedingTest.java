package it.unisa.upr.junittest;

import java.sql.Date;

import it.unisa.upr.data.gestioneProdotti.Proceeding;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProceedingTest extends TestCase{
private Proceeding proc;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		try{
			proc = new Proceeding(1, 1, "Nuove forme di ricerca", "Luigi Dell'Aglio, Nello Polidoro", "2013", "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software", 1, "Completo", "proceeding", "nuoveTecniche.pdf", "", "invito", "Nuove tecniche", "30", "Gianni Vivo, Giuseppe Bianchi", "Marco Dodero", "", "", "20-30", "Central Progress", new Date(2013,12,12), "London", "Nazionale", "289302938223", "44575410100797836437", "tecniche, Informatica");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		proc = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Prodotto() {
		assertEquals(proc.getID_Prodotto(), 1);
	}
	
	@Test
	public void testSetID_Prodotto() {
		proc.setID_Prodotto(2);
		assertEquals(proc.getID_Prodotto(), 2);
	}
	
	@Test
	public void testGetID_Proprietario() {
		assertEquals(proc.getID_Proprietario(), 1);
	}

	@Test
	public void testSetID_Proprietario() {
		proc.setID_Proprietario(2);
		assertEquals(proc.getID_Proprietario(), 2);
	}

	@Test
	public void testGetTitolo() {
		assertEquals(proc.getTitolo(), "Nuove forme di ricerca");
	}
	
	@Test
	public void testSetTitolo() {
		proc.setTitolo("Le nuove frontiere dell'informatica");
		assertEquals(proc.getTitolo(), "Le nuove frontiere dell'informatica");
	}
	
	@Test
	public void testGetAutori() {
		assertEquals(proc.getAutori(), "Luigi Dell'Aglio, Nello Polidoro");
	}
	
	@Test
	public void testSetAutori() {
		proc.setAutori("Luigi Dell'Aglio");
		assertEquals(proc.getAutori(), "Luigi Dell'Aglio");
	}
	
	@Test
	public void testGetAnno_Pubblicazione() {
		assertEquals(proc.getAnno_Pubblicazione(), "2013");
	}

	@Test
	public void testSetAnno_Pubblicazione() {
		proc.setAnno_Pubblicazione("2014");
		assertEquals(proc.getAnno_Pubblicazione(), "2014");
	}

	@Test
	public void testGetAbstract() {
		assertEquals(proc.getAbstract(), "Tratta le nuove tipologie di ricerca di prodotti per lo sviluppo software");
	}
	
	@Test
	public void testSetAbstract() {
		proc.setAbstract("Descrive le nuove tipologie di sviluppo software");
		assertEquals(proc.getAbstract(), "Descrive le nuove tipologie di sviluppo software");
	}
	
	@Test
	public void testGetPubblico() {
		assertEquals(proc.getPubblico(), 1);
	}

	@Test
	public void testSetPubblico() {
		proc.setPubblico(0);
		assertEquals(proc.getPubblico(), 0);
	}

	@Test
	public void testGetStato() {
		assertEquals(proc.getStato(), "Completo");
	}

	@Test
	public void testSetStato() {
		proc.setStato("Rifiutato");
		assertEquals(proc.getStato(), "Rifiutato");
	}	
	
	@Test
	public void testGetTipologia() {
		assertEquals(proc.getTipologia(), "proceeding");
	}
	
	@Test
	public void testSetTipologia() {
		proc.setTipologia("brevetto");
		assertEquals(proc.getTipologia(), "brevetto");
	}
	
	@Test
	public void testGetURL() {
		assertEquals(proc.getURL(), "nuoveTecniche.pdf");
	}

	@Test
	public void testSetURL() {
		proc.setURL("Tecniche.pdf");
		assertEquals(proc.getURL(), "Tecniche.pdf");
	}
	
	@Test
	public void testGetNote() {
		assertEquals(proc.getNote(), "");
	}
	
	@Test
	public void testSetNote() {
		proc.setNote("Non è coerente con l'abstract descritto");
		assertEquals(proc.getNote(), "Non è coerente con l'abstract descritto");
	}
	
	@Test
	public void testGetRelazione() {
		assertEquals(proc.getRelazione(), "invito");
	}

	@Test
	public void testSetRelazione() {
		proc.setRelazione("contributo");
		assertEquals(proc.getRelazione(), "contributo");
	}
	
	@Test
	public void testGetNome_Volume() {
		assertEquals(proc.getNome_Volume(), "Nuove tecniche");
	}

	@Test
	public void testSetNome_Volume() {
		proc.setNome_Volume("Nuove tecniche informatiche");
		assertEquals(proc.getNome_Volume(), "Nuove tecniche informatiche");
	}
	
	@Test
	public void testGetNumero_Volume() {
		assertEquals(proc.getNumero_Volume(), "30");
	}
	
	@Test
	public void testSetNumero_Volume() {
		proc.setNumero_Volume("20");
		assertEquals(proc.getNumero_Volume(), "20");
	}
	
	@Test
	public void testGetAutori_Volume() {
		assertEquals(proc.getAutori_Volume(), "Gianni Vivo, Giuseppe Bianchi");
	}
	
	@Test
	public void testSetAutori_Volume() {
		proc.setAutori_Volume("Gianni Vivo");
		assertEquals(proc.getAutori_Volume(), "Gianni Vivo");
	}
	
	@Test
	public void testGetEditore() {
		assertEquals(proc.getEditore(), "Marco Dodero");
	}
	
	@Test
	public void testSetEditore() {
		proc.setEditore("Mirco Dodero");
		assertEquals(proc.getEditore(), "Mirco Dodero");
	}
	
	@Test
	public void testGetCitta_Editore() {
		assertEquals(proc.getCitta_Editore(), "");
	}
	
	@Test
	public void testSetCitta_Editore() {
		proc.setCitta_Editore("Milano");
		assertEquals(proc.getCitta_Editore(), "Milano");
	}
	
	@Test
	public void testGetPaese_Editore() {
		assertEquals(proc.getPaese_Editore(), "");
	}

	@Test
	public void testSetPaese_Editore() {
		proc.setPaese_Editore("Italia");
		assertEquals(proc.getPaese_Editore(), "Italia");
	}
	
	@Test
	public void testGetPagine_Riferimento() {
		assertEquals(proc.getPagine_Riferimento(), "20-30");
	}

	@Test
	public void testSetPagine_Riferimento() {
		proc.setPagine_Riferimento("20-25");
		assertEquals(proc.getPagine_Riferimento(), "20-25");
	}
	
	@Test
	public void testGetNome_Congresso() {
		assertEquals(proc.getNome_Congresso(), "Central Progress");
	}

	@Test
	public void testSetNome_Congresso() {
		proc.setNome_Congresso("Central Technology Progress");
		assertEquals(proc.getNome_Congresso(), "Central Technology Progress");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetData_Congresso() {
		assertEquals(proc.getData_Congresso(), new Date(2013,12,12));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSetData_Congresso() {
		proc.setData_Congresso(new Date(2014,1,20));
		assertEquals(proc.getData_Congresso(), new Date(2014,1,20));
	}
	
	@Test
	public void testGetLuogo_Congresso() {
		assertEquals(proc.getLuogo_Congresso(), "London");
	}
	
	@Test
	public void testSetLuogo_Congresso() {
		proc.setLuogo_Congresso("Dublin");
		assertEquals(proc.getLuogo_Congresso(), "Dublin");
	}
	
	@Test
	public void testGetRilevanza() {
		assertEquals(proc.getRilevanza(), "Nazionale");
	}
	
	@Test
	public void testSetRilevanza() {
		proc.setRilevanza("Internazionale");
		assertEquals(proc.getRilevanza(), "Internazionale");
	}
	
	@Test
	public void testGetISBN() {
		assertEquals(proc.getISBN(), "289302938223");
	}

	@Test
	public void testSetISBN() {
		proc.setISBN("289302938225");
		assertEquals(proc.getISBN(), "289302938225");
	}
	
	@Test
	public void testGetDOI() {
		assertEquals(proc.getDOI(), "44575410100797836437");
	}

	@Test
	public void testSetDOI() {
		proc.setDOI("44575410100797836439");
		assertEquals(proc.getDOI(), "44575410100797836439");
	}
	
	@Test
	public void testGetKeywords() {
		assertEquals(proc.getKeywords(), "tecniche, Informatica");
	}

	@Test
	public void testSetKeywords() {
		proc.setKeywords("Informatica");
		assertEquals(proc.getKeywords(), "Informatica");
	}	
}