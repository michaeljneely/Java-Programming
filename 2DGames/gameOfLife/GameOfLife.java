package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameOfLife extends JFrame implements Runnable, MouseListener, MouseMotionListener {

	/** Member Variables **/
	//Window
	public static final Dimension WINDOW_SIZE = new Dimension(800,800);
	//Booleans
	private boolean readyToPaint = false; //Ready to start paint() or not
	private boolean inPlayState = false; //Game states - playing (true) or not playing (false)
	//Buffer
	private BufferStrategy strategy;
	//Cells
	private final int COLUMNS = 40;
	private final int ROWS = 40;
	private boolean cells[][][] = new boolean[ROWS][COLUMNS][2];
	//Strings for Buttons
	private String start = "Start";
	private String stop = "Stop";
	private String random = "Random";
	private String save = "Save";
	private String load = "Load";
	private String clear = "Clear";
	//Font
	public Font buttonFont = new Font("Dialog", Font.PLAIN, 24);
	//Percent Chance for cells to be alive on random board generation
	private final double percent = 0.15;
	//Old Mouse Point
	private Point old = new Point(-1,-1); //initialized
	//File Name of Save File
	private String filename = "U:\\lifegamefile.txt";

	/** Constructor **/
	public GameOfLife() {
		//Window Setup
		this.setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WINDOW_SIZE.width/2;
		int y = screensize.height/2 - WINDOW_SIZE.height/2;
		setBounds(x, y, WINDOW_SIZE.width, WINDOW_SIZE.height);
		setVisible(true);

		//Initialize cells to false;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				cells[i][j][0] = false;
				cells[i][j][1] = false;
			}
		}

		//Add Mouse Listeners
		addMouseListener(this);
		addMouseMotionListener(this);

		//Add Buffer Strategy
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		//Thread Setup
		Thread t = new Thread(this);
		t.start();

		//Ready to Start Painting
		readyToPaint = true;
	}

	/** Main **/
	public static void main(String args[]){
		@SuppressWarnings("unused")
		GameOfLife gol = new GameOfLife(); //Make new instance of game
	}

	/** Methods **/
	public void mousePressed(MouseEvent m) {

		//Get Point Clicked
		Point p = m.getPoint();
		int x = (int) p.getX();
		int y = (int) p.getY();

		//If Start/Stop Button Clicked -> Switch States and Return
		if (x >= 20 && x <=120 && y >= 40 && y <= 80){
			inPlayState = !inPlayState; //switch play states
			return;
		}

		//If random clicked -> Generate Random Board and Return
		if (!inPlayState){
			if(x >= 140 && x <= 240 && y >= 40 && y <= 80){
				randomState();
				return;
			}
		}

		//If clear clicked -> Clear Board
		if (!inPlayState){
			if (x >= 440 && x <= 540 && y >= 40 && y <= 80){
				clearBoard();
				return;
			}
		}

		//If Save Clicked -> Save Game to .txt file
		if (!inPlayState){
			if(x >= 560 && x <= 660 && y >= 40 && y <= 80){

				//Create Output Text
				int[][] outputText = new int[ROWS][COLUMNS];
				for(int i = 0; i < ROWS; i++){
					for(int j = 0; j < COLUMNS; j++){
						outputText[i][j] = (cells[i][j][0] == true) ? 1 : 0;
					}
				}

				//Write Output Text
				try{
					BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
					for(int i = 0; i < ROWS; i++){
						for(int j = 0; j < COLUMNS; j++){
							writer.write(outputText[i][j]+"");
						}
					}
					writer.close();
				}
				catch(IOException e) { }
				return;
			}
		}

		//If Load Clicked -> Load game from .txt file
		if(!inPlayState){
			if(x >= 680 && x <= 780 && y >= 40 && y <= 80){
				//Read File
				String inputText = "";
				try{
					BufferedReader reader = new BufferedReader(new FileReader(filename));
					inputText = reader.readLine();
					reader.close();
				}
				catch(IOException e) { }

				//Update Array
				for(int i = 0; i < ROWS; i++){
					for(int j = 0; j < COLUMNS; j++){
						//Pos of Cell in String
						int pos = (40*i) + j;
						//Get Correct Substring
						String a = inputText.substring(pos,pos+1);
						//Set Cell Value
						cells[i][j][0] = cells[i][j][1] = a.equals("1") ? true : false;
					}
				}
				//Repaint
				this.repaint();
				return;
			}
		}

		//Get coordinates in boolean array and set cell to opposite if clicked, then pause game
		int i = (int)(x / 20);
		int j = (int)(y / 20);
		cells[i][j][0] = !cells[i][j][0];
		inPlayState = false;

		//Force Repaint
		this.repaint();

		//Update Old Point
		old = p;
	}

	public void mouseDragged(MouseEvent m) {
		Point p = m.getPoint();
		//Get Current X and Y
		int x = ((int) p.getX())/20;
		int y = ((int) p.getY())/20;
		//Check if mouse dragged in window
		boolean inWindow = (x < 40 && x >= 0 && y < 40 && y >= 0);
		//Get Old X and Y
		int oldX = ((int) old.getX())/20;
		int oldY = ((int) old.getY())/20;
		//If the mouse has been dragged to another cell it is okay to work on the old cell
		//This prevents flickering
		if ((x!= oldX || y != oldY) && inWindow){
			//Toggle Cell
			cells[x][y][0] = !cells[x][y][0];
			//Stop Game
			inPlayState = false;
			//Force Repaint
			this.repaint();
			//Update Old Point
			old = p;
		}
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			//If playing -> simulate
			if(inPlayState){
				simulate();
			}
			this.repaint();
		}
	}

	public void paint(Graphics g){
		if (readyToPaint) { //If ready for painting

			//Draw to offscreen buffer
			g = strategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0,0,WINDOW_SIZE.width,WINDOW_SIZE.height);

			//Paint True Cells
			for(int i = 0; i < ROWS; i++){
				for(int j = 0; j < COLUMNS; j++){
					if(cells[i][j][0] == true){
						g.setColor(Color.WHITE);
						g.fillRect(i*20, j*20,20,20);
					}
				}
			}

			//Draw Start/Stop Button Rectangle
			g.setColor(Color.GREEN);
			g.fillRect(20,40,100,40);
			g.setFont(buttonFont);
			g.setColor(Color.BLACK);
			FontMetrics buttonFM  = g.getFontMetrics(buttonFont);

			if(inPlayState){
				//Draw Stop Text
				Rectangle2D fontRect = buttonFM.getStringBounds(stop, g);
				int textHeight = (int)(fontRect.getHeight());
				int textWidth = (int)(fontRect.getWidth());
				g.drawString(stop, 20+(100-textWidth)/2, 40+(40-textHeight/2));
			}

			else if (!inPlayState){
				//Draw Start Text
				Rectangle2D fontRect = buttonFM.getStringBounds(start, g);
				int textHeight = (int)(fontRect.getHeight());
				int textWidth = (int)(fontRect.getWidth());
				g.drawString(start, 20+(100-textWidth)/2, 40+(40-textHeight/2));

				//Draw Random Button
				g.setColor(Color.GREEN);
				g.fillRect(140,40,100,40);
				g.setColor(Color.BLACK);
				fontRect = buttonFM.getStringBounds(random, g);
				textHeight = (int)(fontRect.getHeight());
				textWidth = (int)(fontRect.getWidth());
				g.drawString(random, 140+(100-textWidth)/2, 40+ (40-textHeight/2));

				//Draw Clear Button
				g.setColor(Color.GREEN);
				g.fillRect(440,40,100,40);
				g.setFont(buttonFont);
				g.setColor(Color.BLACK);
				fontRect = buttonFM.getStringBounds(clear, g);
				textHeight = (int)(fontRect.getHeight());
				textWidth = (int)(fontRect.getWidth());
				g.drawString(clear, 440+(100-textWidth)/2, 40+ (40-textHeight/2));

				//Draw Save Button
				g.setColor(Color.GREEN);
				g.fillRect(560,40,100,40);
				g.setFont(buttonFont);
				g.setColor(Color.BLACK);
				fontRect = buttonFM.getStringBounds(save, g);
				textHeight = (int)(fontRect.getHeight());
				textWidth = (int)(fontRect.getWidth());
				g.drawString(save, 560+(100-textWidth)/2, 40+ (40-textHeight/2));

				//Draw Load Button
				g.setColor(Color.GREEN);
				g.fillRect(680,40,100,40);
				g.setFont(buttonFont);
				g.setColor(Color.BLACK);
				fontRect = buttonFM.getStringBounds(load, g);
				textHeight = (int)(fontRect.getHeight());
				textWidth = (int)(fontRect.getWidth());
				g.drawString(load, 680+(100-textWidth)/2, 40+ (40-textHeight/2));		
			}

			//Flip the buffers
			g.dispose();
			strategy.show();
		}
	}

	//Generates a random board
	public void randomState(){
		//Clear board
		clearBoard();

		//Randomly set cell booleans based on defined percentage
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				if(Math.random() < percent) cells[i][j][0] = !cells[i][j][0];			
			}
		}
	}

	//Clear Board
	public void clearBoard(){
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				cells[i][j][0] = false;
				cells[i][j][1] = false;
			}
		}
	}

	//Simulates a Generation
	public void simulate(){
		int above; // number of rows above row i
		int below; // number of rows below row i
		int left; // number of columns left of column j 
		int right; // number of columns right of column j
		for (int i = 0; i < ROWS; i++ ) {
			//Number of rows above i is i-1 unless i is the first row
			above = (i > 0) ? i-1 : ROWS-1; //Or 0 -> No wraparound
			//Number of rows below i is i+1 unless i is the last row
			below = (i < ROWS-1) ? i+1 : 0;
			for (int j = 0; j < COLUMNS; j++ ) {
				//Number of columns left of j is j-1 unless j is the first column
				left =  (j > 0) ? j-1 : COLUMNS-1; ////Or 0 -> No wraparound
				//Number of columns right of j is j+1 unless j is the last column
				right = (j < COLUMNS-1) ? j+1 : 0;
				//Count number of cells alive around the current cell
				int count = 0;
				if (cells[above][left][0])
					count++;
				if (cells[above][j][0])
					count++;
				if (cells[above][right][0])
					count++;
				if (cells[i][left][0])
					count++;
				if (cells[i][right][0])
					count++;
				if (cells[below][left][0])
					count++;
				if (cells[below][j][0])
					count++;
				if (cells[below][right][0])
					count++;

				//State of current cell
				boolean living = cells[i][j][0];
				//The result of applying the rules
				boolean result = false;

				//Rule 1
				if (living && count < 2)
					result = false;
				//Rule 2
				if (living && (count == 2 || count == 3))
					result = true;
				//Rule 3
				if (living && count > 3)
					result = false;
				//Rule 4
				if (!living && count == 3)
					result = true;

				//Set State
				cells[i][j][1] = result;
			}
		}

		//Copy Back Buffer to Front
		for(int i = 0; i < ROWS; i++){
			for(int j = 0 ; j < COLUMNS; j++){
				cells[i][j][0] = cells[i][j][1];
			}
		}
	}

	/** Unimplemented Mouse Methods **/

	public void mouseClicked(MouseEvent m) {
		//Unimplemented
	}
	public void mouseEntered(MouseEvent m) {
		//Unimplemented
	}
	public void mouseExited(MouseEvent m) {
		//Unimplemented
	}
	public void mouseReleased(MouseEvent m) {
		//Unimplemented
	}
	public void mouseMoved(MouseEvent m) {
		//Unimplemented	
	}
}
