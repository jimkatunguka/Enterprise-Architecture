package edu.mum.cs.cs544.exercises.a;

import javax.persistence.Entity;

@Entity
public class Deciduous extends Tree {
    private int leafSize;

    public Deciduous(int leafSize) {
        this.leafSize = leafSize;
    }
}
