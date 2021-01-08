package com.example.demo.Repository;

import com.example.demo.Model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findMemberById(int id);
}
