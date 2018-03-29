package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamDAO {

	protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());
	private static final String editStatementString = "UPDATE exams SET exams.grade = ? WHERE exams.idexam = ?";

	public static void changeGrade(ExamEntity exam) {
		Connection dbConnection = DBConnection.getConnection();
		PreparedStatement editStatement = null;
		try {
			editStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setInt(1, exam.getGrade());
			editStatement.setInt(2, exam.getIdexam());
			editStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ExamDAO:edit " + e.getMessage());
		}
	}
}
