package com.andy.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.andy.cruddemo.dao.StudentDAO;
import com.andy.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandlineRunner(StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			// readStudent(studentDAO);
			// queryForStudents(studentDAO);
			// queryForStudentsByLastName(studentDAO);
			// updateStudent(studentDAO);
			// deleteStudent(studentDAO);
			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		// Deleting all students
		int numRowsDeleted = studentDAO.deleteAll();
		System.err.println("Deleted rows: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student with id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on id
		int studentId = 1;
		System.out.println("Retrieving student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);
		// change first name to scooby
		System.out.println("Updating student...");
		myStudent.setFirstName("Paul");
		// update the student
		studentDAO.update(myStudent);
		// display the updated student
		System.err.println("Updated student: " + myStudent);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Duck");
		// display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();
		// display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

		// Save the student
		System.out.println("Saving student...");
		studentDAO.save(tempStudent);

		// Display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student abased on id
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
		Student tempStudent2 = new Student("Mary", "Public", "marry@gmail.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@gmail.com");

		// save the student object
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent = new Student("Paul", "Doe", "pauldoe@gmail.com");

		// save the student object
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
