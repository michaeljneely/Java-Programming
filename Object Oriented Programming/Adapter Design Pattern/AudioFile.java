package finalvlc;

public class AudioFile {

	private String audioType;
	private String name;
	
	public AudioFile(String type, String name){
		this.audioType = type;
		this.name = name;
	}
	
	public String getFileName(){
		return this.name;
	}
	
	public String getAudioType(){
		return this.audioType;
	}
}
