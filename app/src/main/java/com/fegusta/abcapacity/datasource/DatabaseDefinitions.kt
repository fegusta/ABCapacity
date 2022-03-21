package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class DatabaseDefinitions {

    object Level {

        const val TABLE_NAME = "tbl_level"

        object Columns {
            const val ID = "id"
            const val INFOLEVEL = "infoLevel"
        }
    }

    object Quest {

        const val TABLE_NAME = "tbl_quest"

        object Columns {
            const val ID = "id"
            const val LEVEL_ID = "levelId"
            const val QUESTION = "question"
            const val ALTERNATIVA_A = "alternativaA"
            const val ALTERNATIVA_B = "alternativaB"
            const val ANSWER = "answer"
        }
    }

    object User {

        const val TABLE_NAME = "tbl_user"

        object Columns {
            const val ID = "id"
            const val NOME = "nome"
            const val EMAIL = "email"
            const val PASSWORD = "password"
        }
    }
}