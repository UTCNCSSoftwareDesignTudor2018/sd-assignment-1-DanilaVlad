package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.StudentBLL;
import business.TeacherBLL;

public class StudentController {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static JFrame frame;
	public static JButton viewStudentInfo;
	public static JButton updateStudentInfo;
	public static JButton loginBtn;
	public static JButton createProfileBtn;
	public static JButton saveBtn;
	public static JButton viewProfileBtn;
	public static JTable tableStudent;
	public static JScrollPane scrollStudent;
	public static JTextField usernameText;
	public static JTextField passwordText;
	public static JTextField profileIdText;
	public static JTextField profileNameText;
	public static JTextField profileCardText;
	public static JTextField profilePersonalNrText;
	public static JTextField profileAddressText;
	public static JTextField profileGroupText;
	public static JTextField profileUsernameText;
	public static JTextField profilePasswordText;

	public static JPanel panelHome;
	public static JPanel panelStudent;

	public static JPanel loginPanel;
	public static JPanel createProfilePanel;
	public static JTextField nameText;
	public static JTextField cardText;
	public static JTextField personal_numText;
	public static JTextField addressText;
	public static JTextField idText;

	public StudentController() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		panelHome = new JPanel();
		panelHome.setBounds(0, 0, 950, 357);
		panelHome.setLayout(null);
		frame.add(panelHome);

		panelStudent = new JPanel();
		panelStudent.setBounds(0, 0, 630, 291);
		panelStudent.setLayout(null);

		tableStudent = new JTable();
		tableStudent.setBounds(200, 30, 456, 188);

		loginBtn = new JButton("Login");
		loginBtn.setBounds(80, 27, 144, 25);
		panelHome.add(loginBtn);

		createProfileBtn = new JButton("Create Profile");
		createProfileBtn.setBounds(80, 127, 144, 25);
		panelHome.add(createProfileBtn);

		usernameText = new JTextField();
		passwordText = new JPasswordField();
		loginPanel = new JPanel(new GridLayout(0, 1));
		loginPanel.add(new JLabel("Username:"));
		loginPanel.add(usernameText);
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(passwordText);

		loginBtn.addActionListener(new ActionListener() {
			String username = "";
			String password = "";

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == loginBtn) {

					int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						username = usernameText.getText();
						password = passwordText.getText();
					} else {
						System.out.println("Cancelled");
					}
					try {

						ArrayList<String> loginResult = StudentBLL.login(username, password);

						if (!loginResult.isEmpty()) {
							if ((username == loginResult.get(1) && password == loginResult.get(2))) {
								if (loginResult.get(0) == "s") {
									panelStudent.setVisible(true);
									panelHome.setVisible(false);
									loginPanel.setVisible(false);
									frame.getContentPane().add(panelStudent);
								}
							}
						}
					} catch (Exception e1) {
					}

					try {
						ArrayList<String> loginResult2 = TeacherBLL.login(username, password);
						if (!loginResult2.isEmpty()) {
							if (username == loginResult2.get(1) && password == loginResult2.get(2)) {
								if (loginResult2.get(0) == "t") {
									TeacherController t = new TeacherController();
									t.panelTeacher.setVisible(true);
									panelStudent.setVisible(false);
									panelHome.setVisible(false);
									loginPanel.setVisible(false);
									frame.getContentPane().add(t.panelTeacher);
								}

							}
						}
					} catch (Exception e2) {

					}
				}
			}
		});

		viewStudentInfo = new JButton("View info");
		viewStudentInfo.setBounds(12, 27, 144, 25);
		panelStudent.add(viewStudentInfo);

		viewStudentInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewStudentInfo) {
					DefaultTableModel dm;
					try {
						dm = StudentBLL.buildTableModel(StudentBLL.viewInfo());
						tableStudent.setModel(dm);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					panelStudent.add(tableStudent);

					frame.repaint();

				}

			}
		});

		updateStudentInfo = new JButton("Update info");
		updateStudentInfo.setBounds(12, 67, 144, 25);
		panelStudent.add(updateStudentInfo);

		saveBtn = new JButton("Save");
		saveBtn.setBounds(50, 250, 100, 20);

		idText = new JTextField();
		idText.setBounds(50, 125, 100, 20);
		nameText = new JTextField("name");
		nameText.setBounds(50, 150, 100, 20);
		cardText = new JTextField("card");
		cardText.setBounds(50, 175, 100, 20);
		personal_numText = new JTextField("personal_numerical_code");
		personal_numText.setBounds(50, 200, 100, 20);
		addressText = new JTextField("address");
		addressText.setBounds(50, 225, 100, 20);

		updateStudentInfo.addActionListener(new ActionListener() {
			String name = "";
			String card = "";
			String personal_num = "";
			String address = "";
			int id = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == updateStudentInfo) {
					panelStudent.add(saveBtn);
					panelStudent.add(nameText);
					panelStudent.add(cardText);
					panelStudent.add(personal_numText);
					panelStudent.add(addressText);
					panelStudent.add(idText);

					saveBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							name = nameText.getText();
							card = cardText.getText();
							personal_num = personal_numText.getText();
							address = addressText.getText();
							id = Integer.parseInt(idText.getText());
							StudentBLL student = new StudentBLL();
							student.editInfo(id, name, card, personal_num, address);
							DefaultTableModel dm;
							try {
								dm = StudentBLL.buildTableModel(StudentBLL.viewInfo());
								tableStudent.setModel(dm);

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});

					panelStudent.add(tableStudent);

					frame.repaint();

				}

			}
		});
		viewProfileBtn = new JButton("View Profile");
		viewProfileBtn.setBounds(200, 250, 150, 20);
		panelStudent.add(viewProfileBtn);

		viewProfileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewProfileBtn) {
					DefaultTableModel dm;
					try {
						dm = StudentBLL.buildTableModel(StudentBLL.viewProfile());
						tableStudent.setModel(dm);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					panelStudent.add(tableStudent);

					frame.repaint();

				}

			}
		});
		profileIdText = new JTextField();
		profileNameText = new JTextField();
		profileCardText = new JTextField();
		profilePersonalNrText = new JTextField();
		profileAddressText = new JTextField();
		profileGroupText = new JTextField();
		profileUsernameText = new JTextField();
		profilePasswordText = new JTextField();
		createProfilePanel = new JPanel(new GridLayout(0, 1));
		createProfilePanel.add(new JLabel("Id:"));
		createProfilePanel.add(profileIdText);
		createProfilePanel.add(new JLabel("Name:"));
		createProfilePanel.add(profileNameText);
		createProfilePanel.add(new JLabel("CardNr:"));
		createProfilePanel.add(profileCardText);
		createProfilePanel.add(new JLabel("PNC:"));
		createProfilePanel.add(profilePersonalNrText);
		createProfilePanel.add(new JLabel("Address:"));
		createProfilePanel.add(profileAddressText);
		createProfilePanel.add(new JLabel("Group:"));
		createProfilePanel.add(profileGroupText);
		createProfilePanel.add(new JLabel("Username:"));
		createProfilePanel.add(profileUsernameText);
		createProfilePanel.add(new JLabel("Password:"));
		createProfilePanel.add(profilePasswordText);

		createProfileBtn.addActionListener(new ActionListener() {
			int id = 0;
			String name = "";
			String card = "";
			String pnc = "";
			String address = "";
			String group = "";
			String username = "";
			String password = "";

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == createProfileBtn) {

					int result = JOptionPane.showConfirmDialog(null, createProfilePanel, "Create profile",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (result == JOptionPane.OK_OPTION) {
						id = Integer.parseInt(profileIdText.getText());
						name = profileNameText.getText();
						card = profileCardText.getText();
						pnc = profilePersonalNrText.getText();
						address = profileAddressText.getText();
						group = profileGroupText.getText();
						username = profileUsernameText.getText();
						password = profilePasswordText.getText();
					} else {
						System.out.println("Cancelled");
					}
					try {
						StudentBLL.addProfileData(id, name, card, pnc, address, group, username, password);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, "Invalid input!");
					}
				}
			}
		});
	}

}
