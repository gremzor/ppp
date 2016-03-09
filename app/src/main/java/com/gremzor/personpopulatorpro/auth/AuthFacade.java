package com.gremzor.personpopulatorpro.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class AuthFacade {

    private static final String TAG = "AuthFacade";
    private static final String personsPath = "persons";
    private static final String fireBaseURL = "https://sizzling-torch-4682.firebaseio.com/";
    private Firebase firebase;
    private String userID;

    private Firebase getBaseFirebase() {
        if(firebase == null) {
            firebase = new Firebase(fireBaseURL);
        }
        return firebase;
    }

    public Firebase getFirebase() throws UserNotLoggedInException {
        if(userID == null) {
            throw new UserNotLoggedInException();
        }
        return getBaseFirebase().child(userID).child(personsPath);
    }

    public void login(String email, String password, final AuthFacadeInterface authFacadeInterface) {
        getBaseFirebase().authWithPassword(email, password, new Firebase.AuthResultHandler() {
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
        getBaseFirebase().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
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
