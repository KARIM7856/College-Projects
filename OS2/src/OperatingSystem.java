
public class OperatingSystem {
	private String mode;
	private Command command;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	private CommandParser commandParser;
	
	
	
	OperatingSystem(String mode){
		this.mode = mode;
		fileSystem = new FileSystem();
		commandParser = new CommandParser();
		
		if(mode == "C") {
			memmgr = new ContinousMemoryManager();
		}
	}
	
	public void execute(String cmdString) {
		
		commandParser.parse(cmdString);
		
		String cmd = commandParser.getCmd();
		
		switch(cmd){
		case "CreateFile":
			setCommand(new CreateFileCommand(commandParser.getDirectory(), commandParser.getSize(), memmgr, fileSystem));
			
			command.execute();
			break;
			
		case "CreateDirectory":
			setCommand(new CreateFolderCommand(commandParser.getDirectory(), memmgr, fileSystem));
			
			command.execute();
		}
		
	}
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public FileSystem getFileSystem() {
		return fileSystem;
	}
	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public MemoryManager getMemmgr() {
		return memmgr;
	}
	public void setMemmgr(MemoryManager memmgr) {
		this.memmgr = memmgr;
	}
	
}
