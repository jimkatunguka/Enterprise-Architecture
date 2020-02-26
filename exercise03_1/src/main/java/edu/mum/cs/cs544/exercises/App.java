package edu.mum.cs.cs544.exercises;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class App {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static  void main(String[] args) {
        //Hibernate properties
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //create new instance of Owner
            Owner owner1 = new Owner("Jim", "Fairfield");
            Owner owner2 = new Owner("James", "Igaba");

            //create new instances of car
            Car car1 = new Car("BMW", "2015", 35000, owner1);
            Car car2 = new Car("Mercedes", "2013", 100000, owner2);
            Car car3 = new Car("Toyota", "2003", 10000, owner2);

            //save the cars
            session.persist(car1);
            session.persist(car2);
            session.persist(car3);

            //retrieve the cars and their owners.
            List<Car> cars = (List<Car>) session.createQuery("from Car").list();
            cars.forEach(c -> {
                System.out.println("Brand: " + c.getBrand() + " Year: " + c.getYear()
                + " Owner: " + c.getOwner().getName() + " Address " + c.getOwner().getAddress());
            });


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
    }}
