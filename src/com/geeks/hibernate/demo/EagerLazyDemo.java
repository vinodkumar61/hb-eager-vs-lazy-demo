package com.geeks.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.geeks.hibernate.demo.entity.Course;
import com.geeks.hibernate.demo.entity.Instructor;
import com.geeks.hibernate.demo.entity.InstructorDetail;
import com.geeks.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factory
		 SessionFactory factory= new Configuration()
				 .configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Instructor.class)
				 .addAnnotatedClass(InstructorDetail.class)
				 .addAnnotatedClass(Course.class)
				 .buildSessionFactory();
		// create session 
		Session session = factory.getCurrentSession();
		try {
	        						
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db 
			int theId=1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Geeks: Instructor: " +tempInstructor);
			
			System.out.println("Geeks: Courses: "+tempInstructor.getCourses());
	
			// commit transaction
			 session.getTransaction().commit();
			
			 // close the session
			 session.close();
			 
			 System.out.println("\nGeeks: The session is now closed!\n");
			 // option 1: call getter method while session is open
			 
			 // since courses are lazy loaded.... this should fail
			// get course of the instructor
				System.out.println("Geeks: Courses: "+tempInstructor.getCourses());
			 
			 System.out.println("Geeks: Done!");
			 
		} finally {
			
			// add clean up code 
			session.close();
			factory.close();
		}

	}

}
