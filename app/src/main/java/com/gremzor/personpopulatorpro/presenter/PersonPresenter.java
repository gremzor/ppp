package com.gremzor.personpopulatorpro.presenter;

import com.gremzor.personpopulatorpro.PersonPopulatorProApplication;
import com.gremzor.personpopulatorpro.view.PersonActivity;

public class PersonPresenter extends BasePresenter {

    public PersonActivity personActivity;

    @Override
    void init() {
        PersonPopulatorProApplication.getGraph().inject(this);
    }
}
