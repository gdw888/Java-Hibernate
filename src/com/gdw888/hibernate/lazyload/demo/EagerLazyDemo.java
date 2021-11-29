package com.gdw888.hibernate.lazyload.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.lazyload.entity.Course;
import com.gdw888.hibernate.lazyload.entity.Instructor;
import com.gdw888.hibernate.lazyload.entity.InstructorDetail;
import com.gdw888.hibernate.lazyload.entity.Student;

public class EagerLazyDemo {

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
	
			System.out.println(instructor);
			
			
			System.out.println("Commit and done");
			
			for (Course course: instructor.getCourses()) {
				System.out.println(course);
			}
			
			session.getTransaction().commit();
			
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
