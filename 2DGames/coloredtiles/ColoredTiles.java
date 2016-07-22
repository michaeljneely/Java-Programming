package coloredtiles;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ColoredTiles extends JFrame {
	private static final Dimension WindowSize = new Dimension(610, 630);
	public ColoredTiles() {
		//Create and set up the window.
		this.setTitle("Random Colored Tiles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Display the window, centered on the screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		setVisible(true);
	}
	public static void main(String [ ] args) {
		@SuppressWarnings("unused")
		ColoredTiles w = new ColoredTiles();
	}
	public void paint(Graphics graphics){
		int i = 0;
		int j = 0;
		for(i = 10; i < 600; i+= 60){
			for(j = 30; j < 600; j+= 60){
				int r = (int)(Math.random() * 256);
				int g = (int)(Math.random() * 256);
				int b = (int)(Math.random() * 256);
				graphics.setColor(new Color(r,g,b));
				graphics.fillRect(i, j, 50, 50);
			}
		}
	}
}