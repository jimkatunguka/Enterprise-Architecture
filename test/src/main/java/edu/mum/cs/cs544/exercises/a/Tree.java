package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Plant")
@DiscriminatorColumn(name = "Ttype")
public class Tree {
    @Id
    @GeneratedValue
    private int id;
    private int height;
    private double price;
    @OneToMany(mappedBy = "tree")
    private List<Treatment> treatments =
            new ArrayList<Treatment>();

    public Tree() {
    }

    public Tree(int height, double price) {
        this.height = height;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
