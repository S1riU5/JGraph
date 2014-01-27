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
//http://www.tutorials.de/java/197375-daten-aus-einer-excel-oder-csv-datei-eine-jtable-auslesen.html

public class Filehandler {
	private Writer fw = null;
	private FileReader fr;
	private BufferedReader br;
	
	private String[] nodes;
	private int[][] edges;

	public Filehandler() {
		// ----
	}
	
	public String[] loadNodes(){
		this.loadGraphCSV();
		return nodes;
	}
	
	public int[][] loadEdges(){
		this.loadGraphCSV();
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
		int[] raw_data = null;
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
				
				// Each line have to be parsed through 
				// a raw_data element
				raw_data = new int[maxValue];
				
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
					raw_data = parseLine(line);
					intrprtData(raw_data, i);
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
	
	private void intrprtData(int[] data, int line){
		if (data == null){
			throw new IllegalStateException("No Data found!");
		}
		//First element into nodes.
		nodes[line] = "" + data[0];	
		for (int i = 1; i < data.length; i++){
			// The second and following elements are the edges.
			//FIXME  nullspam!
			edges[line][i] = data[i];	
			}
	}
		
	private int[] parseLine(String data){
		// TODO Parser
		// skip commentlines. They will be marked with # on the beginning of the line.
		// # 
		// Put first elements of each dataline to listofnodes, (*)then make the edges to the second element with the third element as value
		// if here line doesn't end goto (*)
		List<String> bigdat;
		int[] dat = new int[getNumOfElems(data)];
		bigdat = Arrays.asList(Pattern.compile(",").split(data));
	    Iterator itr = bigdat.iterator(); // here was the prob with data
	    int i = 0;
	    while(itr.hasNext()){
	    	String elem = (String) itr.next();
	    	dat[i] = Integer.parseInt(elem);
	    	i++;
	    }
		return dat;
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

}
