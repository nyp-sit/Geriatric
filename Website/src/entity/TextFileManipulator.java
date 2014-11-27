package entity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class TextFileManipulator {

	
	public static void main (String [] args) throws Exception, IOException{
		// TextFileManipulator.deleteFromID("3");
		// TextFileManipulator.addLine("ABCDEFG");
		

		 ArrayList<String> arrayList = TextFileManipulator.returnArrayList();
		 
		 System.out.println(arrayList.size());
		 for(int i=0;i<arrayList.size();i++){
			 System.out.println(i + "." + arrayList.get(i));
		 }
		 
		 
		 
		 
		 
		
	}
	
	
	public static void addLine (String newLine) throws IOException{
		Writer output;
		output = new BufferedWriter(new FileWriter("C:\\Project\\appointments.txt", true));
		output.append("\n");
		output.append(newLine);
		output.close();
	}
	
	
	
	
	
	
	
	
	
	public static ArrayList<String> returnArrayList() throws Exception{

		 Scanner scanner = new Scanner(new File("C:\\Project\\appointments.txt"));
		 ArrayList<String> arrayList = new ArrayList<String>();
		 
		 while (scanner.hasNext()){
			 arrayList.add(scanner.next());
		 }
		 scanner.close();
		 
		 return arrayList;
	}
	
	
	
	
	
	
	

	
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
	
	
	
	
	
	
	
	public static void deleteFromID(String lineIDToRemove) throws Exception{
		
		
		// Count the number of lines
		
		String inputFileName = "C:\\Project\\appointments.txt";
		String tempFileName = "C:\\Project\\appointmentsTemp.txt";
		
		File inputFile = new File(inputFileName);
		File tempFile  = new File(tempFileName);
		
		FileReader fr = new FileReader(inputFile);
	    LineNumberReader lnr = new LineNumberReader(fr);
	    int linenumber = 0;
        while (lnr.readLine() != null){
        	linenumber++;
            }
        System.out.println("Total number of lines :" + linenumber);
		
		
		// Now create a temporary file
		// Load all desired lines from original into temporary
		// Discard all unnecessary lines


		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


		String currentLine;

		while((currentLine = reader.readLine()) != null) {
		    // trim newline when comparing with lineToRemove
		    String trimmedLine = currentLine.trim();
		    
		    
		    String[] parts = trimmedLine.split("-");
		    String   splitID = parts[0];
		    
		    
		    // Compare only the id of the line
		    if(splitID.equals(lineIDToRemove)|| trimmedLine.equals("") || trimmedLine.equals(" ")|| trimmedLine.equals(null))	{continue;}
		    writer.write(currentLine + System.getProperty("line.separator"));
		}


		
		
		
		
		
		
		
		writer.close(); 
		reader.close(); 
		fr.close();
		lnr.close();
		
		
		
		
		// Delete the original file
		inputFile.delete();
		
		// Rename temp file back to original name
		tempFile.renameTo(inputFile);
		
		
		// We are done!!!
	}
	
	
	
}
