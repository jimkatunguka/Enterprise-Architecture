package edu.mum.cs.cs544.exercises.b;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int flightnumber;
    @Column(name = "_From")
    private String from;
    @Column(name = "_To")
    private String to;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Flight() {
    }

    public Flight(int flightnumber, String from, String to, Date date) {
        this.flightnumber = flightnumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public int getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(int flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
