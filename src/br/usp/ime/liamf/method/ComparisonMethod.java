package br.usp.ime.liamf.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ComparisonMethod {

	private Set<MetricValues> m;
	private String measure;
	private String sigma[];
	private List<AlignmentSequence> bigSigma;
	private List<String> mu;	
	
	public ComparisonMethod(Set<MetricValues> m, String measure, String sigma[]){
		
		for(int i=0; i<sigma.length; i++){
			for(MetricValues metricValues : m){
				
				if( !metricValues.getValues().containsKey(measure) ){
					throw new IllegalArgumentException(
							"Invalid parameter! There is one metric does not contains the informed measure."
						);
				}
				
				MeasureValues measureValues = metricValues.getMeasureValues(measure);
				
				boolean containsAlignmentName = false;
				for(String alignment : measureValues.getValues().keySet()){
					if(alignment == sigma[i])
						containsAlignmentName = true;
				}
				if( !containsAlignmentName ){
					throw new IllegalArgumentException(
						"Invalid parameters! The sets of alignments of each metric are differents."
					);
				}
			}
		}
		
		this.m = m;
		this.measure = measure;
		this.sigma = sigma;
		this.bigSigma = new ArrayList<AlignmentSequence>();
		this.mu = new ArrayList<String>();
	}
	
	public List<AlignmentSequence> getBigSigma(){
		return bigSigma;
	}
	public List<String> getMu(){
		return mu;
	}
		
	public void run(){
		
		for(MetricValues metricValues : m){			
			String metric = metricValues.getMetric();
			MeasureValues measureValues = metricValues.getMeasureValues(measure);
			List<Pair> sigmaLine = measureValues.getSortedValues();
			
			AlignmentSequence alignmentSequence = new AlignmentSequence(metric, sigmaLine, sigma);
			bigSigma.add(alignmentSequence);
		}
		
		Collections.sort(bigSigma);
		
		for(int i=0; i < bigSigma.size(); i++){
			mu.add(  bigSigma.get(i).getMetric() );
		}
		
	}
	
	public void showResults(){
		System.out.println("=================");
		System.out.println("====" + measure.toUpperCase());
		System.out.println("=================");
		
		for(AlignmentSequence as : bigSigma){
			String metric = as.getMetric();
			System.out.println(metric + ": ");
			
			List<Pair> values = as.getValues();
			for(Pair p : values){
				System.out.println(p.getAlignment() + ": " + String.format( "%.2f", p.getValue()) );
			}
			System.out.println("Delta: " + String.format( "%.2f", as.getDelta()));
			System.out.println("=================");
		}
	}
	
	public void latexResults(){
		int metricNumber = bigSigma.size();
		int alignmentNumber = bigSigma.get(0).getValues().size();
		
		System.out.println("\\begin{table}[]");
		System.out.println("\t \\centering");
		System.out.println("\t \\caption{My caption}");
		System.out.println("\t \\begin{tabular}{c|c|c|c|c|c}");
		System.out.println("\t\t \\hline");
		System.out.println("\t\t\t \\multirow{"+ (metricNumber + 2) + "}{*}{\\rotatebox[origin=c]{90}{" + measure.toUpperCase() + "}} &");
		System.out.println("\t\t\t \\multirow{2}{*}{Metric} &");
		System.out.println("\t\t\t \\multicolumn{" + alignmentNumber + "}{c|}{Alignment} &");
		System.out.println("\t\t\t \\multirow{2}{*}{Delta}");
		System.out.println("\t\t \\\\ \\cline{3-" + (alignmentNumber + 2) + "}");
		System.out.println("\t\t\t &");
		System.out.println("\t\t\t &");
		
		List<Pair> valuesPairs = bigSigma.get(0).getValues();
		List<String> alignmentNames = new ArrayList<String>();
		for(Pair p : valuesPairs)
			alignmentNames.add( p.getAlignment() );
		
		int lastPosition = alignmentNames.size()-1;
		for(int i=0; i<lastPosition; i++){
			System.out.println("\t\t\t \\multicolumn{1}{c}{" + alignmentNames.get(i) + "} &");
		}
		System.out.println("\t\t\t \\multicolumn{1}{c|}{" + alignmentNames.get(lastPosition) + "} &");
		
		System.out.println("\t\t\t ");
		System.out.println("\t\t \\\\ \\cline{2-" + (alignmentNumber + 3) + "}");
		
		for(AlignmentSequence as : bigSigma){
			String metric = as.getMetric();
			System.out.println("\t\t\t &");
			System.out.println("\t\t\t " + metric + " &");
			
			//HOW PRINT THE ALIGNMENT VALUES IN PROPERLY ORDER???
			valuesPairs = as.getValues();
			lastPosition = valuesPairs.size()-1;
			for(int i=0; i<lastPosition; i++){
				System.out.println("\t\t\t \\multicolumn{1}{c}{" + String.format( "%.2f", valuesPairs.get(i).getValue()) + "} &");
			}
			System.out.println("\t\t\t \\multicolumn{1}{c|}{" + String.format( "%.2f", valuesPairs.get(lastPosition).getValue()) + "} &");
			
			System.out.println("\t\t\t \\\\");
		}
		System.out.println("\t\t \\hline");
		System.out.println("\t \\end{tabular}");
		System.out.println("\\end{table}");
				
				
	}
}
