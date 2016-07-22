package warehouse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WarehouseTest {

	Assembly assembly1;
	
	@Before
	public void setUp() throws Exception{
		CatalogueEntry drill = new CatalogueEntry("drill", 28834, 70.00);
		CatalogueEntry lathe = new CatalogueEntry("lathe", 28835, 140.00);
		CatalogueEntry cutter = new CatalogueEntry("tile cutter", 28836, 90.00);
		CatalogueEntry afterSales = new CatalogueEntry("after sales", 2, 10.00);
		
		assembly1 = new Assembly();
		assembly1.add(new Part(drill));
		assembly1.add(new Part(cutter));
		assembly1.add(new Part(lathe));
		assembly1.add(new Service(afterSales));

	}
	
	@Test
	public void myTest(){
		double output = assembly1.cost();
		double expected = 310;
		assertEquals(expected, output, 0.01);
	}
}
