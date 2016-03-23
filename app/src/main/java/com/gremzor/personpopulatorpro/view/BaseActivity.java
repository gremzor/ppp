package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Base activity class. Sub-classes must implement the init method where injection should be performed.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        ButterKnife.bind(this);
    }

    abstract void init();
}
