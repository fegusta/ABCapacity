package com.fegusta.abcapacity.data.model

data class User(
    var id: Int = -1,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var userLevel: Int = 0
)
