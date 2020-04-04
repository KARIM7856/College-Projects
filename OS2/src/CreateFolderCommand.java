
public class CreateFolderCommand implements Command {

	private String directory;
	private MemoryManager memmgr;
	private FileSystem fileSystem;

	public CreateFolderCommand(String directory, MemoryManager memmgr, FileSystem fileSystem) {
		this.directory = directory;
		this.memmgr = memmgr;
		this.fileSystem = fileSystem;
	}

	@Override
	public void execute() {
		

	}

}
