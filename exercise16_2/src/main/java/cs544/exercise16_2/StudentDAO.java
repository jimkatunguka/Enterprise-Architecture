package cs544.exercise16_2;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.Collection;

public class StudentDAO {
	private SessionFactory sf = HibernateUtil.getSessionFactory();

	private Collection<Student> studentlist = new ArrayList<Student>();

	public StudentDAO() {
		Student student = new Student(11334, "Frank", "Brown");
		Course course1 = new Course(1101, "Java", "A");
		Course course2 = new Course(1102, "Math", "B-");
		student.addCourse(course1);
		student.addCourse(course2);
		studentlist.add(student);

	}

	public Student load(long studentid) {
//		for (Student student : studentlist) {
//			if (student.getStudentid() == studentid) {
//				return student;
//			}
//		}
//		return null;
		return (Student) sf.getCurrentSession().get(Student.class, studentid);
	}
}
