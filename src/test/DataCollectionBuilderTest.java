package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import hig.jonasoster.inl1.DataCollection;
import hig.jonasoster.inl1.DataCollectionBuilder;
import hig.jonasoster.inl1.DataSourceImp;
import hig.jonasoster.inl1.MatchedDataPair;
import hig.jonasoster.inl1.Resolution;

public class DataCollectionBuilderTest {
	private DataSourceImp dsX;
	private DataSourceImp dsY;
	private DataCollectionBuilder dcb;
	private DataCollection dc;
	private Map<String, MatchedDataPair> map;

	@Before
	public void setUp() throws Exception {
		dsX = new DataSourceImp("Temperature", "C");
		dsY = new DataSourceImp("Gaols", "Z+");
		setUpData();
		dcb = new DataCollectionBuilder(dsX, dsY, Resolution.DAY);
	}
	
	private void setUpData() {
		dsX.addData(LocalDate.of(2000, 12, 31), 5d);
		dsX.addData(LocalDate.of(2000, 01, 01), 10d);
		dsX.addData(LocalDate.of(2000, 01, 03), 5d);
		dsX.addData(LocalDate.of(2000, 01, 20), 15d);
		dsX.addData(LocalDate.of(2000, 04, 20), 3d);
		
		dsY.addData(LocalDate.of(2000, 12, 31), 2d);
		dsY.addData(LocalDate.of(2000, 01, 01), 3d);
		dsY.addData(LocalDate.of(2000, 01, 03), 4d);
		dsY.addData(LocalDate.of(2000, 01, 20), 5d);
		dsY.addData(LocalDate.of(2000, 04, 20), 3d);
	}

	/*@After
	public void tearDown() throws Exception {
		dcb = null;
		dsX = null;
		dsY = null;
	}*/

	@Test
	public void testGetResult() {
		// Days
		/*dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair1 = map.get("2000-1-1-1");
		assertEquals(10d, pair1.getxValue(), 0.001);
		assertEquals(3d, pair1.getyValue(), 0.001);*/
		// Months
		dcb.setResolution(Resolution.MONTH);
		dc = dcb.getResult();
		map = dc.getData();
		MatchedDataPair pair2 = map.get("2000-01");
		//System.out.println(pair2);
		//System.out.println(pair2);
		assertEquals(10d, pair2.getxValue(), 0.001);
		assertEquals(4d, pair2.getyValue(), 0.001);
	}

}
