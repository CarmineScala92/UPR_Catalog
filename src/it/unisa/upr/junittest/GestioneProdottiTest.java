package it.unisa.upr.junittest;


import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneProdotti.Altro;
import it.unisa.upr.data.gestioneProdotti.Brevetto;
import it.unisa.upr.data.gestioneProdotti.Prodotto;
import it.unisa.upr.data.gestioneProdotti.Produzione;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.Dipartimento;
import it.unisa.upr.gestioneProdotti.GestioneProdotti;
import it.unisa.upr.gestioneProdotti.IFacadeGestioneProdotti;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class GestioneProdottiTest extends TestCase {
	private IFacadeGestioneProdotti gestProd;

	@Before
	public void setUp() {
		try {
			gestProd=GestioneProdotti.getInstance();
		} catch (Exception e) {
			fail();
		}
	}

	@After
	public void tearDown() {
		gestProd=null;
		System.gc();
		
	}

	@Test
	public void testCaricaListaAreeScientifiche() {
		try {
			ArrayList<AreaScientifica> list = gestProd.caricaListaAreeScientifiche();
			System.out.println("caricaListaAreeScientifiche():");
			for(AreaScientifica a : list)
				System.out.println(a.getNome());
			System.out.println("Fine caricaListaAreeScientifiche()\n");
			assertNotNull(list);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaListaDipartimenti() {
		try {
			ArrayList<Dipartimento> list = gestProd.caricaListaDipartimenti();
			System.out.println("caricaListaDipartimenti():");
			for(Dipartimento d : list)
				System.out.println(d.getNome());
			System.out.println("Fine caricaListaDipartimenti()\n");
			assertNotNull(list);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListaRicercatori() {
		try{
			ArrayList<Ricercatore> list = gestProd.getListaRicercatori();
			System.out.println("getListaRicercatori():");
			for(Ricercatore r : list)
				System.out.println(r.getNome());
			System.out.println("Fine getListaRicercatori()\n");
			assertNotNull(list);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCaricaListaProdottiHomepage() {
		Account a = new Account(3,"","","Ricercatore");
		ArrayList<Prodotto> list = gestProd.caricaListaProdottiHomepage(a);
		System.out.println("caricaListaProdottiHomepage():");
		for(Prodotto p : list)
			System.out.println(p.getTitolo());
		System.out.println("Fine caricaListaProdottiHomepage()\n");
		assertNotNull(list);
	}
	
	@Test
	public void testGetProduzioni() {
		Prodotto p = new Prodotto(2,1, "","","","",0, "","", "", "");
		ArrayList<Produzione> list = gestProd.getProduzioni(p);
		System.out.println("getProduzioni():");
		for(Produzione pr : list)
			System.out.println(pr.getID_Ricercatore());
		System.out.println("Fine getProduzioni()\n");
		assertNotNull(list);
	}
	
	@Test
	public void testInserisciRicercatoriProduzione() {
		Prodotto p = new Prodotto(2,1, "","","","",0, "","", "", "");
		String[] Autori_per_produzione = {"3", "2"};
		int[] id_proprietari = {3, 2};
		boolean done = gestProd.inserisciRicercatoriProduzione(p, Autori_per_produzione, id_proprietari);
		assertEquals(true, done);
	}
	
	@Test
	public void testEliminaProdotto() {
		Altro p = new Altro(14,1, "","","","",0, "","Altro", "", "", "", "", "", "", "");
		boolean done = gestProd.eliminaProdotto(p);
		assertEquals(true, done);
	}

	@Test
	public void testInserisciProdotto() {
		Brevetto b = new Brevetto(52,1, "","","","",0, "","brevetto", "", "", "", "", "", "", "");
		String[] Autori_per_produzione = {"Luigi Dell'Aglio", "Nello Polidoro"};
		int[] id_proprietari = {3, 2};
		try {
			boolean done = gestProd.inserisciProdotto(b, Autori_per_produzione, id_proprietari);
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaProdotto() {
		Prodotto p = new Prodotto(10,1, "","","","",0, "","", "", "");
		boolean done = gestProd.modificaProdotto(p, "Titolo", "I valori dei soldi");
		assertEquals(true, done);
	}
	
	@Test
	public void testVisualizzaProdotto() {
		try {
			Prodotto p = gestProd.visualizzaProdotto(1);
			System.out.println("visualizzaProdotto: " + p.getTitolo());
			assertNotNull(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaProdotto() {
		ArrayList<Prodotto> list = gestProd.ricercaProdotto("At", "", "", "", "", "", "");
		System.out.println("ricercaProdotti(titolo):");
		for(Prodotto p : list)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(titolo)\n");
		assertNotNull(list);
		
		ArrayList<Prodotto> list2 = gestProd.ricercaProdotto("", "2011", "", "", "", "", "");
		System.out.println("ricercaProdotti(anno):");
		for(Prodotto p : list2)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(anno)\n");
		assertNotNull(list2);
		
		ArrayList<Prodotto> list3 = gestProd.ricercaProdotto("", "", "brevetto", "", "", "", "");
		System.out.println("ricercaProdotti(tipologia):");
		for(Prodotto p : list3)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(tipologia)\n");
		assertNotNull(list3);
		
		ArrayList<Prodotto> list4 = gestProd.ricercaProdotto("", "", "", "2", "", "", "");
		System.out.println("ricercaProdotti(area):");
		for(Prodotto p : list4)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(area)\n");
		assertNotNull(list4);
		
		ArrayList<Prodotto> list5 = gestProd.ricercaProdotto("", "", "", "", "4", "", "");
		System.out.println("ricercaProdotti(dipartimento):");
		for(Prodotto p : list5)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(dipartimento)\n");
		assertNotNull(list5);
		
		ArrayList<Prodotto> list6 = gestProd.ricercaProdotto("", "", "", "", "", "Luigi", "");
		System.out.println("ricercaProdotti(nome_prop):");
		for(Prodotto p : list6)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(nome_prop)\n");
		assertNotNull(list6);
		
		ArrayList<Prodotto> list7 = gestProd.ricercaProdotto("", "", "", "", "", "", "Del");
		System.out.println("ricercaProdotti(cognome_prop):");
		for(Prodotto p : list7)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(cognome_prop)\n");
		assertNotNull(list7);
	}
	
	@Test
	public void testStampaProdotto() {
		//metodo non implementato
	}
	
	@Test
	public void testRicercaProdottoUe() {
		ArrayList<Prodotto> list = gestProd.ricercaProdottoUe("At", "", "", "", "", "", "");
		System.out.println("ricercaProdotti(titolo):");
		for(Prodotto p : list)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(titolo)\n");
		assertNotNull(list);
		
		ArrayList<Prodotto> list2 = gestProd.ricercaProdottoUe("", "2011", "", "", "", "", "");
		System.out.println("ricercaProdotti(anno):");
		for(Prodotto p : list2)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(anno)\n");
		assertNotNull(list2);
		
		ArrayList<Prodotto> list3 = gestProd.ricercaProdottoUe("", "", "brevetto", "", "", "", "");
		System.out.println("ricercaProdotti(tipologia):");
		for(Prodotto p : list3)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(tipologia)\n");
		assertNotNull(list3);
		
		ArrayList<Prodotto> list4 = gestProd.ricercaProdottoUe("", "", "", "2", "", "", "");
		System.out.println("ricercaProdotti(area):");
		for(Prodotto p : list4)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(area)\n");
		assertNotNull(list4);
		
		ArrayList<Prodotto> list5 = gestProd.ricercaProdottoUe("", "", "", "", "4", "", "");
		System.out.println("ricercaProdotti(dipartimento):");
		for(Prodotto p : list5)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(dipartimento)\n");
		assertNotNull(list5);
		
		ArrayList<Prodotto> list6 = gestProd.ricercaProdottoUe("", "", "", "", "", "Luigi", "");
		System.out.println("ricercaProdotti(nome_prop):");
		for(Prodotto p : list6)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(nome_prop)\n");
		assertNotNull(list6);
		
		ArrayList<Prodotto> list7 = gestProd.ricercaProdottoUe("", "", "", "", "", "", "Del");
		System.out.println("ricercaProdotti(cognome_prop):");
		for(Prodotto p : list7)
			System.out.println(p.getTitolo());
		System.out.println("Fine ricercaProdotti(cognome_prop)\n");
		assertNotNull(list7);
	}
	
	@Test
	public void testExists() {
		try {
			boolean done = gestProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2010");
			assertEquals(false, done);
			
			done  = gestProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "curatela", "2013");
			assertEquals(false, done);
			
			done  = gestProd.exists("Canoni Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2010");
			assertEquals(false, done);
			
			done  = gestProd.exists("Canoni Arcadia. Muratori Maffei Lemene Ceva Quadrio", "curatela", "2013");
			assertEquals(false, done);
			
			done  = gestProd.exists("Canoni d’Arcadia. Muratori Maffei Lemene Ceva Quadrio", "monografia", "2013");
			assertEquals(true, done);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}