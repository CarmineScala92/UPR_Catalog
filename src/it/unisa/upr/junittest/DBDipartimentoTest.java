package it.unisa.upr.junittest;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.Dipartimento;
import it.unisa.upr.data.gestioneSistema.IDipartimentoManager;
import junit.framework.TestCase;

public class DBDipartimentoTest extends TestCase{
	private IDipartimentoManager dbDip;
	
	@Before
	public void setUp() {
		try{
			dbDip = (IDipartimentoManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_DIPARTIMENTO);
		}catch(Exception e){
			fail();
		}
		
	}
	
	@After
	public void tearDown() {
		dbDip = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetDipartimento() {
		Dipartimento d;
		try {
			d = dbDip.getDipartimento(2);
			//System.out.println(d.getNome());   //controllo che il dipartimento esiste davvero nel database
			assertNotNull(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaRicercatori() {
		Dipartimento d;
		ArrayList<Ricercatore> ricDip;
		try {
			d = dbDip.getDipartimento(5);
			ricDip = dbDip.caricaRicercatori(d);
			for(Ricercatore r : ricDip)		//per controllare che i ricercatori sono davvero del dipartimento 5
				System.out.println(r.getUsername()+ " "+r.getPassword());
			assertNotNull(ricDip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaDipartimentoPerNome() {
		ArrayList<Dipartimento> dip;
		try {
			dip = dbDip.ricercaDipartimento("Scienze");
			for(Dipartimento d : dip)
				System.out.println(d.getNome() + " "+ d.getEmail());
			assertNotNull(dip);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInserimentoDipartimento() {
		try{
			int result = dbDip.inserimentoDipartimento("Matematica&Informatica", "0891233433", "0891233434", "www.matinf.com", "matinf@live.com");
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaDipartimento() {
		try{
			int result = dbDip.modificaDipartimento(1, "Scienze Culturali", "", "", "", "");
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaDipartimento(){
		try{
			int result = dbDip.eliminaDipartimento(1);
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVisualizzaDipartimento(){
		Dipartimento d;
		try{
			d = dbDip.visualizzaDipartimento(3);
			System.out.println(d.getNome());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListaDipartimenti() {
		ArrayList<Dipartimento> dip;
		try {
			dip = dbDip.getListaDipartimenti();
			System.out.println("GetListaDipartimenti:");
			for(Dipartimento d : dip)
				System.out.println(d.getNome());
			assertNotNull(dip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetDirettoreDipartimento() {
		Dipartimento d;
		Ricercatore r;
		try {
			d = dbDip.getDipartimento(3);
			r = dbDip.getDirettoreDipartimento(d);
			System.out.println("Direttore del Dipartimento 3:"+r.getNome()+" "+r.getCognome());
			assertNotNull(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
