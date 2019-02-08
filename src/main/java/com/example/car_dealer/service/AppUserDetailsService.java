package com.example.car_dealer.service;

import com.example.car_dealer.model.AppUserDetails;
import com.example.car_dealer.model.User;
import com.example.car_dealer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByUserName(username);
        if (!foundUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new AppUserDetails(foundUser.get());
    }
}
