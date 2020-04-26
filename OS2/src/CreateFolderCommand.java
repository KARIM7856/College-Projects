
public class CreateFolderCommand implements Command {

	private String directory;
	private MemoryManager memmgr;
	private FileSystem fileSystem;
	private SecurityModule securityModuel;


	public CreateFolderCommand(String directory, MemoryManager memmgr, FileSystem fileSystem,
			SecurityModule securityModuel) {
		super();
		this.directory = directory;
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
		
		int errno = fileSystem.updateTreeFolder(directory);
		 
		 if(errno != 0)
			 System.out.println("Directory doesn't exists");
		 
		 
	}

}
