package com.gremzor.personpopulatorpro.model;

import android.os.Parcel;

import com.gremzor.personpopulatorpro.BaseUnitTest;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PersonTest extends BaseUnitTest {

    @Test
    public void testParcelableInterfaceForPerson() {
        Person personBeforeParceling = new Person("Bob", "Smith", new Date(), "12345");
        personBeforeParceling.uniqueKey = "testKey";

        Parcel personParcel = Parcel.obtain();
        personBeforeParceling.writeToParcel(personParcel, 0);
        personParcel.setDataPosition(0);
        Person personAfterParceling =  Person.CREATOR.createFromParcel(personParcel);

        assertEquals(personBeforeParceling.getFirstName(), personAfterParceling.getFirstName());
        assertEquals(personBeforeParceling.getLastName(), personAfterParceling.getLastName());
        assertEquals(personBeforeParceling.getDOB(), personAfterParceling.getDOB());
        assertEquals(personBeforeParceling.getZip(), personAfterParceling.getZip());
        assertEquals(personBeforeParceling.uniqueKey, personAfterParceling.uniqueKey);
    }

    @Test
    public void testComparableInterfaceForPerson() {
        Person personWithLexicallySmallerLastName = new Person("Bob", "Brown", new Date(), "12345");
        Person personWithLexicallyGreaterLastName = new Person("Bob", "Smith", new Date(), "12345");

        assertTrue(personWithLexicallySmallerLastName.compareTo(personWithLexicallyGreaterLastName) < 0);
        assertTrue(personWithLexicallyGreaterLastName.compareTo(personWithLexicallySmallerLastName) > 0);
        assertEquals(personWithLexicallySmallerLastName.compareTo(personWithLexicallySmallerLastName), 0);
    }

    @Test
    public void testEqualityForPerson() {
        Person person1 = new Person("Bob", "Brown", new Date(), "12345");
        person1.uniqueKey = "testKey";
        Person person2 = new Person("Joe", "Smith", new Date(), "54321");
        person2.uniqueKey = "testKey";
        Person person3 = new Person("Joe", "Smith", new Date(), "54321");
        person3.uniqueKey = "testKey2";

        assertEquals(person1, person2);
        assertNotEquals(person2, person3);
    }
}