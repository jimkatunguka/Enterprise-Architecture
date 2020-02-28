package edu.mum.cs.cs544.exercises.b;

import javax.persistence.Entity;

@Entity
public class Book extends Product {
    private String title;

    public Book() {
    }

    public Book(String name, String description, String title) {
        super(name, description);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String artist) {
        this.title = title;
    }
}
