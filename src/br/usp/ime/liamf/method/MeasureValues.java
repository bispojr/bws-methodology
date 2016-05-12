package br.usp.ime.liamf.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeasureValues {
	
	private String measure;
	private Map<String,Double> values;	//Keep pairs (alignment, value) [V_i]
	
	public MeasureValues(String measure){
		this.measure = measure;
		this.values = new HashMap<String,Double>();
	}
	
	public void addPair(String alignment, Double value){
		this.values.put(alignment, value);
	}
	public String getMeasure(){
		return this.measure;
	}
	public Map<String,Double> getValues(){
		return values;
	}
	public double getValue(String alignment){
		return this.values.get(alignment);
	}
	public List<Pair> getSortedValues(){
		
		List<Pair> sortedValues = new ArrayList<Pair>();
		
		for(String alignment : values.keySet()){
			Pair p = new Pair(alignment, values.get(alignment));
			sortedValues.add(p);
		}
		
		Collections.sort(sortedValues);
		
		return sortedValues;
	}
}
