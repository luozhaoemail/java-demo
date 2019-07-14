package spring.pojo;

//1.第一步建立bean，也就是POJO（Plain Ordinary Java Object）简单Java对象
public class User {
	private int id;
	private String username;
	private String password;
	private String photo;
	
	public User(){
		
	}
	
	public User(String username, String password, String photo) {
		this.username = username;
		this.password = password;
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", photo=" + photo + "]";
	}
	
	
	
}
