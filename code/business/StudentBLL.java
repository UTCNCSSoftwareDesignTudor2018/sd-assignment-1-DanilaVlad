package business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import data.StudentDAO;
import data.StudentEntity;

public class StudentBLL {

	private static List<Validator<StudentEntity>> validators;

	public StudentBLL() {
		validators = new ArrayList<Validator<StudentEntity>>();
		validators.add(new NameValidator());
	}

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

	public static ResultSet viewInfo() {
		return StudentDAO.display();

	}

	public static ArrayList<String> login(String username, String password) {
		return StudentDAO.login(username, password);
	}

	public void editInfo(int id, String name, String card, String personal_num, String address) {

		StudentEntity student = new StudentEntity();
		student.setName(name);
		student.setCard_nr(card);
		student.setPersonal_numerical_code(personal_num);
		student.setAddress(address);
		student.setIdstudent(id);
		StudentDAO.editInfo(student);
	}

	public void editGroup(int id, String group) {
		StudentEntity student = new StudentEntity();
		student.setGroup(group);
		student.setIdstudent(id);
		StudentDAO.editGroup(student);
	}

	public static ResultSet viewProfile() {
		return StudentDAO.viewProfile();

	}

	public static void addProfileData(int id, String name, String card, String personal_num, String address,
		String group, String username, String password) {
		StudentEntity student = new StudentEntity();
		student.setIdstudent(id);
		student.setName(name);
		student.setCard_nr(card);
		student.setPersonal_numerical_code(personal_num);
		student.setAddress(address);
		student.setGroup(group);
		student.setUsername(username);
		student.setPassword(password);
		System.out.println(student.toString());
		createProfile(student);
	}

	public static void createProfile(StudentEntity student) {
		
		NameValidator v= new NameValidator();
		System.out.println(v.validate(student));
			v.validate(student);
		StudentDAO.createProfile(student);
	}
	
}
