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

public class AddCoursesForTerryDemo {

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
			
			Student mary = session.get(Student.class, 2);
 
			Course course = new Course("Game101");
			Course course2 = new Course("youtube101");
			course.addStudent(mary);
			course2.addStudent(mary);
			
			//session.save(student1);
			session.save(course);
			session.save(course2);
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
