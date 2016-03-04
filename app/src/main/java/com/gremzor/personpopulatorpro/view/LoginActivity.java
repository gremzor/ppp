package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Inject LoginPresenter loginPresenter;

    @Bind(R.id.emailField)
    EditText emailFieldEditText;

    @Bind(R.id.passwordField)
    EditText passwordFieldEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    void doInjection() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }

    @OnClick (R.id.loginButton)
    public void onLoginClick() {
        loginPresenter.testFunction();
    }
}
