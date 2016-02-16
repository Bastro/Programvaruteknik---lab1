package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hig.jonasoster.inl1.DataSourceImp;

public class DataSourceImpTest {
	private DataSourceImp ds;

	@Before
	public void setUp() throws Exception {
		ds = new DataSourceImp("Temperature", "C");
	}

	@After
	public void tearDown() throws Exception {
		ds = null;
	}

	@Test
	public void testGetName() {
		assertEquals("Temperature", ds.getName());
	}

	@Test
	public void testGetUnit() {
		assertEquals("C", ds.getUnit());
	}

	@Test
	public void testData() {
		ds.addData(LocalDate.of(0000, 01, 01), 0.0);
		ds.addData(LocalDate.of(1999, 12, 31), 3.5);
		ds.addData(LocalDate.of(2000, 01, 01), 1.4);
		ds.addData(LocalDate.of(2014, 11, 20), Double.MAX_VALUE);
		ds.addData(LocalDate.of(2015, 10, 01), Double.MIN_VALUE);
		Map<LocalDate, Double> map = ds.getData();
		assertEquals(0.0, map.get(LocalDate.of(0000, 01, 01)), 0.001);
		assertEquals(3.5, map.get(LocalDate.of(1999, 12, 31)), 0.001);
		assertEquals(1.4, map.get(LocalDate.of(2000, 01, 01)), 0.001);
		assertEquals(Double.MAX_VALUE, map.get(LocalDate.of(2014, 11, 20)), 0.001);
		assertEquals(Double.MIN_VALUE, map.get(LocalDate.of(2015, 10, 01)), 0.001);
	}
}
