package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Inject
    private LoginPresenter loginPresenter;

    @Bind(R.id.emailField)
    private
    EditText emailFieldEditText;

    @Bind(R.id.passwordField)
    private
    EditText passwordFieldEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
        loginPresenter.loginActivity = this;
    }

    @OnClick (R.id.loginButton)
    public void onLoginClick() {
        loginPresenter.handleLoginClick(emailFieldEditText.getText().toString(), passwordFieldEditText.getText().toString());
    }
}
