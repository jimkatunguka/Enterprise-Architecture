package edu.mum.cs.cs544.exercises.d;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;

    public Reservation() {

    }
    public Reservation(LocalDate date) {
        this.date=date;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Reservation [date=" + date + "]";
    }

}

