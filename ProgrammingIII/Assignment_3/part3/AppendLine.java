package part3;

//CT326 - Programming III
//Author - Michael Neely 13100590

import java.io.IOException;
import java.io.RandomAccessFile;

/* Uses a RandomAccessFile object to open an ordinary text file 
and then append an extra line of content onto the end of the file */

public class AppendLine {
	
	//Open Random Access File and Append a Line of Text to It
	public static void append(String filename) throws IOException{
		RandomAccessFile raf = new RandomAccessFile(filename, "rw"); 
		//Skip to end of file
		raf.skipBytes((int) raf.length());
		//Write out string
		raf.writeBytes("\nModified by AppendLine.java :)\nYou've been hacked by the NSA :P");
		//Close file
		raf.close();
		//Prove Success
		System.out.println("File Successfully Modified");
	}
	
	//Test append function
	public static void main(String[] args) throws IOException {
		append("heythere.txt");
	}
}
