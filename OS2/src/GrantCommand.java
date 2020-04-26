
public class GrantCommand implements Command {
	private SecurityModule securityModule;
	private String username;
	private String directory;
	private boolean[] authorities;



	public GrantCommand(SecurityModule securityModule, String username, String directory, boolean[] authorities) {
		super();
		this.securityModule = securityModule;
		this.username = username;
		this.directory = directory;
		this.authorities = authorities;
	}



	@Override
	public void execute() {
		if(SecurityModule.getCurrentUser().getUsername() != securityModule.getDefaultUser().getUsername()) {
			System.err.println("Only the admin can use this command.");
			return;
		}
		
		securityModule.getUsers().get(username).getAuthorities().put(directory, authorities);
		
	}

}
