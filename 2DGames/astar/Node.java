package astar;

public class Node implements Comparable<Node> {

	//Member Variables
	private int x, y, g, h, f;
	private Node parent;

	//Constructor
	public Node(int x, int y, int g, int h, Node parent){

		this.setX(x);
		this.setY(y);
		this.g = g;
		this.h = h;
		this.f = this.g + this.h;
		this.setParent(parent);

	}
	//Nodes are comparable by their F value
	public int compareTo(Node n) {

		Node a = (Node) n;
		if (this.getF() < a.getF())return -1;
		else if (this.getF() > a.getF()) return 1;
		else return 0;
	}

	/** Getters and Setters **/
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getF(){
		return f;
	}

	public void setF(int f){
		this.f = f;
	}

	public int getH(){
		return h;
	}

	public int getG(){
		return g;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}