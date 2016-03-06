package com.gremzor.personpopulatorpro.presenter;

import android.widget.Toast;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.auth.AuthFacadeInterface;
import com.gremzor.personpopulatorpro.view.LoginActivity;
import com.gremzor.personpopulatorpro.view.fragment.AuthDialogFragment;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter {

    @Inject
    AuthFacade authFacade;

    public LoginActivity loginActivity;

    public void handleLoginClick(String email, String password) {
        authFacade.login(email, password, new AuthFacadeInterface() {
            @Override
            public void authStatus(int authStatus) {
                switch (authStatus) {
                    case AuthFacadeInterface.SUCCESS:
                        break;
                    case AuthFacadeInterface.AUTH_FAILURE_USER_DOES_NOT_EXIST:
                        AuthDialogFragment.getCreateUserDialog().show(loginActivity.getFragmentManager(), "AuthDialogFragment");
                        break;
                    case AuthFacadeInterface.AUTH_FAILURE_INVALID_PASSWORD:
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
        authFacade.createUser(email, password, new AuthFacadeInterface() {
            @Override
            public void authStatus(int authStatus) {
                switch (authStatus) {
                    case AuthFacadeInterface.SUCCESS:
                        handleLoginClick(email, password);
                        break;
                    default:
                        Toast.makeText(loginActivity, "Failed to create user.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }
}


