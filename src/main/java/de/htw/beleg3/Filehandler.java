package de.htw.beleg3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
//http://www.tutorials.de/java/197375-daten-aus-einer-excel-oder-csv-datei-eine-jtable-auslesen.html

public class Filehandler {
	Writer fw = null;
	FileReader fr;
	BufferedReader br;
	
	int[] nodes;
	int[][] edges;

	public Filehandler() {
		// ----
	}
	
	public void saveGraphCSV(String[] nodes, int[][] adjMat){
		String printline = "#" + adjMat.length; // Amounts of Slots 
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
	
	public void openGraphCSV(){
		String file = "save.csv";
		String line;
		int[][] data = null;
		int maxValue = 0;
		int i;

			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				line = br.readLine();
				if (line.startsWith("#")){
					maxValue = Integer.parseInt((Arrays.asList( Pattern.compile("#").split(line) ).toArray(new String[1])[1]));
				}
				else{
					throw new IllegalStateException("File corrupt!");
				}
				data = new int[maxValue][maxValue];
				
				i = 0;
				while (true){
					line = br.readLine();
			
					if (line == null){
						break;
					}
					//System.out.println(parseLine(line));
					i++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			intrprtData(data, maxValue);
	}
	
	private void intrprtData(int[][] data, int val){
		if (data == null){
			throw new IllegalStateException("No Data found!");
		}
		nodes = new int[val];
		edges = new int[val][val];
		
		for (int i = 0; i < data.length; i++){
			for (int j = 0; j < data[i].length; j++){
		         System.out.printf("%s\t", data[i][j]);
			}
			System.out.println();
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
			System.out.printf("Write information in file.\n" );

		}
		catch ( IOException e){
			e.printStackTrace();
		}
	}

}
