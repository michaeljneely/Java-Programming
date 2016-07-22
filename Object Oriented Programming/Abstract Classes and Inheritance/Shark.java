package week6;

public class Shark extends Fish {

	protected boolean dangerous = true;
	protected boolean bites = true;
	protected int dangerLevel = 0;
	protected int teethSize = 0;
	protected String name= "";
	
	public Shark(String name, int dangerLevel, int teethSize) {
		this.name = name;
		this.dangerLevel = dangerLevel;
		this.teethSize = teethSize;
	}

	@Override
	public boolean equals (Object obj){
		//check to make sure object is not pointing at null
		if (obj==null){ 
			return false;
		}
		// check to make sure obj is a Shark object
		if (!(obj instanceof Shark)){ 
			return false;
		}
		// Now we have to *cast* obj to a Shark object
		Shark s = (Shark)obj;
		//test for equality
		if(this.dangerLevel == s.dangerLevel && this.teethSize == s.teethSize ){
			return true;
		}
		return false;
	}
	
	@Override
	public void move(int length) {
		swim(length);
	}
	
	@Override
	public void swim(int length) {
		System.out.println("I can swim " + length + " metres");
	}

	
	
	@Override
	public String toString(){
		String output = "I'm " + this.name + " the Shark; I am a Fish. I have a danger level of " +
				this.dangerLevel + " and teeth of size " + this.teethSize + ".";
		return output;
	}

}
