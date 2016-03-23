package com.gremzor.personpopulatorpro.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Base dialogfragment provides basic functionality for all other DialogFragments.
 * @param <T>
 */

abstract public class BaseDialogFragment <T> extends DialogFragment {

    T mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (T) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflateFragmentView(inflater);

        setButtons(builder, view);
        builder.setView(view);
        ButterKnife.bind(this, view);
        return builder.create();
    }

    abstract View inflateFragmentView(LayoutInflater inflater);

    abstract void setButtons(AlertDialog.Builder builder, View view);
}
