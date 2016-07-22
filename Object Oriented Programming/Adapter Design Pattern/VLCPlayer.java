package finalvlc;

public class VLCPlayer {

	private boolean playState;
	private PluginAdapter player;
	
	public VLCPlayer(){
		this.playState = false;
		this.player = new PluginAdapter();
	}
	public void play(AudioFile audio) throws UnSupportedAudioFormatException{
		this.player.play(audio);
		this.playState = true;
	}
	
	public boolean getPlayState(){
		return this.playState;
	}
	
	public void stop(){
		this.playState = false;
	}
	
	public void registerPlugin(String audioType, Plugin p){
		player.registerPlugin(audioType, p);
	}
}
