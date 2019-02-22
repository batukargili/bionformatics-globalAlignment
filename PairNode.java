
public class PairNode {

	private String first;
	private String second;
	private double similarity;
	private PairNode next;

	public PairNode(String first, String second, double similarity) {
		this.first = first;
		this.second = second;
		this.similarity = similarity;
		next = null;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public PairNode getNext() {
		return next;
	}

	public void setNext(PairNode next) {
		this.next = next;
	}

	
	
}
