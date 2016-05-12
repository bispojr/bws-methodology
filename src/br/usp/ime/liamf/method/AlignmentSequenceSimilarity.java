package br.usp.ime.liamf.method;

import java.util.Arrays;
import java.util.List;

public class AlignmentSequenceSimilarity {
	
	private String[] sigma;
	
	public AlignmentSequenceSimilarity(String[] sigma){
		this.sigma = sigma;
	}
	
	public double getSimilarity(List<Pair> values){
		
		double hammingDistance = hammingDistance(values);
		int n = values.size();
		double similarity = (n - hammingDistance)/n;
		
		return similarity;
	}
	
	private int hammingDistance(List<Pair> values){
		int distance = 0;
		for(int i=0; i<values.size(); i++){
			if(values.get(i).getAlignment() != sigma[i])
				distance++;
		}
		return distance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(sigma);
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
		if (!(obj instanceof AlignmentSequenceSimilarity)) {
			return false;
		}
		AlignmentSequenceSimilarity other = (AlignmentSequenceSimilarity) obj;
		if (!Arrays.equals(sigma, other.sigma)) {
			return false;
		}
		return true;
	}
	
	

}
