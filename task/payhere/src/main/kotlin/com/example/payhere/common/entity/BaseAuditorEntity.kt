package com.example.payhere.common.entity

import com.example.payhere.domain.user.entity.User

interface BaseAuditorEntity : BaseTimeEntity {
    var createdBy: User?
    var modifiedBy: User?
}