package com.gremzor.personpopulatorpro.model;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date dob;
    private String zip;

    public Person() {}

    public Person(String firstName, String lastName, Date dob, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDOB() {
        return dob;
    }

    public String getZip() {
        return zip;
    }
}
