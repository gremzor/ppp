package com.gremzor.personpopulatorpro.presenter;

import android.widget.Toast;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.auth.AuthFacadeInterface;
import com.gremzor.personpopulatorpro.view.LoginActivity;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter {

    @Inject
    private
    AuthFacade authFacade;

    public LoginActivity loginActivity;

    public void handleLoginClick(String email, String password) {
        authFacade.tryLogin(email, password, new AuthFacadeInterface() {
            @Override
            public void isAuthorized(boolean authorized) {
                String msg;
                if (authorized) {
                    msg = "Authorized!";
                } else {
                    msg = "Not Authorized :(";
                }
                Toast.makeText(loginActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }
}


