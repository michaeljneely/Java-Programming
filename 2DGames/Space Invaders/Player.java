package assignment4;

import java.awt.Image;

public class Player extends Sprite2D {

	public Player(Image i, int xPos, int yPos, int winWidth) {
		super(i, xPos, yPos, winWidth);
	}

	//How Player Sprite Moves
	@Override
	public void move() {
		x += xSpeed;
		//Keep Player in Window Boundaries
		if ( x >= windowWidth - spriteImage.getWidth(null)) x = windowWidth - spriteImage.getWidth(null);
		else if (x <= 0) x = 0;
	}
}
