package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.auth.AuthFacade;
import com.gremzor.personpopulatorpro.model.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;

public class ModifyPersonDialogFragment extends CreatePersonDialogFragment{

    @Inject
    AuthFacade authFacade;

    @Bind(R.id.dialogTitle)
    TextView dialogTitle;

    public static final String PERSON_KEY = "person";
    private static final String firstNamePath = "/firstName";
    private static final String lastNamePath = "/lastName";
    private static final String dobPath = "/dob";
    private static final String zipPath = "/zip";

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
        dialogTitle.setText(R.string.modify_person);
        return dialog;
    }

    @Override
    void setButtons(AlertDialog.Builder builder, View view) {

        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> fieldsToUpdate = new HashMap<>();
                if(!person.getFirstName().equals(firstNameET.getText().toString())) {
                   fieldsToUpdate.put(person.uniqueKey + firstNamePath, firstNameET.getText().toString());
                }

                if(!person.getLastName().equals(lastNameET.getText().toString())) {
                    fieldsToUpdate.put(person.uniqueKey + lastNamePath, lastNameET.getText().toString());
                }

                GregorianCalendar gregorianCalendar = new GregorianCalendar(dOBDP.getYear(), dOBDP.getMonth(), dOBDP.getDayOfMonth());
                if(!person.getDOB().equals(gregorianCalendar.getTime())) {
                    fieldsToUpdate.put(person.uniqueKey + dobPath, gregorianCalendar.getTimeInMillis());
                }

                if(!person.getZip().equals(zIPET.getText().toString())) {
                    fieldsToUpdate.put(person.uniqueKey + zipPath, zIPET.getText().toString());
                }

                try {
                    authFacade.getFirebase().updateChildren(fieldsToUpdate);
                } catch (AuthFacade.UserNotLoggedInException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onAuthDialogNeutralClick(person.uniqueKey);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
    }
}
