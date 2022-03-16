package com.fegusta.abcapacity.model

data class Level(
    val id: Int,
    val infoLevel: String,
    val listQuest: ArrayList<Quest>
)
