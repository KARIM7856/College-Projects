
public class FileSystem {
	
	private Directory root;
	FileSystem(){
		root = new Directory();
	}
	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}
}
