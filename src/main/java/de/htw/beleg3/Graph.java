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
		//Testcase
		int node1 = addNode();
		int node2 = addNode();
		int node3 = addNode();
		this.addEdge(node1, node2, 30);
		// ---
		// Printing
		System.out.printf("node1: %s\nnode2: %s\nedge: %s",
				node1, node2, this.getEdgeValue(node1, node2));
	    // ------

	}
	
	private void emptyDataSet(){
		/**
		 * emptyDataSet()
		 * 
		 * Init the Dataset with null-values.
		 * 
		 *  nodes = false
		 *  
		 *  adjacencyMatzrix = -1
		 */
		for (int i = 0; i < this.adjacencyMatrix.length; i++ ){
			this.nodes[i] = false;
			for (int j = 0; j < this.adjacencyMatrix[0].length; j++){
				this.adjacencyMatrix[i][j] = -1;
			}
		}
	}

	public int  addNode(){
		/**
		 * addNode()
		 * 
		 * Adds a node at first empty place to the entire dataset.
		 * @return (int) place were node got saved.
		 */
		int val = fFEP();
		this.nodes[val] = true; 
		return val;
	}
	
	public void addEdge(int x, int y, int value){
		/**
		 * addEdge()
		 * 
		 * Adds a Edge to the entire dataset.
		 * 
		 * @param (int) x  first node
		 * @param (int) y  second node
		 * @param (int) value  value of the edge 
		 */
		if (!this.nodes[x] || !this.nodes[y]) {
			throw new IllegalArgumentException("Unknown node!");
		}
		//TODO tryblock
		this.adjacencyMatrix[x][y] = value;
		this.adjacencyMatrix[y][x] = value;		
	}
	
	public void delEdge(int x, int y){
		//TODO tryblock
		this.adjacencyMatrix[x][y] = -1;
		this.adjacencyMatrix[y][x] = -1;
	}
	
	public boolean[] getNodes(){
		return this.nodes;
	}
	
	public int[][] getAdjancencyMatrix(){
		return this.adjacencyMatrix;
	}
	
	public int getEdgeValue(int x, int y){
		return this.adjacencyMatrix[x][y];
	}
	
	public boolean[] getEdgesOfNode(int node){
		boolean[] listOfNodes = new boolean[this.nodes.length];
		for (int i = 0; i<listOfNodes.length; i++){
			listOfNodes[i] = false;
		}
		// Sehe nach in Matritze
		// Wenn Node verbindung => trage in Liste ein
		return listOfNodes;
	}
	
	private int fFEP(){
		/**
		 * fFEP
		 * 
		 * find first empty place
		 * 
		 * @return place of the first empty element; If -1 list is full.
		 */
		for (int i = 0; i < this.nodes.length; i++){
			if (!this.nodes[i]){
				return i;
			}
		}
		return -1;
	}
	
	
	
	
}
