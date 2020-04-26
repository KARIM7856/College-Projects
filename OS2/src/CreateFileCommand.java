
public class CreateFileCommand implements Command {
	

	private String directory;
	private int size;
	private MemoryManager memmgr;
	private FileSystem fileSystem;
	private SecurityModule securityModuel;



	public CreateFileCommand(String directory, int size, MemoryManager memmgr, FileSystem fileSystem,
			SecurityModule securityModuel) {
		super();
		this.directory = directory;
		this.size = size;
		this.memmgr = memmgr;
		this.fileSystem = fileSystem;
		this.securityModuel = securityModuel;
	}



	@Override
	public void execute() {
		
		if(!securityModuel.checkCreateAuthority(directory)) {
			System.err.println("User doesn't have this authority.");
			return;
		}
			
			
			
		 int errno = fileSystem.updateTreeFile(directory, size);
		 
		 if(errno != 0)
			 System.out.println("Directory doesn't exists");
		 
		 memmgr.alloc(directory, size);
		 

	}

}
