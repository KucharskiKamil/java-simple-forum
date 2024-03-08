package com.example.Wersja2.controller;

import com.example.Wersja2.module.MyInfo;
import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyPostRepository;
import com.example.Wersja2.repository.MyUserRepository;
import com.example.Wersja2.service.LocaleUtil;
import com.example.Wersja2.service.MyUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class MyUserController {
    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserService userService;
    @Autowired
    private LocaleUtil localeUtil;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Validated MyUser user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
        {
            if (bindingResult.hasFieldErrors("password")) {
                System.out.println(1);
                model.addAttribute("errorMessage", bindingResult.getFieldError("password").getDefaultMessage());

            }
            else if(bindingResult.hasFieldErrors("username"))
            {
                //Username jest pusty lub email jest zly

                System.out.println(2);
                if(user.getUsername().length()<3 || user.getUsername().length()>20)
                {
                    FieldError usernameError = bindingResult.getFieldError("username");
                    model.addAttribute("errorMessage", usernameError.getDefaultMessage());
                }
                else
                {
                    Locale locale = localeUtil.getLocale();
                    String message= messageSource.getMessage("error.username.incorrect", null, locale);
                    model.addAttribute("errorMessage", message);
                }

            }
            else if(bindingResult.hasFieldErrors("email"))
            {
                System.out.println(3);
                Locale locale = localeUtil.getLocale();
                String message= messageSource.getMessage("error.email.incorrect", null, locale);
                model.addAttribute("errorMessage", message);
            }
            System.out.println(4);
            model.addAttribute("user",user);
            return "register";
        }
        System.out.println(5);
        MyInfo isRegistered = userService.registerUser(user);
        if (!isRegistered.isSuccess())
        {
            String errorText = isRegistered.getErrorMessage();
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", errorText);
            return "register";
        }

        String message = isRegistered.getErrorMessage();
        System.out.println(message);
        model.addAttribute("successMessage", message);
        return "login";

    }



    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) boolean error) {
        if (error) {
            Locale locale = localeUtil.getLocale();
            String message = messageSource.getMessage("error.login", null, locale);
            System.out.println("Login error");
            model.addAttribute("errorMessage", message);
        }
        return "login";
    }
    @GetMapping("/logout")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/users")
    public String listUsers(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "username,asc") String sort) {

        String[] sortParams = sort.split(",");
        String sortProperty = sortParams[0];
        Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortProperty));


        Page<MyUser> usersPage = userRepository.findAll(pageable);


        model.addAttribute("usersPage", usersPage);
        model.addAttribute("sort", sort);
        List<Integer> pageSizes = Arrays.asList(5, 10, 20);
        model.addAttribute("pageSizes", pageSizes);

        return "users";
    }



    @Autowired
    private MyPostRepository postRepository;


    @GetMapping("/test")
    public String testt(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        //Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
        List <MyPost> posty = postRepository.findByTagName("test");
        System.out.println(posty.size());
        for (MyPost post:posty
             ) {
            System.out.println(post.getContent());
        }
        Page<MyUser> usersPage = userRepository.findAll(pageable);
        model.addAttribute("usersPage", usersPage);
        return "users";
    }





    @GetMapping("/mydetails")
    public String currentUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<MyUser> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            MyUser user = optionalUser.get();
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new MyUser());
        }
        return "mydetails";
    }
}

