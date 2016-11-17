package assignment8a;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

//Author: Michael Neely 13100590 3BCT
//CT 326 Assignment 9a - November 11th, 2016

//GUI Based Connection to Server For File Transfer
public class Client implements ActionListener {

	// Members
	private String name; // name of client
	private Socket s; // connection to server
	private File r; // File received on download

	// GUI Elements
	private GridBagLayout gridBagLayout;
	private GridBagConstraints gbctitle, gbcfilename, gbcupload, gbcdownload, gbcfilechooser, gbcfilelist,
			gbcstatuslabel, gbcstatustext;
	private JButton uploadButton, downloadButton, chooserButton, fileListButton;
	private JLabel clientNameLabel, fileNameLabel, statusLabel;
	private JTextArea status;
	private JTextField fileNameInput;
	private JFrame frame;
	private JFileChooser fileChooser;
	private JPanel titlePanel, fileNamePanel, uploadPanel, downloadPanel, fileChooserPanel, fileListPanel,
			statusLabelPanel, statusTextPanel;

	// Streams
	private ByteArrayOutputStream baos;
	private DataOutputStream dos;
	private DataInputStream dis;
	private FileOutputStream fos;
	private FileInputStream fis;

	// Constructor
	// Set Name, Launch GUI and Connect to Server
	public Client(String name) {
		this.name = name;
		initialize();
		connect();
	}

	// Connect to Server
	private void connect() {
		try {
			s = new Socket(InetAddress.getLocalHost().getHostName(), 5867);
			System.out.println(name + " Connected to Server");
			status.setText("Connected to File Server");
		} catch (UnknownHostException e) {
			System.out.println("Could not resolve host");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Launch GUI
	private void initialize() {

		// JFrame - 500 x 500 Window
		// Grid Bag Layout - 2x Columns by 6x Rows
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		// Title Panel
		titlePanel = new JPanel();
		gbctitle = new GridBagConstraints();
		gbctitle.gridwidth = 2;
		gbctitle.insets = new Insets(0, 0, 5, 0);
		gbctitle.fill = GridBagConstraints.BOTH;
		gbctitle.gridx = 0;
		gbctitle.gridy = 0;
		frame.getContentPane().add(titlePanel, gbctitle);
		clientNameLabel = new JLabel(name);
		clientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titlePanel.add(clientNameLabel);

		// File Name Panel
		fileNamePanel = new JPanel();
		gbcfilename = new GridBagConstraints();
		gbcfilename.gridwidth = 2;
		gbcfilename.insets = new Insets(0, 0, 5, 0);
		gbcfilename.fill = GridBagConstraints.BOTH;
		gbcfilename.gridx = 0;
		gbcfilename.gridy = 1;
		frame.getContentPane().add(fileNamePanel, gbcfilename);
		fileNameLabel = new JLabel("File Name:");
		fileNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fileNamePanel.add(fileNameLabel);
		fileNameInput = new JTextField();
		fileNamePanel.add(fileNameInput);
		fileNameInput.setColumns(30);

		// Upload Panel and Button
		uploadPanel = new JPanel();
		gbcupload = new GridBagConstraints();
		gbcupload.insets = new Insets(0, 0, 5, 5);
		gbcupload.fill = GridBagConstraints.BOTH;
		gbcupload.gridx = 0;
		gbcupload.gridy = 2;
		frame.getContentPane().add(uploadPanel, gbcupload);
		uploadButton = new JButton("Upload to Server");
		uploadButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uploadButton.addActionListener(this);
		uploadPanel.add(uploadButton);

		// Download Panel and Button
		downloadPanel = new JPanel();
		gbcdownload = new GridBagConstraints();
		gbcdownload.insets = new Insets(0, 0, 5, 0);
		gbcdownload.fill = GridBagConstraints.VERTICAL;
		gbcdownload.gridx = 1;
		gbcdownload.gridy = 2;
		frame.getContentPane().add(downloadPanel, gbcdownload);
		downloadButton = new JButton("Download From Server");
		downloadButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		downloadButton.addActionListener(this);
		downloadPanel.add(downloadButton);

		// JFileChooser Panel
		fileChooserPanel = new JPanel();
		gbcfilechooser = new GridBagConstraints();
		gbcfilechooser.anchor = GridBagConstraints.WEST;
		gbcfilechooser.insets = new Insets(0, 0, 5, 0);
		gbcfilechooser.gridwidth = 2;
		gbcfilechooser.fill = GridBagConstraints.VERTICAL;
		gbcfilechooser.gridx = 0;
		gbcfilechooser.gridy = 3;
		frame.getContentPane().add(fileChooserPanel, gbcfilechooser);
		chooserButton = new JButton("Choose File to Upload");
		chooserButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chooserButton.addActionListener(this);
		fileChooserPanel.add(chooserButton);

		// File List Button Panel
		fileListPanel = new JPanel();
		gbcfilelist = new GridBagConstraints();
		gbcfilelist.anchor = GridBagConstraints.EAST;
		gbcfilelist.insets = new Insets(0, 0, 5, 0);
		gbcfilelist.fill = GridBagConstraints.VERTICAL;
		gbcfilelist.gridx = 1;
		gbcfilelist.gridy = 3;
		frame.getContentPane().add(fileListPanel, gbcfilelist);
		fileListButton = new JButton("Get File List");
		fileListPanel.add(fileListButton);
		fileListButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		fileListButton.addActionListener(this);
		fileListPanel.add(fileListButton);

		// Status Label Panel
		statusLabelPanel = new JPanel();
		gbcstatuslabel = new GridBagConstraints();
		gbcstatuslabel.insets = new Insets(0, 0, 5, 0);
		gbcstatuslabel.gridwidth = 2;
		gbcstatuslabel.fill = GridBagConstraints.BOTH;
		gbcstatuslabel.gridx = 0;
		gbcstatuslabel.gridy = 4;
		frame.getContentPane().add(statusLabelPanel, gbcstatuslabel);
		statusLabel = new JLabel("Status");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		statusLabelPanel.add(statusLabel);

		// Status Text Panel
		statusTextPanel = new JPanel();
		gbcstatustext = new GridBagConstraints();
		gbcstatustext.gridwidth = 2;
		gbcstatustext.insets = new Insets(0, 0, 0, 5);
		gbcstatustext.fill = GridBagConstraints.BOTH;
		gbcstatustext.gridx = 0;
		gbcstatustext.gridy = 5;
		frame.getContentPane().add(statusTextPanel, gbcstatustext);
		status = new JTextArea();
		status.setRows(12);
		status.setColumns(40);
		statusTextPanel.add(status);

		// File Chooser
		fileChooser = new JFileChooser();

		frame.setVisible(true);
	}

	// Action Event Handler
	public void actionPerformed(ActionEvent e) {
		// If Upload Button Pressed - Upload File By Name
		if (e.getSource() == uploadButton) {
			// Get File Name
			String filename = fileNameInput.getText();
			// Get Path to That File
			Path p = Paths.get(filename);
			// Attempt to Upload File with Helper Function
			upload(p.toFile());
		}
		// Upload File with JFileChooser
		else if (e.getSource() == chooserButton) {
			int returnVal = fileChooser.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Get File and Path
				File file = fileChooser.getSelectedFile();
				// Attempt to upload file with helper function
				upload(file);
			}
		}
		// If Download Button Pressed - Download File By Name
		else if (e.getSource() == downloadButton) {
			// Get Filename From Input Area
			String filename = fileNameInput.getText();
			// Make sure user entered something
			if (filename == null || filename.equals("")) {
				status.setText("Please Enter a Filename");
				return;
			}
			// Attempt to download file with helper function
			System.out.println(name + " Asking for : " + filename);
			download(filename);
		}
		// If List Button Pressed - Get List of Files from Server
		else if (e.getSource() == fileListButton) {
			getFileList();
		}
	}

	// Helper Function to Upload File to Server
	private void upload(File f) {
		try {
			fis = new FileInputStream(f);
			// Convert file to byte array
			byte[] data = Files.readAllBytes(f.toPath());
			fis.close();
			dos = new DataOutputStream(s.getOutputStream());
			// Send Upload Command
			dos.writeUTF("upload");
			// Write Name + Length + Data
			dos.writeUTF(f.getName());
			dos.writeInt(data.length);
			dos.write(data);
			status.setText("File \'" + f.getName() + "\' Uploaded.");
			System.out.println("File \'" + f.getName() + "\' Uploaded.");
			dos.flush();
			fis.close();
		} catch (FileNotFoundException e) {
			status.setText("File Not Found. Try Again");
			System.out.println("File Not Found. Try Again");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Helper Function to Download a File From the Server
	private void download(String filename) {
		try {
			dos = new DataOutputStream(s.getOutputStream());
			// Send Download Command
			dos.writeUTF("download");
			dos.writeUTF(filename);
			dos.flush();
			dis = new DataInputStream(s.getInputStream());
			String name = dis.readUTF(); // Get Name
			// If 404 Error Display 'Not Found' in Text Area
			if (name.equals("404")) {
				System.out.println("File Not Found on Server");
				status.setText("File Not Found on Server");
				return;
			}
			// Else Receive File
			else {
				System.out.println(name + " Receiving File: \'" + name + "\'");
				// Get Length and Read that many Bytes into Stream
				int length = dis.readInt();
				if (length > 0) {
					baos = new ByteArrayOutputStream();
					for (int i = 0; i < length; i++) {
						baos.write(dis.readByte());
					}
					System.out.println(name + " Received a File");
					// Convert Stream to Byte Array
					byte[] data = baos.toByteArray();
					// Write File to Downloads Folder
					r = new File("C:\\Users\\Michael\\Downloads\\" + name);
					fos = new FileOutputStream(r);
					System.out.println(name + " Wrote " + data.length + "bytes to Downloads Folder");
					status.setText("Received \'" + name + "\' to " + r.getAbsolutePath());
					fos.write(data);
					baos.close();
					fos.close();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Get List of Files From the Server and Display in Text Area
	private void getFileList() {
		try {
			dos = new DataOutputStream(s.getOutputStream());
			// Send List Command
			dos.writeUTF("list");
			dos.flush();
			// Read Back List
			dis = new DataInputStream(s.getInputStream());
			String fileList = dis.readUTF();
			// If No Files
			if (fileList.equals("No Files")) {
				System.out.println("There Are No Files on the Server Currently");
				status.setText("No Files Yet");
				return;
			}
			// If There Are Files
			else {
				System.out.println(name + " Got File List.");
				// Split By Semicolon and Print to Status Area
				String[] files = fileList.split(";");
				String output = "Files:\n";
				for (String f : files) {
					output += f + "\n";
				}
				status.setText(output);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
