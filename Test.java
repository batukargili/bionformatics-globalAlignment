import java.util.List;

import java.util.LinkedList;

import java.io.*;

import java.text.DecimalFormat;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.StringTokenizer;

public class  Test{

	public static String[][] sequences;

	public static void main(String[] args) throws Exception {

		
		String token1="";

		String[] sequences=null;

		String[] names=null;

		int gap=0,match=2;

		File file = new File("Input2.txt");
		Scanner file1=new Scanner(file);

		List<String> temps = new ArrayList<String>();  

		while (file1.hasNext()) {

			token1=file1.next();

			temps.add(token1);

		}    

		file1.close();

		String[] tempArray =temps.toArray(new String[0]);

		ArrayList<String> tempSequences = new ArrayList<String>();

		ArrayList<String> tempNames = new ArrayList<String>();

		for(String s: tempArray ) {

			if(s.charAt(0)!='>') {

				System.out.println(s);

				tempSequences.add(s);

			}

			else {

				tempNames.add(s);

			}

			sequences = tempSequences.toArray(new String[0]);

			names = tempNames.toArray(new String[0]);

		}

		System.out.println("-----------------------");

		for(int i=0;i<names.length;i++) {

			System.out.println(names[i]);

		}

		double[][] similarity = new double[names.length][names.length];

		ArrayList<Integer> tempScores = new ArrayList<Integer>();

		for(int k=0;k<names.length;k++) {

			for(int m=0;m<names.length;m++) {

				int simCounter=0;

				int alignedLen=0;



				if(k!=m) {



					int[][] globalAlign = new int[sequences[k].length()+1][sequences[m].length()+1]; 



					for(int i=1;i<= sequences[k].length();i++) {



						globalAlign[i][0] = globalAlign[i-1][0]+gap;


					}

					for(int j=1;j<=sequences[m].length();j++) {

						globalAlign[0][j] = globalAlign[0][j-1]+gap;

					}







					for(int i=1;i<=sequences[k].length();i++) {

						for(int j=1;j<=sequences[m].length();j++) {



							if(sequences[k].charAt(i-1)==sequences[m].charAt(j-1)) {

								int forMatch =	getMatchMismatch(sequences[k].charAt(i-1), sequences[m].charAt(j-1));
								globalAlign[i][j]=globalAlign[i-1][j-1] + forMatch;



							}

							else {


								int forMisMatch = getMatchMismatch(sequences[k].charAt(i-1), sequences[m].charAt(j-1));

								int diagonal=globalAlign[i-1][j-1]+forMisMatch;

								int left=globalAlign[i][j-1]+gap;

								int above=globalAlign[i-1][j]+gap;

								globalAlign[i][j]= Math.max((Math.max(left,above)),diagonal);



							}



						}



					}



					for(int i=0;i<sequences[k].length();i++) {

						for(int j=0;j<sequences[m].length();j++) {

							System.out.print(globalAlign[i][j]+ "\t");

						}

						System.out.println();

					}

					System.out.println(names[k]+"-"+names[m]);

					int x=globalAlign[sequences[k].length()][sequences[m].length()]-1;

					System.out.println("Score"+x);

					System.out.println();

					System.out.println();



					int l=sequences[k].length()+sequences[m].length();



					int i=sequences[k].length();

					int j=sequences[m].length();



					int xpos=l;

					int ypos=l;



					// 	        	  final answers for the respective strings



					int xans[]=new int[l+1];

					int yans[]=new int[l+1];

					double count=0;



					while(!(i==0||j==0)) {

						int forMatch = getMatchMismatch(sequences[k].charAt(i-1), sequences[m].charAt(j-1));

						if(sequences[k].charAt(i-1)== sequences[m].charAt(j-1)) {

							xans[xpos--]=(int)sequences[k].charAt(i-1);

							yans[ypos--]=(int)sequences[m].charAt(j-1);

							i--;

							j--;

							count++;

						}

						else if(globalAlign[i-1][j-1]+forMatch == globalAlign[i][j]) {

							xans[xpos--]=(int)sequences[k].charAt(i-1);

							yans[ypos--]=(int)sequences[m].charAt(j-1);

							i--;

							j--;

							count++;

						}

						else if(globalAlign[i-1][j]+gap==globalAlign[i][j]) {

							xans[xpos--]=(int)sequences[k].charAt(i-1);

							yans[ypos--]=(int)'_';

							i--;

						}

						else if(globalAlign[i][j-1]+gap==globalAlign[i][j]) {

							xans[xpos--]=(int)'_';

							yans[ypos--]=(int)sequences[m].charAt(j-1);

							j--;



						}









					}

					while (xpos > 0) 

					{ 

						if (i > 0) xans[xpos--] = (int)sequences[k].charAt(--i); 

						else xans[xpos--] = (int)'_'; 

					} 

					while (ypos > 0) 

					{ 

						if (j > 0) yans[ypos--] = (int)sequences[m].charAt(--j); 

						else yans[ypos--] = (int)'_'; 

					} 





					//Since we have assumed the  

					// answer to be n+m long,  

					// we need to remove the extra  

					// gaps in the starting id  

					// represents the index from  

					// which the arrays xans, 

					// yans are useful 



					int id = 1; 

					for (i = l; i >= 1; i--) 

					{ 

						if ((char)yans[i] == '_' &&  

								(char)xans[i] == '_') 

						{ 

							id = i + 1; 

							break; 

						} 

					} 

					int t=0;

					while(xans[t]=='_') {

						id++;

						t++;	  

					}



					similarity[k][m]= (float) (count/l);

					System.out.print("Similarity score = "); 

					System.out.print(similarity[k][m] + "\n"); 

					System.out.println("The aligned proteins are :"); 

					for (i = id; i <= l; i++) 

					{ 

						System.out.print((char)xans[i]+"\t"); 

					} 

					System.out.print("\n"); 

					for (i = id; i <= l; i++) 

					{ 

						System.out.print((char)yans[i]+"\t"); 

					} 	  



				}        	



				System.out.println();

				System.out.println();


			}



		}


		PriortyNodes List = new PriortyNodes();
		for(int i=0;i<similarity.length;i++) {
			for(int j=0;j<similarity[0].length;j++) {
				
				PairNode newNode = new PairNode(names[i],names[j], similarity[i][j]);
				List.insert(newNode);				
			}
		}
		
		String smlr ="(" + (List.getMax()).getFirst() + " " + (List.getMax()).getSecond() + ")";		
        System.out.println(smlr);
        //List.getTotal(">HUMAN");

        
        PriortyNodes tempList = List;
        PriortyNodes tempList2 = new PriortyNodes();
       LinkedList<String> namesLink = new LinkedList();
       LinkedList<String> namesLink2 = new LinkedList();
       for(int i = 0 ; i< names.length;i++) {
    	   namesLink.add(names[i]);
       }
        
    while(tempList.size()>2) {   
    	       //int[] gTreeCont = maxSim(similarity);
       for(int i =0 ;i<names.length;i++) {
    	  // for(int j = 0;j<names.length;j++) {
    		   
    		   if(!(tempList.getMax().getFirst().equals(names[i]) || tempList.getMax().getSecond().equals(names[i]) || tempList.getMax().getFirst().equals(names[i]) || tempList.getMax().getSecond().equals(names[i]))){
    			  double newSim =  List.getTotal(names[i]);
    			  String name = names[i];
    			  PairNode newNode = new PairNode(smlr,name,newSim);
    			  tempList2.insert(newNode);
    		   }
    		 
       }   
       smlr ="(" + (tempList2.getMax()).getFirst() + " " + (tempList2.getMax()).getSecond() + ")";
       tempList = tempList2;
       tempList2 = new PriortyNodes();
    }
    
    
        System.out.println(smlr);
		System.out.println("SIMILARITY MATRIX" +"\n");

		DecimalFormat df = new DecimalFormat("#0.##");

		for(int i=0;i<similarity.length;i++) {

			for(int j=0;j<similarity[0].length;j++) {

				if(i<=j) {

					similarity[i][j]=0;

				}

				System.out.print(df.format(similarity[i][j])+"\t");

			}

			System.out.println();

		}
	
		
		
		
		
		
		int[] gTree;
		
		while(similarity.length > 2) {
			
			 gTree = maxSim(similarity);
			
			for(int i=0;i<similarity.length;i++) {

				for(int j=0;j<similarity[0].length;j++) {
			
					if((i == gTree[0] && j ==gTree[1]) || (j == gTree[0] && i ==gTree[1])) {
						if(similarity[i][j] != 0) {
							
						}
					}
			              
				}
			}
			
		}


	}

	public static int getMatchMismatch(char seq1, char seq2) throws IOException {

		char a = seq1;
		char b = seq2;

		File file = new File("Blosum62.txt");

		//List<String> temps = new ArrayList<String>();  


		int index= 0;

		String first = new String("");
		String second = new String("");
		FileReader rd = new FileReader(file);
		BufferedReader br = new BufferedReader(rd);

		int firstChar =0;
		int secondChar=0;

		int returnNum=0;

		br = new BufferedReader(new FileReader(file));
		String str = br.readLine();

		for(int i = 0 ; i< str.length();i++) {
			int j = 0;
			if(str != null) {
				StringTokenizer st=new StringTokenizer(str);

				while(st.hasMoreTokens())
				{	
					if(i == 0) {						
						first  = first + (String)st.nextToken();							
					}	

					else if(j == 0) {
						second   = second + (String)st.nextToken(); 
						j++;
					}
					else {
						if(index == 0) {
							index = first.length();
							sequences= new String[index][index];
						}
						sequences[i-1][j-1] = (String)st.nextToken();
						j++;

					}


				} 

			}
			str = br.readLine();
			if(str == null) break;
		}

		for(int i=0;i<= first.length()-1;i++) {
			if(first.charAt(i) == a) {
				firstChar = i;
			}	        				        	
		}

		for(int i=0;i<= second.length()-1;i++) {
			if(second.charAt(i) == b) {
				secondChar = i;
			}	        				        	
		}

		returnNum =Integer.parseInt((sequences[firstChar][secondChar]));


		return returnNum;
	}



	public static int[] maxSim(double[][] temp) {
		
		double[][] gTree = temp;
		double max = Integer.MIN_VALUE;
		int node1 = 0, node2 = 0;
		
		for(int i = 0;i<gTree.length;i++) {
			for(int j = 0 ; j<gTree.length;j++) {
				
				if(i > j) {		
					
					 max = Math.max(max, gTree[i][j]);
					 node1 = i;
					 node2 = j;
				}
			}
		}
		int[] index= new int[2];
		index[0]=node1;index[1] = node2;
		return index;
			
	}
	
	public static double refresh(double[][] temp, int[] index) {

		double[][] gTree = temp;
		int first = index[0];
		int second = index[1];
		double sum=0;
		int cont = 0;
		
		for(int i = 0;i<gTree.length;i++) {
					if(i != first && i != second ) {
						cont = 0;
						sum =( gTree[first][i] + gTree[second][i]+ gTree[i][first]+gTree[i][second] ) / 2;	
						cont++;
						break;
					}	
					if(cont != 0) {break;}
		}
		
		return sum;
		
			
	}	
	
}





