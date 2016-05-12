package br.usp.ime.liamf.method.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.usp.ime.liamf.method.MeasureValues;
import br.usp.ime.liamf.method.Pair;

public class MeasureValuesTest {

	@Test
	public void testCreateInstance() {
		MeasureValues measureValues = new MeasureValues("measure");
		assertEquals("measure", measureValues.getMeasure());
	}
	@Test
	public void testAddPair(){
		
		MeasureValues measureValues = new MeasureValues("measure");
		measureValues.addPair("alignment", 3.9);
		
		assertEquals(3.9, measureValues.getValue("alignment"), 0.1);
	}
	@Test
	public void testSortedValuesWithThree(){
		MeasureValues measureValues = new MeasureValues("measure");
		measureValues.addPair("alignment", 3.9);
		measureValues.addPair("otherAlignment", 1.9);
		measureValues.addPair("anotherAlignment", 4.6);
		
		List<Pair> sortedValues = measureValues.getSortedValues();
		
		String obtained = "";
		for(Pair p : sortedValues){
			obtained += p.getAlignment() + " ";
		}
		String expected = "anotherAlignment alignment otherAlignment ";
		
		assertEquals(expected, obtained);
	}
	@Test
	public void testSortedValuesWithFive(){
		MeasureValues measureValues = new MeasureValues("measure");
		measureValues.addPair("goodAlignment", 3.9);
		measureValues.addPair("badAlignment", 1.9);
		measureValues.addPair("topAlignment", 4.6);
		measureValues.addPair("impossibleAlignment", 0.2);
		measureValues.addPair("mediumAlignment", 3.5);
		
		List<Pair> sortedValues = measureValues.getSortedValues();
		
		String obtained = "";
		for(Pair p : sortedValues){
			obtained += p.getAlignment() + " ";
		}
		String expected = "topAlignment goodAlignment mediumAlignment ";
		expected += "badAlignment impossibleAlignment ";
		
		assertEquals(expected, obtained);
	}
}
