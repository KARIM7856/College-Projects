
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
		int errno = fileSystem.updateTreeFolder(directory);
		 
		 if(errno != 0)
			 System.out.println("Directory doesn't exists");
		 
		 
	}

}
