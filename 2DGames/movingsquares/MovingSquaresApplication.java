package movingsquares;

import javax.swing.*; 

import java.awt.*;

@SuppressWarnings("serial")
public class MovingSquaresApplication extends JFrame implements Runnable {

	private static final Dimension WindowSize = new Dimension(600, 600);
	private static final int NumGameObjects = 50;
	private GameObject[] GameObjectsArray = new GameObject[NumGameObjects]; 

	public MovingSquaresApplication(){

		//Window Setup
		this.setTitle("Moving Squares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		int x = screensize.width/2 - WindowSize.width/2; 
		int y = screensize.height/2 - WindowSize.height/2; 
		setBounds(x, y, WindowSize.width, WindowSize.height); 
		setVisible(true);

		//Fill GameObject Array
		for(int i = 0; i < GameObjectsArray.length; i++){
			GameObjectsArray[i] = new GameObject();
		}
		//Thread Setup 
		Thread t = new Thread(this);
		t.start();
	}


	public void run() {
		//Refresh Every 1/50th of a second 
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//Move all GameObjects 
			for(GameObject object : GameObjectsArray){
				object.move();
			} 
			this.repaint();
		}
	}

	public void paint(Graphics g){
		//Clear Screen by drawing a large white rectangle
		g.setColor(Color.WHITE);
		g.fillRect(0,0,WindowSize.width,WindowSize.height);
		//Paint all GameObjects 
		for(GameObject object : GameObjectsArray){ 
			object.paint(g);
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MovingSquaresApplication app = new MovingSquaresApplication();
	}
}