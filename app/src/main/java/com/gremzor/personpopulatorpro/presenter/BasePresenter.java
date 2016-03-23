package com.gremzor.personpopulatorpro.presenter;

/**
 * Base presenter class. Sub-classes must implement the init method where injection should be performed.
 */

abstract class BasePresenter {

    BasePresenter () {
        init();
    }

    abstract void init();
}
