package br.usp.ime.liamf.method;

public class Pair implements Comparable<Pair>{
	private String alignment;
	private Double value;
	
	public Pair(String alignment, double value){
		this.alignment = alignment;
		this.value = value;
	}
	
	public String getAlignment(){
		return this.alignment;
	}
	public double getValue(){
		return this.value;
	}
	
	@Override
	public int compareTo(Pair p){
		return ((Double)p.getValue()).compareTo( this.value );
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alignment == null) ? 0 : alignment.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		if (alignment == null) {
			if (other.alignment != null) {
				return false;
			}
		} else if (!alignment.equals(other.alignment)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	
}
