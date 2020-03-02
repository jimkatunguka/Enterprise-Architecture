package edu.mum.cs.cs544.exercises.a;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Evergreen extends Tree {
    private long needleAmount;

    public Evergreen() {
    }

    public Evergreen(int height, double price, long needleAmount) {
        super(height, price);
        this.needleAmount = needleAmount;
    }

    public long getNeedleAmount() {
        return needleAmount;
    }

    public void setNeedleAmount(long needleAmount) {
        this.needleAmount = needleAmount;
    }
}