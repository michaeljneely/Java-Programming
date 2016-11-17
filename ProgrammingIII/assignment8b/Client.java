package assignment8b;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

//Author: Michael Neely 13100590 3BCT
//CT326 Assignment 9b - November 11th, 2016

public class Client {

	// Members
	private int port;
	private InetAddress address;
	private String name;
	private DatagramPacket receivedPacket, sentPacket;
	private DatagramSocket s;

	// Constructor
	public Client() {
		try {
			this.address = InetAddress.getLocalHost(); //set address to local host
			this.port = 5000; 
			this.s = new DatagramSocket();
		} catch (UnknownHostException e) {
			System.out.println("Couldn't get localhost.");
		} catch (SocketException e) {
			System.out.println("Couldn't bind socket.");
		}
	}

	// Send Packet
	public void send() throws IOException {
		byte[] data = name.getBytes();
		sentPacket = new DatagramPacket(data, data.length, address, port);
		displayPacketSent();
		s.send(sentPacket);
	}

	// Wait for reply packet to arrive from server and display it
	public void waitForReply() {
		while (true) {
			try {
				byte[] data = new byte[1000];
				receivedPacket = new DatagramPacket(data, data.length);
				s.receive(receivedPacket);
				System.out.println("Packet Received!");
				displayPacketReceived();
			} catch (IOException e) {
				System.out.println("Error receiving packet");
			}
		}
	}

	// Print sent packet details
	private void displayPacketSent() {
		System.out.println("\nPacket sent to server:" + "\nFrom host: " + sentPacket.getAddress() + "\nHost port: "
				+ sentPacket.getPort() + "\nLength: " + sentPacket.getLength() + "\nContaining:\n\t"
				+ new String(sentPacket.getData(), 0, sentPacket.getLength()));
	}

	// Print received packet details
	private void displayPacketReceived() {
		System.out.println("\nPacket received from server:" + "\nFrom host: " + receivedPacket.getAddress()
				+ "\nHost port: " + receivedPacket.getPort() + "\nLength: " + receivedPacket.getLength()
				+ "\nContaining:\n\t" + new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
	}

	// Get Name from Console
	private void getInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your Name: ");
		this.name = input.nextLine().trim();
		input.close();
	}

	// Execute Client
	public static void main(String[] args) {
		Client c = new Client();
		c.getInput();
		try {
			c.send();
			c.waitForReply();
		} catch (IOException e) {
			System.out.println("Execution Error.");
		}
	}
}
