
public class RemoveFileCommand implements Command {
	private String directory;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	
	

	public RemoveFileCommand(String directory, FileSystem fileSystem, MemoryManager memmgr) {
		super();
		this.directory = directory;
		this.fileSystem = fileSystem;
		this.memmgr = memmgr;
	}



	@Override
	public void execute() {
		fileSystem.removeFile(directory);
		memmgr.deAlloc(directory);
		

	}

}
