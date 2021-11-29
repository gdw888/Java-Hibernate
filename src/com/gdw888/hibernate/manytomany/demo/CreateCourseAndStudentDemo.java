package com.gdw888.hibernate.manytomany.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.manytomany.entity.Course;
import com.gdw888.hibernate.manytomany.entity.Instructor;
import com.gdw888.hibernate.manytomany.entity.InstructorDetail;
import com.gdw888.hibernate.manytomany.entity.Review;
import com.gdw888.hibernate.manytomany.entity.Student;

public class CreateCourseAndStudentDemo {

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
		
	
			Course course1 = new Course("CS101");
			Course course2 = new Course("MATH101");
			Course course3 = new Course("SCIENCE101");
			
			//session.save(course1);
			//session.save(course2);
			//session.save(course3);
			
			//Student student1 = new Student("Jon", "Doe", "gmail.com");
			Student student = new Student("erin", "park", "gmail.com");
 
			//course1.addStudent(student1);
			//course1.addStudent(student2);
			
			List<Course> courses = session.createQuery("from Course c where c.title like '%MATH%'", Course.class).getResultList();
			student.setCourses(courses);
			
			//session.save(student1);
			session.save(student);
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
