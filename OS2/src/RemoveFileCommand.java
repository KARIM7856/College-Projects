
public class RemoveFileCommand implements Command {
	private String directory;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	private SecurityModule securityModuel;
	
	public RemoveFileCommand(String directory, FileSystem fileSystem, MemoryManager memmgr,
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
		
		fileSystem.removeFile(directory);
		memmgr.deAlloc(directory);
		

	}

}
