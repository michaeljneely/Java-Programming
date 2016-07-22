package assignment4;

import java.awt.Graphics;
import java.awt.Image;

//Parent Class for All Sprites - Defines Basic Behavior 
public abstract class Sprite2D {
	
	protected double x; //xPos
	protected double y; //yPos
	protected double xSpeed; //how fast the Sprite moves in x direction
	protected int windowWidth; //Width of screen
	protected Image spriteImage; //Image
	
	//Setup for a Sprite
	public Sprite2D(Image i, int xPos, int yPos, int winWidth){
		spriteImage = i;
		x = xPos;
		y = yPos;
		xSpeed = 0;
		windowWidth = winWidth;
	}
	
	//How the Sprite moves
	public abstract void move();
	
	//Change Sprite's position
	public void setPosition(double newX, double newY){
		x = newX;
		y = newY;
	}
	
	//Change Sprite's x speed
	public void setXSpeed(double newXSpeed ){
		xSpeed = newXSpeed;
	}
	
	public void paint(Graphics g){
		g.drawImage(spriteImage, (int)x, (int)y, null);
	}
}
