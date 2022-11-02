package com.geeks.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.geeks.hibernate.demo.entity.Instructor;
import com.geeks.hibernate.demo.entity.InstructorDetail;
import com.geeks.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create session factory
		 SessionFactory factory= new Configuration()
				 .configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Instructor.class)
				 .addAnnotatedClass(InstructorDetail.class)
				 .buildSessionFactory();
		// create session 
		Session session = factory.getCurrentSession();
		try {
	        
			// start the transaction 
			session.beginTransaction();
			
			// get instructor by primary key/id
			 int theId = 1;
			 Instructor tempInstructor=session.get(Instructor.class, theId);
			 
			 System.out.println("Found instructor: "+tempInstructor);
			// delete the instructors
			if (tempInstructor != null) {
				System.out.println("deleting: "+tempInstructor);
				
				// Note: will ALSO delete associated "detail" object
				// because of CascadeType.All
				// 
				session.delete(tempInstructor);
			}
			 
			 
			// commit transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
			 
		} finally {
			factory.close();
		}

	}

}
