package com.dlsu.model.repository;

import com.dlsu.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by avggo on 7/25/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);
    User findByUserId(Integer userId);

    @Query("SELECT u " +
            "from User u")
    ArrayList<User> findAllUsers();

    @Query("SELECT u " +
            "from User u " +
            "where u.department = 'engineering - inspector'")
    ArrayList<User> findInspectors();

    @Query("SELECT u " +
            "from User u " +
            "where u.department = 'bpld' or u.department = 'chief'")
    ArrayList<User> findEncoders();

    @Query("SELECT u " +
            "from User u " +
            "where u.department = 'bpld' or u.department = 'engineering - employee'")
    ArrayList<User> findAssessors();
}
