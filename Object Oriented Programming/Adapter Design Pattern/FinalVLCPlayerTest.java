package finalvlc;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class FinalVLCPlayerTest {
	
	VLCPlayer player;

	@Before
	public void setUp() throws Exception{
		player = new VLCPlayer();
		player.registerPlugin("ogg", new OGGPlugin());
		player.registerPlugin("wma", new WMAPlugin());
		player.registerPlugin("mp3", new MP3Plugin());
		
	}
	
	@Test
	public void testOGG() {
		AudioFile oggFile = new AudioFile("ogg", "C://Music/Desperation_Song.ogg");
		try{
			player.play(oggFile);
		}catch(UnSupportedAudioFormatException e){
			System.out.println(e.getMessage());
		}
		assertTrue(player.getPlayState()); // assert that the Player is playing
		player.stop();
	}
	
	@Test
	public void testWMA(){
		AudioFile wmaFile = new AudioFile("wma", "C://Music/Wintersong.wma");
		try{
			player.play(wmaFile);
		}catch(UnSupportedAudioFormatException e){
			System.out.println(e.getMessage());
		}
		assertTrue(player.getPlayState());
		player.stop();
	}

	@Test
	public void testMP3(){
		AudioFile mp3File = new AudioFile("mp3", "C://Music/Mr_Jones.mp3");
		try{
			player.play(mp3File);
		}catch(UnSupportedAudioFormatException e){
			System.out.println(e.getMessage());
		}
		assertTrue(player.getPlayState());
		player.stop();
	}
	
	@Test
	public void unSupportedAudioTypeTest(){
		AudioFile flacFile = new AudioFile("flac", "C://Music/Desperation_Song.flac");
		try{
			player.play(flacFile);
		}catch(UnSupportedAudioFormatException e){
			System.out.println(e.getMessage());
		}
		assertFalse(player.getPlayState());
		player.stop();
	}
}

