package edu.mum.cs.cs544.exercises.f;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    private Office office;

    public Employee(){

    }

    public Employee(String name) {
        super();
        this.name = name;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Employee Name--->" + name+" "+department;
    }


}