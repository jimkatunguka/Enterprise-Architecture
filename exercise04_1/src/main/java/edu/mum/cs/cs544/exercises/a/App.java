package edu.mum.cs.cs544.exercises.a;

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

            //create new instances of Laptops
            Laptop l1 = new Laptop("HP", "Envy");
            Laptop l2 = new Laptop("Dell", "Convertible");
            Laptop l3 = new Laptop("HP", "Spectra");

            //create new instance of employee
            Employee emp1 = new Employee("Andrew", "Gembes", l1);
            emp1.addLaptop(l2);
            emp1.addLaptop(l3);
            session.persist(emp1);

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
