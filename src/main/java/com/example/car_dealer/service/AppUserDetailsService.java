package com.example.car_dealer.service;

import com.example.car_dealer.model.User;
import com.example.car_dealer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
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
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority("ROLE_" + foundUser.get().getRoleName());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(simpleGrantedAuthority);

        org.springframework.security.core.userdetails.User
                user = new org.springframework.security.core.userdetails.User(
                foundUser.get().getUserName(),
                foundUser.get().getPassword(),
                grantedAuthorities);


        return user;
    }
}
