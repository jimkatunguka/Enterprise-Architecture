package cs544.exercise16_2;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {
	private StudentDAO studentdao;

	SessionFactory sf = HibernateUtil.getSessionFactory();
	public StudentService() {

		studentdao = new StudentDAO();
	}

	public Student getStudent(long studentid) {
		return studentdao.load(studentid);
	}
}
