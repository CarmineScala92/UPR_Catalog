package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Responsabile;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;
import it.unisa.upr.gestioneSistema.GestioneSistema;
import it.unisa.upr.gestioneSistema.IFacadeGestioneSistema;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GestioneSistemaTest extends TestCase{
	private IFacadeGestioneSistema gestSist;
	
	@Before
	public void setUp() {
		try{
			gestSist = GestioneSistema.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		gestSist = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testEliminaAccount() {
		Ricercatore r = new Ricercatore(33, "", "", "ricercatore", 33, "", "", "", "1967-2-4", "", "", "", "", "2010-6-5", 2, 2, "ricercatore", "");
		boolean done = gestSist.eliminaAccount(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciAccount() {
		Ricercatore r = new Ricercatore(40, "pippo22", "pippo.baudo", "ricercatore", 50, "Pippo", "Baudo", "", "1992-3-1", "", "", "", "", "2013-4-7", 2, 2, "ricercatore", "M");
		boolean done = gestSist.inserisciAccount(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testRicercaAccount() {
		ArrayList<Account> list = gestSist.ricercaAccount("", "");
		assertNotNull(list);
		
		ArrayList<Account> list1 = gestSist.ricercaAccount("Lu", "");
		assertNotNull(list1);
		
		ArrayList<Account> list2 = gestSist.ricercaAccount("", "D");
		assertNotNull(list2);
		
		ArrayList<Account> list3 = gestSist.ricercaAccount("gi", "D");
		assertNotNull(list3);
	}
	
	@Test
	public void testVisualizzaAccount() {
		try {
			Amministratore a = (Amministratore) gestSist.visualizzaAccount(33);
			System.out.println("visualizzaAccount(): " + a.getNome() + " " + a.getCognome());
			assertNotNull(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaAccount() {
		Responsabile r = new Responsabile(35, "paolo", "fortino", "responsabile", 35, "Paolo", "Fortino");
		boolean done = gestSist.modificaAccount(r);
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaDipartimento() {
		Dipartimento d = new Dipartimento(6, "", "", "", "", "");
		boolean done = gestSist.eliminaDipartimento(d);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciDipartimento() {
		Dipartimento d = new Dipartimento(17, "", "", "", "", "");
		boolean done = gestSist.inserisciDipartimento(d);
		assertEquals(true, done);
	}
	
	@Test
	public void testRicercaDipartimento() {
		ArrayList<Dipartimento> list;
		try {
			list = gestSist.ricercaDipartimento("");
			assertNotNull(list);
			
			ArrayList<Dipartimento> list1 = gestSist.ricercaDipartimento("Scien");
			assertNotNull(list1);
			
			ArrayList<Dipartimento> list2 = gestSist.ricercaDipartimento("Xwq");
			assertEquals(0, list2.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVisualizzaDipartimento() {
		try {
			Dipartimento d = gestSist.visualizzaDipartimento(2);
			System.out.println("visualizzaDipartimento(): " + d.getNome());
			assertNotNull(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaDipartimento() {
		try {
			boolean done = gestSist.modificaDipartimento(5, "Informatica applicata", "", "", "", "");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaAreaScientifica() {
		AreaScientifica a = new AreaScientifica(6, "", "", "", "", "", "");
		boolean done = gestSist.eliminaAreaScientifica(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testInserisciAreaScientifica() {
		AreaScientifica a = new AreaScientifica(1, "", "", "", "", "", "");
		boolean done = gestSist.inserisciAreaScientifica(a);
		assertEquals(true, done);
	}
	
	@Test
	public void testRicercaAreaScientifica() {
		try {
			ArrayList<AreaScientifica> list = gestSist.ricercaAreaScientifica("");
			assertNotNull(list);
			
			ArrayList<AreaScientifica> list1 = gestSist.ricercaAreaScientifica("Scien");
			assertNotNull(list1);
			
			ArrayList<AreaScientifica> list2 = gestSist.ricercaAreaScientifica("Xwq");
			assertEquals(0, list2.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVisualizzaAreaScientifica() {
		try {
			AreaScientifica a = gestSist.visualizzaAreaScientifica(2);
			System.out.println("visualizzaAreaScientifica(): " + a.getNome());
			assertNotNull(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaAreaScientifica() {
		try {
			boolean done = gestSist.modificaAreaScientifica(5, "05", "Informatica applicata", "", "", "", "");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}