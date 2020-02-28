package edu.mum.cs.cs544.exercises.e;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Hello world!
 *
 */
public class App {
    private static SessionFactory sessionFactory;

    static {
        // This step will read hibernate.cfg.xml and prepare hibernate for use
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Reservation.class);
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(sr);
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        //insert two sample customers on database
        InsertData(session,tx);
        //Show data to the console
        retriveData(session,tx);


        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
    }



    private static void retriveData(Session session, Transaction tx) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            // retrieve all customers
            List<Customer> customerList = session.createQuery("from Customer").list();
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

    }

    private static void InsertData(Session session, Transaction tx) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Customer customer1=new Customer("Jim Kats");

            Reservation reservation1 = new Reservation(LocalDate.of(2020, 2, 11));
            Reservation reservation2 = new Reservation(LocalDate.of(2020, 2, 29));
            customer1.addReservation(reservation1);
            customer1.addReservation(reservation2);

            Book book1=new Book("201939-10","Spring MVC","Jim Kats");
            reservation1.setBook(book1);
            reservation2.setBook(book1);

            // save the customers
            session.persist(customer1);


            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }

    }
}