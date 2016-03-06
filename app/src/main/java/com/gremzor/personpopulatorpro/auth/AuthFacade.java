package com.gremzor.personpopulatorpro.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;

import java.util.Map;

public class AuthFacade {

    private static final String TAG = "AuthFacade";

    public void login(String email, String password, final AuthFacadeInterface authFacadeInterface) {
        PersonPopulatorProApplication.getFirebase().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                authFacadeInterface.authStatus(AuthFacadeInterface.SUCCESS);
                Log.d(TAG, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                int authError;
                switch (firebaseError.getCode()) {
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        authError = AuthFacadeInterface.AUTH_FAILURE_USER_DOES_NOT_EXIST;
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        authError = AuthFacadeInterface.AUTH_FAILURE_INVALID_PASSWORD;
                        break;
                    default:
                        authError = AuthFacadeInterface.AUTH_FAILURE_UNKNOWN;
                        break;
                }
                authFacadeInterface.authStatus(authError);
                Log.d(TAG, "Error authenticating");
            }
        });
    }

    public void createUser (String email, String password, final AuthFacadeInterface authFacadeInterface) {
        PersonPopulatorProApplication.getFirebase().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                authFacadeInterface.authStatus(AuthFacadeInterface.SUCCESS);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                authFacadeInterface.authStatus(AuthFacadeInterface.AUTH_FAiLURE_CREATE_ACCOUNT);
            }
        });
    }
}
