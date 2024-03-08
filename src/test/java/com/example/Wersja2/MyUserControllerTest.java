package com.example.Wersja2;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.example.Wersja2.controller.MyPostController;
import com.example.Wersja2.controller.MyUserController;
import com.example.Wersja2.module.MyInfo;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyPostRepository;
import com.example.Wersja2.repository.MyUserRepository;
import com.example.Wersja2.service.LocaleUtil;
import com.example.Wersja2.service.MyPostService;
import com.example.Wersja2.service.MyUserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


import org.springframework.http.MediaType;


import java.util.Collections;


@WebMvcTest(controllers = MyUserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MyUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserRepository userRepository;
    @MockBean
    private MyPostService postService;


    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MyUserService userService;

    @MockBean
    private LocaleUtil localeUtil;

    @MockBean
    private MessageSource messageSource;
    @MockBean
    private MyPostRepository postRepository;


    @Test
    @WithMockUser
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void testListUsers() throws Exception {
        // Create a mock Page of MyUser
        Page<MyUser> mockUserPage = new PageImpl<>(Collections.emptyList());

        // When userRepository.findAll is called, return the mock Page
        when(userRepository.findAll(any(Pageable.class))).thenReturn(mockUserPage);

        // Perform a GET request to the /users endpoint
        mockMvc.perform(get("/users")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "username,asc"))
                .andExpect(status().isOk()) // Expect a 200 OK status
                .andExpect(view().name("users")) // Expect the 'users' view
                .andExpect(model().attributeExists("usersPage")) // Expect 'usersPage' attribute in the model
                .andExpect(model().attributeExists("sort")) // Expect 'sort' attribute in the model
                .andExpect(model().attributeExists("pageSizes")); // Expect 'pageSizes' attribute in the model
    }
    @Test
    @WithMockUser
    public void testProcessRegistrationSuccess() throws Exception {
        MyUser user = new MyUser();
        user.setUsername("testUser");
        user.setPassword("passW1ord");
        user.setEmail("test@example.com");

        MyInfo myInfo = new MyInfo();
        myInfo.setSuccess(true);
        myInfo.setErrorMessage("Success Message");

        when(userService.registerUser(any(MyUser.class))).thenReturn(myInfo);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testUser")
                        .param("password", "Password1")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("errorMessage", equalTo("Success Message")));
    }
}

