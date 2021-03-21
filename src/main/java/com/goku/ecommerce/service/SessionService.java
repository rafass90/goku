package com.goku.ecommerce.service;

public interface SessionService {

    String loginUser(String username, String password);

    void logoffUser(String authorization);

}
