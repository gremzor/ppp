package com.gremzor.personpopulatorpro.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.model.Person;
import com.gremzor.personpopulatorpro.presenter.PersonPresenter;
import com.gremzor.personpopulatorpro.view.fragment.CreatePersonDialogFragment;
import com.gremzor.personpopulatorpro.view.fragment.CreateModifyPersonDialogFragmentListener;

import javax.inject.Inject;

public class PersonActivity extends BaseActivity implements View.OnClickListener, CreateModifyPersonDialogFragmentListener {

    @Inject
    PersonPresenter personPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_activity);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        personPresenter.loadPersons();
    }

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
        personPresenter.personActivity = this;
    }

    @Override
    public void onClick(View v) {
        new CreatePersonDialogFragment().show(getFragmentManager(), "CreatePersonDialogFragment");
    }

    @Override
    public void onAuthDialogPositiveClick(Person person) {
        personPresenter.handleAddUserClick(person);
    }

    @Override
    public void onAuthDialogNeutralClick(String key) {
        personPresenter.handleRemoveUserClick(key);
    }
}
