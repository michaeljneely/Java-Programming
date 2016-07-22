package week6;

public abstract class Animal {
	
	private boolean hasSkin = true;
	protected boolean moves = true;
	protected boolean eats = true;
	protected boolean breathes = true;
	
	public abstract void move(int length);

	public boolean isHasSkin() {
		return hasSkin;
	}

	public void setHasSkin(boolean hasSkin) {
		this.hasSkin = hasSkin;
	}
}
