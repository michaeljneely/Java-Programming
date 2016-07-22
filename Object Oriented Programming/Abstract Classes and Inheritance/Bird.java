package week6;

public abstract class Bird extends Animal{
	
	protected boolean wings = true;
	protected boolean flies = true;
	protected boolean feathers = true;

	public abstract void fly(int length);
}
