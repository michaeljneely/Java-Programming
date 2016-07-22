package week6;

public class Salmon extends Fish {

	protected boolean scales = true;
	protected boolean healthy = true;
	protected int weight = 0;
	protected int meatQuality = 0;
	protected String name= "";
	
	public Salmon(String name, int weight, int meatQuality) {
		this.name = name;
		this.weight = weight;
		this.meatQuality = meatQuality;
	}

	@Override
	public void swim(int length) {
		System.out.println("I can swim " + length + " metres");
	}

	@Override
	public void move(int length) {
		swim(length);
	}
	
	@Override
	public boolean equals (Object obj){
		//check to make sure object is not pointing at null
		if (obj==null){ 
			return false;
		}
		// check to make sure obj is a Salmon object
		if (!(obj instanceof Salmon)){ 
			return false;
		}
		// Now we have to *cast* obj to a Salmon object
		Salmon s = (Salmon)obj;
		//test for equality
		if(this.weight == s.weight && this.meatQuality == s.meatQuality ){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String output = "I'm " + this.name + " the Salmon; I am a Fish. I have a weight of " +
				this.weight + " and a meat quality of " + this.meatQuality + ".";
		return output;
	}
}
