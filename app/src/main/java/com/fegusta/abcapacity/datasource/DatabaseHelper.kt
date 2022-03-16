package com.fegusta.abcapacity.datasource

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val DATABASE_NAME = "abcapacity.db"
        private const val DATABASE_VERSION = 1

        private const val  CREATE_TABLE_QUEST = "CREATE TABLE ${DatabaseDefinitions.Quest.TABLE_NAME} (" +
                "${DatabaseDefinitions.Quest.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseDefinitions.Quest.Columns.QUESTION} TEXT, " +
                "${DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A} TEXT, " +
                "${DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B} REAL, " +
                "${DatabaseDefinitions.Quest.Columns.ANSWER} TEXT);"
    }
}