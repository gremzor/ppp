package com.gremzor.personpopulatorpro;

import android.app.Application;

import com.firebase.client.Firebase;
import com.gremzor.personpopulatorpro.di.DaggerMVPComponent;
import com.gremzor.personpopulatorpro.di.MVPComponent;
import com.gremzor.personpopulatorpro.di.MVPModule;

public class PersonPopulatorProApplication extends Application {
    private static MVPComponent graph;
    private static Firebase firebase;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = DaggerMVPComponent.builder().mVPModule(new MVPModule(this)).build();
        Firebase.setAndroidContext(this);
    }

    public static Firebase getFirebase() {
        if(firebase == null) {
            firebase = new Firebase("https://sizzling-torch-4682.firebaseio.com/");
        }
        return firebase;
    }

    public static MVPComponent getGraph() {
        return graph;
    }
}
