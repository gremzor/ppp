package com.gremzor.personpopulatorpro.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;

public class AuthFacade {

    private static final String TAG = "AuthFacade";

    public void tryLogin(String email, String password, final AuthFacadeInterface authFacadeInterface) {
        PersonPopulatorProApplication.getFirebase().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                authFacadeInterface.isAuthorized(true);
                Log.d(TAG, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                authFacadeInterface.isAuthorized(false);
                Log.d(TAG, "Error authenticating");
            }
        });
    }
}
