//package edu.mum.cs.cs544.exercises.b;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//public class App {
//    private static final SessionFactory sessionFactory;
//    private static final ServiceRegistry serviceRegistry;
//
//    static {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//    }
//
//    public static void main(String[]args){
//        //Hibernate properties
//        Session session = null;
//        Transaction tx = null;
//
//
//        try{
//            session = sessionFactory.openSession();
//            tx = session.beginTransaction();
//
//            //create new instance of department
//            Publisher p1 = new Publisher("James Twine");
//
//            //create new instance of employee
//            Book bk1 = new Book(001, "The rise", "Gembes");
//            session.persist(bk1);
//
//            Book bk2 = new Book(002, "The fall", "Chiko");
//            bk2.setPublisher(p1);
//            session.persist(bk2);
//
//            Book bk3 = new Book(003, "The land", "Mange");
//            bk3.setPublisher(p1);
//            session.persist(bk3);
//
//            tx.commit();
//
//
//
//        }catch (HibernateException e) {
//            if (tx != null) {
//                System.err.println("Rolling back: " + e.getMessage());
//                tx.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
//}
