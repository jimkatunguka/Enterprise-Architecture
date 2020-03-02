package cs544.exercise06_1.model;

import javax.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Type")
    private String doctorType;
    private String firstname;
    private String lastname;

    public Doctor() {
    }

    public Doctor(String doctorType, String firstname, String lastname) {
        this.doctorType = doctorType;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
