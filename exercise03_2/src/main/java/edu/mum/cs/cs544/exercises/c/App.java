package edu.mum.cs.cs544.exercises.c;

import edu.mum.cs.cs544.exercises.b.Book;
import edu.mum.cs.cs544.exercises.b.Publisher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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

            //create new instance of Student
            Student s1 = new Student(123,"James",  "Twine");
            Student s2 = new Student(124,"Jerry",  "Tacos");
            Student s3 = new Student(125,"Jean",  "Moso");
            Student s4 = new Student(126,"Jim",  "Ayo");

            //create new instance of Course
            Course c1 = new Course(001, "EA");
            c1.addStudent(s1);
            c1.addStudent(s4);
            session.persist(c1);

            Course c2 = new Course(002, "WAP");
            c2.addStudent(s1);
            c2.addStudent(s2);
            c2.addStudent(s3);
            session.persist(c2);

            Course c3 = new Course(003, "WAA");
            c3.addStudent(s4);
            session.persist(c3);

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
