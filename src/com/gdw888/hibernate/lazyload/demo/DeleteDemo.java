package com.gdw888.hibernate.lazyload.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.lazyload.entity.Course;
import com.gdw888.hibernate.lazyload.entity.Instructor;
import com.gdw888.hibernate.lazyload.entity.InstructorDetail;
import com.gdw888.hibernate.lazyload.entity.Student;

public class DeleteDemo {

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

			List<Course> courses = session.createQuery("from Course").getResultList();
			
			for (Course course : courses) {
				session.delete(course);
			}
						
			session.getTransaction().commit();
			System.out.println("Commit and done");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
