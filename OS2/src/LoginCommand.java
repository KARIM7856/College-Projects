
public class LoginCommand implements Command {
	private String username;
	private String password;
	private SecurityModule securityModule;
	public LoginCommand(String username, String password, SecurityModule securityModule) {
		super();
		this.username = username;
		this.password = password;
		this.securityModule = securityModule;
	}
	@Override
	public void execute() {
		if(SecurityModule.getCurrentUser().getUsername() == username) {
			System.out.println("User already logged in");
			return;
		}
		securityModule.login(username, password);
	}
	
	
}
