package com.example.Wersja2.repository;



import com.example.Wersja2.module.MyPostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyTagRepository extends JpaRepository<MyPostTag, Long> {
    Optional<MyPostTag> findByName(String name);
    }

