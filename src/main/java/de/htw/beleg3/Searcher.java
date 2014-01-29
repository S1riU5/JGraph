package de.htw.beleg3;

public class Searcher {
	
	public Searcher(){
		/**
		 * 
		 */
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
