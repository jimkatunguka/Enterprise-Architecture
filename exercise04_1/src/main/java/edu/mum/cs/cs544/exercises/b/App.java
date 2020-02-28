package edu.mum.cs.cs544.exercises.b;

import edu.mum.cs.cs544.exercises.a.Employee;
import edu.mum.cs.cs544.exercises.a.Laptop;
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

            //create new instances of Flight
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            Flight f1 = new Flight(30, "EBB", "BBR",df.parse("04/02/2003") );
            Flight f2 = new Flight(02, "BBR", "ORD",df.parse("07/12/2013") );
            Flight f3 = new Flight(30, "ORD", "CID",df.parse("12/28/2021") );

            //create new instance of employee
            Passenger pass1 = new Passenger("Andrew Gembes", f1);
            Passenger pass2 = new Passenger("Jim Kats", f3);

            pass1.addFlight(f2);
            pass1.addFlight(f3);
            session.persist(pass1);
            session.persist(pass2);

            tx.commit();

        }catch (HibernateException | ParseException e) {
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
