package cs544.exercise06_1.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SecondaryTable(name = "Address")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(table = "Address", name = "STREET")
    private String street;
    @Column(table = "Address", name = "ZIP")
    private String zip;
    @Column(table = "Address", name = "CITY")
    private String city;


    public Patient() {
    }

    public Patient(String name, String street, String zip, String city) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
