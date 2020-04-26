
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
		securityModule = new SecurityModule();
		
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
			
		
		

		
		switch(cmd.toLowerCase()){
		case "createfile":
			setCommand(new CreateFileCommand(commandParser.getDirectory(), commandParser.getSize(), memmgr, fileSystem, securityModule));
			command.execute();
			break;
			
		case "createdirectory":
			setCommand(new CreateFolderCommand(commandParser.getDirectory(), memmgr, fileSystem, securityModule));
			command.execute();
			break;
			
		case "deletefolder":
			setCommand(new RemoveFolderCommand(commandParser.getDirectory(), fileSystem, memmgr, securityModule));
			command.execute();
			break;
			
		case "deletefile":
			setCommand(new RemoveFileCommand(commandParser.getDirectory(), fileSystem, memmgr, securityModule));
			command.execute();
			break;
			
		case "fiscstatus":
			setCommand(new DiskInfoCommand());
			command.execute();
			break;
			
		case "displaydiscstructure":
			setCommand(new DisplayDiskStructureCommand(fileSystem));
			command.execute();
			break;
			
			//SecurityModlue
			
		case "createuser":
			setCommand(new CreateUserCommand(securityModule, commandParser.getUsername(), commandParser.getPassword()));
			command.execute();
			break;
			
		case "deleteuser":
			setCommand(new DeleteUserCommand(commandParser.getUsername(), securityModule));
			command.execute();
			break;
			
		case "login":
			setCommand(new LoginCommand(commandParser.getUsername(), commandParser.getPassword(), securityModule));
			command.execute();
			break;
			
		case "telluser":
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
