package com.hibernate.student.utils;

import java.util.List;
import java.util.Scanner;

import com.hibernate.student.entity.Course;
import com.hibernate.student.entity.Enrollment;
import com.hibernate.student.dao.CourseDao;
import com.hibernate.student.dao.CourseDaoImpl;
import com.hibernate.student.dao.EnrollmentDao;
import com.hibernate.student.dao.EnrollmentDaoImpl;
import com.hibernate.student.dao.StudentDao;
import com.hibernate.student.dao.StudentDaoImpl;
import com.hibernate.student.entity.Student;

public class Main {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final StudentDao studentDao = new StudentDaoImpl();
	private static final EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();
	private static final CourseDao courseDao = new CourseDaoImpl();

	// 💡 FIXED: Read integers safely via nextLine() to clear trailing newlines from the stream buffer
	private static int studentMenuList() {
		System.out.println("-------------------Student Menu---------------------");
		System.out.println("Enter: 0-Exit | 1-List | 2-Add | 3-Find | 4-Update | 5-Delete");
		System.out.print("Enter your choice: ");
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			return -1; 
		}
	}

	private static int enrollmentMenuList() {
		System.out.println("-------------------Enrollment Menu---------------------");
		System.out.println("Enter: 0-Exit | 1-Add Enrollment | 2-List All | 3-Find By Roll No");
		System.out.print("Enter your choice: ");
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			return -1;
		}
	}

	private static int courseMenuList() {
		System.out.println("-------------------Course Menu---------------------");
		System.out.println("Enter: 0-Exit | 1-Add Course | 2-List All | 3-Find By Code | 4-Update | 5-Delete");
		System.out.print("Enter your choice: ");
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			return -1;
		}
	}

	private static void courseManagement() {
		int courseChoice;
		try {
			while ((courseChoice = courseMenuList()) != 0) {
				switch (courseChoice) {
					case 1: {
						Course c = new Course();
						c.acceptCourseDetails(sc); // 💡 FIXED: Passing global scanner
						int rowsAffected = courseDao.addNewCourse(c);
						System.out.println("Rows Affected: " + rowsAffected);
						break;
					}
					case 2: {
						displayCourseList(courseDao.findAll());
						break;
					}
					case 3: {
						System.out.print("Enter Course Code: ");
						String courseCode = sc.nextLine(); // 💡 FIXED: Avoid sc.next()
						Course c1 = courseDao.findCourseById(courseCode);
						if (c1 != null) {
							Course.displayCourseDetails(c1);
						} else {
							System.out.println("Course not found.");
						}
						break;
					}
					case 4: {
						System.out.print("Enter Course Code: ");
						String courseCode = sc.nextLine();
						Course oldCourse = courseDao.findCourseById(courseCode);
						if (oldCourse != null) {
							Course c = new Course();
							c.acceptCourseDetails(sc);
							int rowsAffected = courseDao.updateCourse(c, courseCode);
							System.out.println("Rows Affected: " + rowsAffected);
						} else {
							System.out.println("Course code: " + courseCode + " is not found");
						}
						break;
					}
					case 5: {
						System.out.print("Enter Course Code: ");
						String courseCode = sc.nextLine();
						Course st = courseDao.findCourseById(courseCode);
						if (st != null) {
							int rowsAffected = courseDao.removeCourse(courseCode);
							System.out.println("Rows Affected: " + rowsAffected);
						} else {
							System.out.println("Course not found.");
						}
						break;
					}
					default:
						System.out.println("Invalid Choice");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void studentManagement() {
		int ch;
		try {
			while ((ch = studentMenuList()) != 0) {
				switch (ch) {
					case 1: {
						displayStudentList(studentDao.getStudentList());
						break;
					}
					case 2: {
						Student stud = new Student();
						stud.acceptStudentDetails(sc); // 💡 FIXED: Passing global scanner
						int rowsAffected = studentDao.addNewStudent(stud);
						System.out.println("Rows Affected: " + rowsAffected + " | New student added");
						break; // 💡 FIXED: Added missing critical break statement!
					}
					case 3: {
						System.out.print("Enter Roll No: ");
						String rollNo = sc.nextLine();
						Student student = studentDao.findStudentByRollNo(rollNo);
						if (student != null) {
							Student.displayStudentDetails(student);
						} else {
							System.out.println("Student with roll number " + rollNo + " is not found");
						}
						break;
					}
					case 4: {
						System.out.print("Enter Roll No: ");
						String rollNo = sc.nextLine();
						Student student = studentDao.findStudentByRollNo(rollNo);
						if (student != null) {
							Student s = new Student();
							s.acceptStudentDetails(sc);
							int rowsAffected = studentDao.editStudentDetails(s, rollNo);
							System.out.println("Rows Affected: " + rowsAffected);
						} else {
							System.out.println("Student with roll number " + rollNo + " is not found");
						}
						break;
					}
					case 5: {
						System.out.print("Enter Roll No: ");
						String rollNo = sc.nextLine();
						Student student = studentDao.findStudentByRollNo(rollNo);
						if (student != null) {
							int rowsAffected = studentDao.removeStudent(rollNo);
							System.out.println("Rows Affected: " + rowsAffected);
						} else {
							System.out.println("Student with roll number " + rollNo + " is not found");
						}
						break;
					}
					default:
						System.out.println("Invalid Choice");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void enrollmentManagement() {
		int enrollmentChoice;
		while ((enrollmentChoice = enrollmentMenuList()) != 0) {
			switch (enrollmentChoice) {
				case 1: {
					// 💡 FIXED: Orchestrating lookup safety logic directly inside Main console orchestrator
					System.out.print("Enter Student Roll Number: ");
					String rollNo = sc.nextLine();
					Student student = studentDao.findStudentByRollNo(rollNo);

					System.out.print("Enter Course Code: ");
					String courseCode = sc.nextLine();
					Course course = courseDao.findCourseById(courseCode);

					if (student == null || course == null) {
						System.out.println("❌ Invalid Roll Number or Course Code.");
						break;
					}

					Enrollment enrollment = new Enrollment();
					enrollment.setStudent(student);
					enrollment.setCourse(course);

					System.out.print("Enter Semester: ");
					enrollment.setSemester(sc.nextLine());

					System.out.print("Enter Grade (Leave blank if ongoing): ");
					String grade = sc.nextLine();
					enrollment.setGrade(grade.trim().isEmpty() ? null : grade);

					System.out.print("Enter Enrollment Status: ");
					enrollment.setEnrollmentStatus(sc.nextLine());

					int rowsAffected = enrollmentDao.addNewEnrollment(enrollment);
					System.out.println("Rows Affected: " + rowsAffected);
					break;
				}
				case 2: {
					displayEnrollment(enrollmentDao.findAll());
					break;
				}
				case 3: {
					System.out.print("Enter Roll Number: ");
					String enrollmentCode = sc.nextLine();
					displayEnrollment(enrollmentDao.findEnrollmentByRollNo(enrollmentCode));
					break;
				}
				default:
					System.out.println("Invalid Choice");
			}
		}
	}

	private static void displayEnrollment(List<Enrollment> enrollmentList) {
		if (enrollmentList == null || enrollmentList.isEmpty()) {
			System.out.println("No enrollments found.");
			return;
		}
		for (Enrollment e : enrollmentList) {
			Enrollment.displayEnrollmentDetails(e);
		}
	}

	private static void displayCourseList(List<Course> courseList) {
		if (courseList == null || courseList.isEmpty()) {
			System.out.println("No courses found.");
			return;
		}
		for (Course c : courseList) {
			Course.displayCourseDetails(c);
		}
	}

	private static void displayStudentList(List<Student> studentList) {
		if (studentList == null || studentList.isEmpty()) {
			System.out.println("No students found.");
			return;
		}
		for (Student s : studentList) {
			Student.displayStudentDetails(s);
		}
	}

	// 💡 Central Application Runner Loop
	public static void main(String[] args) {
		System.out.println("================ MASTER MANAGEMENT SYSTEM ================");
		System.out.println("1. Manage Students | 2. Manage Courses | 3. Manage Enrollments | 0. Exit");
		int systemChoice;
		try {
			while (true) {
				System.out.print("\nEnter System Choice: ");
				systemChoice = Integer.parseInt(sc.nextLine());
				if (systemChoice == 0) break;

				switch (systemChoice) {
					case 1: studentManagement(); break;
					case 2: courseManagement(); break;
					case 3: enrollmentManagement(); break;
					default: System.out.println("Invalid Choice");
				}
				System.out.println("\n================ MASTER MANAGEMENT SYSTEM ================");
				System.out.println("1. Manage Students | 2. Manage Courses | 3. Manage Enrollments | 0. Exit");
			}
		} catch (Exception e) {
			System.out.println("Application encountered an execution error.");
			e.printStackTrace();
		} finally {
			sc.close(); // Clean shutdown
		}
	}
}
