package assignment8a;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket s;
	private Socket c;
	private CustomFile uploadedFile;

	public ClientThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			upload();
		}

	}
	
	private void upload() {
		try {
			  ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			  oos.writeObject(uploadedFile);
		}catch (IOException e) {
            e.printStackTrace();
        }
	}

	/*
	public void download() {
		try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            downloadedFile = (CustomFile) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	*/
	public CustomFile getUploadedFile() {
		return this.uploadedFile;
	}

}
