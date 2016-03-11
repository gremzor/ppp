package com.gremzor.personpopulatorpro;

import android.app.Application;

import com.firebase.client.Firebase;
import com.gremzor.personpopulatorpro.di.DaggerMVPComponent;
import com.gremzor.personpopulatorpro.di.MVPComponent;
import com.gremzor.personpopulatorpro.di.MVPModule;

public class PersonPopulatorProApplication extends Application {
    private static MVPComponent graph;
    private static final String fireBaseURL = "https://sizzling-torch-4682.firebaseio.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        graph = DaggerMVPComponent.builder().mVPModule(new MVPModule(this, fireBaseURL)).build();
    }

    public static MVPComponent getGraph() {
        return graph;
    }
}
