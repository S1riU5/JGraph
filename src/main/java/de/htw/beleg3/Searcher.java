package de.htw.beleg3;

import java.util.ArrayList;


public class Searcher {

	/**
	 * class Searcher
	 */
	

	public Integer[][] dijk(int[][] adjMat, int current, int target){
		/**
		 * dijk()
		 * 
		 * dijkstra-algorithm to find the shortest way in graph.
		 * 
		 * @return Integer[]   shortes way through the nodes
		 */
		Integer[][] way 	= new Integer[3][adjMat.length];
		boolean[] discov 	= new boolean[adjMat.length];
		Integer[] dist 		= new Integer[adjMat.length];
		
		for (int i = 0; i < discov.length; i++){
			discov[i] =  false;
			dist[i] = null;
		}
	
		way[current][current] = 0;
		dist [current] = 0;
		discov[current] = true;
		dist[current] = 0;
		
		
		boolean finished = false;
		while (!finished){

		}
		

		
		
		return way;
	}
	
	public int[][] dms (int[][] adjMat, int current, int target){
		/**
		 * dms()
		 *
		 * depth-first search
		 *
		 * @return int[][]    pathtree to target.
		 * 
		 * @param int[][] adjMat   adjacencyMatrix
		 * @param int current   startnode
		 * @param int target   targetnode
		 */
		 /*
		  *  Calling dms with backtracking:
		  * 
		  *  backtrack(target, dms(adjMat, start, target))
		  */		
		Stack st = new Stack(); 
		Stack fst = new Stack(); // fromstack
		boolean[] discovered = new boolean[adjMat.length];
		for (int i = 0; i < discovered.length; i++){
			discovered[i] = false;
		}
		int[][] way = new int[2][adjMat.length];
		int itert = 0;
		st.push(current);
		fst.push(current);
		
		while ( !st.isEmpty() && current != target ){
			way[0][itert] = fst.pop();
			current = st.pop();	
			if (!discovered[current]){
				discovered[current] = true;				
				way[1][itert] = current;
				itert++;
				
				for (int i = 0; i < adjMat.length; i++){
					if ( !(adjMat[i][current] < 0) && ! discovered[i]){
						st.push(i);
						fst.push(current);
					}
				}	
			}
		}
		return way;
	}
	
	public Integer[] backtrack( int target, int[][] way){
		/**
		 * backtrack()
		 * 
		 * backtrackmethode to find ways in searchtrees.
		 * 
		 * @return Integer[]  Sorted array of nodes
		 * @param int target   targetnode
		 * int [][] way   searchtree
		 */
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
		/**
		 * class Stack
		 */
		private ArrayList<Integer> stack;
		
		public  Stack(){
			 /**
			  * Stack()
			  *
			  * Simple implementation of a Stack.
			  */
			 stack = new ArrayList<Integer>();
		}
		
		public void push(int elem){
			/**
			 * push()
			 * 
			 * Pushing object on stack.
			 * 
			 * @param int elem    
			 */
			stack.add(elem);
		}
		
		public int pop(){
			/**
			 * pop()
			 * 
			 * Popping element from stack.
			 * 
			 * @return int    elem
			 */
			int elem = (Integer) stack.get(stack.size()-1);
			stack.remove(stack.size()-1);
			return elem;
		}
		
		 public boolean isEmpty(){
			 /**
			  * isEmpty()
			  * 
			  * is Stack empty ?
			  *
			  * @return boolean   emptystack
			  */
			 return stack.size() <= 0;
		 }
		
	}
}
