package com.example.Wersja2;

import com.example.Wersja2.module.MyPostTag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyPostTagTest {

    @Test
    void testConstructorAndGetterSetter() {
        MyPostTag tag = new MyPostTag();
        tag.setId(1L);
        tag.setName("TestTag");

        assertEquals(1L, tag.getId());
        assertEquals("TestTag", tag.getName());
    }
}
