package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RicercatoreTest extends TestCase{
	
	private Ricercatore ric;
	
	@Before
	public void setUp() {
		try{
			ric = new Ricercatore(1, "giuseppe", "giuseppe.elefante", "Ricercatore", 1, "Giuseppe", "Elefante", "GSPLFN91D30S910S", "1991-10-30", "Gabbiano", "AV", "1234567890", "giuseppe@unisa.it", "2011-11-2", 3, 4, "Ricercatore", "M");
		} catch(Exception e) {
			fail();
		}
	}
	
	@After
	public void tearDown() {
		ric = null;
		System.gc();
	}
	
	@Test
	public void testGetID_Account() {
		assertEquals(ric.getID_Account(), 1);
	}
	
	@Test
	public void testSetID_Account() {
		ric.setID_Account(2);
		assertEquals(ric.getID_Account(), 2);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(ric.getUsername(), "giuseppe");
	}
	
	@Test
	public void testSetUsername() {
		ric.setUsername("giovanni");
		assertEquals(ric.getUsername(), "giovanni");
	}

	@Test
	public void testGetPassword() {
		assertEquals(ric.getPassword(), "giuseppe.elefante");
	}
	
	@Test
	public void testSetPassword() {
		ric.setPassword("elefante");
		assertEquals(ric.getPassword(), "elefante");
	}
	
	@Test
	public void testGetTipologia() {
		assertEquals(ric.getTipologia(), "Ricercatore");
	}
	
	@Test
	public void testSetTipologia() {
		ric.setTipologia("Responsabile");
		assertEquals(ric.getTipologia(), "Responsabile");
	}
	
	@Test
	public void testGetID_Ricercatore() {
		assertEquals(ric.getID_Ricercatore(), 1);
	}

	@Test
	public void testSetID_Ricercatore() {
		ric.setID_Ricercatore(2);
		assertEquals(ric.getID_Ricercatore(), 2);
	}
	
	@Test
	public void testGetNome() {
		assertEquals(ric.getNome(), "Giuseppe");
	}
	
	@Test
	public void testSetNome() {
		ric.setNome("Giovanni");
		assertEquals(ric.getNome(), "Giovanni");
	}
	
	@Test
	public void testGetCognome() {
		assertEquals(ric.getCognome(), "Elefante");
	}
	
	@Test
	public void testSetCognome() {
		ric.setCognome("Russo");
		assertEquals(ric.getCognome(), "Russo");
	}
	
	@Test
	public void testGetCodice_Fiscale() {
		assertEquals(ric.getCodice_Fiscale(), "GSPLFN91D30S910S");
	}
	
	@Test
	public void testSetCodice_Fiscale() {
		ric.setCodice_Fiscale("GSPLFN91D30S910Z");
		assertEquals(ric.getCodice_Fiscale(), "GSPLFN91D30S910Z");
	}
	
	@Test
	public void testGetData_Nascita() {
		assertEquals(ric.getData_Nascita(), "1991-10-30");
	}
	
	@Test
	public void testSetData_Nascita() {
		ric.setData_Nascita("1991-1-30");
		assertEquals(ric.getData_Nascita(), "1991-1-30");
	}
	
	@Test
	public void testGetCitta_Nascita() {
		assertEquals(ric.getCitta_Nascita(), "Gabbiano");
	}
	
	@Test
	public void testSetCitta_Nascita() {
		ric.setCitta_Nascita("Nocera");
		assertEquals(ric.getCitta_Nascita(), "Nocera");
	}
	
	@Test
	public void testGetProvincia_Nascita() {
		assertEquals(ric.getProvincia_Nascita(), "AV");
	}
	
	@Test
	public void testSetProvincia_Nascita() {
		ric.setProvincia_Nascita("SA");
		assertEquals(ric.getProvincia_Nascita(), "SA");
	}
	
	@Test
	public void testGetMatricola() {
		assertEquals(ric.getMatricola(), "1234567890");
	}
	
	@Test
	public void testSetMatricola() {
		ric.setMatricola("1234567895");
		assertEquals(ric.getMatricola(), "1234567895");
	}
	
	@Test
	public void testGetEmail() {
		assertEquals(ric.getEmail(), "giuseppe@unisa.it");
	}
	
	@Test
	public void testSetEmail() {
		ric.setEmail("giuseppe@unisa.com");
		assertEquals(ric.getEmail(), "giuseppe@unisa.com");
	}
	
	@Test
	public void testGetAnno_Inizio_Servizio() {
		assertEquals(ric.getData_Inizio_Servizio(), "2011-11-2");
	}
	
	@Test
	public void testSetAnno_Inizio_Servizio() {
		ric.setData_Inizio_Servizio("2011-1-2");
		assertEquals(ric.getData_Inizio_Servizio(), "2011-1-2");
	}
	
	@Test
	public void testGetID_Dipartimento() {
		assertEquals(ric.getID_Dipartimento(), 3);
	}
	
	@Test
	public void testSetID_Dipartimento() {
		ric.setID_Dipartimento(1);
		assertEquals(ric.getID_Dipartimento(), 1);
	}
	
	@Test
	public void testGetID_Area_Scientifica() {
		assertEquals(ric.getID_Area_Scientifica(), 4);
	}
	
	@Test
	public void testSetID_Area_Scientifica() {
		ric.setID_Area_Scientifica(1);
		assertEquals(ric.getID_Area_Scientifica(), 1);
	}
	
	@Test
	public void testGetRuolo() {
		assertEquals(ric.getRuolo(), "Ricercatore");
	}
	
	@Test
	public void testSetRuolo() {
		ric.setRuolo("Amministratore");
		assertEquals(ric.getRuolo(), "Amministratore");
	}
	
	@Test
	public void testGetSesso() {
		assertEquals(ric.getSesso(), "M");
	}
	
	@Test
	public void testSetSesso() {
		ric.setSesso("F");
		assertEquals(ric.getSesso(), "F");
	}
}
