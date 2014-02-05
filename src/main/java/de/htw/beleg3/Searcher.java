package de.htw.beleg3;

import java.util.ArrayList;


public class Searcher {

	
	
	public Searcher(){
	
	}
	

	
	public int[][] dms (int[][] adjMat, int current, int target){
		/**
		 * 
		 */
		
		Stack st = new Stack();
		boolean[] discovered = new boolean[adjMat.length];
		for (int i = 0; i < discovered.length; i++){
			discovered[i] = false;
		}
		int[][] way = new int[2][adjMat.length];
		int itert = 0;
		st.push(current);
		while ( !st.isEmpty() && current != target ){
			// Ok the problem is:
			//
			// If a edge is gone the second time
			// the value is saved and will be writen
			// in way[0][n]
			way[0][itert] = current;
			current = st.pop();
			if (!discovered[current]){
				discovered[current] = true;				
				way[1][itert] = current;
				System.out.printf("\t%s\t-\t%s\t(%s)\n", 
						way[0][itert], way[1][itert], itert);
				itert++;
				for (int i = 0; i < adjMat.length; i++){
					if ( !(adjMat[i][current] < 0) && ! discovered[i]){
						st.push(i);
					}
				}	
			}
		}
		return way;
	}
	
	public Integer[] backtrack( int target, int[][] way){
		System.out.println();
		Integer[] bt = new Integer[way[0].length];
		int cnt = 0;
		int i;
		for (i = way[0].length-1; i >= 0; i--){
			if (way[1][i] == target){
				for (int j = i; j >= 0; j--){
					if (way[0][i] == way[1][j]){
						bt[cnt] = way[1][i];
						cnt++;
						i = j;
					}
				}
			}
		}
		bt[cnt] = way[1][0];
		return bt;
	}
	
	
	private class Stack {	
		ArrayList<Integer> stack;
		
		public  Stack(){
			 stack = new ArrayList<Integer>();
		}
		
		public void push(int elem){
			stack.add(elem);
		}
		
		public int pop(){
			int elem = (Integer) stack.get(stack.size()-1);
			stack.remove(stack.size()-1);
			return elem;
		}
		
		 public boolean isEmpty(){
			 //System.out.println(stack.size());
			 return stack.size() <= 0;
		 }
		
	}
}
