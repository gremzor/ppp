package com.gremzor.personpopulatorpro.dao;

import com.gremzor.personpopulatorpro.model.Person;

public interface PersonDAOInterface {
    void onChildAdded(Person person);
    void onChildRemoved(Person person);
}
