package com.project.photoupload

import java.util.*

data class Foret (
    val id: Long?,
    val name: String?,
    val introduce: String?,
    val max_member: Int?,
    val reg_date: Date?,
) {
    constructor(name: String, introduce: String, max_member: Int) : this(null,name,introduce,max_member,null)
}
