
public class OperatingSystem {
	private String mode;
	private Command command;
	private FileSystem fileSystem;
	private MemoryManager memmgr;
	private CommandParser commandParser;
	private SecurityModule securityModule;
	
	
	
	OperatingSystem(String mode){
		this.mode = mode;
		fileSystem = new FileSystem();
		commandParser = new CommandParser();
		
		if(mode == "C") {
			memmgr = new ContinousMemoryManager();
		}
	}
	
	public void execute(String cmdString) {
		String cmd = CommandParser.parseCmd(cmdString);
		
		if(commandIsFiles(cmd))
			commandParser.parseForFileSystem(cmdString);
		else
			commandParser.parseForSecurity(cmdString);
			
		
		

		
		switch(cmd){
		case "CreateFile":
			setCommand(new CreateFileCommand(commandParser.getDirectory(), commandParser.getSize(), memmgr, fileSystem));
			command.execute();
			break;
			
		case "CreateDirectory":
			setCommand(new CreateFolderCommand(commandParser.getDirectory(), memmgr, fileSystem));
			command.execute();
			break;
			
		case "DeleteFolder":
			setCommand(new RemoveFolderCommand(commandParser.getDirectory(), fileSystem, memmgr));
			command.execute();
			break;
			
		case "DeleteFile":
			setCommand(new RemoveFileCommand(commandParser.getDirectory(), fileSystem, memmgr));
			command.execute();
			break;
			
		case "DiscStatus":
			setCommand(new DiskInfoCommand());
			command.execute();
			break;
			
		case "DisplayDiscStructure":
			setCommand(new DisplayDiskStructureCommand(fileSystem));
			command.execute();
			break;
			
			//SecurityModlue
			
		case "CreateUser":
			setCommand(new CreateUserCommand(securityModule, commandParser.getUsername(), commandParser.getPassword()));
			command.execute();
			break;
			
		case "DeleteUser":
			setCommand(new DeleteUserCommand(commandParser.getUsername(), securityModule));
			command.execute();
			break;
			
		case "Login":
			setCommand(new LoginCommand(commandParser.getUsername(), commandParser.getPassword(), securityModule));
			command.execute();
			break;
			
		case "TellUser":
			System.out.println("Current User is: " + SecurityModule.getCurrentUser().getUsername());
		}
		
		
	}
	
	private boolean commandIsFiles(String cmd) {
		if(cmd.toLowerCase().contains("user") || cmd.toLowerCase() == "grant" || cmd.toLowerCase() == "login")
			return false;
		else
			return true;
	
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
