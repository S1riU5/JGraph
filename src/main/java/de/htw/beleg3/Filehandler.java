package de.htw.beleg3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import  java.io.BufferedReader.*;
//http://www.tutorials.de/java/197375-daten-aus-einer-excel-oder-csv-datei-eine-jtable-auslesen.html

public class Filehandler {
	Writer fw = null;
	FileReader fr;
	BufferedReader br;

	public Filehandler() {

	}
	
	public void saveGraphCSV(String[] nodes, int[][] adjMat){
		String printline = "";
		writeOpen("save.csv");
		for (int i = 0; i < adjMat.length; i++){
			if (!nodes[i].equals(""))
				printline = nodes[i];
			else{
				continue;
			}
			for (int j = 0; j < adjMat[i].length; j++){
				if (adjMat[i][j] >= 0){
					printline += (";" + j + "," + adjMat[i][j]);
				}
			}
			write(printline);
		}
		save();
	}
	
	public void openGraphCSV(){
		String line;

			try {
				fr = new FileReader("save.csv");
				br = new BufferedReader(fr);
				while (true){
					line = br.readLine();
					if (line == null){
						break;
					}
					System.out.printf("%s\n", line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();

			
		}
	}
	
	private void writeOpen(String filename){
		try {
			fw = new FileWriter(filename);
			fw.write("##  \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void write(String line){
		try{
			fw.append(line + "\n");
		}
		catch ( IOException e){
			e.printStackTrace();;
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
