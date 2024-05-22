package com.rentapp.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentapp.dao.UserRepository;
import com.rentapp.model.User;
import com.rentapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match!";
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return "User registered successfully!";
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}