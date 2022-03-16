package com.fegusta.abcapacity.model

data class Level(
    val id: Int = 0,
    val infoLevel: String = "",
    val listQuest: ArrayList<Quest> = ArrayList<Quest>()
)
