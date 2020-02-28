package edu.mum.cs.cs544.exercises.a;

import org.hibernate.internal.util.StringHelper;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String type;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Laptop() {
    }

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(brand, laptop.brand) &&
                Objects.equals(type, laptop.type) &&
                Objects.equals(employee, laptop.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, type, employee);
    }
}
