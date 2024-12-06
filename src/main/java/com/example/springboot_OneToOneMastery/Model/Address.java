package com.example.springboot_OneToOneMastery.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addressinfo")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="AID",unique = true,nullable = false)
    int aid;

    @Column(name="Street")
    @NotBlank(message = "Street cannot be blank")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    String street;

    @Column(name="City")
    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City must not exceed 50 characters")
    String city;
    @Column(name="State")
    @NotBlank(message = "State cannot be blank")
    @Size(max = 50, message = "State name must not exceed 50 characters")
    String state;

    @Column(name ="Country")
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 50, message = "Country name must not exceed 50 characters")
    String country;

    @Column(name = "Pincode")
    @NotBlank(message = "Pincode cannot be blank")
    @Pattern(regexp = "\\d{6}", message = "Pincode must be a 6-digit number")
    String pincode;

    @OneToOne
    Student student;

    public int getAid() {
        return aid;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

