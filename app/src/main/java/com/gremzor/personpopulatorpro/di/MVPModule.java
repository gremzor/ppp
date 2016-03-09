package com.gremzor.personpopulatorpro.di;

import android.app.Application;
import android.content.Context;

import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.dao.PersonDAO;
import com.gremzor.personpopulatorpro.dao.UserNameDAO;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;
import com.gremzor.personpopulatorpro.presenter.PersonPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {

    private final Application application;

    public MVPModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    PersonPresenter providePersonPresenter() {
        return new PersonPresenter();
    }

    @Singleton
    @Provides
    PersonDAO providePersonDAO() {
        return new PersonDAO();
    }

    @Singleton
    @Provides
    UserNameDAO provideUserNameDAO() {
        return new UserNameDAO();
    }

    @Singleton
    @Provides
    AuthFacade provideAuthManager () {
        return new AuthFacade();
    }
}
