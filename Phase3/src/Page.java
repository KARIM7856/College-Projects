
public class Page {
	
	private String name;
	private String pageType;
	private int admin;
	private String [] admins;
	private String [] followers;
	private int userID;
	private int adminID;
	private int followersID;
	
	public Post p = new Post();
	public ADS ad = new ADS();
	
	
	public void createPage() {}
	public void addAdmin() {}
	public void changePagePic() {}
	public void addLike() {}
	public void pageName(String name) {
		this.name = name;
	}
	public void pageType(String type) {
		this.pageType = type;
	}
	public void deletePage() {}
	public void showTimeline() {}
}
