package edu.mum.cs.cs544.exercises.c;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private int studentid;
    private String firstname;
    private  String lastname;
    @ManyToMany
    @JoinTable(name = "Student_Course",
    joinColumns = {@JoinColumn(name = "Student_id")},
    inverseJoinColumns = {@JoinColumn(name = "Course_id")})
    private List<Course> courses;

    public Student() {
    }

    public Student(int studentid, String firstname, String lastname) {
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
        courses = new ArrayList<>();
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
