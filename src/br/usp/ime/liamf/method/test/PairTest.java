package br.usp.ime.liamf.method.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.usp.ime.liamf.method.Pair;

public class PairTest {

	@Test
	public void testCreatePair() {
		Pair p = new Pair("alignment", 3.0);
		assertEquals("alignment", p.getAlignment());
		assertEquals(3.0, p.getValue(), 0.1);
	}

}
