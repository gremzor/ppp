package com.gremzor.personpopulatorpro.di;

import com.gremzor.personpopulatorpro.dao.PersonDAO;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;
import com.gremzor.personpopulatorpro.presenter.PersonPresenter;
import com.gremzor.personpopulatorpro.view.LoginActivity;
import com.gremzor.personpopulatorpro.view.PersonActivity;
import com.gremzor.personpopulatorpro.view.fragment.ModifyPersonDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules=MVPModule.class)
public interface MVPComponent {
    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);
    void inject(PersonActivity personActivity);
    void inject(PersonPresenter personPresenter);
    void inject(PersonDAO personDAO);
    void inject(ModifyPersonDialogFragment modifyPersonDialogFragment);
}
