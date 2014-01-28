package de.htw.beleg3;

import static java.lang.Math.random;
import static java.lang.System.out;

public class Graph {
	/**
	 * class Graph
	 */
	private int[][] adjacencyMatrix;
	private String[] nodes;
	private Filehandler fh;
	private Searcher sebSearcher = new Searcher();
	
	public Graph(int MAX){
		/**
		 * Graph()
		 * 
		 * Constructor of the Graph.
		 */
		
		adjacencyMatrix = new int[MAX][MAX];
		nodes = new String[MAX];
		
		emptyDataSet();
		// ---
		//Testcase
		this.addNode("test1");
		this.addNode("test2");
		this.addNode("test3");
		this.addEdge(0,1,45);
		
		//permRand(50,50,30); // nodes, edges, maxValue
		// Printing
		printGraph();
	    // ------
		
		fh = new Filehandler();
		// Save data into file
		fh.saveGraphCSV(nodes, adjacencyMatrix);
		// load data from file
		fh.loadData();
		nodes = fh.loadNodes();
		adjacencyMatrix = fh.loadEdges();
		
		printGraph();
		boolean[] nodelistvisited = new boolean[nodes.length]; 
		for (int i = 0; i < this.nodes.length; i++){
			nodelistvisited[i] = false;
		}
		out.printf("%s\n", sebSearcher.DFS(this.adjacencyMatrix, nodelistvisited, 0, 1, 0));


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
			this.nodes[i] = "";
			for (int j = 0; j < this.adjacencyMatrix[0].length; j++){
				this.adjacencyMatrix[i][j] = -1;
			}
		}
	}
	
	private void permRand(int nodes, int edges, int maxValue){
		/**
		 * permRand()
		 * 
		 * Randomized (permutative) Graph.
		 * 
		 * @param (int) nodes  value of nodes
		 * @param (int) edges  value of edges
		 * @param (int) maxValue  maximal posible value of the edges.
		 * 
		 * 
		 */
		int rand1, rand2, rand3;
		for (int i = 0; i<nodes; i++){			
			addNode(String.valueOf(i));
		}
		for (int i = edges; i > 0; i--){
			rand1 = randInt(0, nodes-1);
			rand2 = randInt(0, nodes-1);
			rand3 = randInt(1, maxValue);
			
			if (	rand1 == rand2 || 
					this.adjacencyMatrix[rand1][rand2] >= 0){
				i++;
				continue;
			}
			addEdge(rand1, rand2, rand3);
		}
	}
	
	public void printGraph(){
		boolean[] edge;
		String outpt;
		
		for (int i = 0; i < nodes.length; i++){
			if (!nodes[i].equals("")){
				outpt = this.nodes[i];
				outpt += "\t-> \t(";
				edge = getEdgesOfNode(i);
				for (int j = 0; j<edge.length; j++){
					if (edge[j]){
						outpt += (nodes[j] + "["+this.getEdgeValue(i, j)+"], ");
					}
				}
				outpt += ")\n";
				System.out.printf("%s", outpt);
			}
		}
	}
	
    private int randInt( int low, int high ){
        /**
         * randInt
         * 
         * Simple Dice-Method to get randomized Integer inside 
         * freely selectable range.
         * 
         * @param int low        lowest possible Value
         * @param int high        highest possible Value
         * @return int Randomized Integer
         */
        return low + (int)(random() * ((high - low) + 1));
    }

	public int  addNode(String name){
		/**
		 * addNode()
		 * 
		 * Adds a node at first empty place to the entire dataset.
		 * @return (int) place were node got saved.
		 */
		//No Empty name
		// Little hack to get them whitespace
		if (name.length() <= 0){
			throw new IllegalArgumentException("Empty names not allowed!");
		}
		if (exists(name)){
			throw new IllegalArgumentException("Name already exists!");
		}
		int val = fFEP();
		this.nodes[val] = name; 
		return val;
	}
	
	private boolean exists(String elem){
		for (int i = 0; i < nodes.length; i++){
			if (nodes[i].equals(elem)){
				return true;
			}
		}
		return false;
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
		if (this.nodes[x].length() <= 0 || this.nodes[y].length() <= 0) {
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
	
	public String[] getNodes(){
		return this.nodes;
	}
	
	public String getNodeName(int x){
		return this.nodes[x];
	}
	
	public int[][] getEdges(){
		return this.adjacencyMatrix;
	}
	
	public int getEdgeValue(int x, int y){
		return this.adjacencyMatrix[x][y];
	}
	
	public int getNumerOfNodes(){
		int val = 0;
		for (int i = 0; i < this.nodes.length; i++){
			if (!this.nodes[i].equals("")){
				val++;
			}
		}
		return val;
	}
	
	private boolean[] getEdgesOfNode(int node){
		boolean[] listOfNodes = new boolean[this.nodes.length];
		for (int i = 0; i < listOfNodes.length; i++)
			listOfNodes[i] = false;
		for (int i = 0; i < this.adjacencyMatrix[node].length; i++){
			if (this.adjacencyMatrix[node][i] >= 0){
				listOfNodes[i] = true;
			}
		}
		// Sehe nach in Matritze
		// Wenn Node verbindung => trage in Liste ein
		return listOfNodes;
	}
	
	public void deleteNode(int node){
		for (int i = 0; i < this.adjacencyMatrix[node].length; i++){
			this.adjacencyMatrix[node][i] = -1;
			this.adjacencyMatrix[i][node] = -1;
		}
		nodes[node] = "";
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
			if (this.nodes[i].length() <= 0){
				return i;
			}
		}
		return -1;
	}
	
}
