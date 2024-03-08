package com.example.Wersja2.service;
import org.springframework.security.core.Authentication;
import com.example.Wersja2.module.MyInfo;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MyUserService {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleUtil localeUtil;


    public Optional<MyUser> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    public MyUser getCurrentUser(Authentication authentication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username).orElse(null);
        } else {
            return null;
        }
    }

    public MyInfo registerUser(MyUser user) {
        Optional<MyUser> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            Locale locale = localeUtil.getLocale();
            String message= messageSource.getMessage("error.username.exists", null, locale);
            return new MyInfo(false, message);
        }
        Optional<MyUser> existingUser2 = userRepository.findByEmail(user.getEmail());
        if(existingUser2.isPresent())
        {
            Locale locale = localeUtil.getLocale();
            String message= messageSource.getMessage("error.email.exists", null, locale);
            return new MyInfo(false, message);
        }


        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<String> roles = Arrays.asList("USER");
        user.setRoles(roles);
        userRepository.save(user);
        Locale locale = localeUtil.getLocale();
        String message = messageSource.getMessage("success.user.registered",null,locale);
        return new MyInfo(true, message);
    }

}
