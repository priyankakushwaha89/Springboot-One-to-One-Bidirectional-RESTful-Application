package com.example.springboot_OneToOneMastery.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="studentinfo")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="ID",unique = true,nullable = false)
    int id;

    @Column(name="Name",nullable = false,updatable = false)
    @NotEmpty(message = "name Can not be empty")
    String name;

    @Column(name="Course")
    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 3, max = 50, message = "Course name must be between 3 and 50 characters")
     String course;

    @Column(name="CourseCode")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Course name can only contain alphanumeric characters and spaces")
    String courseCode;

    @Column(name="Age",nullable = false,updatable = true)
    @Min(value = 18)
    @Max(value=45)
    int age;
    @Column(name="Phone number",updatable = true,nullable = false,insertable = true,unique = true)
    @NotBlank(message = "Please enter phone number ")
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$",message = "Please enter an Valid phone number ")
    String phone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "aid")
    Address address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
