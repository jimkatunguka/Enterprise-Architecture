package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "department")
    List<Employee> employeeList;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
        employeeList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee e) {
      employeeList.add(e);
    }
}
