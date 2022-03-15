package com.fegusta.abcapacity.model

data class Quest(
        val id: Int,
        val question: String,
        val listaAlternativa: Array<String>,
        val answer: String
)
