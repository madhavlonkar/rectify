package com.rentapp.service;

import com.rentapp.model.User;

public interface UserService {
    String registerUser(User user);
    User findByEmail(String email);
    User authenticateUser(String email, String password);
}
