package com.example.Wersja2.service;

import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.module.MyUser;
import com.example.Wersja2.repository.MyTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyTagService {

    private final MyTagRepository tagRepository;

    @Autowired
    public MyTagService(MyTagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public MyPostTag findOrCreateTag(String name) {
        Optional<MyPostTag> tag = tagRepository.findByName(name);
        if (!tag.isPresent())
        {
            MyPostTag newTag = new MyPostTag(name);
            return tagRepository.save(newTag);
        }
        return tag.get();
    }


    public Optional<MyPostTag> findByName(String username)
    {
        return tagRepository.findByName(username);
    }
}
