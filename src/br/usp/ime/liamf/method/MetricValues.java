package br.usp.ime.liamf.method;

import java.util.HashMap;
import java.util.Map;

public class MetricValues {
	
	private String metric;
	private Map<String, MeasureValues> values; 	//Keep pairs (measure, MeasureValues)
	
	public MetricValues(String metric){
		this.metric = metric;
		this.values = new HashMap<String, MeasureValues>();
	}
	public void add(MeasureValues measureValues){
		this.values.put(measureValues.getMeasure(), measureValues);
	}
	public String getMetric(){
		return this.metric;
	}
	public Map<String, MeasureValues> getValues(){
		return values;
	}
	public MeasureValues getMeasureValues(String measure){
		return this.values.get(measure);
	}
}
