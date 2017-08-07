package com.dlsu.service;

import com.dlsu.model.User;
import com.dlsu.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by avggo on 7/25/2017.
 */

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public ArrayList<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

    public ArrayList<User> getInspectors(){
        return userRepository.findInspectors();
    }

    public boolean validateUser(String username, String password) {
        User user = findUserByUsername(username);

        if (user == null)
            return false;

        return password.equals(user.getPassword());
    }

    public ArrayList<User> findEncoders(){
        return userRepository.findEncoders();
    }

    public ArrayList<User> findAssessors(){
        return userRepository.findAssessors();
    }

    public ArrayList<User> findInspectors(){
        return userRepository.findInspectors();
    }
}
