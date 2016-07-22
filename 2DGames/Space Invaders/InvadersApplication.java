package assignment4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InvadersApplication extends JFrame implements Runnable, KeyListener {

	//Window
	public static final Dimension WINDOW_SIZE = new Dimension(800,600);

	//Sprites
	public static final int NUM_ENEMIES_PER_ROW = 6;
	public static final int NUM_ROWS = 5;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>(NUM_ENEMIES_PER_ROW * NUM_ROWS);
	private Player player;

	//Buffer
	private BufferStrategy strategy;

	//Ready to start paint() or not
	private boolean readyToPaint = false;

	public InvadersApplication(){

		//Setup
		this.setTitle("Invaders 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WINDOW_SIZE.width/2;
		int y = screensize.height/2 - WINDOW_SIZE.height/2;
		setBounds(x, y, WINDOW_SIZE.width, WINDOW_SIZE.height);
		setVisible(true);

		//Add Key Listener
		addKeyListener(this);

		//Add Buffer Strategy
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		//Image Setup
		ImageIcon enemyIcon = new ImageIcon("C:\\Users\\Michael\\Pictures\\Misc\\panthers.png");
		ImageIcon playerIcon = new ImageIcon("C:\\Users\\Michael\\Pictures\\Misc\\seahawks.png");
		Image enemyImage = enemyIcon.getImage();
		Image playerImage = playerIcon.getImage();

		//Add Enemies to Screen in Grid (with buffer space in between) and set their speed
		for(int rows = 0; rows < NUM_ROWS; rows++){
			for(int cols = 0; cols < NUM_ENEMIES_PER_ROW; cols++){
				Enemy e = new Enemy(enemyImage, enemyImage.getWidth(null) + (cols * 50), enemyImage.getHeight(null) + (rows * 50), WINDOW_SIZE.width);
				enemies.add(e);
				e.setXSpeed(1);
			}
		}

		//Setup Player Sprite and Position
		player = new Player(playerImage, WINDOW_SIZE.width/2 - playerImage.getWidth(null)/2, WINDOW_SIZE.height - playerImage.getHeight(null), WINDOW_SIZE.width );

		//Thread Setup
		Thread t = new Thread(this);
		t.start();

		//Ready to Start Painting
		readyToPaint = true;
	}

	@Override
	public void run() {
		//Refresh Every 1/50th of a second
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			//Move all Enemy Sprites
			for(Enemy enemy : enemies){
				//If an enemy has collided with a window edge
				if(enemy.getCollided()){
					for(Enemy e : enemies){
						//reverse direction and set collided to false
						e.reverseDirection();
						e.setCollided(false);
					}
					break;
				}
				else enemy.move();
			}
			//Move Player Sprite
			player.move();
			this.repaint();
		}	
	}

	@Override
	public void paint(Graphics g){
		if (readyToPaint) { //If ready for painting
			g = strategy.getDrawGraphics();
			//Clear Screen by drawing a large white rectangle
			g.setColor(Color.WHITE);
			g.fillRect(0,0,WINDOW_SIZE.width,WINDOW_SIZE.height);
			//Paint all Enemy Sprites
			for(Enemy enemy : enemies){
				enemy.paint(g);
			}
			//Paint Player Sprite
			player.paint(g);
			g.dispose();
			strategy.show();
		}
	}

	//Left and Right Arrow Keys Set xSpeed when pressed (player sprite moves)
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.setXSpeed(-10);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setXSpeed(10);
		}
	}

	//Left and Right Arrow Keys set xSpeed to 0 when released (player sprite stops moving)
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setXSpeed(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	//Start Game
	public static void main(String args[]){
		@SuppressWarnings("unused")
		InvadersApplication app = new InvadersApplication();
	}
}
