package finalfilesystem;

import java.util.ArrayList;

public class Directory extends Component{

	private ArrayList<Component>components = new ArrayList<Component>();
	
	public Directory(String name){
		this.name = name;
	}
	
	public void add(Component c){
		components.add(c);
	}

	@Override
	public int size() {
		int size = 0;
		for(Component component : components){
			size += component.size();
		}
		return size;
	}

	@Override
	public int getNumFiles() {
		int numFiles = 0;
		for(Component component : components){
			numFiles += component.getNumFiles();
		}
		return numFiles;
	}

	@Override
	public int getNumFolders() {
		int numFolders = 0;
		for(Component component : components){
			if(component instanceof Directory){
				numFolders += 1;
			}
			numFolders += component.getNumFolders();
		}
		return numFolders;
	}
}
