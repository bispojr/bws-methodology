package br.usp.ime.liamf.method.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.usp.ime.liamf.method.AlignmentSequence;
import br.usp.ime.liamf.method.Pair;

public class AlignmentSequenceTest {

	@Test
	public void testGetMetric() {

		String metric = "metric";
		
		Pair p1 = new Pair("a1", 1.0);
		Pair p2 = new Pair("a2", 2.0);
		Pair p3 = new Pair("a3", 3.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		
		String sigma[] = {"a1", "a2", "a3"};
		
		AlignmentSequence alignmentSequence = new AlignmentSequence(metric, values, sigma);
		
		assertEquals(metric, alignmentSequence.getMetric());
	}
	@Test
	public void testGetValues() {

		String metric = "metric";
		
		Pair p1 = new Pair("a1", 1.0);
		Pair p2 = new Pair("a2", 2.0);
		Pair p3 = new Pair("a3", 3.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		
		String sigma[] = {"a1", "a2", "a3"};
		
		AlignmentSequence alignmentSequence = new AlignmentSequence(metric, values, sigma);
		
		assertEquals(values, alignmentSequence.getValues());
	}
	@Test
	public void testGetDelta() {

		String metric = "metric";
		
		Pair p1 = new Pair("a1", 1.0);
		Pair p2 = new Pair("a2", 2.0);
		Pair p3 = new Pair("a3", 3.0);
		
		List<Pair> values = new ArrayList<Pair>();
		values.add(p1);
		values.add(p2);
		values.add(p3);
		Collections.sort(values);
		
		String sigma[] = {"a1", "a2", "a3"};
		
		AlignmentSequence alignmentSequence = new AlignmentSequence(metric, values, sigma);
		
		assertEquals( 1.0/3.0 , alignmentSequence.getDelta(), 0.1);
	}

}
