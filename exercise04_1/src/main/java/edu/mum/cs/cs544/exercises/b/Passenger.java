package edu.mum.cs.cs544.exercises.b;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    @OrderColumn(name = "sequence")
    @JoinColumn(name = "passenger_id")
    private List<Flight> flights;

    public Passenger() {
    }

    public Passenger(String name, Flight flight) {
        this.name = name;
        flights = new ArrayList<>();
        flights.add(flight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
       flights.add(flight);
    }
}
