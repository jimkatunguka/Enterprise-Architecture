package edu.mum.cs.cs544.exercises.f;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class App {

    public static SessionFactory  sessionFactory;
    static {
        // This step will read hibernate.cfg.xml and prepare hibernate for use
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Office.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(sr);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Session session=null;
        Transaction tx=null;



        System.out.println("=====================================================");
        System.out.println("-------session 1: opened----------");
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Employee emp1 =new Employee("Jim");
            Employee emp2 =new Employee("Nurlan");
            Employee emp3 =new Employee("Jemo");

            Department dep= new Department("computer");
            dep.addEmp(emp1);
            dep.addEmp(emp2);
            dep.addEmp(emp3);

            Office of= new Office(227,"Building-144");
            of.addEmployee(emp1);
            of.addEmployee(emp1);
            of.addEmployee(emp2);
            of.addEmployee(emp3);



            session.persist(dep);


            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }




        System.out.println("=======================List of Employees==============================");

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            // retrieve all persons
            List<Employee> emps = session.createQuery("from Employee").list();
            System.out.println("List of Person: ");
            for (Employee e : emps) {
                System.out.println(e);
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

}

