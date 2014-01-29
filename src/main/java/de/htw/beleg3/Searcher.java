package de.htw.beleg3;

import java.util.ArrayList;


public class Searcher {

	
	
	public Searcher(){
		/**
		 * 
		 */
	}
	

	
	public int[][] DMS (int[][] adjMat, int current, int target){
		/**
		 * 
		 */
		
		Stack st = new Stack();
		boolean[] discovered = new boolean[adjMat.length];
		for (int i = 0; i < discovered.length; i++){
			discovered[i] = false;
		}
		int[][] way = new int[adjMat.length][2];
		st.push(current);
		//System.out.println(current);
		int itert = 0;
		
		while ( !st.isEmpty() && current != target ){

			current = st.pop();
			//System.out.println(current+1);

			if (!discovered[current]){
				discovered[current] = true;
				
				itert++;
				
				for (int i = 0; i < adjMat.length; i++){
					if (adjMat[i][current] >= 0){
						//TODO Make a propper List for backtracking
						// (from, to)
						st.push(i);
					}
				}
				
			}
		
		}
		return way;
	}
	
	public void backtrack( int target, int[] way){
		boolean innendrinnen = false; 
		int i;
		for (i = way.length-1; i>=0; i--){
			if (way[i] == target){
				innendrinnen = true;
				break;
			}
		}
		if (! innendrinnen){
			System.err.printf("Way not found!");
		}
		else{
			for (i = i; i>0; i--){
				System.out.println(i+1);
			}
		}
	}
	
	private class Stack {
		
		ArrayList<Integer> stack;
		
		public  Stack(){
			 stack = new ArrayList<Integer>();
		}
		
		public void push(int elem){
			//System.out.println(elem);
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
