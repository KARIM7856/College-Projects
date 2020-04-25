
public class CommandParser {
	
	private String cmd;
	private String directory;
	private int size;
	private String username;
	private String  password;
	private boolean[] capapilities;

	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void parseForFileSystem(String cmdString) {
		String[] cmdList = cmdString.split(" ");
		
		cmd = cmdList[0];
		
		if(cmdList.length == 1)
			return;
		
		directory = cmdList[1];
		
		if(cmdList.length == 3)
			size = Integer.parseInt(cmdList[2]);
		
		
	}
	public void parseForSecurity(String cmdString)
	{
		String[] cmdList = cmdString.split(" ");
		
		cmd = cmdList[0];
		
		if(cmdList.length == 1)
			return;
		
		username = cmdList[1];
		if(cmdList.length == 2)
			return;
		
		if(cmd.toLowerCase() == "grant") {
			directory = cmdList[2];
			capapilities = new boolean[]{cmdList[3].charAt(0)==1, cmdList[3].charAt(1) ==1};
		}
		else {
			password = cmdList[2];
		}
			
		
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static String parseCmd(String cmdString) {
		return cmdString.split(" ", 2)[0];
	}
	
	
}
