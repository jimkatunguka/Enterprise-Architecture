package edu.mum.cs.cs544.exercises;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class AppBook {
    private  static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


    public static void main(String[] args){
        //Hibernate placeholders
        Session session = null;
        Transaction tx =null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //create new instances of book and set values in it.
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            Book b1 = new Book("Spring Core", "123", "JK Kats", 300, df.parse("11/17/2016"));
            session.persist(b1);

            Book b2 = new Book("Spring Boot", "124", "JM Minor", 500, df.parse("04/14/2018"));
            session.persist(b2);

            Book b3 = new Book("Spring MVC", "125", "JV Vangard", 200, df.parse("02/30/2019"));
            session.persist(b3);

            tx.commit();

        }catch(HibernateException | ParseException e){
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        }finally{
            if(session != null) session.close();
        }

        //Retrieve all books and output them to the console
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Retrieve all books
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", author= " + book.getAuthor() + ", price= " + book.getPrice() + ", publish_date= "
                + book.getPublish_date());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        //Retrieve a book from the database and change its title and price.
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            @SuppressWarnings("unchecked")
            Book b1 = (Book) session.get(Book.class, 1);
            b1.setTitle("Spring Hibernate");
            b1.setPrice(100);
            session.update(b1);

            //Delete book
            Book b2 = (Book) session.load(Book.class, 2);
            session.delete(b2);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

       //Retrieve all books
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", author= " + book.getAuthor() + ", price= " + book.getPrice() + ", publish_date= "
                        + book.getPublish_date());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


        // Close the SessionFactory
        sessionFactory.close();
        System.exit(0);
    }
}
