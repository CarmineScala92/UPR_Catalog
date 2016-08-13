package it.unisa.upr.junittest;

import it.unisa.upr.commons.DBNames;
import it.unisa.upr.commons.RefinedAbstractManager;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;
import it.unisa.upr.data.gestioneSistema.AreaScientifica;
import it.unisa.upr.data.gestioneSistema.IAreaScientificaManager;

import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBAreaScientificaTest extends TestCase{
private IAreaScientificaManager dbArea;
	
	@Before
	public void setUp() {
		try{
			dbArea = (IAreaScientificaManager) RefinedAbstractManager.getInstance().getManagerImplementor(DBNames.TABLE_AREA_SCIENTIFICA);
		}catch(Exception e){
			fail();
		}
		
	}
	
	@After
	public void tearDown() {
		dbArea = null;
		System.gc();	//per deallocare
	}
	
	@Test
	public void testGetAreaScientifica() {
		AreaScientifica a;
		try {
			a = dbArea.getAreaScientifica(2);
			//System.out.println(d.getNome());   //controllo che l'area esiste davvero nel database
			assertNotNull(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInserimentoAreaScientifica() {
		try{
			int result = dbArea.inserimentoAreaScientifica("15", "MatematicaInformatica", "0891233433", "0891233434", "www.matinf.com", "matinf@live.com");
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificaAreaScientifica() {
		try{
			int result = dbArea.modificaAreaScientifica(1, "12", "Scienze Culturali", "", "", "", "");
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEliminaAreaScientifica(){
		try{
			int result = dbArea.eliminaAreaScientifica(15);
			assertEquals(1, result);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCaricaRicercatori() {
		AreaScientifica a;
		ArrayList<Ricercatore> ricDip;
		try {
			a = dbArea.getAreaScientifica(5);
			ricDip = dbArea.caricaRicercatori(a);
		/*	for(Ricercatore r : ricDip)		//per controllare che i ricercatori sono davvero dell'area 5
				System.out.println(r.getUsername()+ " "+r.getPassword());
		*/
			assertNotNull(ricDip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testVisualizzaAreaScientifica(){
		AreaScientifica a;
		try{
			a = dbArea.visualizzaAreaScientifica(3);
			System.out.println(a.getNome());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListaAreeScientifiche() {
		ArrayList<AreaScientifica> aree;
		try {
			aree = dbArea.getListaAreeScientifiche();
			System.out.println("GetListaDipartimenti:");
			for(AreaScientifica a : aree)
				System.out.println(a.getNome());
			assertNotNull(aree);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRicercaAreaScientificaPerNome() {
		ArrayList<AreaScientifica> aree;
		try {
			aree = dbArea.ricercaAreaScientifica("Scienze");
			for(AreaScientifica a : aree)
				System.out.println(a.getNome() + " "+ a.getEmail());
			assertNotNull(aree);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}	
}