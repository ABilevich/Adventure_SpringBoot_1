package com.andy.cruddemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {

    // Define fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    public Student() {
        // This no-argument constructor is required by JPA/Hibernate
    }
    
    // Define constructors
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Define getters/setters
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Define toString() method

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

}
