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
	private boolean[] activeNodes;
	private Searcher se;
	private Integer[] path;
	
	public Graph(int MAX){
		/**
		 * Graph()
		 * 
		 * Constructor of the Graph.
		 */
		
		adjacencyMatrix = new int[MAX][MAX];
		nodes = new String[MAX];
		se = new Searcher();
		fh = new Filehandler();

		
		emptyDataSet();
		// ---
		//Testcase
//		permRand(10,3,30); // nodes, edges, maxValue
//		printGraph();
//	    // ------
//		addNode("A");//0
//		addNode("B");//1
//		addNode("C");//2
//		addNode("D");//3
//		addNode("E");//4
//		addNode("F");//5
//		addNode("G");//6
//		addNode("H");//7
//		addNode("I");//8
//		addNode("J");//9
//		addNode("K");//10
//		addNode("L");//11
//		
//		addEdge(0 , 1, 850);
//		addEdge(0 , 3, 500);
//		addEdge(1 , 2, 550);
//		addEdge(1 , 5, 250);
//		addEdge(2 , 6, 150);
//		addEdge(3 , 4, 550);
//		addEdge(3 , 8, 200);
//		addEdge(4 , 5, 200);
//		addEdge(4 , 9, 250);
//		addEdge(5 , 6, 550);
//		addEdge(5 , 9, 100);
//		addEdge(6 , 10, 200);
//		addEdge(7 , 8, 550);
//		addEdge(8 , 9, 200);
//		addEdge(8 , 11, 450);
//		addEdge(9 , 10, 500);
//		addEdge(9 , 7, 200);
//		addEdge(10 , 11, 150);
		
		
		//Zeichenbeispiel
		
		addNode("A");//0
		addNode("B");//1
		addNode("C");//2
		addNode("D");//3
		addNode("E");//4
		addNode("F");//5
		addNode("G");//6
		addNode("H");//7
		addNode("I");//8
		addNode("J");//9
		addNode("K");//10
	
		
		addEdge(0 , 1, 21);
		addEdge(1 , 3, 8);
		addEdge(4 , 6, 23);
		addEdge(6 , 10, 20);
		addEdge(7 , 8, 8);
		addEdge(8 , 9, 5);
		addEdge(9 , 4, 30);
		addEdge(0 , 9, 15);
		addEdge(5 , 2, 3);
		addEdge(8 , 7, 3);
		addEdge(8, 1, 10);
		addEdge(6 , 0, 10);
		addEdge(10 , 4, 20);
		addEdge(6 , 3, 16);
		addEdge(2 , 10, 19);





//		// Save data into file
//		fh.saveGraphCSV(nodes, adjacencyMatrix);
//		// load data from file
//		fh.loadData();
//		nodes = fh.loadNodes();
//		adjacencyMatrix = fh.loadEdges();
		
//		printGraph();
//		boolean[] nodelistvisited = new boolean[nodes.length]; 
//		for (int i = 0; i < this.nodes.length; i++){
//			nodelistvisited[i] = false;
//		}

		// Aufruf backtrack (target,  path from dms(adjMat, startpoint, target))
		//path = se.backtrack(3, se.dms(this.adjacencyMatrix, 2, 3));
		
		//path = se.dijk(this.adjacencyMatrix, 4, 8);
		
//		for (int i = 0; i<path.length; i++){
//			if (path[i] == null)
//				break;
//		 	System.out.printf("-- %s -- \t",  path[i]);
//		}
//		System.out.println();
		
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
	
	public void permRand(int nodes, int edges, int maxValue){
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
		emptyDataSet();
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
	
	public boolean exists(String elem){
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
	public int[][] getAdjMat(){
		return adjacencyMatrix;
	}
	
	public boolean[] getEdgesOfNode(int node){
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
	
	public boolean[] getActiveNodes(){
		activeNodes = new boolean[nodes.length];
		for (int i = 0; i < activeNodes.length; i++){
			if (nodes[i].equals("")){
				activeNodes[i] = false;
			}
			else{
				activeNodes[i] = true;
			}
		}
		return activeNodes;
	}
	
	public void save(){
	    //FIXME java.lang.NullPointerException
			fh.saveGraphCSV(nodes, adjacencyMatrix);
		
	}
	
	public void load(){
	    fh.loadData();
	    nodes = fh.loadNodes();
        adjacencyMatrix = fh.loadEdges();
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
