package com.gremzor.personpopulatorpro;

import android.app.Application;

import com.firebase.client.Firebase;
import com.gremzor.personpopulatorpro.di.DaggerMVPComponent;
import com.gremzor.personpopulatorpro.di.MVPComponent;
import com.gremzor.personpopulatorpro.di.MVPModule;

public class PersonPopulatorProApplication extends Application {
    private static MVPComponent graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = DaggerMVPComponent.builder().mVPModule(new MVPModule()).build();
        Firebase.setAndroidContext(this);
    }

    public static MVPComponent getGraph() {
        return graph;
    }
}
