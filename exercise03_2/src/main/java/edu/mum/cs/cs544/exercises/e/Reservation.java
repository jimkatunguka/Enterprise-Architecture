package edu.mum.cs.cs544.exercises.e;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="book_id")
    private Book book;

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
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    @Override
    public String toString() {
        return "Reservation [id=" + id + ", date=" + date + ", book=" + book + "]";
    }



}
