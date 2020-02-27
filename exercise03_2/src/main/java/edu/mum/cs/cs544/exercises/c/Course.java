package edu.mum.cs.cs544.exercises.c;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int coursenumber;
    private String name;
    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST)
    private List<Student> students;

    public Course() {
    }

    public Course(int coursenumber, String name) {
        this.coursenumber = coursenumber;
        this.name = name;
        this.students = students;
        students = new ArrayList<>();
    }

    public int getCoursenumber() {
        return coursenumber;
    }

    public void setCoursenumber(int coursenumber) {
        this.coursenumber = coursenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
