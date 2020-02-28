package edu.mum.cs.cs544.exercises.f;

import java.util.*;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy="department" ,cascade=CascadeType.ALL)

    private List<Employee> emp=new ArrayList<Employee>();

    public Department() {

    }

    public Department(String name) {
        super();
        this.name = name;

    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addEmp(Employee e) {
        emp.add(e);
        e.setDepartment(this);
    }

    public List<Employee> getEmp() {
        return emp;
    }

    public void setEmp(List<Employee> emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return ", Department Name-->" + name;
    }



}

