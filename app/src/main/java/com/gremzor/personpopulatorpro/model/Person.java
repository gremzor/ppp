package com.gremzor.personpopulatorpro.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Model for persons. Allows marshalling through the Parcelable interface. Equality is checked based
 * on the unique key assigned by Firebase. Comparison is based on last name.
 */

public class Person implements Parcelable, Comparable<Person>{
    private String firstName;
    private String lastName;
    private Date dob;
    private String zip;

    public String uniqueKey;

    public Person() {}

    public Person(String firstName, String lastName, Date dob, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.zip = zip;
    }

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        dob = new Date(in.readLong());
        zip = in.readString();
        uniqueKey = in.readString();
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

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeLong(dob.getTime());
        dest.writeString(zip);
        dest.writeString(uniqueKey);
    }

    @Override
    public int compareTo(Person another) {
        return this.lastName.compareTo(another.getLastName());
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Person)
        {
            if ( this.uniqueKey.equals(((Person) o).uniqueKey)) return true;
        }
        return false;
    }
}
