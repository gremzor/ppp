package com.gremzor.personpopulatorpro.presenter;

import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;
import com.gremzor.personpopulatorpro.view.LoginActivity;
import com.gremzor.personpopulatorpro.view.PersonActivity;
import com.gremzor.personpopulatorpro.view.fragment.AuthDialogFragment;

import java.util.Date;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter {

    @Inject
    AuthFacade authFacade;

    public LoginActivity loginActivity;

    public void handleLoginClick(String email, String password) {
        authFacade.login(email, password, new AuthFacade.AuthFacadeInterface() {
            @Override
            public void authStatus(int authStatus) {
                switch (authStatus) {
                    case AuthFacade.AuthFacadeInterface.SUCCESS:
                        routeToPersonActivity();
                        break;
                    case AuthFacade.AuthFacadeInterface.AUTH_FAILURE_USER_DOES_NOT_EXIST:
                        AuthDialogFragment.getCreateUserDialog().show(loginActivity.getFragmentManager(), "AuthDialogFragment");
                        break;
                    case AuthFacade.AuthFacadeInterface.AUTH_FAILURE_INVALID_PASSWORD:
                        AuthDialogFragment.getLoginFailedDialog().show(loginActivity.getFragmentManager(), "AuthDialogFragment");
                        break;
                    default:
                        Toast.makeText(loginActivity, "Failed to login", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void handleCreateUserClick(final String email, final String password) {
        authFacade.createUser(email, password, new AuthFacade.AuthFacadeInterface() {
            @Override
            public void authStatus(int authStatus) {
                switch (authStatus) {
                    case AuthFacade.AuthFacadeInterface.SUCCESS:
                        handleLoginClick(email, password);
                        break;
                    default:
                        Toast.makeText(loginActivity, "Failed to create user.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void routeToPersonActivity () {
        Intent intent = new Intent(loginActivity, PersonActivity.class);
        loginActivity.startActivity(intent);
        loginActivity.finish();
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }
}


