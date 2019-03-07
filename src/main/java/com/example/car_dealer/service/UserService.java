package com.example.car_dealer.service;

import com.example.car_dealer.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    List<User> findUsersInDB();


}
