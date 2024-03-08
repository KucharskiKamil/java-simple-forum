package com.example.Wersja2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private MyUserDTO user;
    private List<String> tags;

}
