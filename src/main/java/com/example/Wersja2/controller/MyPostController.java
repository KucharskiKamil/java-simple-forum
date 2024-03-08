package com.example.Wersja2.controller;

import com.example.Wersja2.dto.MyPostDTO;
import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.service.LocaleUtil;
import com.example.Wersja2.service.MyPostService;
import com.example.Wersja2.service.MyTagService;
import com.example.Wersja2.service.MyUserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class MyPostController {
    private final MyPostService postService;
    private final MyUserService userService;
    private final MyTagService tagService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleUtil localeUtil;



    @Autowired
    public MyPostController(MyPostService postService, MyUserService userService, MyTagService tagService) {
        this.postService = postService;
        this.userService = userService;
        this.tagService = tagService;
    }
    @GetMapping("/create-post")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new MyPost());
        return "create-post";
    }
    @PostMapping("/savePost")
    public String savePost(@Valid @ModelAttribute("post") MyPost post, BindingResult result, Authentication authentication, Model model,String mytags) {
        if (authentication == null)
        {
            // Here is error
            System.out.println("Null values detected: post=" + post + ", result=" + result + ", authentication=" + authentication + ", model=" + model);
            return "error-page";
        }
        if (result.hasErrors())
        {

            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "create-post";
        }


        List<MyPostTag> tags = new ArrayList<>();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (mytags != null && !mytags.isEmpty())
        {
            String[] tagNames = mytags.split(",");
            for (String tagName : tagNames) {
                tagName = tagName.trim();
                if (!tagName.isEmpty() && pattern.matcher(tagName).matches())
                {
                    MyPostTag tag = tagService.findOrCreateTag(tagName);
                    tags.add(tag);
                }
                else
                {
                    //tag jest, ale niepoprawny
                    Locale locale = localeUtil.getLocale();
                    String message= messageSource.getMessage("error.incorrect.tags", null, locale);
                    model.addAttribute("errorMessage", message);
                    return "create-post";
                }
            }
        }
        else
        {
            Locale locale = localeUtil.getLocale();
            String message= messageSource.getMessage("error.empty.tags", null, locale);
            model.addAttribute("errorMessage", message);
            return "create-post";
        }
        //Tagi sa okej


        MyUser currentUser = userService.getCurrentUser(authentication);
        post.setUser(currentUser);
        post.setTags(tags);
        post.setDate(LocalDateTime.now());
        postService.savePost(post);

        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<MyPostDTO> getPostById(@PathVariable Long id) {
        MyPostDTO postDto = postService.getPostDtoById(id);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/")
    public String showAllPosts(Model model) {
        List<MyPost> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

}
