package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hig.jonasoster.inl1.MatchedDataPair;

public class MatchedDataPairTest {
	private MatchedDataPair mdp;

	@Before
	public void setUp() throws Exception {
		mdp = new MatchedDataPair(2d, 3d);
	}

	@After
	public void tearDown() throws Exception {
		mdp = null;
	}

	@Test
	public void testGetxValue() {
		assertEquals(2d, mdp.getxValue(), 0.001);
	}

	@Test
	public void testGetyValue() {
		assertEquals(3d, mdp.getyValue(), 0.001);
	}

}
