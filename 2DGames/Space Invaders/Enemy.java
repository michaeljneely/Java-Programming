package assignment4;

import java.awt.Image;

public class Enemy extends Sprite2D {

	private boolean collided = false; //If any enemy has hit the edge of the screen
	int direction = 1; //Whether the sprite is moving west(-1) or east (1)
	
	public Enemy(Image i, int xPos, int yPos, int winWidth) {
		super(i, xPos, yPos, winWidth);
	}

	//Setter for collided
	public void setCollided(boolean newVal){
		collided = newVal;
	}
	
	//Getter for collided
	public boolean getCollided(){
		return collided;
	}
	
	@Override
	public void move() {
		//Move in proper direction
		x += ((direction) * xSpeed);
		//If any sprite has collided with the left or right edge of the screen set collided to true
		if (x >= 800 - spriteImage.getWidth(null) || x <= 0) collided = true;
	}

	//Change direction and move down a bit
	public void reverseDirection() {
		direction *= -1;
		y += 10;
	}
}
