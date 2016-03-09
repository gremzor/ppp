package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class ModifyPersonDialogFragment extends CreatePersonDialogFragment{

    @Inject
    AuthFacade authFacade;

    public static final String PERSON_KEY = "person";

    private Person person;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        PersonPopulatorProApplication.getGraph().inject(this);
        person = getArguments().getParcelable(PERSON_KEY);
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        firstNameET.setText(person.getFirstName());
        lastNameET.setText(person.getLastName());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(person.getDOB());
        dOBDP.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        zIPET.setText(person.getZip());
        return dialog;
    }

    @Override
    void setButtons(AlertDialog.Builder builder, View view) {

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> fieldsToUpdate = new HashMap<>();
                if(!person.getFirstName().equals(firstNameET.getText().toString())) {
                   fieldsToUpdate.put(person.uniqueKey + "/firstName", firstNameET.getText().toString());
                }

                if(!person.getLastName().equals(lastNameET.getText().toString())) {
                    fieldsToUpdate.put(person.uniqueKey + "/lastName", lastNameET.getText().toString());
                }

                GregorianCalendar gregorianCalendar = new GregorianCalendar(dOBDP.getYear(), dOBDP.getMonth(), dOBDP.getDayOfMonth());
                if(!person.getDOB().equals(gregorianCalendar.getTime())) {
                    fieldsToUpdate.put(person.uniqueKey + "/dob", gregorianCalendar.getTimeInMillis());
                }

                if(!person.getZip().equals(zIPET.getText().toString())) {
                    fieldsToUpdate.put(person.uniqueKey + "/zip", zIPET.getText().toString());
                }

                try {
                    authFacade.getFirebase().child("persons").updateChildren(fieldsToUpdate);
                } catch (AuthFacade.UserNotLoggedInException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onAuthDialogNeutralClick(person.uniqueKey);
            }
        });
        builder.setNegativeButton("Cancel", null);
    }
}
