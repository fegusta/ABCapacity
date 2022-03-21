package com.fegusta.abcapacity.data.model

data class Quest(
        var id: Int =0,
        var levelId: Int = 0,
        var question: String = "",
        var alternativaA: String = "",
        var alternativaB: String = "",
        var answer: String = "",
        var typeOfQuest: String = ""
)
