package astar;

import java.awt.Graphics;
import java.awt.Image;

public class Player {
	Image myImage;
	int x=0,y=0;
	int xSpeed=0, ySpeed=0;
	
	public Player( Image i, int x, int y ) {
		myImage=i;
		this.x = x;
		this.y = y;
	}
	public void setXSpeed( int x ) {
		xSpeed=x;
	}
	public void setYSpeed( int y ) {
		ySpeed=y;
	}
	public void move(boolean map[][]) {
		int newx=x+xSpeed;
		int newy=y+ySpeed;
		//If the square doesn't have a wall and is inside the map boundaries -> Execute a move
		if (!map[newx][newy] && (newx < 40 && newx > 0 && newy < 40 && newy > 0) ) {
			x=newx;
			y=newy;
		}
	}
	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}
}