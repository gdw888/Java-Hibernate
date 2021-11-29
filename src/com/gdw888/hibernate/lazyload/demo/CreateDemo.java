package com.gdw888.hibernate.lazyload.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.lazyload.entity.Instructor;
import com.gdw888.hibernate.lazyload.entity.InstructorDetail;
import com.gdw888.hibernate.lazyload.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			InstructorDetail instructorDetail = new InstructorDetail("my youtube", "basketball"); 
			Instructor instructor = new Instructor("Terry","Lee", "terrylee@gmail.com"); 
			instructor.setInstructorDetail(instructorDetail);

			session.beginTransaction();
			
			session.save(instructor);
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			factory.close();
		}
	}

}
