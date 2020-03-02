package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Treatment {
    @Id
    @GeneratedValue
    private int id;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private Tree tree;

    public Treatment() {
    }

    public Treatment(String description, Date date, Tree tree) {
        this.description = description;
        this.date = date;
        this.tree = tree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}

