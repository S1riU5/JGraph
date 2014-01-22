package de.htw.beleg3;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Filehandler {
	Writer fw = null;

	public Filehandler() {

	}
	
	public void writeOpen(String filename){
		try {
			fw = new FileWriter(filename);
			fw.write("##  \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(String line){
		try{
			fw.append(line + "\n");
		}
		catch ( IOException e){
			e.printStackTrace();;
		}
	}
	
	public void save(){
		try{
			fw.close();
		}
		catch ( IOException e){
			e.printStackTrace();
		}
	}

}
