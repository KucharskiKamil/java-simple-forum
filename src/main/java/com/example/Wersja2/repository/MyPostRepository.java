package com.example.Wersja2.repository;

import com.example.Wersja2.module.MyPost;
import com.example.Wersja2.module.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MyPostRepository extends JpaRepository<MyPost, Long> {
    Optional<MyPost> findByTitle(String title);
    Optional<MyPost> findByDate(LocalDateTime date);
    Optional<MyPost> findByContent(String content);
    @Query(value = "SELECT * FROM mypost WHERE content LIKE %:content%", nativeQuery = true)
    List<MyPost> findByContentContaining(@Param("content") String content);

    @Query(value = "SELECT * FROM mypost WHERE title LIKE %:content%", nativeQuery = true)
    List<MyPost> findByTitleContaining(@Param("content") String content);

    @Query("SELECT p FROM MyPost p JOIN p.tags t WHERE t.name = :tagName")
    List<MyPost> findByTagName(@Param("tagName") String tagName);
}
