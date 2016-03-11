package com.gremzor.personpopulatorpro.di;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
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
    private final String fireBaseURL;

    public MVPModule(Application application, String fireBaseURL) {
        Firebase.setAndroidContext(application);
        this.application = application;
        this.fireBaseURL = fireBaseURL;
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

    @Singleton
    @Provides
    Firebase provideFirebase() {
        return new Firebase(fireBaseURL);
    }
}
