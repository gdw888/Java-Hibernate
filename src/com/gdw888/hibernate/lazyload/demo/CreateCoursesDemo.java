package com.gdw888.hibernate.lazyload.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.lazyload.entity.Course;
import com.gdw888.hibernate.lazyload.entity.Instructor;
import com.gdw888.hibernate.lazyload.entity.InstructorDetail;
import com.gdw888.hibernate.lazyload.entity.Student;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Instructor instructor = session.get(Instructor.class, 1);
	
			Course course1 = new Course("CS101");
			Course course2 = new Course("MATH101");
			Course course3 = new Course("SCIENCE101");
			
			instructor.addCourse(course1);
			instructor.addCourse(course2);
			instructor.addCourse(course3);

			session.save(course1);
			session.save(course2);
			session.save(course3);
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
