package com.gremzor.personpopulatorpro.di;

import android.app.Application;
import android.content.Context;

import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {

    private Application application;

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

    @Singleton
    @Provides
    AuthFacade provideAuthManager () {
        return new AuthFacade();
    }
}
