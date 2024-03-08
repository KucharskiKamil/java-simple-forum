package com.example.Wersja2.repository;

import com.example.Wersja2.module.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface MyUserRepository extends PagingAndSortingRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    Optional<MyUser> findByEmail(String email);

    void save(MyUser user);

    List<MyUser> findAll();

}
    