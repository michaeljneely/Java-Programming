package assignment9a;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;


public class Client implements ActionListener {

	private String destPath = "C:\\Users\\I330364\\ReceivedJavaFiles\\";
	private String srcPath;
	private JFrame frame;
	private JPanel filepanel;
	private JTextField textField;
	private JTable table;
	private JFileChooser choose;
	private JButton uploadButton, download;
	private Socket s;
	private CustomFile cf, downloadedFile;
	DefaultTableModel dtm;

	//Constructor
	public Client() {
		connect();
		initialize();
	}

	private void connect() {
		try {
			s = new Socket("140.203.241.103", 5867 );
		} catch (UnknownHostException e) {
			System.out.println("Could not resolve host");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		
		JLabel lblFileServer = new JLabel("File Server");
		lblFileServer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_3.add(lblFileServer);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblFileList = new JLabel("Current File");
		lblFileList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblFileList);
		
		JPanel file_panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		frame.getContentPane().add(file_panel, gbc_panel);
		
			
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		
		JLabel lblNewLabel = new JLabel("File Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(30);
		
		choose = new JFileChooser();
		
		uploadButton = new JButton("Select File to Upload");
		uploadButton.addActionListener(this);
		panel_2.add(uploadButton);
		
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//Upload File
		if (e.getSource() == uploadButton) {
			int returnVal = choose.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	//Get File and Path
            	File file = choose.getSelectedFile();
            	if(file.isFile()) {
            		//Convert File type to CustomFile
            		srcPath = file.getAbsolutePath();
                    System.out.println("File Selected is: " + srcPath);
                    long length = file.length();
                    byte[] data = new byte[(int) length];
                    try{
                    	FileInputStream fis = new FileInputStream(file);
                    	fis.read(data);
                    } catch (FileNotFoundException ex) {
                    	System.out.println("File Not Found...");
                    } catch (IOException ex) {
                    	System.out.println("Error Reading File...");
                    }
                    cf = new CustomFile(file.getName(), length, data, srcPath, destPath);
                    upload(cf);
            	}
            }
		}
		//Download File
		else if (e.getSource() == download) {
			try {
			  ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); //get the input stream from the client
              CustomFile downloadedFile = (CustomFile) ois.readObject();
			} catch (IOException ex){
				System.out.println("Error Downloading File");
			} catch (ClassNotFoundException ex){
				System.out.println("Error Downloading File");
			}
		}
		
	}
	
	public void upload(CustomFile cf){
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(cf);
			System.out.println("File Successfully Sent");
		} catch (IOException e) {
			System.out.println("Error While Sending File");
		}
	}
	
	public void update(){
		while(true){
			try {
				  ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				  CustomFile temp = (CustomFile) ois.readObject();
				  refreshTable();
			} catch (ClassNotFoundException ex) {
            	System.out.println("File Not Found...");
            } catch (IOException ex) {
            	System.out.println("Error Reading File...");
            }
		}
	}
	
	public void refreshTable() {
		table.removeAll();
		download = new JButton("Download");
		download.addActionListener(this);
		filepanel.add(new JLabel(cf.getFilename()));
		filepanel.add(download);
	}

}
