package com.gremzor.personpopulatorpro.presenter;

import com.firebase.client.Firebase;

public class LoginPresenter {


public void testFunction () {
    Firebase myFirebaseRef = new Firebase("https://sizzling-torch-4682.firebaseio.com/");
    myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase111.");
}
}


