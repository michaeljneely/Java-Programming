package finalfilesystem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FinalFileSystemTest {

	Directory documents;
	
	@Before
	public void setUp() throws Exception{
		documents = new Directory("Documents");
		Directory music = new Directory("Music"),
				photos = new Directory("Photos"), dylan = new Directory("Dylan"), band = new Directory("Band");
		
		File a = new File("assign1.doc"), b = new File("family.jpg"), c = new File("tambourine.mp3"), 
				d = new File("dixie.mp3"), e = new File("weight.mp3");

		documents.add(a);
		documents.add(music);
		documents.add(photos);
		photos.add(b);
		music.add(dylan);
		music.add(band);
		dylan.add(c);
		band.add(d);
		band.add(e);
	}
	
	@Test
	public void sizeTest(){
		int expectedSize = 54;
		int actualSize = documents.size();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void getNumFilesTest(){
		int expectedNumFiles = 5;
		int actualNumFiles = documents.getNumFiles();
		assertEquals(expectedNumFiles, actualNumFiles);
	}
	
	@Test
	public void getNumFoldersTest(){
		int expectedNumFolders = 4;
		int actualNumFolders = documents.getNumFolders();
		assertEquals(expectedNumFolders, actualNumFolders);
	}
}
