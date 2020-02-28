package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Laptop> laptops;

    public Employee() {
    }

    public Employee(String firstname, String lastname, Laptop laptop) {
        this.firstname = firstname;
        this.lastname = lastname;
        laptops = new HashSet<>();
        addLaptop(laptop);
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

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }
}
