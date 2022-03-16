package com.fegusta.abcapacity.repository

import android.content.ContentValues
import android.content.Context
import com.fegusta.abcapacity.datasource.DatabaseDefinitions
import com.fegusta.abcapacity.datasource.DatabaseHelper
import com.fegusta.abcapacity.model.Quest

class QuestRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun save(quest: Quest) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues()
        valores.put(DatabaseDefinitions.Quest.Columns.QUESTION, quest.question)
        valores.put(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A, quest.alternativaA)
        valores.put(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B, quest.alternativaB)
        valores.put(DatabaseDefinitions.Quest.Columns.ANSWER, quest.answer)

        val id = db.insert(DatabaseDefinitions.Quest.TABLE_NAME, null, valores)
        return id.toInt()
    }
    
}