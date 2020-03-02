package edu.mum.cs.cs544.exercises.a;

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

            //create new instance of employee
            Customer c1 = new Customer("Andrew", "Gembes");

            //create new instances of Laptops
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            Order o1 = new Order(24, c1, df.parse("06/12/2020"));
            Order o2 = new Order(25, c1, df.parse("07/12/2020"));
            Order o3 = new Order(26, c1, df.parse("08/12/2020"));

            c1.addOrder(o1);
            c1.addOrder(o2);
            c1.addOrder(o3);

            Product p1 = new Product("Phone", "Samsung");
            Product p2 = new Product("Car", "Tesla");
            Product p3 = new Product("Book", "The Rise");

            OrderLine ol1 = new OrderLine(34, p1);
            OrderLine ol2 = new OrderLine(35, p2);
            OrderLine ol3 = new OrderLine(36, p3);

            o1.addOrderline(ol1);
            o1.addOrderline(ol2);
            o1.addOrderline(ol3);

            session.persist(c1);

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

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all owners
            @SuppressWarnings("unchecked")
            List<Product> productList = session.createQuery("from Product").list();
            for (Product p : productList) {
                System.out.println("Product_Name= " + p.getName() + ", Description= "
                        + p.getDescription());
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
    }

}
