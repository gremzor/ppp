package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.dao.PersonDAO;
import com.gremzor.personpopulatorpro.model.Person;
import com.gremzor.personpopulatorpro.presenter.PersonPresenter;
import com.gremzor.personpopulatorpro.view.adapter.PersonAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    PersonPresenter personPresenter;

    @Inject
    PersonDAO personDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        try {
            ArrayList<Person> arrayOfPersons = personDAO.getPersonArray();
            PersonAdapter adapter = new PersonAdapter(this, arrayOfPersons);
            ListView listView = (ListView) findViewById(R.id.person_listview);
            listView.setAdapter(adapter);
        } catch (AuthFacade.UserNotLoggedInException e) {
            Log.e("a", e.getMessage(), e);
        }
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
        personPresenter.personActivity = this;
    }

    @Override
    public void onClick(View v) {
        Log.d("FAB", "FAB CLICKED");
    }
}
