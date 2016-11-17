package assignment9a;

import java.io.Serializable;

//Author: Michael Neely 13100590 3BCT
//CT 236 Assignment 9a - November 11th, 2016

@SuppressWarnings("serial")
public class CustomFile implements Serializable {

	// Members
	private String filename;
	private String source;
	private String destination;
	private long filesize; // in bytes
	private byte[] filedata; // data of file (byte array)

	// Constructor
	public CustomFile(String name, long size, byte[] data, String src, String dest) {
		this.filename = name;
		this.filesize = size;
		this.filedata = data;
		this.source = src;
		this.destination = dest;
	}

	// Getters and Setters
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public byte[] getData() {
		return filedata;
	}

	public void setData(byte[] data) {
		this.filedata = data;
	}
}
