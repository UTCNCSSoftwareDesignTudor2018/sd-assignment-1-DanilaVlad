package data;

public class EnrollmentEntity {
	private int idenrollment;
	private int students_id;
	private int courses_id;
	private int exams_id;

	public EnrollmentEntity() {
		super();
	}

	public EnrollmentEntity(int idenrollment, int students_id, int courses_id, int exams_id) {
		super();
		this.idenrollment = idenrollment;
		this.students_id = students_id;
		this.courses_id = courses_id;
		this.exams_id = exams_id;
	}

	public int getIdenrollment() {
		return idenrollment;
	}

	public void setIdenrollment(int idenrollment) {
		this.idenrollment = idenrollment;
	}

	public int getStudents_id() {
		return students_id;
	}

	public void setStudents_id(int students_id) {
		this.students_id = students_id;
	}

	public int getCourses_id() {
		return courses_id;
	}

	public void setCourses_id(int courses_id) {
		this.courses_id = courses_id;
	}

	public int getExams_id() {
		return exams_id;
	}

	public void setExams_id(int exams_id) {
		this.exams_id = exams_id;
	}

	@Override
	public String toString() {
		return "EnrollmentsEntity [idenrollment=" + idenrollment + ", students_id=" + students_id + ", courses_id="
				+ courses_id + ", exams_id=" + exams_id + "]";
	}

}
