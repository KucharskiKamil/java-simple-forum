package com.example.Wersja2.module;

import com.example.Wersja2.security.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message ="Username moze miec dlugosc od 3 do 20 znakow!")
    private String username;
    @NotNull
    @NotBlank
    @ValidPassword
    @Size(min = 6, max = 200, message ="Haslo moze miec dlugosc od 6 do 200 znakow!")
    private String password;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private List<String> roles;
    @OneToMany(mappedBy = "user")
    private List<MyPost> posts = new ArrayList<>();
}
