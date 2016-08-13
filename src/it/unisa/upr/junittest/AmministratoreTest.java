package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneAutenticazione.Amministratore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class AmministratoreTest extends TestCase{

	private Amministratore amm; 
	@Before
	public void setUp() {
		try{
			amm = new Amministratore(0, "giuseppe", "giuseppe.esposito", "Amministratore", 0, "Giuseppe", "Esposito");
		} catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		amm = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetID_Account() {
		assertEquals(amm.getID_Account(), 0);
	}
	
	@Test
	public void testSetID_Account() {
		amm.setID_Account(2);
		assertEquals(amm.getID_Account(), 2);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(amm.getUsername(), "giuseppe");
	}
	
	@Test
	public void testSetUsername() {
		amm.setUsername("vincenzo");
		assertEquals(amm.getUsername(), "vincenzo");
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(amm.getPassword(), "giuseppe.esposito");
	}
	
	@Test
	public void testSetPassword() {
		amm.setPassword("santonastaso");
		assertEquals(amm.getPassword(), "santonastaso");
	}
	
	@Test
	public void testGetTipologia() {
		assertEquals(amm.getTipologia(), "Amministratore");
	}
	
	@Test
	public void testSetTipologia() {
		amm.setTipologia("Responsabile");
		assertEquals(amm.getTipologia(), "Responsabile");
	}
	
	@Test
	public void testGetID_Aministratore() {
		assertEquals(amm.getID_Amministratore(), 0);
	}
	
	@Test
	public void testSetID_Amministratore() {
		amm.setID_Amministratore(3);
		assertEquals(amm.getID_Amministratore(), 3);
	}
	
	@Test
	public void testGetNome() {
		assertEquals(amm.getNome(), "Giuseppe");
	}
	
	@Test
	public void testSetNome() {
		amm.setNome("Vincenzo");
		assertEquals(amm.getNome(), "Vincenzo");
	}
	
	@Test
	public void testGetCognome() {
		assertEquals(amm.getCognome(), "Esposito");
	}
	
	@Test
	public void testSetCognome() {
		amm.setCognome("Santonastaso");
		assertEquals(amm.getCognome(), "Santonastaso");
	}
}