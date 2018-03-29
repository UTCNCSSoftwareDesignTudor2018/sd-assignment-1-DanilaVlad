package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.EnrollmentBLL;
import business.StudentBLL;
import business.TeacherBLL;

public class TeacherController {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public JButton viewProfileBtn;
	public JButton enrollBtn;
	public JButton saveBtn;
	public JButton saveEnrollBtn;
	public JButton saveDeleteBtn;
	public JButton generateReportBtn;
	public JButton editProfileBtn;
	public JButton deleteEnrollBtn;
	public JButton saveDeleteEnrollBtn;
	public JButton getReportBtn;
	public JTextField idStudentText;
	public JTextField idCourseText;
	public JTextField idCourseReportText;
	public JTextField idExamDeleteText;
	public JTextField groupText;
	public JTextField gradeText;
	public JTextField idStudentEnrollText;
	public JTextField idCourseEnrollText;
	public JTextField idExamEnrollText;
	public static JTable tableTeacher;
	public JPanel panelTeacher;

	public TeacherController() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {
		panelTeacher = new JPanel();
		panelTeacher.setBounds(0, 0, 950, 357);
		panelTeacher.setLayout(null);

		viewProfileBtn = new JButton("View Students Profile");
		viewProfileBtn.setBounds(10, 50, 180, 20);
		panelTeacher.add(viewProfileBtn);

		enrollBtn = new JButton("Enroll student");
		enrollBtn.setBounds(202, 220, 160, 20);
		panelTeacher.add(enrollBtn);

		deleteEnrollBtn = new JButton("Delete enroll");
		deleteEnrollBtn.setBounds(700, 20, 160, 20);
		panelTeacher.add(deleteEnrollBtn);

		idExamDeleteText = new JTextField("idExam");
		idExamDeleteText.setBounds(700, 60, 160, 20);

		saveDeleteBtn = new JButton("Save delete");
		saveDeleteBtn.setBounds(700, 90, 160, 20);

		generateReportBtn = new JButton("Generate report");
		generateReportBtn.setBounds(700, 130, 160, 20);
		panelTeacher.add(generateReportBtn);

		idCourseReportText = new JTextField("idCourse");
		idCourseReportText.setBounds(700, 150, 160, 20);

		getReportBtn = new JButton("Get Report");
		getReportBtn.setBounds(700, 180, 160, 20);

		idStudentEnrollText = new JTextField("idStudent");
		idStudentEnrollText.setBounds(190, 250, 100, 20);

		idCourseEnrollText = new JTextField("idCourse");
		idCourseEnrollText.setBounds(320, 250, 100, 20);

		idExamEnrollText = new JTextField("idExam");
		idExamEnrollText.setBounds(260, 271, 100, 20);

		editProfileBtn = new JButton("Edit Students Profile");
		editProfileBtn.setBounds(10, 20, 180, 20);
		panelTeacher.add(editProfileBtn);

		tableTeacher = new JTable();
		tableTeacher.setBounds(200, 30, 456, 188);

		saveBtn = new JButton("Save");
		saveBtn.setBounds(50, 250, 100, 20);

		saveEnrollBtn = new JButton("Save enroll");
		saveEnrollBtn.setBounds(440, 250, 110, 20);

		idStudentText = new JTextField("idStudent");
		idStudentText.setBounds(50, 125, 100, 20);
		idCourseText = new JTextField("idCourse");
		idCourseText.setBounds(50, 150, 100, 20);
		groupText = new JTextField("group");
		groupText.setBounds(50, 175, 100, 20);
		gradeText = new JTextField("grade");
		gradeText.setBounds(50, 200, 100, 20);

		enrollBtn.addActionListener(new ActionListener() {
			int studentId = 0;
			int courseId = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == enrollBtn) {
					panelTeacher.add(saveEnrollBtn);
					panelTeacher.add(idStudentEnrollText);
					panelTeacher.add(idCourseEnrollText);
					panelTeacher.add(idExamEnrollText);
					saveEnrollBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							studentId = Integer.parseInt(idStudentEnrollText.getText());
							courseId = Integer.parseInt(idCourseEnrollText.getText());

							EnrollmentBLL.addEnrollment(studentId, courseId);

							DefaultTableModel dm;
							try {
								dm = StudentBLL.buildTableModel(StudentBLL.viewProfile());
								tableTeacher.setModel(dm);

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});

					panelTeacher.add(tableTeacher);

					StudentController.frame.repaint();

				}

			}
		});

		generateReportBtn.addActionListener(new ActionListener() {
			int courseId = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == generateReportBtn) {
					panelTeacher.add(getReportBtn);
					panelTeacher.add(idCourseReportText);
					getReportBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							courseId = Integer.parseInt(idCourseReportText.getText());

							try {
								TeacherBLL.findStudentsByCourse(courseId);
							} catch (UnsupportedEncodingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (FileNotFoundException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

						}
					});

					panelTeacher.add(tableTeacher);

					StudentController.frame.repaint();

				}

			}
		});

		deleteEnrollBtn.addActionListener(new ActionListener() {
			int examId = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deleteEnrollBtn) {
					panelTeacher.add(idExamDeleteText);
					panelTeacher.add(saveDeleteBtn);

					saveDeleteBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							examId = Integer.parseInt(idExamDeleteText.getText());
							EnrollmentBLL.deleteEnrollment(examId);
							DefaultTableModel dm;
							try {
								dm = StudentBLL.buildTableModel(StudentBLL.viewProfile());
								tableTeacher.setModel(dm);

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					panelTeacher.add(tableTeacher);

					StudentController.frame.repaint();

				}

			}
		});

		viewProfileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewProfileBtn) {
					DefaultTableModel dm;
					try {
						dm = StudentBLL.buildTableModel(StudentBLL.viewProfile());
						tableTeacher.setModel(dm);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					panelTeacher.add(tableTeacher);

					StudentController.frame.repaint();

				}

			}
		});

		editProfileBtn.addActionListener(new ActionListener() {
			int studentId = 0;
			int courseId = 0;
			String group = "";
			int grade = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == editProfileBtn) {
					panelTeacher.add(saveBtn);
					panelTeacher.add(idStudentText);
					panelTeacher.add(idCourseText);
					panelTeacher.add(groupText);
					panelTeacher.add(gradeText);

					saveBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							group = groupText.getText();
							grade = Integer.parseInt(gradeText.getText());
							studentId = Integer.parseInt(idStudentText.getText());
							courseId = Integer.parseInt(idCourseText.getText());

							StudentBLL student = new StudentBLL();
							student.editGroup(studentId, group);

							EnrollmentBLL enrollment = new EnrollmentBLL();
							enrollment.changeGrade(studentId, courseId, grade);

							DefaultTableModel dm;
							try {
								dm = StudentBLL.buildTableModel(StudentBLL.viewProfile());
								tableTeacher.setModel(dm);

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});

					panelTeacher.add(tableTeacher);

					StudentController.frame.repaint();

				}

			}
		});
	}
}
