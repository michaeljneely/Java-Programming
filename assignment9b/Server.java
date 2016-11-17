package assignment9b;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//Author: Michael Neely 13100590 3BCT
//CT326 Assignment 9b - November 11th, 2016

public class Server {

	//Members
	private DatagramSocket s;
	private DatagramPacket receivedPacket, sentPacket;

	// Set Up DatagramSocket on Port 5000
	public Server() {
		try {
			s = new DatagramSocket(5000);
		} catch (SocketException e) {
			System.out.println("Error. Server Aborting...");
			System.exit(1);
		}
	}

	// Wait for packets to arrive from clients and display them before sending
	// a reply that echoes the data
	public void waitForPackets() {
		while (true) {
			try {
				//Set up packet
				byte[] data = new byte[1000];
				receivedPacket = new DatagramPacket(data, data.length);
				//Wait for packet
				s.receive(receivedPacket);
				//Display
				displayPacket();
				System.out.println("Reply Sent!");
				//Echo data received to client
				sendPacketToClient();
			} catch (IOException e) {
				System.exit(1);
			}
		}
	}

	//Print packet details
	private void displayPacket() {
		System.out.println("\nPacket received from client:" + "\nFrom host: " + receivedPacket.getAddress()
				+ "\nHost port: " + receivedPacket.getPort() + "\nLength: " + receivedPacket.getLength()
				+ "\nContaining:\n\t" + new String(receivedPacket.getData(), 0, receivedPacket.getLength())
				+ "\n Reply to Client...");
	}

	//Echo data received from client back in a new packet
	public void sendPacketToClient() {
		sentPacket = new DatagramPacket(receivedPacket.getData(), receivedPacket.getLength(),
				receivedPacket.getAddress(), receivedPacket.getPort());
		try {
			s.send(sentPacket);
		} catch (IOException e) {
			System.out.println("Error sending reply.");
		}
	}

	//Execute Server
	public static void main(String[] args) {
		Server app = new Server();
		app.waitForPackets();
	}
}