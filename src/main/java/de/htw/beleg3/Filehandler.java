package de.htw.beleg3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Filehandler {
	private Writer fw = null;
	private FileReader fr;
	private BufferedReader br;
	private String[] nodes;
	private int[][] edges;
	
	
	
	public Filehandler() {
		// ----
	}
	
	public void loadData(){
		this.loadGraphCSV();
	}
	
	public String[] loadNodes(){
		return nodes;
	}
	
	public int[][] loadEdges(){
		return edges;
	}
	
	public void saveGraphCSV(String[] nodes, int[][] adjMat){
		// First line have to begin with # 
		// and shows the size of the System
		// eg. #50 >>  50 nodes
		String printline = "#" + adjMat.length;  
		writeOpen("save.csv");
		write(printline);
		for (int i = 0; i < adjMat.length; i++){
			if (!nodes[i].equals(""))
				printline = nodes[i];
			else{
				continue;
			}
			for (int j = 0; j < adjMat[i].length; j++){
				if (adjMat[i][j] >= 0){
					printline += ("," + j + "," + adjMat[i][j]);
				}
			}
			write(printline);
		}
		save();
	}
	
	private void loadGraphCSV(){
		String file = "save.csv";
		String line;
		int maxValue = 0;
		int i;

			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				line = br.readLine();
				// First line begins with # 
				// and shows the size of the System
				// eg. #50 >>  50 nodes
				if (line.startsWith("#")){
					// cut out the # and read the number into an int
					maxValue = Integer.parseInt((Arrays.asList( Pattern.compile("#").split(line) ).toArray(new String[1])[1]));
				}
				else{
					throw new IllegalStateException("File corrupt!");
				}
				// Now we knew how big our system is
				// so we can build the dataset.
				nodes = new String[maxValue];
				edges = new int[maxValue][maxValue];
				
				for (i = 0; i < this.edges.length; i++ ){
					this.nodes[i] = "";
					for (int j = 0; j < this.edges[0].length; j++){
						this.edges[i][j] = -1;
					}
				}
				
				// Each line have to be parsed through 
				// a raw_data element
				int[] raw_data = new int[maxValue];
				
				i = 0;
				while (true){
					// read line after line
					line = br.readLine();
					// May this will cause problems..
					// TODO Are they null-lines followed by data-lines??
					if (line == null){
						break;
					}
					//System.out.println(parseLine(line));
					parseLine(line);
					i++;
					
				}
				System.out.printf("INFO:\tDataloading succeeded.\n");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			//intrprtData(data, maxValue);
	}
	

		
	private void parseLine(String data){
		
		
		List<String> bigdat;
		int[] dat;

		int i = 0;
	    int from_node = 0;
	    int to_node = 0;
	    
	    dat = new int[getNumOfElems(data)];
		bigdat = Arrays.asList(Pattern.compile(",").split(data));
	    Iterator itr = bigdat.iterator(); 
	    while(itr.hasNext()){
	    	String elem = (String) itr.next();
	    	
	    	// FIXME Parse data from string into dataset (fFEP does nasty things)
	    	
	    	// Ok! Ill now do solving the problem here
	    	// Think I could make it without the itrprt-methode
	    	
	    	if (i == 0){
	    		from_node = this.fFEP();
	    		nodes[from_node]  = elem;
	    	}
	    	else if(i % 2 != 0){
	    		to_node = Integer.parseInt(elem);
	    	}
	    	else{
	    		edges[from_node][to_node] = Integer.parseInt(elem);
	    	}
	    	i++;
	    }
	}
	
	private int getNumOfElems(String data){
		int val = 1; 
		for (int i = 0; i < data.length(); i++){
			if (data.charAt(i) == ',' ){
				val += 1;
			}
		}
		//System.out.println(""+ val);
		return val;
	}
	
	private void writeOpen(String filename){
		try {
			fw = new FileWriter(filename);
			//fw.write("##  \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void write(String line){
		try{
			fw.append(line + "\n");
		}
		catch ( IOException e){
			e.printStackTrace();
		}
	}
	
	private void save(){
		try{
			fw.close();
			System.out.printf("INFO:\tWriting information in file.\n" );

		}
		catch ( IOException e){
			e.printStackTrace();
		}
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
