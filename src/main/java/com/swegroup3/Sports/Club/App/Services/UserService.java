package com.swegroup3.Sports.Club.App.Services;


import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> obtainAll();
    Optional<User> obtainById(Long id);
    List<User> findByIds(List<Long> ids);
    User getByUsername(String username);
    User createUser(User user);
    User updateUser(Long id, User user);
    User saveUser(UserDto userDto);
    void deleteUser(Long id);
    long quantityOfUsers();
    List<User> userRolesList(Long roleID);
}
