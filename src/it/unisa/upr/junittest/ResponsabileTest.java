package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResponsabileTest extends TestCase {
	
	private Responsabile resp;
	
	@Before
	public void setUp() {
		try{
			resp = new Responsabile(1, "giovanni", "giovanni.russo", "Responsabile", 1, "Giovanni", "Russo");
		} catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		resp = null;
		System.gc();	// per deallocare
	}
	
	@Test
	public void testGetID_Account() {
		assertEquals(resp.getID_Account(), 1);
	}
	
	@Test
	public void testSetID_Account() {
		resp.setID_Account(2);
		assertEquals(resp.getID_Account(), 2);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(resp.getUsername(), "giovanni");
	}
	
	@Test
	public void testSetUsername() {
		resp.setUsername("vincenzo");
		assertEquals(resp.getUsername(), "vincenzo");
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(resp.getPassword(), "giovanni.russo");
	}
	
	@Test
	public void testSetPassword() {
		resp.setPassword("santonastaso");
		assertEquals(resp.getPassword(), "santonastaso");
	}
	
	@Test
	public void testGetTipologia() {
		assertEquals(resp.getTipologia(), "Responsabile");
	}
	
	@Test
	public void testSetTipologia() {
		resp.setTipologia("Amministratore");
		assertEquals(resp.getTipologia(), "Amministratore");
	}
	
	@Test
	public void testGetID_Aministratore() {
		assertEquals(resp.getID_Responsabile(), 1);
	}
	
	@Test
	public void testSetID_Amministratore() {
		resp.setID_Responsabile(2);
		assertEquals(resp.getID_Responsabile(), 2);
	}
	
	@Test
	public void testGetNome() {
		assertEquals(resp.getNome(), "Giovanni");
	}
	
	@Test
	public void testSetNome() {
		resp.setNome("Vincenzo");
		assertEquals(resp.getNome(), "Vincenzo");
	}
	
	@Test
	public void testGetCognome() {
		assertEquals(resp.getCognome(), "Russo");
	}
	
	@Test
	public void testSetCognome() {
		resp.setCognome("Santonastaso");
		assertEquals(resp.getCognome(), "Santonastaso");
	}
}