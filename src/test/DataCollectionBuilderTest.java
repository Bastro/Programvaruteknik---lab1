package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
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

	@Before
	public void setUp() throws Exception {
		dsX = new DataSourceImp("Temperature", "C");
		dsY = new DataSourceImp("Gaols", "Z+");
		setUpData();
		dcb = new DataCollectionBuilder(dsX, dsY, Resolution.DAY);
	}
	
	private void setUpData() {
		//dsX.addData(LocalDate.of(0000, 01, 01), 0.0);
		dsX.addData(LocalDate.of(1999, 12, 31), -3.5);
		dsX.addData(LocalDate.of(2000, 01, 01), 30d);
		dsX.addData(LocalDate.of(2000, 01, 01), 20d);
		
		//dsY.addData(LocalDate.of(0000, 01, 01), 0d);
		dsY.addData(LocalDate.of(2000, 12, 31), 2d);
		dsY.addData(LocalDate.of(2000, 01, 01), 7d);
		dsY.addData(LocalDate.of(2000, 01, 01), 3d);
	}

	@After
	public void tearDown() throws Exception {
		dcb = null;
		dsX = null;
		dsY = null;
	}

	@Test
	public void testGetResult() {
		DataCollection dc = dcb.getResult();
		Map<String, MatchedDataPair> map = dc.getData();
		MatchedDataPair pair = map.get("2000-1-1-SATURDAY-1");
		System.out.println(pair);
		System.out.println(dc.getData());
		//assertEquals(25.0, pair.getxValue(), 0.001);
		assertEquals(4.0, pair.getyValue(), 0.001);
	}

}
