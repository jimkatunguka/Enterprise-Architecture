package edu.mum.cs.cs544.exercises.c;

import edu.mum.cs.cs544.exercises.b.Flight;
import edu.mum.cs.cs544.exercises.b.Passenger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

public class App {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[]args){
        //Hibernate properties
        Session session = null;
        Transaction tx = null;


        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //create new instances of Student
            Student s1 = new Student(30, "Thomas", "Andrew" );
            Student s2 = new Student(50, "Keith", "Jenkins" );
            Student s3 = new Student(90, "Adele", "Adeola" );


            //create a new instance of School
            School sc1 = new School("MIU", s1);
            School sc2 = new School("MUM", s3);

            //add students to the map in school.
            sc1.addStudent(s1);
            sc1.addStudent(s2);
            sc1.addStudent(s3);

            //save the school
            session.persist(sc1);
            session.persist(sc2);

            tx.commit();

        }catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
