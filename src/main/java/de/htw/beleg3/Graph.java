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

	public void addNode(){
		this.nodes[fFEP()] = true; 
	}
	
	public void addEdge(int x, int y, int value){
		if (!this.nodes[x] || !this.nodes[y]){
			throw new IllegalArgumentException("Unknown node!");
		}
		
		this.adjacencyMatrix[x][y] = value;
		this.adjacencyMatrix[y][x] = value;		
	}
	
	public boolean[] getNodes(){
		return this.nodes;
	}
	
	public int[][] getAdjancencyMatrix(){
		return this.adjacencyMatrix;
	}
	
	private int fFEP(){
		/**
		 * fFEP
		 * 
		 * find first empty place
		 * 
		 * @return place of the first empty element; If -1 List is full.
		 */
		for (int i = 0; i < this.nodes.length; i++){
			if (this.nodes[i]){
				return i;
			}
		}
		return -1;
	}
	
	
	
	
}
