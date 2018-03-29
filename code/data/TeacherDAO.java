package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherDAO {
	protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

	private static final String displayProfileString = "SELECT students.name, students.group, courses.name, exams.grade FROM students, courses, exams, enrollments ";
	private static final String loginStatementTeacherString = "SELECT teachers.username,teachers.password FROM teachers where username=? and password=?";
	private static final String editStudentStatementString = "UPDATE students SET students.group =?  WHERE students.idstudent = ?";
	private static final String findStudentStatementString = "SELECT * FROM students JOIN enrollments ON students.idstudent = enrollments.students_id WHERE enrollments.courses_id = ?";

	public static ResultSet viewProfile() {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement displayProfileStatement = null;
		ResultSet rs = null;
		try {
			displayProfileStatement = dbConnection.prepareStatement(displayProfileString);
			rs = displayProfileStatement.executeQuery();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:viewProfile " + e.getMessage());
		}
		return rs;
	}

	public static ArrayList<String> login(String username, String password) {
		Connection dbConnection = DBConnection.getConnection();
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement loginTeacherStatement = null;

		try {
			loginTeacherStatement = dbConnection.prepareStatement(loginStatementTeacherString,
					Statement.RETURN_GENERATED_KEYS);
			loginTeacherStatement.setString(1, username);
			loginTeacherStatement.setString(2, password);
			ResultSet rs = loginTeacherStatement.executeQuery();
			if (rs.next()) {
				System.out.println("Teacher matched");
				result.add("t");
				result.add(username);
				result.add(password);
				return result;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDAO:login " + e.getMessage());
		}
		return null;
	}

	public static void editProfile(StudentEntity student) {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement editStatement = null;
		try {
			editStatement = dbConnection.prepareStatement(editStudentStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setString(1, student.getGroup());
			editStatement.setInt(2, student.getIdstudent());
			editStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeachertDAO:edit " + e.getMessage());
		}
	}

	public static ArrayList<StudentEntity> findStudentsByCourse(int courseId) {
		Connection dbConnection = DBConnection.getConnection();
		ArrayList<StudentEntity> result = new ArrayList<StudentEntity>();
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findStudentStatementString, Statement.RETURN_GENERATED_KEYS);
			findStatement.setInt(1, courseId);
			ResultSet rs = findStatement.executeQuery();
			while (rs.next()) {
				StudentEntity student = new StudentEntity(rs.getInt("idstudent"), rs.getString("name"),
						rs.getString("card_nr"), rs.getString("personal_numerical_code"), rs.getString("address"),
						rs.getString("group"), rs.getString("username"), rs.getString("password"));
				result.add(student);
			}
			return result;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeachertDAO:edit " + e.getMessage());
		}
		return result;
	}

}
