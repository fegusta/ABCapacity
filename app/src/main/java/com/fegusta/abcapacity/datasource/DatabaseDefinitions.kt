package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class DatabaseDefinitions {

    object Level {

        const val TABLE_NAME = "tbl_level"

        object Columns {
            const val ID = "id"
            const val NAME_LEVEL = "nameLevel"
            const val INFO_LEVEL = "infoLevel"
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
            const val TYPE_OF_QUEST = "typeOfQuest"
        }
    }

    object User {

        const val TABLE_NAME = "tbl_user"

        object Columns {
            const val ID = "id"
            const val NOME = "nome"
            const val EMAIL = "email"
            const val PASSWORD = "password"
            const val USER_LEVEL = "userLever"
        }
    }
}