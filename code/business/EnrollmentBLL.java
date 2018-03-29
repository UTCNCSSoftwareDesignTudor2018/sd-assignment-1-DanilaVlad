package business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import data.EnrollmentDAO;
import data.EnrollmentEntity;
import data.ExamDAO;
import data.ExamEntity;

public class EnrollmentBLL {

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> colName = new Vector<String>();
		int colCount = metaData.getColumnCount();
		for (int column = 0; column < colCount; column++) {
			colName.add(metaData.getColumnName(column + 1));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int colIndex = 0; colIndex < colCount; colIndex++) {
				vector.add(rs.getObject(colIndex + 1));
			}
			data.add(vector);
		}
		DefaultTableModel table = new DefaultTableModel(data, colName);
		return table;
	}

	public void changeGrade(int studentId, int courseId, int grade) {
		int examId;
		examId = EnrollmentDAO.selectEnroll(studentId, courseId);
		ExamEntity exam = new ExamEntity();
		exam.setIdexam(examId);
		exam.setGrade(grade);
		ExamDAO.changeGrade(exam);
	}

	public static void addEnrollment(int studentId, int courseId) {
		EnrollmentEntity enrollment = new EnrollmentEntity();
		enrollment.setStudents_id(studentId);
		enrollment.setCourses_id(courseId);
		createEnroll(enrollment);
	}

	public static void createEnroll(EnrollmentEntity enrollment) {
		EnrollmentDAO.addEnrollment(enrollment);

	}

	public static void deleteEnrollment(int examId) {
		EnrollmentEntity enrollment = new EnrollmentEntity();
		enrollment.setExams_id(examId);
		EnrollmentDAO.deleteEnrollment(enrollment);

	}
}
