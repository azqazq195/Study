package com.example.payhere.common.entity

import java.time.LocalDateTime

interface BaseTimeEntity : BaseEntity {
    var createdAt: LocalDateTime?
    var modifiedAt: LocalDateTime?
    var deletedAt: LocalDateTime?
}