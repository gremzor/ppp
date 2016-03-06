package com.gremzor.personpopulatorpro.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gremzor.personpopulatorpro.R;

public class AuthDialogFragment extends DialogFragment {

    static private final int CREATE_USER_DIALOG = 0;
    static private final int LOGIN_FAILED_DIALOG = 1;
    static private final String DIALOG_TYPE = "dialog_type";

    AuthDialogFragmentListener mListener;

    public interface AuthDialogFragmentListener {
        void onAuthDialogPositiveClick(String password);
    }

    public static AuthDialogFragment getCreateUserDialog() {
        return getDialogForType(CREATE_USER_DIALOG);
    }

    public static AuthDialogFragment getLoginFailedDialog() {
        return getDialogForType(LOGIN_FAILED_DIALOG);
    }

    private static AuthDialogFragment getDialogForType(int type) {
        Bundle args = new Bundle();
        args.putInt(DIALOG_TYPE, type);
        AuthDialogFragment authDialogFragment = new AuthDialogFragment();
        authDialogFragment.setArguments(args);
        return authDialogFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (AuthDialogFragmentListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String negativeButtonText;
        View view;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        if (getArguments().getInt(DIALOG_TYPE, LOGIN_FAILED_DIALOG) == CREATE_USER_DIALOG) {
            view = inflater.inflate(R.layout.create_user_dialog, null);
            final EditText dialogPasswordET = (EditText) view.findViewById(R.id.dialogPassword);
            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onAuthDialogPositiveClick(dialogPasswordET.getText().toString());
                }
            });
            negativeButtonText = "Cancel";
        } else {
            view = inflater.inflate(R.layout.login_dialog, null);
            negativeButtonText = "OK";
        }

        builder.setNegativeButton(negativeButtonText, null);
        builder.setView(view);
        return builder.create();
    }
}
