package br.usp.ime.liamf.method;

import java.util.List;

public class AlignmentSequence implements Comparable<AlignmentSequence>{
	
	private String metric;
	private List<Pair> values;
	private AlignmentSequenceSimilarity delta;
	
	public AlignmentSequence(String metric, List<Pair> values, String[] sigma){
		this.metric = metric;
		this.values = values;
		this.delta = new AlignmentSequenceSimilarity(sigma);
	}
	
	public String getMetric(){
		return metric;
	}
	public List<Pair> getValues(){
		return values;
	}	
	public double getDelta(){
		return delta.getSimilarity(values);
	}
	
	@Override
	public int compareTo(AlignmentSequence alignmentSequence){
		return ((Double)alignmentSequence.getDelta()).compareTo( this.getDelta() );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delta == null) ? 0 : delta.hashCode());
		result = prime * result + ((metric == null) ? 0 : metric.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AlignmentSequence)) {
			return false;
		}
		AlignmentSequence other = (AlignmentSequence) obj;
		if (delta == null) {
			if (other.delta != null) {
				return false;
			}
		} else if (!delta.equals(other.delta)) {
			return false;
		}
		if (metric == null) {
			if (other.metric != null) {
				return false;
			}
		} else if (!metric.equals(other.metric)) {
			return false;
		}
		if (values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!values.equals(other.values)) {
			return false;
		}
		return true;
	}
	
	
}
