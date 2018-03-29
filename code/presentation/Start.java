package presentation;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {
		StudentController s = new StudentController();
		s.frame.setVisible(true);

	}
}
