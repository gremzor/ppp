package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.R;
import com.gremzor.personpopulatorpro.model.Person;

import java.util.GregorianCalendar;

import butterknife.Bind;

public class CreatePersonDialogFragment extends BaseDialogFragment<CreateModifyPersonDialogFragmentListener>{

    @Bind(R.id.personFirstName)
    EditText firstNameET;

    @Bind(R.id.personLastName)
    EditText lastNameET;

    @Bind(R.id.personDOB)
    DatePicker dOBDP;

    @Bind(R.id.personZip)
    EditText zIPET;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dOBDP.setMaxDate(System.currentTimeMillis());
        return dialog;
    }

    @Override
    View inflateFragmentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.create_person_dialog, null);
    }

    @Override
    void setButtons(AlertDialog.Builder builder, View view) {
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(dOBDP.getYear(), dOBDP.getMonth(), dOBDP.getDayOfMonth());
                Person person = new Person(firstNameET.getText().toString(), lastNameET.getText().toString(), gregorianCalendar.getTime(), zIPET.getText().toString());
                mListener.onAuthDialogPositiveClick(person);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
    }
}
