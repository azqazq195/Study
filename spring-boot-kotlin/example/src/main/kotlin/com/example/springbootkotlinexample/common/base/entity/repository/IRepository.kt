package com.example.springbootkotlinexample.common.base.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface IRepository<E> : JpaRepository<E, Long>, JpaSpecificationExecutor<E>
