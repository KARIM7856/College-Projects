
public class CreateFileCommand implements Command {
	

	private String directory;
	private int size;
	private MemoryManager memmgr;
	private FileSystem fileSystem;

	public CreateFileCommand(String directory, int size, MemoryManager memmgr, FileSystem fileSystem) {
		this.directory = directory;
		this.size = size;
		this.memmgr = memmgr;
		this.fileSystem = fileSystem;
		
	}

	@Override
	public void execute() {
		 int errno = fileSystem.updateTreeFile(directory, size);
		 
		 if(errno != 0)
			 System.out.println("Directory doesn't exists");
		 
		 memmgr.alloc(directory, size);
		 

	}

}
