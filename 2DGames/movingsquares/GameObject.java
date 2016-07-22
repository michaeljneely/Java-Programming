package movingsquares;

import java.awt.*;

public class GameObject {
	private double x, y; 
	private Color c; 
	private int sizeX = 40; 
	private int sizeY = 40;

	//Set Random Position and Color of the GameObject 
	public GameObject(){ 
		int r = (int)(Math.random() * 255); 
		int g = (int)(Math.random() * 255); 
		int b = (int)(Math.random() * 255); 
		c = new Color(r,g,b); 
		x = Math.random() * 600; 
		y = Math.random() * 600;

	}

	//Move the object +/- 10 on x and y 
	public void move(){
		x += ((Math.random() * 2 - 1)*10); 
		y += ((Math.random() * 2 - 1)*10);
	}

	//Draw the GameObject 
	public void paint(Graphics g){
		g.setColor(c);
		g.fillRect((int)x, (int)y, sizeX, sizeY);
	}
}
