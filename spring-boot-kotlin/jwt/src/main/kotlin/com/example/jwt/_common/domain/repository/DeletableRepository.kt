package com.example.jwt._common.domain.repository

import com.example.jwt._common.domain.ModifiableEntity
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface DeletableRepository<T : ModifiableEntity> : ModifiableRepository<T>
