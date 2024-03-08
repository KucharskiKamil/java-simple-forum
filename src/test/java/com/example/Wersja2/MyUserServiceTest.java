package com.example.Wersja2;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyUserRepository;
import com.example.Wersja2.service.LocaleUtil;
import com.example.Wersja2.service.MyUserService;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.MessageSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MyUserServiceTest {

    @Mock
    private MyUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MessageSource messageSource;

    @Mock
    private LocaleUtil localeUtil;

    @InjectMocks
    private MyUserService myUserService;

    @Test
    void findByUsername_userExists_returnsUser() {
        MyUser mockUser = new MyUser();
        mockUser.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));

        Optional<MyUser> result = myUserService.findByUsername("testUser");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
    }

    @Test
    void findByUsername_userNotExists_returnsEmptyOptional() {
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        Optional<MyUser> result = myUserService.findByUsername("nonExistingUser");

        assertFalse(result.isPresent());
    }
}


