package com.gremzor.personpopulatorpro.dao;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;

import javax.inject.Inject;

public class PersonDAO {

    @Inject
    AuthFacade authFacade;

    public PersonDAO () {
        init();
    }

    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }


    public void registerForPersonEvents(final PersonDAOInterface personDAOInterface) throws AuthFacade.UserNotLoggedInException {
            authFacade.getFirebase().child("persons").orderByChild("lastName").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Person person = dataSnapshot.getValue(Person.class);
                    person.uniqueKey = dataSnapshot.getKey();
                    personDAOInterface.onChildAdded(person);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

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
        authFacade.getFirebase().child("persons").push().setValue(person);
    }
}
