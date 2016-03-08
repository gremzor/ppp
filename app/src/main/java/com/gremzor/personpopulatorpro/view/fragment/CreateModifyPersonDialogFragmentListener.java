package com.gremzor.personpopulatorpro.view.fragment;

import com.gremzor.personpopulatorpro.model.Person;

public interface CreateModifyPersonDialogFragmentListener {
    void onAuthDialogPositiveClick(Person person);
    void onAuthDialogNeutralClick(String key);
}
