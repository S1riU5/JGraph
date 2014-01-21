package de.htw.beleg3;

public class Graph {
	/**
	 * class Graph
	 */
	private int[][] adjacencyMatrix;
	private boolean[] nodes;
	
	public Graph(int MAX){
		/**
		 * Graph()
		 * 
		 * Constructor of the Graph.
		 */
		
		adjacencyMatrix = new int[MAX][MAX];
		nodes = new boolean[MAX];
		
		emptyDataSet();
				

	}
	
	private void emptyDataSet(){
		for (int i = 0; i < this.adjacencyMatrix.length; i++ ){
			this.nodes[i] = false;
			for (int j = 0; j < this.adjacencyMatrix[0].length; j++){
				this.adjacencyMatrix[i][j] = -1;
			}
		}
	}
	
	
	
	
}
