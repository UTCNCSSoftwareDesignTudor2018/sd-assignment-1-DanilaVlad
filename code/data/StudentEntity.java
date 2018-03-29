package data;

public class StudentEntity {
	private int idstudent;
	private String name;
	private String card_nr;
	private String personal_numerical_code;
	private String address;
	private String group;
	private String username;
	private String password;

	public StudentEntity() {
		super();
	}

	public StudentEntity(int idstudent, String name, String card_nr, String personal_numerical_code, String address) {
		super();
		this.idstudent = idstudent;
		this.name = name;
		this.card_nr = card_nr;
		this.personal_numerical_code = personal_numerical_code;
		this.address = address;
	}

	public StudentEntity(int idstudent, String name, String card_nr, String personal_numerical_code, String address,
			String group, String username, String password) {
		super();
		this.idstudent = idstudent;
		this.name = name;
		this.card_nr = card_nr;
		this.personal_numerical_code = personal_numerical_code;
		this.address = address;
		this.group = group;
		this.username = username;
		this.password = password;
	}

	public int getIdstudent() {
		return idstudent;
	}

	public void setIdstudent(int idstudent) {
		this.idstudent = idstudent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_nr() {
		return card_nr;
	}

	public void setCard_nr(String card_nr) {
		this.card_nr = card_nr;
	}

	public String getPersonal_numerical_code() {
		return personal_numerical_code;
	}

	public void setPersonal_numerical_code(String personal_numerical_code) {
		this.personal_numerical_code = personal_numerical_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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
		return "StudentEntity [idstudent=" + idstudent + ", name=" + name + ", card_nr=" + card_nr
				+ ", personal_numerical_code=" + personal_numerical_code + ", address=" + address + ", group=" + group
				+ ", username=" + username + ", password=" + password + "]";
	}

}
