package com.example.Wersja2;

import com.example.Wersja2.module.MyPostTag;
import com.example.Wersja2.repository.MyTagRepository;
import com.example.Wersja2.service.MyTagService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MyTagServiceTest {

    @Mock
    private MyTagRepository mockRepository;

    @InjectMocks
    private MyTagService service;

    @Test
    void testFindOrCreateTag_NewTag() {
        String tagName = "NewTag";
        when(mockRepository.findByName(tagName)).thenReturn(Optional.empty());
        MyPostTag newTag = new MyPostTag(tagName);
        when(mockRepository.save(any(MyPostTag.class))).thenReturn(newTag);

        MyPostTag result = service.findOrCreateTag(tagName);

        assertNotNull(result);
        assertEquals(tagName, result.getName());
    }

    @Test
    void testFindOrCreateTag_ExistingTag() {
        String tagName = "ExistingTag";
        MyPostTag existingTag = new MyPostTag(tagName);
        when(mockRepository.findByName(tagName)).thenReturn(Optional.of(existingTag));

        MyPostTag result = service.findOrCreateTag(tagName);

        assertNotNull(result);
        assertEquals(tagName, result.getName());
    }

}
