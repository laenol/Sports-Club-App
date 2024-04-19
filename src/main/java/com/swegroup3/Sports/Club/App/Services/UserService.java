package com.swegroup3.Sports.Club.App.Services;


import com.swegroup3.Sports.Club.App.Entities.User;

import java.util.List;

public interface UserService {
    List<User> obtainAll();
    User obtainById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    long quantityOfUsers();
}
