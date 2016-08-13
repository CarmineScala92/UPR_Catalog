package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.SupportoValidazione.IFacadeSupportoValidazione;
import it.unisa.upr.SupportoValidazione.SupportoValidazione;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SupportoValidazioneTest extends TestCase{
	private IFacadeSupportoValidazione suppVal;
	
	@Before
	public void setUp() {
		try{
			suppVal = SupportoValidazione.getInstance();
		}catch(Exception e){
			fail();
		}
	}
	
	@After
	public void tearDown() {
		suppVal = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testAccettaProdotto() {
		try {
			boolean done = suppVal.accettaProdotto(24, "Bel lavoro");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRifiutaProdotto() {
		try {
			boolean done = suppVal.rifiutaProdotto(21, "Brutto lavoro");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidaProdotto() {
		try {
			boolean done = suppVal.validaProdotto(1, "Ottimo lavoro");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaListaProdottiCompleti() {
		try {
			ArrayList<Prodotto> list = suppVal.caricaListaProdottiCompleti();
			System.out.println("caricaListaProdottiCompleti():");
			for(Prodotto p : list)
				System.out.println(p.getStato() + p.getTipologia());
			System.out.println("Fine caricaListaProdottiCompleti()");
			assertNotNull(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaListaProdottiAccettati() {
		try {
			ArrayList<Prodotto> list = suppVal.caricaListaProdottiAccettati();
			System.out.println("caricaListaProdottiAccettati():");
			for(Prodotto p : list)
				System.out.println(p.getStato() + p.getTipologia());
			System.out.println("Fine caricaListaProdottiAccettati()");
			assertNotNull(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}