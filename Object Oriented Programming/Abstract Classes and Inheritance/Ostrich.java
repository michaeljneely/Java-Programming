package week6;

public class Ostrich extends Bird {

	protected boolean hasThinLongLegs = true;
	protected boolean isTall = true;
	protected boolean flies = false;
	protected int height = 0;
	protected int beakSize = 0;
	protected String name = "";
	
	public Ostrich(String name, int height, int beakSize) {
		this.name = name;
		this.height = height;
		this.beakSize = beakSize;
	}

	@Override
	public void fly(int length) {
		System.out.println("Ostriches's Can't Fly!!");
	}

	public void move(int length) {
		fly(length);
		System.out.println("I can walk " + length + " metres");
	}
	
	@Override
	public boolean equals (Object obj){
		//check to make sure object is not pointing at null
		if (obj==null){ 
			return false;
		}
		// check to make sure obj is an Ostrich object
		if (!(obj instanceof Ostrich)){ 
			return false;
		}
		// Now we have to *cast* obj to an Ostrich object
		Ostrich o = (Ostrich)obj;
		//test for equality
		if(this.height == o.height && this.beakSize == o.beakSize){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String output = "I'm " + this.name + " the Ostrich; I am a Bird. I have a height of " +
				this.height + " and a beak size of " + this.beakSize + ".";
		return output;
	}

}
