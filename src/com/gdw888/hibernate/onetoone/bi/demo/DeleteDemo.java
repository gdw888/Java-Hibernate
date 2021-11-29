package com.gdw888.hibernate.onetoone.bi.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gdw888.hibernate.onetoone.bi.entity.Instructor;
import com.gdw888.hibernate.onetoone.bi.entity.InstructorDetail;
import com.gdw888.hibernate.onetoone.bi.entity.Student;

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

			List<InstructorDetail> instructorDetails = session.createQuery("from InstructorDetail").getResultList();
			
			for (InstructorDetail instructorDetail : instructorDetails) {
				//remove associated object
				instructorDetail.getInstructor().setInstructorDetail(null);
				
				session.delete(instructorDetail);
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
