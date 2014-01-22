package de.htw.beleg3;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//http://www.tutorials.de/java/197375-daten-aus-einer-excel-oder-csv-datei-eine-jtable-auslesen.html

public class Filehandler {
	Writer fw = null;

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
		}
		catch ( IOException e){
			e.printStackTrace();
		}
	}

}
