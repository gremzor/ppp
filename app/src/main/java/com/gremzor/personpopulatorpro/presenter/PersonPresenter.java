package com.gremzor.personpopulatorpro.presenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.dao.PersonDAO;
import com.gremzor.personpopulatorpro.dao.PersonDAOInterface;
import com.gremzor.personpopulatorpro.model.Person;
import com.gremzor.personpopulatorpro.view.PersonActivity;
import com.gremzor.personpopulatorpro.view.adapter.PersonAdapter;
import com.gremzor.personpopulatorpro.view.fragment.ModifyPersonDialogFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class PersonPresenter extends BasePresenter implements PersonDAOInterface, AdapterView.OnItemLongClickListener {

    private static final String TAG = "PersonPresenter";

    @Inject
    PersonDAO personDAO;

    public PersonActivity personActivity;

    private PersonAdapter personAdapter;

    private ArrayList<Person> arrayOfPersons;

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }

    public void loadPersons() {
        arrayOfPersons = new ArrayList<>();
        personAdapter = new PersonAdapter(personActivity, arrayOfPersons);
        ListView listView = (ListView) personActivity.findViewById(R.id.person_listview);
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(personAdapter);
        try {
            personDAO.registerForPersonEvents(this);
        } catch (AuthFacade.UserNotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void handleAddUserClick(final Person person) {
        try {
            personDAO.addPerson(person);
        } catch (AuthFacade.UserNotLoggedInException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public void handleRemoveUserClick(String key) {
        try {
            personDAO.removePerson(key);
        } catch (AuthFacade.UserNotLoggedInException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onChildAdded(Person person) {
        personAdapter.add(person);
    }

    @Override
    public void onChildRemoved(Person person) {
        personAdapter.remove(person);
    }

    @Override
    public void onChildChanged(Person person) {
        int indexToChange = arrayOfPersons.indexOf(person);
        arrayOfPersons.set(indexToChange, person);
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ModifyPersonDialogFragment modifyPersonDialogFragment = new ModifyPersonDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(ModifyPersonDialogFragment.PERSON_KEY, personAdapter.getItem(position));
        modifyPersonDialogFragment.setArguments(args);
        modifyPersonDialogFragment.show(personActivity.getFragmentManager(), "CreatePersonDialogFragment");
        return false;
    }
}
