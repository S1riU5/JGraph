package de.htw.beleg3;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.File.*;
import java.io.DataOutput;
import java.io.DataInput;

public class Filehandler {
	
	public Filehandler(String path) throws IOException{
		
		Writer fw = null;
		
		try{
			fw = new FileWriter("fileWriter.txt");
			fw.write("Zwei Jäger treffen sich");
			fw.append("\n");
		}
		catch ( IOException e){
			System.err.println(e.getStackTrace());
		}
		finally {
			if ( fw != null){
				try {fw.close(); } catch (IOException e) { }
			}
		}
	}

}
