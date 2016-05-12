package br.usp.ime.liamf.method.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.usp.ime.liamf.method.AlignmentSequence;
import br.usp.ime.liamf.method.ComparisonMethod;
import br.usp.ime.liamf.method.MeasureValues;
import br.usp.ime.liamf.method.MetricValues;
import br.usp.ime.liamf.method.Pair;

public class ComparisonMethodTest {
	
	//RULE
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void testValidationAlignment() {
		
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("Invalid parameters! The sets of alignments of each metric are differents.");
		
		String measure = "measure";
		String sigma[] = {"anotherAlignment", "alignment", "otherAlignment"};
		Set<MetricValues> m = new HashSet<MetricValues>();
		
		MetricValues metricValues1 = new MetricValues("metric1");
		
		MeasureValues measureValues1 = new MeasureValues("measure");
		measureValues1.addPair("alignment", 3.9);
		measureValues1.addPair("otherAlignment", 1.9);
		measureValues1.addPair("anotherAlignment", 4.6);
		
		metricValues1.add(measureValues1);
		
		MetricValues metricValues2 = new MetricValues("metric2");
		
		MeasureValues measureValues2 = new MeasureValues("measure");
		measureValues2.addPair("alignment", 5.8);
		measureValues2.addPair("otherAlignment", 6.7);
		measureValues2.addPair("crazyAlignment", 9.6);	//this alignment is different than others
		
		metricValues2.add(measureValues2);
		
		m.add(metricValues1);
		m.add(metricValues2);
		
		ComparisonMethod method = new ComparisonMethod(m, measure, sigma);
	}
	@Test
	public void testValidationMeasure() {
		
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("Invalid parameter! There is one metric does not contains the informed measure.");
		
		String measure = "measure";
		String sigma[] = {"anotherAlignment", "alignment", "otherAlignment"};
		Set<MetricValues> m = new HashSet<MetricValues>();
		
		MetricValues metricValues1 = new MetricValues("metric1");
		
		MeasureValues measureValues1 = new MeasureValues("crazyMeasure"); //this measure is different than others
		measureValues1.addPair("alignment", 3.9);
		measureValues1.addPair("otherAlignment", 1.9);
		measureValues1.addPair("anotherAlignment", 4.6);
		
		metricValues1.add(measureValues1);
		
		MetricValues metricValues2 = new MetricValues("metric2");
		
		MeasureValues measureValues2 = new MeasureValues("measure");
		measureValues2.addPair("alignment", 5.8);
		measureValues2.addPair("otherAlignment", 6.7);
		measureValues2.addPair("anotherAlignment", 9.6);	
		
		metricValues2.add(measureValues2);
		
		m.add(metricValues1);
		m.add(metricValues2);
		
		ComparisonMethod method = new ComparisonMethod(m, measure, sigma);
	}
	@Test
	public void testRun01() {

		String measure = "measure";
		String sigma[] = {"anotherAlignment", "alignment", "otherAlignment"};
		Set<MetricValues> m = new HashSet<MetricValues>();
		
		MetricValues metricValues1 = new MetricValues("metric1");
		
		MeasureValues measureValues1 = new MeasureValues("measure");
		measureValues1.addPair("alignment", 3.9);
		measureValues1.addPair("otherAlignment", 1.9);
		measureValues1.addPair("anotherAlignment", 4.6);
		
		metricValues1.add(measureValues1);
		
		List<Pair> sigmaLine1 = measureValues1.getSortedValues();
		AlignmentSequence alignmentSequence1 = new AlignmentSequence("metric1", sigmaLine1, sigma);
		
		MetricValues metricValues2 = new MetricValues("metric2");
		
		MeasureValues measureValues2 = new MeasureValues("measure");
		measureValues2.addPair("alignment", 5.8);
		measureValues2.addPair("otherAlignment", 6.7);
		measureValues2.addPair("anotherAlignment", 9.6);
		
		metricValues2.add(measureValues2);
		
		List<Pair> sigmaLine2 = measureValues2.getSortedValues();
		AlignmentSequence alignmentSequence2 = new AlignmentSequence("metric2", sigmaLine2, sigma);
		
		m.add(metricValues1);
		m.add(metricValues2);
		
		ComparisonMethod method = new ComparisonMethod(m, measure, sigma);
		method.run();
		
		//EXPECTED OUTPUT		
		List<String> mu = new ArrayList<String>();
		mu.add("metric1");
		mu.add("metric2");
		
		List<AlignmentSequence> bigSigma = new ArrayList<AlignmentSequence>();
		bigSigma.add(alignmentSequence1);
		bigSigma.add(alignmentSequence2);		
		Collections.sort(bigSigma);
		
		//VALIDATION
		assertEquals(mu, method.getMu());
		assertEquals(bigSigma, method.getBigSigma());
	}
	@Test
	public void testRun02() {

		String measure = "measure";
		String sigma[] = {"anotherAlignment", "alignment", "otherAlignment"};
		Set<MetricValues> m = new HashSet<MetricValues>();
		
		MetricValues metricValues1 = new MetricValues("metric1");
		
		MeasureValues measureValues1 = new MeasureValues("measure");
		measureValues1.addPair("alignment", 3.9);
		measureValues1.addPair("otherAlignment", 1.9);
		measureValues1.addPair("anotherAlignment", 4.6);
		
		metricValues1.add(measureValues1);
		
		List<Pair> sigmaLine1 = measureValues1.getSortedValues();
		AlignmentSequence alignmentSequence1 = new AlignmentSequence("metric1", sigmaLine1, sigma);
		
		MetricValues metricValues2 = new MetricValues("metric2");
		
		MeasureValues measureValues2 = new MeasureValues("measure");
		measureValues2.addPair("alignment", 12.8);
		measureValues2.addPair("otherAlignment", 6.7);
		measureValues2.addPair("anotherAlignment", 2.6);
		
		metricValues2.add(measureValues2);
		
		List<Pair> sigmaLine2 = measureValues2.getSortedValues();
		AlignmentSequence alignmentSequence2 = new AlignmentSequence("metric2", sigmaLine2, sigma);
		
		MetricValues metricValues3 = new MetricValues("metric3");
		
		MeasureValues measureValues3 = new MeasureValues("measure");
		measureValues3.addPair("alignment", 5.8);
		measureValues3.addPair("otherAlignment", 6.7);
		measureValues3.addPair("anotherAlignment", 9.6);
		
		metricValues3.add(measureValues3);
		
		List<Pair> sigmaLine3 = measureValues3.getSortedValues();
		AlignmentSequence alignmentSequence3 = new AlignmentSequence("metric3", sigmaLine3, sigma);
		
		m.add(metricValues1);
		m.add(metricValues2);
		m.add(metricValues3);
		
		ComparisonMethod method = new ComparisonMethod(m, measure, sigma);
		method.run();
		
		//EXPECTED OUTPUT
		List<String> mu = new ArrayList<String>();
		mu.add("metric1");
		mu.add("metric3");
		mu.add("metric2");
		
		List<AlignmentSequence> bigSigma = new ArrayList<AlignmentSequence>();
		bigSigma.add(alignmentSequence1);
		bigSigma.add(alignmentSequence2);
		bigSigma.add(alignmentSequence3);
		Collections.sort(bigSigma);
		
		//VALIDATION
		assertEquals(mu, method.getMu());
		assertEquals(bigSigma, method.getBigSigma());
	}
	@Test
	public void testRun03() {

		String measure = "measure";
		String sigma[] = {"alignment-01", "alignment-03", "alignment-04", "alignment-02", "alignment-05"};
		Set<MetricValues> m = new HashSet<MetricValues>();
		
		MetricValues metricValues1 = new MetricValues("metric1");
		
		MeasureValues measureValues1 = new MeasureValues("measure");
		measureValues1.addPair("alignment-01", 3.2);
		measureValues1.addPair("alignment-02", 5.8);
		measureValues1.addPair("alignment-03", 9.0);
		measureValues1.addPair("alignment-04", 7.9);
		measureValues1.addPair("alignment-05", 6.3);
		
		metricValues1.add(measureValues1);
		
		List<Pair> sigmaLine1 = measureValues1.getSortedValues();
		AlignmentSequence alignmentSequence1 = new AlignmentSequence("metric1", sigmaLine1, sigma);
		
		MetricValues metricValues2 = new MetricValues("metric2");
		
		MeasureValues measureValues2 = new MeasureValues("measure");
		measureValues2.addPair("alignment-01", 1.2);
		measureValues2.addPair("alignment-02", 7.8);
		measureValues2.addPair("alignment-03", 9.0);
		measureValues2.addPair("alignment-04", 5.9);
		measureValues2.addPair("alignment-05", 8.3);
		
		metricValues2.add(measureValues2);
		
		List<Pair> sigmaLine2 = measureValues2.getSortedValues();
		AlignmentSequence alignmentSequence2 = new AlignmentSequence("metric2", sigmaLine2, sigma);
		
		MetricValues metricValues3 = new MetricValues("metric3");
		
		MeasureValues measureValues3 = new MeasureValues("measure");
		measureValues3.addPair("alignment-01", 19.2);
		measureValues3.addPair("alignment-02", 7.8);
		measureValues3.addPair("alignment-03", 12.0);
		measureValues3.addPair("alignment-04", 10.9);
		measureValues3.addPair("alignment-05", 6.3);
		
		metricValues3.add(measureValues3);
		
		List<Pair> sigmaLine3 = measureValues3.getSortedValues();
		AlignmentSequence alignmentSequence3 = new AlignmentSequence("metric3", sigmaLine3, sigma);
		
		MetricValues metricValues4 = new MetricValues("metric4");
		
		MeasureValues measureValues4 = new MeasureValues("measure");
		measureValues4.addPair("alignment-01", 19.2);
		measureValues4.addPair("alignment-02", 12.8);
		measureValues4.addPair("alignment-03", 5.0);
		measureValues4.addPair("alignment-04", 8.9);
		measureValues4.addPair("alignment-05", 2.9);
		
		metricValues4.add(measureValues4);
		
		List<Pair> sigmaLine4 = measureValues4.getSortedValues();
		AlignmentSequence alignmentSequence4 = new AlignmentSequence("metric4", sigmaLine4, sigma);
		
		MetricValues metricValues5 = new MetricValues("metric5");
		
		MeasureValues measureValues5 = new MeasureValues("measure");
		measureValues5.addPair("alignment-01", 9.2);
		measureValues5.addPair("alignment-02", 12.8);
		measureValues5.addPair("alignment-03", 16.0);
		measureValues5.addPair("alignment-04", 18.9);
		measureValues5.addPair("alignment-05", 8.9);
		
		metricValues5.add(measureValues5);
		
		List<Pair> sigmaLine5 = measureValues5.getSortedValues();
		AlignmentSequence alignmentSequence5 = new AlignmentSequence("metric5", sigmaLine5, sigma);
		
		m.add(metricValues1);
		m.add(metricValues2);
		m.add(metricValues3);
		m.add(metricValues4);
		m.add(metricValues5);
		
		ComparisonMethod method = new ComparisonMethod(m, measure, sigma);
		method.run();
		
		//EXPECTED OUTPUT
		List<String> mu = new ArrayList<String>();
		mu.add("metric3");
		mu.add("metric4");
		mu.add("metric5");
		mu.add("metric1");
		mu.add("metric2");
		
		List<AlignmentSequence> bigSigma = new ArrayList<AlignmentSequence>();
		bigSigma.add(alignmentSequence1);
		bigSigma.add(alignmentSequence2);
		bigSigma.add(alignmentSequence3);
		bigSigma.add(alignmentSequence4);
		bigSigma.add(alignmentSequence5);
		Collections.sort(bigSigma);
		
		//VALIDATION		
		assertEquals(mu, method.getMu());
		assertEquals(bigSigma, method.getBigSigma());
	}
}
