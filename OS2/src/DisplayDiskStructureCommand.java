
public class DisplayDiskStructureCommand implements Command {
	private FileSystem fileSystem;
	
	DisplayDiskStructureCommand(FileSystem fileSystem){
		this.fileSystem = fileSystem;
	}

	@Override
	public void execute() {
		fileSystem.displayTree();

	}

}
