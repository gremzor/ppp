package com.gremzor.personpopulatorpro.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;

import javax.inject.Inject;

/**
 * Data access object that stores the last successfully logged in user name.
 */

public class UserNameDAO {

    @Inject
    Context context;

    private static final String SHARED_PREF_USER_KEY = "com.gremzor.personpopulatorpro.PREFERENCE_USER_KEY";
    private static final String SHARED_PREF_FILE_KEY = "com.gremzor.personpopulatorpro.PREFERENCE_FILE_KEY";

    public UserNameDAO () {
        PersonPopulatorProApplication.getGraph().inject(this);
    }

    public String getSavedUser() {
        return context.getSharedPreferences(SHARED_PREF_FILE_KEY, Context.MODE_PRIVATE).getString(SHARED_PREF_USER_KEY, "");
    }

    public void setSavedUser(String savedUser) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREF_FILE_KEY, Context.MODE_PRIVATE).edit();
        editor.putString(SHARED_PREF_USER_KEY, savedUser);
        editor.apply();
    }
}
