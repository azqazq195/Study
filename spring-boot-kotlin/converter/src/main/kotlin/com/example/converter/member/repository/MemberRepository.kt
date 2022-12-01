package com.example.converter.member.repository

import com.example.converter.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long>{
    override fun findAll(): MutableList<Member>
}