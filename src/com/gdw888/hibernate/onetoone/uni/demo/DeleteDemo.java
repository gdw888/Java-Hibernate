package com.gdw888.hibernate.onetoone.uni.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.onetoone.uni.entity.Instructor;
import com.gdw888.hibernate.onetoone.uni.entity.InstructorDetail;
import com.gdw888.hibernate.onetoone.uni.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			InstructorDetail tempInstructor = session.get(InstructorDetail.class, 3);
			session.delete(tempInstructor);
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		finally {
			factory.close();
		}
	}

}
