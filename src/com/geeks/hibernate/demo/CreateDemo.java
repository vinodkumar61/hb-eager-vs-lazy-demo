package com.geeks.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.geeks.hibernate.demo.entity.Instructor;
import com.geeks.hibernate.demo.entity.InstructorDetail;
import com.geeks.hibernate.demo.entity.Student;

public class CreateDemo {

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
	         // create the objects
			/*
			Instructor tempInstructor = new Instructor("chad","darby","darby@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.geeks.com/youtube","Luv 2 code!!");
			
			*/
			
			
			 // create the objects
			Instructor tempInstructor = new Instructor("vinod","kumar","vinod.kumar@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.bheelgeeks.com/youtube","cricket!!");
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			 // save the instructor
			// Note: this will also save the details object
			// because of CascadeType.ALL
			
			System.out.println("Saving instructor: "+ tempInstructor);
			 session.save(tempInstructor);
			
			// commit transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
			 
		} finally {
			factory.close();
		}

	}

}
