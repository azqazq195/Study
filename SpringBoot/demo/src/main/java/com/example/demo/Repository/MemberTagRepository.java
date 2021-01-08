package com.example.demo.Repository;

import com.example.demo.Model.MemberTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTagRepository extends JpaRepository<MemberTag, Integer> {

}
