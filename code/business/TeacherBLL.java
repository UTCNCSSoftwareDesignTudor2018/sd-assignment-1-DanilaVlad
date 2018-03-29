package business;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import data.StudentDAO;
import data.StudentEntity;
import data.TeacherDAO;

public class TeacherBLL {

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

	public static ArrayList<String> login(String username, String password) {
		return TeacherDAO.login(username, password);
	}

	public static ResultSet viewProfile() {
		return TeacherDAO.viewProfile();

	}

	public void editProfile(int id, String group) {
		StudentEntity student = new StudentEntity();
		student.setGroup(group);
		student.setIdstudent(id);
		StudentDAO.editInfo(student);
	}

	public static void findStudentsByCourse(int idCourse)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		ArrayList<StudentEntity> list = new ArrayList<StudentEntity>();
		list = TeacherDAO.findStudentsByCourse(idCourse);
		generateReport(list);
	}

	public static void generateReport(ArrayList<StudentEntity> list)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		String fileName = "Report" + ".txt";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).toString() + "\n");
			}
		}

	}
}
