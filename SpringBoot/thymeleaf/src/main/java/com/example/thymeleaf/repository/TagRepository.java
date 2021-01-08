package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
