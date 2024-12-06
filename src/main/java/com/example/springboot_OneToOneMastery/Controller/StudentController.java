package com.example.springboot_OneToOneMastery.Controller;

import com.example.springboot_OneToOneMastery.CustomException.AddressIdNotFoundException;
import com.example.springboot_OneToOneMastery.CustomException.DataNotFoundException;
import com.example.springboot_OneToOneMastery.CustomException.IdNotFoundException;
import com.example.springboot_OneToOneMastery.CustomException.NameNotFoundException;
import com.example.springboot_OneToOneMastery.Model.Address;
import com.example.springboot_OneToOneMastery.Model.Student;
import com.example.springboot_OneToOneMastery.Repository.AddressRepository;
import com.example.springboot_OneToOneMastery.Repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class StudentController {

    @Autowired
    StudentRepository srepo;

    @Autowired
    AddressRepository arepo;

//This endpoint is for the test purpose
    @RequestMapping("/test")
    public String test()
    {
        return "Hello! This is for the test Purpose";
    }

    //This endpoint is for sending data to the server to create a resource
    @PostMapping("/save")
    public String saveData(@Valid @RequestBody Student student)
    {
        srepo.save(student);
        return "Data saved";
    }

    //This endpoint is for retrieving entire data from the server
    @GetMapping("/all")
    public List<Student> allInfo()
    {
        List<Student> student=srepo.findAll();
        if(student.isEmpty())
        {
            throw new DataNotFoundException();
        }
        return student;
    }

    //This endpoint is for retrieving data based on provided Id
    @GetMapping("/id/{id}")
    public Optional<Student> byId(@PathVariable int id)
    {
        Optional<Student> student=srepo.findById(id);
        if(student.isEmpty())
        {
            throw new IdNotFoundException();
        }
        return student;
    }

    //This endpoint is for retrieving data from the server by name
    @GetMapping("/name/{name}")
    public List<Student> byName(@PathVariable String name)
    {
        List<Student> student=srepo.findByName(name);
        if(student.isEmpty())
        {
            throw new NameNotFoundException();
        }
        return student;
    }

    //This endpoint is for retrieving data from server by the address id
    @GetMapping("/address/id/{aid}")
    public Optional<Address> addressById(@PathVariable int aid)
    {
        Optional<Address> address=arepo.findById(aid);
        if(address.isEmpty())
        {
            throw new AddressIdNotFoundException();
        }
        return address;
    }

    //This endpoint is for send data to the server to update an specific resource
    @PutMapping("/upd/{id}")
    public String updateById(@Valid @RequestBody Student student, @PathVariable int id)
    {
        Optional<Student> optionalStudent = srepo.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new IdNotFoundException();
        }

        Student existingStudent = optionalStudent.get();
        existingStudent.setName(student.getName());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setCourseCode(student.getCourseCode());
        existingStudent.setAge(student.getAge());
        existingStudent.setPhone(student.getPhone());

        // Update or replace the associated address
        if (existingStudent.getAddress() == null) {
            existingStudent.setAddress(student.getAddress());
        } else {
            Address existingAddress = existingStudent.getAddress();
            Address newAddress = student.getAddress();
            existingAddress.setStreet(newAddress.getStreet());
            existingAddress.setCity(newAddress.getCity());
            existingAddress.setState(newAddress.getState());
            existingAddress.setCountry(newAddress.getCountry());
            existingAddress.setPincode(newAddress.getPincode());
        }

        srepo.save(existingStudent);
        return "Data Updated";
    }

    //This endpoint is for deleting a resource from server based on the provided id
    @DeleteMapping("/del/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        // Check if the student exists
        Optional<Student> student = srepo.findById(id);
        if (student.isEmpty()) {
            throw new IdNotFoundException();
        }

        // Delete the student
        srepo.deleteById(id);
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
    }



}
