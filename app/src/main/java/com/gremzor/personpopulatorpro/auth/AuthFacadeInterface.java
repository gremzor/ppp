package com.gremzor.personpopulatorpro.auth;

public interface AuthFacadeInterface {
    int SUCCESS = 0;
    int AUTH_FAILURE_USER_DOES_NOT_EXIST = 1;
    int AUTH_FAILURE_INVALID_PASSWORD = 2;
    int AUTH_FAiLURE_CREATE_ACCOUNT = 3;
    int AUTH_FAILURE_UNKNOWN = 4;
    void authStatus(int authorized);
}
