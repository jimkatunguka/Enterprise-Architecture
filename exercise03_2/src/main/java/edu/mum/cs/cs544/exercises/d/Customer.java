package edu.mum.cs.cs544.exercises.d;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Reservation> reservation=new ArrayList<Reservation>();

    public Customer() {

    }
    public Customer(String name) {
        this.name=name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Reservation> getReservation() {
        return reservation;
    }
    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public void addReservation(Reservation r) {
        reservation.add(r);
    }
    public void removeReservation(Reservation r) {
        reservation.remove(r);
    }
    @Override
    public String toString() {
        return "Customer [name=" + name + ", reservation=" + reservation + "]";
    }

}

