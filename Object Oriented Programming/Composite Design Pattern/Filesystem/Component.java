package finalfilesystem;

public abstract class Component {
	protected String name;
	
	public String getName(){
		return this.name;
	}
	
	public abstract int size();
	
	public abstract int getNumFiles();
	
	public abstract int getNumFolders();
	
}
