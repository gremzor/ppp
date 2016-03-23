package com.gremzor.personpopulatorpro.dao;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;

import javax.inject.Inject;

/**
 * Data access object for persons. Allows addition and removal of persons. Uses Firebase events to
 * maintain the list of persons to ensure parity with back office.
 */

public class PersonDAO {

    @Inject
    AuthFacade authFacade;

    public PersonDAO () {
        PersonPopulatorProApplication.getGraph().inject(this);
    }

    public void registerForPersonEvents(final PersonDAOInterface personDAOInterface) throws AuthFacade.UserNotLoggedInException {
            authFacade.getFirebase().orderByChild("lastName").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Person person = dataSnapshot.getValue(Person.class);
                    person.uniqueKey = dataSnapshot.getKey();
                    personDAOInterface.onChildAdded(person);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Person person = dataSnapshot.getValue(Person.class);
                    person.uniqueKey = dataSnapshot.getKey();
                    personDAOInterface.onChildChanged(person);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Person person = dataSnapshot.getValue(Person.class);
                    person.uniqueKey = dataSnapshot.getKey();
                    personDAOInterface.onChildRemoved(person);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
    }

    public void addPerson (Person person) throws AuthFacade.UserNotLoggedInException{
        authFacade.getFirebase().push().setValue(person);
    }

    public void removePerson (String key) throws AuthFacade.UserNotLoggedInException{
        authFacade.getFirebase().child(key).removeValue();
    }
}
