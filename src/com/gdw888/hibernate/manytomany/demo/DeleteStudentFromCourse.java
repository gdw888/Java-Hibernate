package com.gdw888.hibernate.manytomany.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.manytomany.entity.Course;
import com.gdw888.hibernate.manytomany.entity.Instructor;
import com.gdw888.hibernate.manytomany.entity.InstructorDetail;
import com.gdw888.hibernate.manytomany.entity.Review;
import com.gdw888.hibernate.manytomany.entity.Student;

public class DeleteStudentFromCourse {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			Course youtubeCourse = session.get(Course.class, 11);

			List<Student> students = youtubeCourse.getStudents();
			
			for (Student student: students) {
				if (student.getFirstName().equals("terry")) {
					students.remove(student);
				}
			}
			System.out.println();
			
			session.save(youtubeCourse);
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
