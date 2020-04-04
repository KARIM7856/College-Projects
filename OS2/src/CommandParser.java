
public class CommandParser {
	
	String cmd;
	String directory;
	int size;

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
	
	public void parse(String cmdString) {
		String[] cmdList = cmdString.split(" ");
		
		cmd = cmdList[0];
		
		if(cmdList.length == 1)
			return;
		
		directory = cmdList[1];
		
		if(cmdList.length == 3)
			size = Integer.parseInt(cmdList[2]);
		
		
	}
	
	
}
