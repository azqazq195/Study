package com.example.jpa.repository;

import com.example.jpa.model.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
