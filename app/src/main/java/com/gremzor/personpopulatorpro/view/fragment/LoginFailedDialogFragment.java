package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.gremzor.personpopulatorpro.R;

public class LoginFailedDialogFragment extends BaseDialogFragment{
    @Override
    View inflateFragmentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.login_dialog, null);
    }

    @Override
    void setButtons(AlertDialog.Builder builder, View view) {
        builder.setNegativeButton(R.string.ok, null);
    }
}
