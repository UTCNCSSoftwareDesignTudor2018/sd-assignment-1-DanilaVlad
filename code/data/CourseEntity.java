package data;

public class CourseEntity {
	private int idcourse;
	private String name;
	private int teachers_id;

	public CourseEntity() {
		super();
	}

	public CourseEntity(int idcourse, String name, int teachers_id) {
		this.idcourse = idcourse;
		this.name = name;
		this.teachers_id = teachers_id;
	}

	public int getIdcourse() {
		return idcourse;
	}

	public void setIdcourse(int idcourse) {
		this.idcourse = idcourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeachers_id() {
		return teachers_id;
	}

	public void setTeachers_id(int teachers_id) {
		this.teachers_id = teachers_id;
	}

	@Override
	public String toString() {
		return "CourseEntity [idcourse=" + idcourse + ", name=" + name + ", teachers_id=" + teachers_id + "]";
	}

}
