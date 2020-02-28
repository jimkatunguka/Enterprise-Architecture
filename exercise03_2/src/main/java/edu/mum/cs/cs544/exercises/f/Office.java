package edu.mum.cs.cs544.exercises.f;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Office {
    @Id
    @GeneratedValue
    private int id;
    private int roomnumber;
    private String building;



    @OneToMany(cascade = CascadeType.ALL,mappedBy="office")
    private List<Employee> employees= new ArrayList<Employee>();

    public Office() {

    }

    public  List<Employee> getEmployee(){
        return employees;
    }


    public Office(int roomnumber,String building) {
        super();
        this.roomnumber=roomnumber;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void addEmployee(Employee e) {
        if(e!=null) {
            e.setOffice(this);
            employees.add(e);
        }
    }


}
