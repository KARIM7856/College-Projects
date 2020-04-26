import java.util.ArrayList;

public class RemoveFolderCommand implements Command {
	private String directory;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	private SecurityModule securityModuel;
	
	public RemoveFolderCommand(String directory, FileSystem fileSystem, MemoryManager memmgr,
			SecurityModule securityModuel) {
		super();
		this.directory = directory;
		this.fileSystem = fileSystem;
		this.memmgr = memmgr;
		this.securityModuel = securityModuel;
	}

	@Override
	public void execute() {
		
		if(!securityModuel.checkDeleteAuthority(directory)) {
			System.err.println("User doesn't have this authority.");
			return;
		}
		
	
		 ArrayList<String> result = fileSystem.removeFolder(directory);
		
		 for(String s : result) {
			 memmgr.deAlloc(s);
		 }
		

	}

}
