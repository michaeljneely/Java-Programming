package week6;

public  class Canary extends Bird {
	
	protected boolean sings = true;
	protected int colour =0;
	protected int songQuality = 0;
	protected String name= "";

	public Canary(String name, int quality, int clr) {
		this.colour = clr;
		this.songQuality=quality;
		this.name = name;
	}
	
	@Override
	public boolean equals (Object obj){
		//check to make sure object is not pointing at null
		if (obj==null){
			return false;
		}
		// check to make sure obj is a Canary object
		if (!(obj instanceof Canary)){
			return false;
		}
		// Now we have to *cast* obj to a Canary object
		Canary c = (Canary)obj;
		//test for equality
		if(this.songQuality == c.songQuality && this.colour == c.colour ){
			return true;
		}
		return false;
	}

	@Override
	public void move(int length) {
		fly(length);
	}

	@Override
	public void fly(int length) {
		System.out.println("I can fly " + length + " metres");
	}
	
	@Override
	public String toString(){
		String output = "I'm " + this.name + " the Canary; I am a Bird. I have yellow colour " +
				this.colour + " and can sing with song quality " + this.songQuality + ".";
		return output;
	}

}
