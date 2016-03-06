package com.gremzor.personpopulatorpro.dao;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;

import java.util.ArrayList;

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


    public ArrayList<Person> getPersonArray() throws AuthFacade.UserNotLoggedInException {
        final ArrayList<Person> persons = new ArrayList<>();

            Firebase ref = authFacade.getFirebase().child("persons");
            Query queryRef = ref.orderByChild("lastName");
            queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    persons.add(snapshot.getValue(Person.class));
                    Log.d("a", "a");
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

        return persons;
    }
}
