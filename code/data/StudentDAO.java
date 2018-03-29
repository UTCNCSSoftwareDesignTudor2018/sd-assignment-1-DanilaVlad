package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.DBConnection;

public class StudentDAO {

	protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
	private static final String displayStatementString = "SELECT students.idstudent,students.name,students.card_nr,students.personal_numerical_code,students.address FROM students";
	private static final String loginStatementStudentString = "SELECT students.username,students.password FROM students where username=? and password=?";

	private static final String editStatementString = "UPDATE students SET students.name = ?, students.card_nr = ?, students.personal_numerical_code = ?, students.address = ? WHERE students.idstudent = ?";
	private static final String editGroupStatementString = "UPDATE students SET students.group = ? WHERE students.idstudent = ?";

	private static final String displayProfileString = "SELECT students.name, students.group, courses.name, exams.grade FROM students, courses, exams, enrollments WHERE students.idstudent = enrollments.students_id AND enrollments.exams_id = exams.idexam AND enrollments.courses_id = courses.idcourse";
	private static final String insertStatementString = "INSERT INTO students (students.idstudent, students.name, students.card_nr, students.personal_numerical_code, students.address, students.group, students.username, students.password)"
			+ " VALUES (?,?,?,?,?,?,?,?)";

	public static ResultSet display() {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement displayStatement = null;
		ResultSet rs = null;
		try {
			displayStatement = dbConnection.prepareStatement(displayStatementString);
			rs = displayStatement.executeQuery();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:display " + e.getMessage());
		}
		return rs;
	}

	public static ArrayList<String> login(String username, String password) {
		Connection dbConnection = DBConnection.getConnection();
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement loginStudentStatement = null;

		try {
			loginStudentStatement = dbConnection.prepareStatement(loginStatementStudentString,
					Statement.RETURN_GENERATED_KEYS);
			loginStudentStatement.setString(1, username);
			loginStudentStatement.setString(2, password);
			ResultSet rs = loginStudentStatement.executeQuery();
			if (rs.next()) {
				System.out.println("Student matched");
				result.add("s");
				result.add(username);
				result.add(password);
				return result;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:login " + e.getMessage());
		}
		return null;
	}

	public static void editInfo(StudentEntity student) {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement editStatement = null;
		try {
			editStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setString(1, student.getName());
			editStatement.setString(2, student.getCard_nr());
			editStatement.setString(3, student.getPersonal_numerical_code());
			editStatement.setString(4, student.getAddress());
			editStatement.setInt(5, student.getIdstudent());
			editStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:edit " + e.getMessage());
		}
	}

	public static void editGroup(StudentEntity student) {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement editGroupStatement = null;
		try {
			editGroupStatement = dbConnection.prepareStatement(editGroupStatementString,
					Statement.RETURN_GENERATED_KEYS);
			editGroupStatement.setString(1, student.getGroup());
			editGroupStatement.setInt(2, student.getIdstudent());
			editGroupStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:editGroup " + e.getMessage());
		}
	}

	public static ResultSet viewProfile() {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement displayProfileStatement = null;
		ResultSet rs = null;
		try {
			displayProfileStatement = dbConnection.prepareStatement(displayProfileString);
			rs = displayProfileStatement.executeQuery();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:viewProfile " + e.getMessage());
		}
		return rs;
	}

	public static void createProfile(StudentEntity student) {
		Connection dbConnection = DBConnection.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setLong(1, student.getIdstudent());
			insertStatement.setString(2, student.getName());
			insertStatement.setString(3, student.getCard_nr());
			insertStatement.setString(4, student.getPersonal_numerical_code());
			insertStatement.setString(5, student.getAddress());
			insertStatement.setString(6, student.getGroup());
			insertStatement.setString(7, student.getUsername());
			insertStatement.setString(8, student.getPassword());

			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
		}
	}

}
