package com.example.springbootkotlinexample.common.base.service.exception

import com.example.springbootkotlinexample.common.advice.exception.NotFoundException

open class NotFoundEntityException(entityName: String) : NotFoundException("존재하지 않는 $entityName 입니다.")
