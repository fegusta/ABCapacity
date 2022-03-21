package com.fegusta.abcapacity.datasource

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val DROP_QUEST_TABLE = "DROP TABLE IF EXISTS ${DatabaseDefinitions.Quest.TABLE_NAME}"
    private val DROP_LEVEL_TABLE = "DROP TABLE IF EXISTS ${DatabaseDefinitions.Level.TABLE_NAME}"
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS ${DatabaseDefinitions.User.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUEST)
        db.execSQL(CREATE_TABLE_LEVEL)
        db.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_QUEST_TABLE)
        db.execSQL(DROP_LEVEL_TABLE)
        db.execSQL(DROP_USER_TABLE)

        // Create tables again
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "abcapacity.db"
        private const val DATABASE_VERSION = 3

        private const val  CREATE_TABLE_QUEST = "CREATE TABLE ${DatabaseDefinitions.Quest.TABLE_NAME} (" +
                "${DatabaseDefinitions.Quest.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseDefinitions.Quest.Columns.LEVEL_ID} INTEGER, " +
                "${DatabaseDefinitions.Quest.Columns.QUESTION} TEXT, " +
                "${DatabaseDefinitions.Quest.Columns.ALTERNATIVA_A} TEXT, " +
                "${DatabaseDefinitions.Quest.Columns.ALTERNATIVA_B} TEXT, " +
                "${DatabaseDefinitions.Quest.Columns.ANSWER} TEXT);"

        private const val  CREATE_TABLE_LEVEL = "CREATE TABLE ${DatabaseDefinitions.Level.TABLE_NAME} (" +
                "${DatabaseDefinitions.Level.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseDefinitions.Level.Columns.INFOLEVEL} TEXT);"

        private const val  CREATE_TABLE_USER = "CREATE TABLE ${DatabaseDefinitions.User.TABLE_NAME} (" +
                "${DatabaseDefinitions.User.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DatabaseDefinitions.User.Columns.NOME} TEXT, " +
                "${DatabaseDefinitions.User.Columns.EMAIL} TEXT, " +
                "${DatabaseDefinitions.User.Columns.PASSWORD}  TEXT);"
    }
}