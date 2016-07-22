package week6;

public final class NorwegianBlue extends Canary {

	public NorwegianBlue(String name, int quality, int clr) {
		super(name, quality, clr);
		this.flies = false;
		this.moves = false;
		this.breathes = false;
		this.eats = false;
		this.sings = false;
	}
	
	public String toString(){
		String output = "I'm " + this.name + " the Norwegian Blue; I am a Canary. I have blue colour " +
				this.colour + " and can sing with song quality " + this.songQuality + ".";
		return output;
	}
}
