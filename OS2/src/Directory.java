import java.util.ArrayList;
import java.util.HashMap;

public class Directory {
	
	private String name;
	
	private HashMap<String, File> files = new HashMap<String, File>();
	private HashMap<String, Directory> directories = new HashMap<String, Directory>();
	
	
	public Directory(String folderName) {
		this.name = folderName;
	}

	public Directory() {
		
	}

	public HashMap<String, Directory> getDirectories() {
		return directories;
	}

	public File removeFile(String key) {
		return files.remove(key);
	}
	public ArrayList<String> removeFolder(String key) {
		ArrayList<String> temp = directories.get(key).removeFolder();
		
		ArrayList<String> result = new  ArrayList<String>();
		
		for(String s : temp) {
			result.add(s);
		}
		directories.remove(key);
		
		return result;
	}
	public ArrayList<String> removeFolder(){
		ArrayList<String> result = new ArrayList<String>();
		
		for(File f : files.values()) {
			result.add(name + '/' + f.getName());
		}
		for(Directory d : directories.values()) {
			for(String s : d.removeFolder()) {
				result.add(name + '/' + s);
			}
		}
		return result;
	}

	public void setDirectories(HashMap<String, Directory> directories) {
		this.directories = directories;
	}
	
	public boolean containsFolder(Object arg0) {
		return directories.containsKey(arg0);
	}
	public Directory getFolder(Object key) {
		return directories.get(key);
	}
	public File getFile(Object key) {
		return files.get(key);
	}

	public Directory putFolder(String key, Directory value) {
		return directories.put(key, value);
	}
	
	public File putFile(String key, File value) {
		return files.put(key, value);
	}
	
	public boolean containsFile(Object arg0) {
		return files.containsKey(arg0);
	}
	

	public HashMap<String, File> getFiles() {
		return files;
	}

	public void setFiles(HashMap<String, File> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isEmpty() {
		return files.isEmpty() && directories.isEmpty();
	}

	public void display() {
		System.out.println("\n." + name);
		
		for(String fileName : files.keySet()) {
			System.out.println("\n	" + fileName);
		}
		
		for(Directory name : directories.values()) {
			name.display();
		}
		
	}
	
	
	
	
	
	
	
}
