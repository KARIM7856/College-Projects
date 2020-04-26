
public class DeleteUserCommand implements Command {
	private String username;
	private SecurityModule securityModule;
	public DeleteUserCommand(String username, SecurityModule securityModule) {
		super();
		this.username = username;
		this.securityModule = securityModule;
	}
	@Override
	public void execute() {
		if(SecurityModule.getCurrentUser().getUsername() != securityModule.getDefaultUser().getUsername())
		{
			System.err.println("Only the admin user can do this.");
			return;
		}
		else
		{
			securityModule.deleteUser(username);
		}
		
	}
	
	
}
