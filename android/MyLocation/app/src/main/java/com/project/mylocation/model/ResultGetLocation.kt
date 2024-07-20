package com.project.mylocation.model

data class ResultGetLocation (
    var documents: List<Documents>
)

data class Documents(
    var address_name: String = ""
)