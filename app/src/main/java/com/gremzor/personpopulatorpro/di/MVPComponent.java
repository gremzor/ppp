package com.gremzor.personpopulatorpro.di;

import com.gremzor.personpopulatorpro.presenter.LoginPresenter;
import com.gremzor.personpopulatorpro.view.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules=MVPModule.class)
public interface MVPComponent {
    void inject(LoginActivity activity);
    void inject(LoginPresenter loginPresenter);
}
