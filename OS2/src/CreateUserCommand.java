
public class CreateUserCommand implements Command {
	private SecurityModule securityModule;
	private String username;
	private String password;
	public CreateUserCommand(SecurityModule securityModule, String username, String password) {
		super();
		this.securityModule = securityModule;
		this.username = username;
		this.password = password;
	}

	@Override
	public void execute() {
		if(SecurityModule.getCurrentUser() != securityModule.getDefaultUser())
		{
			System.err.println("Only the admin user can do this.");
			return;
		}
		else
		{
			securityModule.createUser(new User(username, password));
		}
	}

}
