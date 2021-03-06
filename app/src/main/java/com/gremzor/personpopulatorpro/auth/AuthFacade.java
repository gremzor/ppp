package com.gremzor.personpopulatorpro.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;

import java.util.Map;

import javax.inject.Inject;

/**
 * Facade for authentication. Provides methods that allow logging in and creating users. Also provides
 * reference to the Firebase instance.
 */

public class AuthFacade {

    private static final String TAG = "AuthFacade";
    private static final String personsPath = "persons";
    private String userID;

    @Inject
    Firebase firebase;

    public AuthFacade () {
        PersonPopulatorProApplication.getGraph().inject(this);
    }

    public Firebase getFirebase() throws UserNotLoggedInException {
        if(userID == null) {
            throw new UserNotLoggedInException();
        }
        return firebase.child(userID).child(personsPath);
    }

    public void login(String email, String password, final AuthFacadeInterface authFacadeInterface) {
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                userID = authData.getUid();
                authFacadeInterface.authStatus(AuthFacadeInterface.SUCCESS);
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
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                authFacadeInterface.authStatus(AuthFacadeInterface.SUCCESS);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                authFacadeInterface.authStatus(AuthFacadeInterface.AUTH_FAILURE_CREATE_ACCOUNT);
            }
        });
    }

    public interface AuthFacadeInterface {
        int SUCCESS = 0;
        int AUTH_FAILURE_USER_DOES_NOT_EXIST = 1;
        int AUTH_FAILURE_INVALID_PASSWORD = 2;
        int AUTH_FAILURE_CREATE_ACCOUNT = 3;
        int AUTH_FAILURE_UNKNOWN = 4;
        void authStatus(int authorized);
    }

    public class UserNotLoggedInException extends Throwable {
    }
}
