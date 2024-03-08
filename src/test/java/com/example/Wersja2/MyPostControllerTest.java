package com.example.Wersja2;

import com.example.Wersja2.controller.MyPostController;
import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.service.LocaleUtil;
import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.service.MyPostService;
import com.example.Wersja2.service.MyTagService;
import com.example.Wersja2.service.MyUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = MyPostController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MyPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyPostService postService;

    @MockBean
    private MyUserService userService;

    @MockBean
    private MyTagService tagService;

    @MockBean
    private MessageSource messageSource;


    @MockBean
    private LocaleUtil localeUtil;


    @Test
    public void testShowCreatePostForm() throws Exception {
        mockMvc.perform(get("/create-post"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-post"));
    }
    @Test
    public void testSavePostSuccess() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = now.format(formatter);

        String tags = "tag1,tag2,tag3"; // Example tags

        mockMvc.perform(post("/savePost")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("content", "Test Content")
                        .param("title", "title")
                        .param("date", formattedDate)
                        .param("mytags", tags)) // Include tags parameter
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(postService).savePost(any(MyPost.class));
    }
    @Test
    public void testGetPostById() throws Exception
    {
        mockMvc.perform(get("/post/1"))
                .andExpect(status().isOk());
    }

}

