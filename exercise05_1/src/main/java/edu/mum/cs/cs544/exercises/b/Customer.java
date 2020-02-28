package edu.mum.cs.cs544.exercises.b;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    @OrderColumn(name = "sequence")
    private List<Order> orders = new ArrayList<>();;


    public Customer() {
    }

    public Customer(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
