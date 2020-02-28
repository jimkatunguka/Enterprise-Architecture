package edu.mum.cs.cs544.exercises.c;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "School_id")
    @MapKey(name = "studentid")
    Map<Integer, Student> map;

    public School() {
    }

    public School(String name, Student student) {
        this.name = name;
        map = new HashMap<>();
        addStudent(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Student> getMap() {
        return map;
    }

    public void addStudent(Student student) {
       map.put(student.getStudentid(), student);
    }
}
