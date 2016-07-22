package astar;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class BadGuy {

	//Member Variables
	private static final int COLUMNS = 40; //number of columns in grid
	private static final int ROWS = 40; //number of rows in grid
	private Node[][] nodes = new Node[COLUMNS][ROWS]; //2d array of nodes
	Image myImage;
	int x=0,y=0;
	boolean hasPath=false; //Bad Guy has a path to the player
	Stack<Node> path = new Stack<Node>(); //The Path to the player 20

	//Constructor
	public BadGuy( Image i, int x, int y ) {
		myImage=i;
		this.x = x;
		this.y = y;
	}

	//Do an Iteration of A*
	public void reCalcPath(boolean map[][],int targx, int targy) {
		//Make an Open List
		ArrayList<Node> openList = new ArrayList<Node>();
		//Make a Closed List
		ArrayList<Node> closedList = new ArrayList<Node>();
		//Clear the current path
		path.clear();
		//There is no path yet
		hasPath = false;
		//Populate Grid of Nodes
		for (int x=0;x<COLUMNS;x++) {
			for (int y=0;y<ROWS;y++) {
				nodes[x][y] = new Node(x,y,0,0,null);
			}
		}
		/** Steps **/
		// Step 1) Add Starting Point to Open List
		openList.add(nodes[x][y]);
		// Step 2) While Open List in Not Empty and Closed List does not contain target
		while(!openList.isEmpty() && !containsTarget(closedList, targx, targy)){
			//Part a) Look for the lowest F cost square on the open list. We refer to this as the current square.
			Node current = getLowestNode(openList);
			//Part b) Switch it to the closed list.
			openList.remove(current);
			closedList.add(current);
			//Stop if : current node is the target or null
			if (current == nodes[targx][targy] || current == null) {
				break;
			}
			//Get current X and Y
			int curX = current.getX();
			int curY = current.getY();
			//Part c) For each of the 8 squares adjacent to this current square
			for(int i = -1; i < 2; i++){
				for(int j= -1; j < 2; j++){
					//If it attempts to calculate outside of boundary -- skip this iteration
					if(curX+ i > COLUMNS-1 || curX+i < 0 || curY+j > ROWS-1 || curY+j < 0)continue;
					//If it is not walkable or if it is on the closed list, ignore it. Otherwise
					if(!map[curX + i][curY + j] && !closedList.contains(nodes[curX + i][curY + j])){
						//Is g 10(horizontal/vertical) or 14 (diagonal)?
						//If the neighbor is diagonal, the sum of its i and j values will be -2, 0, or 2
						int g = (i+j == 2 || i+j == 0 || i+j == -2) ? 14 : 10;
						//Get the parent's G value (if possible)
						int parentG = 0;
						if( nodes[curX+i][curY+j].getParent() != null){
							parentG = nodes[curX+i][curY+j].getParent().getG();
						}
						//New g value
						int tempG = parentG + g;
						/*
If it isn’t on the open list, add it to the open list.
Make the current square the parent of this square.
Record the F, G, and H costs of the square.
						 */
						if(!openList.contains(nodes[curX+i][curY+j])){
							nodes[curX+i][curY+j] = new Node(curX+i, curY+j, tempG, calcH(curX+i, curY+j, targx, targy), current);
							openList.add(nodes[curX+i][curY+j]);
						}
						/*
If it is on the open list already, check to see if this path to that square is
better, using G cost as the measure. A lower G cost means that this is a better path.
If so, change the parent of the square to the current square, 98 and recalculate the G and F scores of the square.
						 */
						else if (tempG < current.getG()) {
							nodes[curX+i][curY+j] = new Node(curX+i, curY+j, tempG, calcH(curX+i, curY+j, targx, targy), current);
						}
					}
				}
			}
		}
		//Step 3)Save the path. Working backwards from the target square, go from each square to its parent square until
		//you reach the starting square. That is your path.
		//Stop if the target node has no parent -> this means there is no path
		if (nodes[targx][targy].getParent() == null) {
			return;
		}
		//Build Path
		Node target = nodes[targx][targy];
		while (target != nodes[x][y] && target != null){
			path.push(target);
			target = target.getParent();
		}
		//A path has been found!
		hasPath = true;
	}
	//Get the node with the lowest F value in the openList
	private Node getLowestNode(ArrayList<Node> openList) {
		return (Node)Collections.min(openList);
	}
	//Calculate the H value for a node, given current x,y and target x,y
	private int calcH(int currentX, int currentY, int targetX, int targetY){
		return 10*(Math.abs(currentX-targetX) + Math.abs(currentY-targetY));
	}
	private boolean containsTarget(ArrayList<Node> openList, int targx, int targy){
		for(Node n : openList){
			if(n.getX() == targx && n.getY() == targy) return true;
		}
		return false;
	}
	//Move the BadGuy
	public void move(boolean map[][],int targx, int targy) {
		reCalcPath(map, targx, targy);
		if (hasPath) {
			if(!path.isEmpty()){
				Node a = (Node)path.pop();
				x = a.getX();
				y = a.getY();
			}
		}
		else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx=x, newy=y;
			if (targx<x)
				newx--;
			else if (targx>x)
				newx++;
			if (targy<y)
				newy--;
			else if (targy>y)
				newy++;
			if (!map[newx][newy]) {
				x=newx;
				y=newy;
			}
		}
	}
	//Paint the BadGuy
	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}
}