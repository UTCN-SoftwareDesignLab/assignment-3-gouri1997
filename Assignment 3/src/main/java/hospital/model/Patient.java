package hospital.model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String cnp;
    private Date dob;
    private String address;

    public Patient() {
    }

    public Patient(Integer id, String name, String cnp, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.cnp = cnp;
        this.dob = dob;
        this.address = address;
    }

    public Patient(String name, String cnp, Date dob, String address) {
        this.name = name;
        this.cnp = cnp;
        this.dob = dob;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
