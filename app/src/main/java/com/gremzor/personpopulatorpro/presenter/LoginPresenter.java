package com.gremzor.personpopulatorpro.presenter;

import android.content.Intent;
import android.widget.Toast;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.view.LoginActivity;
import com.gremzor.personpopulatorpro.view.PersonActivity;
import com.gremzor.personpopulatorpro.view.fragment.CreateUserDialogFragment;
import com.gremzor.personpopulatorpro.view.fragment.LoginFailedDialogFragment;

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
                        new CreateUserDialogFragment().show(loginActivity.getFragmentManager(), "CreateUserDialogFragment");
                        break;
                    case AuthFacade.AuthFacadeInterface.AUTH_FAILURE_INVALID_PASSWORD:
                        new LoginFailedDialogFragment().show(loginActivity.getFragmentManager(), "LoginFailedDialogFragment");
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


