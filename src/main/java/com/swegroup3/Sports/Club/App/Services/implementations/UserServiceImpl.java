package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> obtainAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> obtainById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public User getByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userDDBB = userRepository.findById(id).orElse(null);
        if(userDDBB != null){
            userDDBB.setName(user.getName());
            userDDBB.setUsername(user.getUsername());
            userDDBB.setPassword(user.getPassword());
            return userRepository.save(userDDBB);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long quantityOfUsers() {
        return userRepository.count();
    }
}
