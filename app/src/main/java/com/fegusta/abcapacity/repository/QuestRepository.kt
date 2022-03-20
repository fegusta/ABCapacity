package com.fegusta.abcapacity.repository

import android.content.ContentValues
import android.content.Context
import com.fegusta.abcapacity.datasource.DatabaseDefinitions
import com.fegusta.abcapacity.datasource.DatabaseHelper
import com.fegusta.abcapacity.fragments.HomeFragment
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

    fun update(quest: Quest) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put(DatabaseDefinitions.Quest.Columns.QUESTION, quest.question)
            put(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A, quest.alternativaA)
            put(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B, quest.alternativaB)
            put(DatabaseDefinitions.Quest.Columns.ANSWER, quest.answer)
        }

        val selection = "${DatabaseDefinitions.Quest.Columns.ID} = ?"

        val selectionArgs = arrayOf(quest.id.toString())

        val count = db.update(
            DatabaseDefinitions.Quest.TABLE_NAME,
            valores,
            selection,
            selectionArgs
        )
        return count
    }

    fun delete(id: Int) : Int{
        val db = dbHelper.writableDatabase
        val selection = "${DatabaseDefinitions.Quest.Columns.ID} = ?"

        val selectionArgs = arrayOf(id.toString())

        val deletedRows = db.delete(DatabaseDefinitions.Quest.TABLE_NAME, selection, selectionArgs)

        return deletedRows
    }

    fun getQuests() : ArrayList<Quest>{
        val db = dbHelper.readableDatabase

        val projection = arrayOf(DatabaseDefinitions.Quest.Columns.ID,
            DatabaseDefinitions.Quest.Columns.QUESTION,
            DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A,
            DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B,
            DatabaseDefinitions.Quest.Columns.ANSWER)

        val orderBy ="${DatabaseDefinitions.Quest.Columns.ID} ASC"

        val cursor = db.query(DatabaseDefinitions.Quest.TABLE_NAME, projection,
            null,null,null,null,orderBy)

        var quests = ArrayList<Quest>()

        if (cursor != null){
            while (cursor.moveToNext()){
                var quest = Quest(
                    id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ID)),
                    question = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.QUESTION)),
                    alternativaA = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A)),
                    alternativaB = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B)),
                    answer = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ANSWER))
                )
                quests.add(quest)
            }
        }
        return quests
    }

    fun getQuest(id: Int): Quest{
        val db = dbHelper.readableDatabase

        val projection = arrayOf(DatabaseDefinitions.Quest.Columns.ID,
            DatabaseDefinitions.Quest.Columns.QUESTION,
            DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A,
            DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B,
            DatabaseDefinitions.Quest.Columns.ANSWER)

        val selection = "${DatabaseDefinitions.Quest.Columns.ID} = ?"

        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(DatabaseDefinitions.Quest.TABLE_NAME, projection,
            selection,selectionArgs,null,null,null)

        var quest = Quest()

        if (cursor != null){
            cursor.moveToNext()
            quest.id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ID))
            quest.question = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.QUESTION))
            quest.alternativaA = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A))
            quest.alternativaB = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B))
            quest.answer = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Quest.Columns.ANSWER))
        }
        return quest
    }

}