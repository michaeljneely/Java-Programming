package assignment9a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket server;
	private static Socket client;
	private static CustomFile file = null;
	
	public static void main(String[] args){
		try{
            server = new ServerSocket(5867);
            System.out.println(server.getInetAddress());
            System.out.println("Server Started");
        } catch (IOException e) {
            System.out.println("Server port already in use");
        }
		
		while(true){
            try{
                client = server.accept(); //accept connection from client
                ClientThread  s = new ClientThread(client); //create a new thread for each client
                s.start(); //start thread
                file = (CustomFile) s.getUploadedFile();
                System.out.println("Accepted Connection: " + client);
                System.out.println("File Succesfully Uploaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
