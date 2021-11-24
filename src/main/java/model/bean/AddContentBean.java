package model.bean;

public class AddContentBean {
	private int id;
	private String title;
	private String brief;
	private String content;
	private int authorId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public AddContentBean(int id, String title, String brief, String content, int authorId) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.authorId = authorId;
	}
	public AddContentBean() {
		
	}
	
	
}
