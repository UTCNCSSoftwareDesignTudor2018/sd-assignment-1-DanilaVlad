package data;

public class ExamEntity {
	private int idexam;
	private int grade;

	public ExamEntity() {
		super();
	}

	public ExamEntity(int idexam, int grade) {
		super();
		this.idexam = idexam;
		this.grade = grade;
	}

	public int getIdexam() {
		return idexam;
	}

	public void setIdexam(int idexam) {
		this.idexam = idexam;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "ExamEntity [idexam=" + idexam + ", grade=" + grade + "]";
	}

}
