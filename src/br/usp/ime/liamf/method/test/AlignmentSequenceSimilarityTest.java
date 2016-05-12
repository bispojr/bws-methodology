package br.usp.ime.liamf.method.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.usp.ime.liamf.method.AlignmentSequenceSimilarity;
import br.usp.ime.liamf.method.Pair;

public class AlignmentSequenceSimilarityTest {

	@Test
	public void testGetSimilarity01() {
		
		String sigma[] = {"a2", "a1"};
		
		Pair p1 = new Pair("a1", 3.0);
		Pair p2 = new Pair("a2", 2.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		Collections.sort(values);
		
		AlignmentSequenceSimilarity delta = new AlignmentSequenceSimilarity(sigma);
		
		assertEquals(0.0, delta.getSimilarity(values), 0.1);
	}
	@Test
	public void testGetSimilarity02() {
		
		String sigma[] = {"a1", "a2"};
		
		Pair p1 = new Pair("a1", 3.0);
		Pair p2 = new Pair("a2", 2.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		Collections.sort(values);
		
		AlignmentSequenceSimilarity delta = new AlignmentSequenceSimilarity(sigma);
		
		assertEquals(1.0, delta.getSimilarity(values), 0.1);
	}
	@Test
	public void testGetSimilarity03() {
		
		String sigma[] = {"a1", "a2", "a3"};
		
		Pair p1 = new Pair("a1", 1.0);
		Pair p2 = new Pair("a2", 2.0);
		Pair p3 = new Pair("a3", 3.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		Collections.sort(values);
		
		AlignmentSequenceSimilarity delta = new AlignmentSequenceSimilarity(sigma);
		
		assertEquals( 1.0/3.0 , delta.getSimilarity(values), 0.1);
	}
	@Test
	public void testGetSimilarity04() {
		
		String sigma[] = {"a1", "a2", "a3"};
		
		Pair p1 = new Pair("a1", 3.0);
		Pair p2 = new Pair("a2", 2.0);
		Pair p3 = new Pair("a3", 1.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		Collections.sort(values);
		
		AlignmentSequenceSimilarity delta = new AlignmentSequenceSimilarity(sigma);
		
		assertEquals(1.0, delta.getSimilarity(values), 0.1);
	}
	@Test
	public void testGetSimilarity05() {
		
		String sigma[] = {"a1", "a2", "a3", "a5", "a4"};
		
		Pair p1 = new Pair("a1", 8.0);
		Pair p2 = new Pair("a2", 4.0);
		Pair p3 = new Pair("a3", 7.0);
		Pair p4 = new Pair("a4", 2.0);
		Pair p5 = new Pair("a5", 9.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		values.add(p4);
		values.add(p5);
		Collections.sort(values);
		
		AlignmentSequenceSimilarity delta = new AlignmentSequenceSimilarity(sigma);
		
		assertEquals( 2.0/5 , delta.getSimilarity(values), 0.1);
	}

}
