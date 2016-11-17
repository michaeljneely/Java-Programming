package assignment8a;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Author: Michael Neely 13100590 3BCT
//CT 326 Assignment 9a - November 11th, 2016

//File Server
public class Server {

	// Sockets
	private static ServerSocket server;
	private static Socket client;
	// Array List of (String, File) Tuples to Store Files
	private final static List<Map.Entry<String, File>> files = new ArrayList<Map.Entry<String, File>>();

	public static void main(String[] args) {
		try {
			// Start Server on Local Host Port 5867
			server = new ServerSocket(5867, 0, InetAddress.getLocalHost());
			System.out.println(server.getInetAddress());
			System.out.println("Server Started");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Handle Client Connections
		while (true) {
			try {
				client = server.accept(); // accept connection from client
				System.out.println("Accepted Connection: " + client);
				// Create a new thread for each client
				ConnectionThread s = new ConnectionThread(client, files);
				s.start(); // start thread
				System.out.println("Thread Started...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
