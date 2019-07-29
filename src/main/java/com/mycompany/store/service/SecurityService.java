package com.mycompany.store.service;

public interface SecurityService {

	String findLoggedInUsername();

    void autoLogin(String username, String password);
}
