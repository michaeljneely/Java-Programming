package finalfilesystem;

public class File extends Component {
	public File(String name){
		this.name = name;
	}

	@Override
	public int size() {
		return name.length(); //File size is num characters in name
	}

	@Override
	public int getNumFiles() {
		return 1; //Just this one file
	}

	@Override
	public int getNumFolders() {
		return 0; //A File cannot have subfolders
	}
}
