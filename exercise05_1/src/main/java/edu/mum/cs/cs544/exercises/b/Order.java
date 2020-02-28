package edu.mum.cs.cs544.exercises.b;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "OrderTable")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderid;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "customer_id", updatable = false, insertable = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderid")
    private List<OrderLine> orderlines = new ArrayList<>();

    public Order() {
    }

    public Order(int orderid, Customer customer, Date date){
        this.orderid = orderid;
        this.customer = customer;
        this.date = date;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void addOrderline(OrderLine orderline) {
       orderlines.add(orderline);
    }
}
