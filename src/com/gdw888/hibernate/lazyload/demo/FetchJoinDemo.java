package com.gdw888.hibernate.lazyload.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.gdw888.hibernate.lazyload.entity.Course;
import com.gdw888.hibernate.lazyload.entity.Instructor;
import com.gdw888.hibernate.lazyload.entity.InstructorDetail;
import com.gdw888.hibernate.lazyload.entity.Student;

public class FetchJoinDemo {

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
			
			Query<Instructor> query = session.createQuery("select i from Instructor i " +
														  "JOIN FETCH i.courses where i.id=:theInstructorId",Instructor.class);
	
			query.setParameter("theInstructorId", 1);
			List<Instructor> instructors = query.getResultList();
			
			for ( Instructor instructor : instructors ) {
				System.out.println(instructor);
			}
			
		
			
			session.getTransaction().commit();
			
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
