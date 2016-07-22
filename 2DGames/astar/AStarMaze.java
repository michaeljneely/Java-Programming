package astar;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AStarMaze extends JFrame implements Runnable, MouseListener, MouseMotionListener, KeyListener {

	/** Member Variables **/
	public static final int NUM_ENEMIES = 1;
	//Window
	public static final Dimension WINDOW_SIZE = new Dimension(800,800);
	//Booleans
	private boolean readyToPaint = false; //Ready to start paint() or not
	private boolean inPlayState = false; //Game states - playing (true) or not playing (false)
	//Buffer
	private BufferStrategy strategy;
	//File Path
	private final String FilePath = "C:\\Users\\I330364\\workspace\\Game\\src\\main\\";
	//Characters
	private BadGuy[] badguys = new BadGuy[NUM_ENEMIES];
	private Player player;
	ImageIcon badguyIcon = new ImageIcon(FilePath+"badguy.png");
	ImageIcon playerIcon = new ImageIcon(FilePath+"player.png");
	
	//Map
	public static final int X_DIMENSION = 40;
	public static final int Y_DIMENSION = 40;
	private boolean[][] map = new boolean[X_DIMENSION][Y_DIMENSION];
	//Movement Tracking
	int prevx=-1, prevy=-1;
	Random random = new Random();
	
	/** Constructor **/
	public AStarMaze() {
		//Window Setup
		this.setTitle("A Star Pathfinding");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WINDOW_SIZE.width/2;
		int y = screensize.height/2 - WINDOW_SIZE.height/2;
		setBounds(x, y, WINDOW_SIZE.width, WINDOW_SIZE.height);
		setVisible(true);

		// load raster graphics and instantiate game objects
		
		start();
		
		//Add Mouse Listeners
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		//Add Buffer Strategy
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		//Thread Setup
		Thread t = new Thread(this);
		t.start();

		//Ready to Start Painting
		readyToPaint = true;
	}
	
	public int randInt(int min, int max){
		return random.nextInt(max - min) + min;
	}
	
	public void start(){
		Image badguyImage = badguyIcon.getImage();
		Image playerImage = playerIcon.getImage();
		
		for(int i=0; i < badguys.length; i++){
			int bx = randInt(0,800)/20;
			int by = randInt(0,800)/20;
			badguys[i] = new BadGuy(badguyImage, bx, by);
		}
		player = new Player(playerImage, 10, 35);

		//Set Up Map
		for(int a = 0; a < X_DIMENSION; a++){
			for(int b = 0; b < Y_DIMENSION; b++){
				map[a][b] = false;
			}
		}
	}
	
	/** Main **/
	public static void main(String args[]){
		@SuppressWarnings("unused")
		AStarMaze a = new AStarMaze(); //Make new instance of game
	}


	public void mouseDragged(MouseEvent e) {
		// determine which cell of the gameState array was clicked on
		// and make sure it has changed since the last mouseDragged event
		int x = e.getX()/20;
		int y = e.getY()/20;
		if (x!=prevx || y!=prevy) {
			// toggle the state of the cell
			map[x][y] = !map[x][y];
			// throw an extra repaint, to get immediate visual feedback
			this.repaint();
			// store mouse position so that each tiny drag doesn't toggle the cell
			prevx=x;
			prevy=y;
		}
	}


	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
			// was the click on the 'start button'?
			int x = e.getX();
			int y = e.getY();
			if (x>=15 && x<=85 && y>=40 && y<=70) {
				inPlayState = !inPlayState;
				return;
			}
		// determine which cell of the gameState array was clicked on
		x = e.getX()/20;
		y = e.getY()/20;
		// toggle the state of the cell
		map[x][y] = !map[x][y];
		// throw an extra repaint, to get immediate visual feedback
		this.repaint();
		// store mouse position so that each tiny drag doesn't toggle the cell
		// (see mouseDragged method below)
		prevx = x;
		prevy = y;
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// Keyboard events
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
			player.setXSpeed(-1);
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT)
			player.setXSpeed(1);
		else if (e.getKeyCode()==KeyEvent.VK_UP)
			player.setYSpeed(-1);
		else if (e.getKeyCode()==KeyEvent.VK_DOWN)
			player.setYSpeed(1);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
			player.setXSpeed(0);
		else if (e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN)
			player.setYSpeed(0);
	}

	public void run() {
		long loops = 0;
		while(true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			//If playing -> simulate
			if(inPlayState){
				loops++;
				player.move(map);
				if(loops % 3 == 0){
					for(BadGuy b : badguys){
						b.move(map, player.x, player.y);
						if(b.x == player.x && b.y == player.y){
							inPlayState = false;
							start();
						}
					}
				}
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
			// redraw the map
			g.setColor(Color.WHITE);
			for (int x=0;x<40;x++) {
				for (int y=0;y<40;y++) {
					if (map[x][y]) {
						g.fillRect(x*20, y*20, 20, 20);
					}
				}
			}
			// redraw the player and badguy
			// paint the game objects
			player.paint(g);
			for(BadGuy b: badguys){
				b.paint(g);
			}
			
			if (!inPlayState) { // game is not running..
				// draw a 'start button' as a rectangle with text on top
				// also draw 'load' and 'save' buttons
				g.setColor(Color.GREEN);
				g.fillRect(15, 40, 70, 30);
				g.setFont(new Font("Times", Font.PLAIN, 24));
				g.setColor(Color.BLACK);
				g.drawString("Start", 22, 62);
			}
			else {
				g.setColor(Color.GREEN);
				g.fillRect(15, 40, 70, 30);
				g.setFont(new Font("Times", Font.PLAIN, 24));
				g.setColor(Color.BLACK);
				g.drawString("Stop", 22, 62);
			}
			//Flip the buffers
			g.dispose();
			strategy.show();
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
