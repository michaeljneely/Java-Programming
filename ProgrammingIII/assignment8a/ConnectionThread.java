package assignment8a;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//Author: Michael Neely 13100590 3BCT
//CT 326 Assignment 9a - November 11th, 2016

//Thread Established Between Client and Server
public class ConnectionThread extends Thread {

	// Store File Objects in a list of (File, Name) Tuples
	List<Map.Entry<String, File>> files;
	private File received, sent;
	private Socket s;
	// Store files in "Server" Folder
	private String storagePath = "C:\\Users\\Michael\\Desktop\\Server\\";

	// Streams
	private DataInputStream dis;
	private DataOutputStream dos;
	private ByteArrayOutputStream baos;
	private FileOutputStream fos;
	private FileInputStream fis;

	// Constructor - Set Tuple List and Socket
	public ConnectionThread(Socket s, List<Map.Entry<String, File>> a) {
		this.s = s;
		this.files = a;
	}

	//Get Command Strings From Client And perform corresponding action
	public void run() {
		while (true) {
			try {
				// Get command
				dis = new DataInputStream(s.getInputStream());
				String choice = dis.readUTF();
				if (choice != null) {
					// Client is uploading file
					if (choice.equals("upload")) {
						receive();
					}
					// Client wants to download a file
					else if (choice.equals("download")) {
						String name = dis.readUTF();
						send(name);
					}
					// Client wants the list of files on the server
					else if (choice.equals("list")) {
						listFiles();
					}
				}
			} catch (IOException e) {
			}
		}
	}

	// Receive Uploaded File From Client
	private void receive() {
		try {
			String name = dis.readUTF(); //get file name
			int length = dis.readInt(); // get file length
			//If data is coming in
			if (length > 0) {
				//Fill ByteArrayStream
				baos = new ByteArrayOutputStream();
				for (int i = 0; i < length; i++) {
					baos.write(dis.readByte());

				}
				System.out.println("File" + "\'" + name + "\' Received");
				//Convert Stream to Byte Array
				byte[] data = baos.toByteArray();
				//Write Bytes to File
				received = new File(storagePath + name);
				fos = new FileOutputStream(received);
				System.out.println("Writing " + data.length + "bytes");
				fos.write(data);
				baos.close();
				fos.close();
				//Create (Name, File) Tuple
				Map.Entry<String, File> temp = new AbstractMap.SimpleEntry<>(name, received);
				//Synchronize for Multi-Threaded Access
				files = (List<Entry<String, File>>) Collections.synchronizedList(files);
				synchronized (files) {
					files.add(temp); //Add Tuple to List
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Send A Named File to Client Upon Download Request
	private void send(String filename) {
		try {
			//Synchronize for Multi-Threaded Access
			files = (List<Entry<String, File>>) Collections.synchronizedList(files);
			Map.Entry<String, File> temp = null;
			synchronized (files) {
				int i = 0;
				//Get File with Matching Name by Iterating over List
				for (Iterator<Entry<String, File>> iter = files.iterator(); iter.hasNext(); i++) {
					if (iter.next().getKey().equals(filename)) {
						temp = files.get(i);
					}
				}
			}
			//If File was Found
			if (temp != null) {
				//Split Tuple into Name and File
				String name = temp.getKey();
				sent = temp.getValue();
				System.out.println("Sending File \'" + name + "\' To Client.");
				//Read All Bytes From File
				fis = new FileInputStream(sent.getAbsolutePath());
				byte[] data = Files.readAllBytes(sent.toPath());
				//Output Name + Length + Data
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(name);
				dos.writeInt(data.length);
				dos.write(data);
				fis.close();
				dos.flush();
				System.out.println("Sent " + data.length + "Bytes");
			} 
			//If File Not Found Return 404 Message
			else {
				System.out.println("File Not Found");
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF("404");
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Inform Client of the List of Files on the Server
	public void listFiles() {
		//Synchronize for Multi-Threaded Access
		files = (List<Entry<String, File>>) Collections.synchronizedList(files);
		System.out.println("Beginning to List Files");
		try {
			dos = new DataOutputStream(s.getOutputStream());
			String message = "";
			synchronized (files) {
				//Iterate over List and Build String of File names
				//separated by semicolon to be split on client retrieval
				Iterator<Entry<String, File>> iter = files.iterator();
				while (iter.hasNext()) {
					message += iter.next().getKey() + ";";
				}
				//If No Files on Server, Send 'No Files' Message
				if (message == "") {
					System.out.println("No Files On Server");
					message = "No Files";
				}
				System.out.println("sending: " + message);
				dos.writeUTF(message);
			}
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
