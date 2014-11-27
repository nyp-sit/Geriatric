package entity;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;


public class Settings {

	

	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main (String [] args) throws Exception, IOException{
		
	}
		

	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public String getUsername(){
		return "";
	}
	public String getSecret(){
		return "";
	}
	public String getIP(){
		return "";
	}
	
	

	//////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static void replaceUsername (String newLine) throws IOException{
		Writer output;
		output = new BufferedWriter(new FileWriter("C:\\Project\\appointments.txt", true));
		output.append("\n");
		output.append(newLine);
		output.close();
	}
	
	
	public static void replaceSecretKey (String newLine) throws IOException{
		Writer output;
		output = new BufferedWriter(new FileWriter("C:\\Project\\appointments.txt", true));
		output.append("\n");
		output.append(newLine);
		output.close();
	}
	
	
	public static void replaceIP (String newLine) throws IOException{
		Writer output;
		output = new BufferedWriter(new FileWriter("C:\\Project\\appointments.txt", true));
		output.append("\n");
		output.append(newLine);
		output.close();
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	

	
	public static int countLines() throws Exception{
		// Count the number of lines
		
				String inputFileName = "C:\\Project\\appointments.txt";
				
				File inputFile = new File(inputFileName);
				
				FileReader fr = new FileReader(inputFile);
			    LineNumberReader lnr = new LineNumberReader(fr);
			    int linenumber = 0;
		        while (lnr.readLine() != null){
		        	linenumber++;
		            }
		        System.out.println("Total number of lines :" + linenumber);
				fr.close();
				lnr.close();
		        
		        
		        return linenumber;
		
	}
	
	

	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
}
