package edu.mum.cs.cs544.exercises.a;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToOne
    private CreditCard cc;
    @OneToMany
    private List<Tree> trees =
            new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, CreditCard cc, List<Tree> trees) {
        this.name = name;
        this.cc = cc;
        this.trees = trees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditCard getCc() {
        return cc;
    }

    public void setCc(CreditCard cc) {
        this.cc = cc;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }
}