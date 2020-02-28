package edu.mum.cs.cs544.exercises.b;

import javax.persistence.Entity;

@Entity
public class DVD extends Product {

    private String genre;

    public DVD(){}


    public DVD(String name, String description, String genre) {
        super(name, description);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
