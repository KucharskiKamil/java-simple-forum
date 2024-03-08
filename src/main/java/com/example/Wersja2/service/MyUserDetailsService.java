package com.example.Wersja2.service;

import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUserOpt = userRepository.findByUsername(username);

        if (!myUserOpt.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        MyUser myUser = myUserOpt.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : myUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User(myUser.getUsername(), myUser.getPassword(), authorities);
    }
}
