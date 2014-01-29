package de.htw.beleg3;

public class Searcher {
	
	public Searcher(){
		/**
		 * 
		 */
	}
	
	public int tiefensuche(int[][] adjMat, boolean [] alreadyVisited, int current , int target) {
		// [start , 1, 3, 56, end]
		
		/*
		 */
		alreadyVisited[current] = true;

		if (current == target ){		
			return (current); 
		}
		
		for (int i = 0; i < adjMat[0].length; i++){
			if (! alreadyVisited[current+i] && adjMat[current][i] >= 0){
				tiefensuche(adjMat,  alreadyVisited, current+1, target);

			}
		}
		System.err.println("NO CONNECTION FOUND!");
		//FIXME
		return -1;
	}
	
	private int[] backtrack(boolean[] ways){
		for (int i = 0; i< ways.length; i++){
			if( ways[i])
				System.out.println(i);
		}
		int[] way = new int[ways.length];
		return way;
	}
}
