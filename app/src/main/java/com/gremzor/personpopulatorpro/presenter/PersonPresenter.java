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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

/**
 * Presents behavior behind the PersonActivity view.
 */

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
            Log.e(TAG, e.getMessage(), e);
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
        addInOrder(person);

    }

    private void addInOrder(Person person) {
        int index = 0;

        for(int i = personAdapter.getCount()-1; i>=0; i--) {
            if(personAdapter.getItem(i).compareTo(person) < 0 ) {
                index = i+1;
                break;
            };
        }
        personAdapter.insert(person, index);
    }

    @Override
    public void onChildRemoved(Person person) {
        personAdapter.remove(person);
    }

    @Override
    public void onChildChanged(Person person) {
        int indexToChange = arrayOfPersons.indexOf(person);
        boolean sortAfterAddingPerson = !arrayOfPersons.get(indexToChange).getLastName().equals(person.getLastName());
        arrayOfPersons.set(indexToChange, person);
        if(sortAfterAddingPerson) {
            Collections.sort(arrayOfPersons);
        }
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
