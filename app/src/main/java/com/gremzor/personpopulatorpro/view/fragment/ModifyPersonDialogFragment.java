package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.gremzor.personpopulatorpro.model.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ModifyPersonDialogFragment extends CreatePersonDialogFragment{

    public static String PERSON_KEY = "person";

    private Person person;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
                //GregorianCalendar gregorianCalendar = new GregorianCalendar(dOBDP.getYear(), dOBDP.getMonth(), dOBDP.getDayOfMonth());
                //Person person = new Person(firstNameET.getText().toString(), lastNameET.getText().toString(), gregorianCalendar.getTime(), zIPET.getText().toString());
                //mListener.onAuthDialogPositiveClick(person);
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
