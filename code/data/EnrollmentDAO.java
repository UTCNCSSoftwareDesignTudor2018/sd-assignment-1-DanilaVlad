package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollmentDAO {
	protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

	private static final String selectEnrollmentsStatementString = "SELECT enrollments.exams_id FROM enrollments WHERE enrollments.students_id=? AND enrollments.courses_id = ?";
	private static final String insertStatementString = "INSERT INTO enrollments (enrollments.students_id, enrollments.courses_id)"
			+ " VALUES (?,?)";
	private static final String deleteStatementString = "DELETE FROM enrollments WHERE exams_id = ?";

	public static int selectEnroll(int studentId, int courseId) {
		Connection dbConnection = DBConnection.getConnection();
		int examId = -1;
		PreparedStatement selectEnrollmentsStudentStatement = null;

		try {
			selectEnrollmentsStudentStatement = dbConnection.prepareStatement(selectEnrollmentsStatementString,
					Statement.RETURN_GENERATED_KEYS);
			selectEnrollmentsStudentStatement.setInt(1, studentId);
			selectEnrollmentsStudentStatement.setInt(2, courseId);
			ResultSet rs = selectEnrollmentsStudentStatement.executeQuery();
			if (rs.next()) {
				examId = rs.getInt("exams_id");
				return examId;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrollmentDAO:selectEnroll " + e.getMessage());
		}
		return 0;
	}

	public static void addEnrollment(EnrollmentEntity enrollment) {
		Connection dbConnection = DBConnection.getConnection();

		PreparedStatement insertStatement = null;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setLong(1, enrollment.getStudents_id());
			insertStatement.setLong(2, enrollment.getCourses_id());

			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrollmentDAO:insert " + e.getMessage());
		}
	}

	public static void deleteEnrollment(EnrollmentEntity enrollment) {
		Connection dbConnection = DBConnection.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, enrollment.getExams_id());
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		}
	}
}
