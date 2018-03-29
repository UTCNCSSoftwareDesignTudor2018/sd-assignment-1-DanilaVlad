package data;

public class TeacherEntity {
	private int idteacher;
	private String name;
	private String username;
	private String password;

	public TeacherEntity() {
		super();
	}

	public TeacherEntity(int idteacher, String name, String username, String password) {
		super();
		this.idteacher = idteacher;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public int getIdteacher() {
		return idteacher;
	}

	public void setIdteacher(int idteacher) {
		this.idteacher = idteacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "TeacherEntity [idteacher=" + idteacher + ", name=" + name + ", username=" + username + ", password="
				+ password + "]";
	}

}
