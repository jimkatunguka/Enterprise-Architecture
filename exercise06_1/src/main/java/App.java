import cs544.exercise06_1.model.Appointment;
import cs544.exercise06_1.model.Doctor;
import cs544.exercise06_1.model.Patient;
import cs544.exercise06_1.model.Payment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    public static void main(String[] args){
        Session session = null;
        Transaction tx = null;

        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Patient p = new Patient("John Doe", "100 Main Street", "23114", "23114");
            Doctor d = new Doctor("Eye Doctor", "Frank", "Brown");
            Payment pa = new Payment("12/06/08", 100);
            Appointment a = new Appointment("15/05/08", p, pa, d);

            session.persist(a);

            tx.commit();

        }catch(HibernateException e){
            if(tx != null){
                tx.rollback();
                e.printStackTrace(System.err);
            }
        }
        finally{
            if(session != null){
                session.close();
            }
        }

    }
}
