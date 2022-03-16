package com.fegusta.abcapacity.datasource

import com.fegusta.abcapacity.model.Quest

class DatabaseDefinitions {

    object Level {

        const val TABLE_NAME = "tbl_level"

        object Columns {
            const val ID = "id"
            const val INFOLEVEL = "infoLevel"
            const val LISTQUEST = "listQuest"
        }
    }

    object Quest {

        const val TABLE_NAME = "tbl_quest"

        object Columns {
            const val ID = "id"
            const val QUESTION = "question"
            const val ALTERNATIVA_A = "alternativaA"
            const val ALTERNATIVA_B = "alternativaB"
            const val ANSWER = "answer"
        }
    }
}