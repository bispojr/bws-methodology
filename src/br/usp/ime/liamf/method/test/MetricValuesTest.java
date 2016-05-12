package br.usp.ime.liamf.method.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.usp.ime.liamf.method.MeasureValues;
import br.usp.ime.liamf.method.MetricValues;

public class MetricValuesTest {

	@Test
	public void testCreateInstance() {
		MetricValues metricValues = new MetricValues("metric");
		assertEquals("metric", metricValues.getMetric());
	}
	@Test
	public void testAddPair(){
		
		MetricValues metricValues = new MetricValues("metric");
		
		MeasureValues measureValues = new MeasureValues("measure");
		measureValues.addPair("alignment", 3.9);
		measureValues.addPair("otherAlignment", 1.9);
		measureValues.addPair("anotherAlignment", 4.6);
		
		metricValues.add(measureValues);
		
		assertEquals(measureValues, metricValues.getMeasureValues("measure"));
	}

}
