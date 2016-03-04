package com.gremzor.personpopulatorpro.di;

import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {
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
