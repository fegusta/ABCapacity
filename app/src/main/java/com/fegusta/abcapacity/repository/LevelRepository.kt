package com.fegusta.abcapacity.repository

import android.content.ContentValues
import android.content.Context
import com.fegusta.abcapacity.datasource.DatabaseDefinitions
import com.fegusta.abcapacity.datasource.DatabaseHelper
import com.fegusta.abcapacity.model.Level
import com.fegusta.abcapacity.model.Quest

class LevelRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun save(level: Level) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues()
        valores.put(DatabaseDefinitions.Level.Columns.INFOLEVEL, level.infoLevel)
        val id = db.insert(DatabaseDefinitions.Level.TABLE_NAME, null, valores)
        return id.toInt()
    }

    fun update(level: Level) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put(DatabaseDefinitions.Level.Columns.INFOLEVEL, level.infoLevel)
        }

        val selection = "${DatabaseDefinitions.Quest.Columns.ID} = ?"

        val selectionArgs = arrayOf(level.id.toString())

        val count = db.update(
            DatabaseDefinitions.Level.TABLE_NAME,
            valores,
            selection,
            selectionArgs
        )
        return count
    }

    fun delete(id: Int) : Int{
        val db = dbHelper.writableDatabase
        val selection = "${DatabaseDefinitions.Level.Columns.ID} = ?"

        val selectionArgs = arrayOf(id.toString())

        val deletedRows = db.delete(DatabaseDefinitions.Level.TABLE_NAME, selection, selectionArgs)

        return deletedRows
    }

    fun getLevels() : ArrayList<Level>{
        val db = dbHelper.readableDatabase

        val projection = arrayOf(DatabaseDefinitions.Level.Columns.ID,
            DatabaseDefinitions.Level.Columns.INFOLEVEL)

        val orderBy ="${DatabaseDefinitions.Level.Columns.ID} ASC"

        val cursor = db.query(DatabaseDefinitions.Quest.TABLE_NAME, projection,
            null,null,null,null,orderBy)

        var levels = ArrayList<Level>()

        if (cursor != null){
            while (cursor.moveToNext()){
                var level = Level(
                    id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Level.Columns.ID)),
                    infoLevel = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Level.Columns.INFOLEVEL))
                )
                levels.add(level)
            }
        }
        return levels
    }

    fun getLevel(id: Int): Level{
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

        var level = Level()

        if (cursor != null){
            cursor.moveToNext()
            level.id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Level.Columns.ID))
            level.infoLevel = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Level.Columns.INFOLEVEL))
        }
        return level
    }

}

private fun ContentValues.put(listquest: String, listQuest: ArrayList<Quest>) {

}
