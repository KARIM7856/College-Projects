import java.util.ArrayList;
import java.util.HashMap;

public class SecurityModule {
	
	private User defaultUser = new User("admin", "admin");
	private static User currentUser;
	private HashMap<String, User> users;
	
	public SecurityModule() {
		super();
		users = new HashMap<String, User>(); 
		setCurrentUser(defaultUser);
	}
	public  User getDefaultUser() {
		return defaultUser;
	}
	

	
	public HashMap<String, User> getUsers() {
		return users;
	}
	
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
	
	public void createUser(User user) {
		if(users.containsKey(user.getUsername())) {
			
			System.err.println("username already exists.");
			return;
		}
		else
		{
			users.put(user.getUsername(), user);
		}
		
	}
	
	public void deleteUser(String username) { 
		if(!users.containsKey(username)) {
			
			System.err.println("user not found.");
			return;
		}
		else
		{
			users.remove(username);
		}
	}
	
	public boolean login(String username, String password) {
		if(!users.containsKey(username)) {
			System.err.println("User doesn't exist.");
			return false;
		}
		
		if(users.get(username).getPassword() == password) {
			currentUser = users.get(username);
			return true;
		}
		else {
			System.err.println("wrong password.");
			return false;
		}
		
	}
	public static User getCurrentUser() {
		return currentUser;
	}
	public static void setCurrentUser(User currentUser) {
		SecurityModule.currentUser = currentUser;
	}
}
