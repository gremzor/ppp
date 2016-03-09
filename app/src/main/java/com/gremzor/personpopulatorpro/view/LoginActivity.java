package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.dao.UserNameDAO;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;
import com.gremzor.personpopulatorpro.view.fragment.CreateUserDialogFragmentListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements CreateUserDialogFragmentListener {

    @Inject
    LoginPresenter loginPresenter;

    @Inject
    UserNameDAO userNameDAO;

    @Bind(R.id.emailField)
    EditText emailFieldEditText;

    @Bind(R.id.passwordField)
    EditText passwordFieldEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        emailFieldEditText.setText(userNameDAO.getSavedUser());
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
        loginPresenter.loginActivity = this;
    }

    @OnClick(R.id.loginButton)
    public void onLoginClick() {
        loginPresenter.handleLoginClick(emailFieldEditText.getText().toString(), passwordFieldEditText.getText().toString());
    }

    @Override
    public void onAuthDialogPositiveClick(String passwordFromDialog) {
        String passwordFromActivity = passwordFieldEditText.getText().toString();
        if(passwordFromDialog.equals(passwordFromActivity)) {
            loginPresenter.handleCreateUserClick(emailFieldEditText.getText().toString(), passwordFromActivity);
        }
    }
}
