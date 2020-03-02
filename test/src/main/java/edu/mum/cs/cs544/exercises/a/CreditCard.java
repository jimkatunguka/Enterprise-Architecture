package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditCard {
    @Id
    private long number;
    @Column(name = "nameOnCard")
    private String name;
    @Temporal(TemporalType.DATE)
    private Date expiration;
    @OneToOne(mappedBy = "cc")
    private Customer customer;

    public CreditCard() {
    }

    public CreditCard(long number, String name, Date expiration, Customer customer) {
        this.number = number;
        this.name = name;
        this.expiration = expiration;
        this.customer = customer;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
