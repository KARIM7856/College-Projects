
public class Profile extends User {
	
	private String path;
	private String status;
	
	public void changeImage(String path) {
		this.path = path;
	}
	public void changeStatus(String status) {
		this.status = status;
	}
	public void changeLocation(String location) {
		User u = new User();
		u.setLocation(location);	
	}
	public void showTimeLine() {}
}
