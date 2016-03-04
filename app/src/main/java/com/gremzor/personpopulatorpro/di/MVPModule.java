package com.gremzor.personpopulatorpro.di;

import com.gremzor.personpopulatorpro.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {
    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}
