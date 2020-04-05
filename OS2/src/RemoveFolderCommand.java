import java.util.ArrayList;

public class RemoveFolderCommand implements Command {
	private String directory;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	
	public RemoveFolderCommand(String directory, FileSystem fileSystem, MemoryManager memmgr) {
		super();
		this.directory = directory;
		this.fileSystem = fileSystem;
		this.memmgr = memmgr;
	}

	@Override
	public void execute() {
	
		 ArrayList<String> result = fileSystem.removeFolder(directory);
		
		 for(String s : result) {
			 memmgr.deAlloc(s);
		 }
		

	}

}
