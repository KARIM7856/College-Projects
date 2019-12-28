
public class Comment {
	private String text;
	private String reply;
	private String path;
	private int like;
	
	public void addText (String text) {
		this.text = text;
	}
	public void addImage(String path) {
		this.path = path;
	}
	public int incLike(int like) {
		this.like = like;
		return like;
	}
	public void replyText(String reply) {
		this.reply = reply;
	}
}
