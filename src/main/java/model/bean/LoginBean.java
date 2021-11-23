package model.bean;

public class LoginBean {
	
	private String email;
	private String password;
	private int id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LoginBean(String email, String password, int id) {
		super();
		this.email = email;
		this.password = password;
		this.id = id;
	}
	public LoginBean() {
		super();
	}
	
	
	
}
