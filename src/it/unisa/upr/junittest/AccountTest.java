package it.unisa.upr.junittest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	private Account acc;
	
	@Before
	public void setUp() {
		try{
			acc = new Account(1, "luigi92", "luigi.dellaglio", "Ricercatore");
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		acc = null;
		System.gc();	//per deallocare
	}

	@Test
	public void testGetID_Account() {
		assertEquals(acc.getID_Account(), 1);
	}
	
	@Test
	public void testSetID_Account() {
		acc.setID_Account(3);
		assertEquals(acc.getID_Account(), 3);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(acc.getUsername(), "luigi92");
	}
	
	@Test
	public void testSetUsername() {
		acc.setUsername("giuseppe");
		assertEquals(acc.getUsername(), "giuseppe");
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(acc.getPassword(), "luigi.dellaglio");
	}
	
	@Test
	public void testSetPassword() {
		acc.setPassword("dell'aglio");
		assertEquals(acc.getPassword(), "dell'aglio");
	}
	
	@Test
	public void testGetTipologia() {
		assertEquals(acc.getTipologia(), "Ricercatore");
	}
	
	@Test
	public void testSetTipologia() {
		acc.setTipologia("Amministratore");
		assertEquals(acc.getTipologia(), "Amministratore");
	}
	
	
}
