package com.gremzor.personpopulatorpro.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.R;

import butterknife.Bind;

public class CreateUserDialogFragment extends BaseDialogFragment<CreateUserDialogFragmentListener> {

    @Bind(R.id.dialogPassword)
    EditText dialogPassword;

    @Override
    View inflateFragmentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.create_user_dialog, null);
    }

    @Override
    void setButtons(AlertDialog.Builder builder, final View view) {
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onAuthDialogPositiveClick(dialogPassword.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
    }
}
