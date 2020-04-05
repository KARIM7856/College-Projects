import java.util.ArrayList;

public class FileSystem {
	
	private Directory root;
	FileSystem(){
		root = new Directory();
		root.setName("root");
	}
	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}
	public int updateTreeFile(String directory, int size) {
		String[] separateFiles = directory.split("/");
		
		String fileName = separateFiles[separateFiles.length-1];
		
		Directory current = root;
		for(int i = 1; i < separateFiles.length-1; i++) {
			if(current.containsFolder(separateFiles[i])) {
				current = current.getFolder(separateFiles[i]);
				
			}
			else
				return 1;
		}
		
		current.putFile(fileName, new File(fileName, size));
		
		return 0;
	}
	
	public int updateTreeFolder(String directory) {
		String[] separateFiles = directory.split("/");
		String folderName = separateFiles[separateFiles.length-1];
		
		Directory current = root;
		for(int i = 1; i < separateFiles.length-1; i++) {
			if(current.containsFolder(separateFiles[i])) {
				current = current.getFolder(separateFiles[i]);
				
			}
			else
				return 1;
		}
		current.putFolder(folderName, new Directory(folderName));
		return 0;
	}
	
	public void displayTree() {
		root.display();
		
	}
	
	public int removeFile(String directory) {
		String[] separateFiles = directory.split("/");
		String fileName = separateFiles[separateFiles.length-1];
		
		Directory current = root;
		for(int i = 1; i < separateFiles.length-1; i++) {
			if(current.containsFolder(separateFiles[i])) {
				current = current.getFolder(separateFiles[i]);
				
			}
			else
				return 1;
		}
		
		current.removeFile(fileName);
		
		
		return 0;
		
	}
	
	public ArrayList<String> removeFolder(String directory) {
		String[] separateFiles = directory.split("/");
		StringBuilder prefix = new StringBuilder();
		ArrayList<String> result = new ArrayList<String>();
		String folderName = separateFiles[separateFiles.length-1];
		Directory current = root;
		prefix.append("root/");
		
		for(int i = 1; i < separateFiles.length-1; i++) {
			if(current.containsFolder(separateFiles[i])) {
				current = current.getFolder(separateFiles[i]);
				prefix.append(current.getName() + '/');
				
			}
			else
				return null;
		}
		
		for(String s : current.removeFolder(folderName))
			result.add(prefix.toString() + s);
		
		
		
		
		
		return result;
		
	}
}
