package it.unisa.upr.junittest;


import it.unisa.upr.data.gestioneSistema.Dipartimento;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DipartimentoTest extends TestCase {

	private Dipartimento dip;

	@Before
	public void setUp() {
		try {
			dip=new Dipartimento(0,"Informatica","08242534","08132424","www.inf.it","inf@email.it");
		} catch (Exception e) {
			fail();
		}
	}

	@After
	public void tearDown() {
		dip=null;
		System.gc();	// per deallocazione
	}

	@Test
	public void testGetID_Dipartimento() {
		assertEquals(dip.getID_Dipartimento(),0);
	}
	
	@Test
	public void testSetID_Dipartimento() {
		dip.setID_Dipartimento(3);
		assertEquals(dip.getID_Dipartimento(),3);
	}
	
	@Test
	public void testGetNome() {
		assertEquals(dip.getNome(),"Informatica");
	}
	
	@Test
	public void testSetNome() {
		dip.setNome("Elettronica");
		assertEquals(dip.getNome(),"Elettronica");
	}

	@Test
	public void testGetTelefono() {
		assertEquals(dip.getTelefono(),"08242534");
	}
	
	@Test
	public void testSetTelefono() {
		dip.setTelefono("222222222");
		assertEquals(dip.getTelefono(),"222222222");
	}
	
	@Test
	public void testGetFax() {
		assertEquals(dip.getFax(), "08132424");
	}
	
	@Test
	public void testSetFax() {
		dip.setFax("08112211");
		assertEquals(dip.getFax(),"08112211");
	}
	
	@Test
	public void testGetSito() {
		assertEquals(dip.getSito(), "www.inf.it");
	}
	
	@Test
	public void testSetSito() {
		dip.setSito("www.info.com");
		assertEquals(dip.getSito(), "www.info.com");
	}
	
	@Test
	public void testGetEmail() {
		assertEquals(dip.getEmail(), "inf@email.it");
	}
	
	@Test
	public void testSetEmail() {
		dip.setEmail("info@gmail.com");
		assertEquals(dip.getEmail(), "info@gmail.com");
	}
}