
public class PriortyNodes {

	private PairNode root ;          // elements
	private int n;             // number of elements


	public PriortyNodes() {
		root = null;
		n = 0;
	}


	public boolean isEmpty() { return n == 0;  }

	public int size(){ 
		PairNode pqTemp = root;
		while(pqTemp != null) {n++; pqTemp = pqTemp.getNext();}
		return n;      
	} 

	public PairNode delRoot(){ 
		PairNode temp = root;
		root = root.getNext();
		return temp;
	}

	public void insert(PairNode newNode) {

		PairNode pqTemp = root;		
		if(root == null) {root = newNode;}
		else if(pqTemp.getNext() == null) {
			if(newNode.getSimilarity() > pqTemp.getSimilarity()) {
				newNode.setNext(pqTemp);
				root = newNode;
			}
			else {
				root.setNext(newNode);
			}
		}
		else {
			PairNode pqTemp2 = pqTemp.getNext();
			if(newNode.getSimilarity() > pqTemp.getSimilarity()) {
				newNode.setNext(pqTemp);
				root = newNode;
			}
			else {	

				while (pqTemp2 != null) {									
					if(newNode.getSimilarity() > pqTemp2.getSimilarity() ) {						
						newNode.setNext(pqTemp2);
						pqTemp.setNext(newNode);							
						break;

					}

					else {						
						PairNode temp3 = pqTemp2;
						pqTemp = temp3;
						pqTemp2 = pqTemp2.getNext();
						if(pqTemp2 == null) {
							pqTemp.setNext(newNode); 
							break;}

					}				

				}


			}
		}
	}

	public PairNode getMax() {
		return root;
	}
	
	public double getTotal(String check) {
		
		PairNode search = root;
		String first = root.getFirst();
		String second = root.getSecond();
		double sum= 0;
		
		while(search != null) {
			
		if(search.getFirst().equals(check) || search.getSecond().equals(check) )	{
			if(search.getFirst().equals(first) ||search.getSecond().equals(first) || search.getFirst().equals(second) || search.getSecond().equals(second)) { 
				sum = sum + search.getSimilarity();
				}
		}
			search = search.getNext();			
		}
		
		return sum/2;
	}
	
	


}
