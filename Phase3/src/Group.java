
public class Group {
	
	private String [] admins;
	private String [] members;
	private int userID;
	private int adminID;
	private int membersID;
	private String groupName;
	private String status;
	private String path;
	
	public ADS ad = new ADS();
	public Post p = new Post();
	
	
	public void createGroup(String name, String status) {
		this.groupName = name;
		this.status = status;
	}
	public void addMember(String name) {}
	public void removeMember(String name) {}
	public void changeStatus(String status) {}
	public void changeGroupPic(String path) {
		this.path = path;
	}
	public void addAdmin(String name) {}
	public void deletePage() {}
	
	
}
