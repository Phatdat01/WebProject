package model.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Content {
	private int id;
	private String title;
	private String brief;
	private String content;
	private String createdate;
	private String updatetime;
	SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	SimpleDateFormat system = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		try {
			this.createdate = myFormat.format(system.parse(createdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getupdatetime() {
		return updatetime;
	}
	public void setupdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public Content(int id, String title, String brief, String createdate){
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		try {
			this.createdate = myFormat.format(system.parse(createdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Content() {
		super();
	}
	

}