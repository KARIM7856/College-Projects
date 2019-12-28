
public class Post {
	private String text;
	private int like;
	private String path;
	
	public Comment c = new Comment();
	
	public String addText(String text) {
		this.text = text;
		return text;
	}
	public String addPost(String path) {
		this.path = path;
		return path;
	}
	public int incLike(int like) {
		this.like = like;
		return like;
	}
	public void hashTagPost() {}
	public void share(Post p) {}
}
