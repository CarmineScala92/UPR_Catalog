package it.unisa.upr.junittest;

import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AreaScientificaTest extends TestCase{
	
	private AreaScientifica area;

	@Before
	public void setUp() {
		try {
			area=new AreaScientifica(0,"12","Informatica","08242534","08132424","www.inf.it","inf@email.it");
		} catch (Exception e) {
			fail();
		}
	}

	@After
	public void tearDown() {
		area=null;
		System.gc();	// per deallocazione
	}

	@Test
	public void testGetID_Dipartimento() {
		assertEquals(area.getID_Area_Scientifica(),0);
	}
	
	@Test
	public void testSetID_Dipartimento() {
		area.setID_Area_Scientifica(3);
		assertEquals(area.getID_Area_Scientifica(),3);
	}
	
	@Test
	public void testGetCodice() {
		assertEquals(area.getCodice(), "12");
	}
	
	@Test
	public void testSetCodice() {
		area.setCodice("1");
		assertEquals(area.getCodice(), "1");
	}
	
	@Test
	public void testGetNome() {
		assertEquals(area.getNome(),"Informatica");
	}
	
	@Test
	public void testSetNome() {
		area.setNome("Elettronica");
		assertEquals(area.getNome(),"Elettronica");
	}

	@Test
	public void testGetTelefono() {
		assertEquals(area.getTelefono(),"08242534");
	}
	
	@Test
	public void testSetTelefono() {
		area.setTelefono("222222222");
		assertEquals(area.getTelefono(),"222222222");
	}
	
	@Test
	public void testGetFax() {
		assertEquals(area.getFax(), "08132424");
	}
	
	@Test
	public void testSetFax() {
		area.setFax("08112211");
		assertEquals(area.getFax(),"08112211");
	}
	
	@Test
	public void testGetSito() {
		assertEquals(area.getSito(), "www.inf.it");
	}
	
	@Test
	public void testSetSito() {
		area.setSito("www.info.com");
		assertEquals(area.getSito(), "www.info.com");
	}
	
	@Test
	public void testGetEmail() {
		assertEquals(area.getEmail(), "inf@email.it");
	}
	
	@Test
	public void testSetEmail() {
		area.setEmail("info@gmail.com");
		assertEquals(area.getEmail(), "info@gmail.com");
	}
	
}
