package com.gremzor.personpopulatorpro.view;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doInjection();
        ButterKnife.bind(this);
    }

    abstract void doInjection();
}
