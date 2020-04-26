import java.util.HashMap;

public class User {
public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
private String username;
private String password;
private HashMap<String, boolean[]> authorities;
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
public HashMap<String, boolean[]> getAuthorities() {
	return authorities;
}
public void setAuthorities(HashMap<String, boolean[]> authorities) {
	this.authorities = authorities;
}


}
