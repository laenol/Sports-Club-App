package com.swegroup3.Sports.Club.App.Services.implementations;

import com.swegroup3.Sports.Club.App.Entities.User;
import com.swegroup3.Sports.Club.App.Repositories.UserRepository;
import com.swegroup3.Sports.Club.App.Services.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }
}
