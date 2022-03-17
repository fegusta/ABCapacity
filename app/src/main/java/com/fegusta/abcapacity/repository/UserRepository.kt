package com.fegusta.abcapacity.repository

import android.content.ContentValues
import android.content.Context
import com.fegusta.abcapacity.datasource.DatabaseDefinitions
import com.fegusta.abcapacity.datasource.DatabaseHelper
import com.fegusta.abcapacity.model.User

class UserRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun save(user: User) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues()
        valores.put(DatabaseDefinitions.User.Columns.NOME, user.name)
        valores.put(DatabaseDefinitions.User.Columns.EMAIL, user.email)
        valores.put(DatabaseDefinitions.User.Columns.PASSWORD, user.password)

        val id = db.insert(DatabaseDefinitions.User.TABLE_NAME, null, valores)
        db.close()
        return id.toInt()
    }

    fun update(user: User) : Int {
        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put(DatabaseDefinitions.User.Columns.NOME, user.name)
            put(DatabaseDefinitions.User.Columns.EMAIL, user.email)
            put(DatabaseDefinitions.User.Columns.PASSWORD, user.password)
        }

        val selection = "${DatabaseDefinitions.Quest.Columns.ID} = ?"

        val selectionArgs = arrayOf(user.id.toString())

        val count = db.update(
            DatabaseDefinitions.User.TABLE_NAME,
            valores,
            selection,
            selectionArgs
        )
        return count
    }

    fun delete(id: Int) : Int{
        val db = dbHelper.writableDatabase
        val selection = "${DatabaseDefinitions.User.Columns.ID} = ?"

        val selectionArgs = arrayOf(id.toString())

        val deletedRows = db.delete(DatabaseDefinitions.User.TABLE_NAME, selection, selectionArgs)

        return deletedRows
    }

    fun getUsers() : ArrayList<User>{
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            DatabaseDefinitions.User.Columns.ID,
            DatabaseDefinitions.User.Columns.NOME,
            DatabaseDefinitions.User.Columns.EMAIL,
            DatabaseDefinitions.User.Columns.PASSWORD)

        val orderBy ="${DatabaseDefinitions.User.Columns.NOME} ASC"

        val cursor = db.query(
            DatabaseDefinitions.User.TABLE_NAME, projection,
            null,null,null,null,orderBy)

        var users = ArrayList<User>()

        if (cursor != null){
            while (cursor.moveToNext()){
                var user = User(
                    id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.ID)),
                    name = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.NOME)),
                    email = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.EMAIL)),
                    password = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.PASSWORD))
                )
                users.add(user)
            }
        }
        return users
    }

    fun getUser(id: Int): User {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            DatabaseDefinitions.User.Columns.ID,
            DatabaseDefinitions.User.Columns.NOME,
            DatabaseDefinitions.User.Columns.EMAIL,
            DatabaseDefinitions.User.Columns.PASSWORD)

        val selection = "${DatabaseDefinitions.User.Columns.ID} = ?"

        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            DatabaseDefinitions.User.TABLE_NAME, projection,
            selection,selectionArgs,null,null,null)

        var user = User()

        if (cursor != null){
            cursor.moveToNext()
            user.id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.ID))
            user.name = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.NOME))
            user.email = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.EMAIL))
            user.password = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.PASSWORD))
        }
        return user
    }

    fun checkUser(email: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(DatabaseDefinitions.User.Columns.ID)
        val db = dbHelper.readableDatabase

        // selection criteria
        val selection = "${DatabaseDefinitions.User.Columns.EMAIL} = ?"

        // selection argument
        val selectionArgs = arrayOf(email)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        val cursor = db.query(DatabaseDefinitions.User.TABLE_NAME, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order


        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }

    fun checkUser(email: String, password: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(DatabaseDefinitions.User.Columns.ID,
            DatabaseDefinitions.User.Columns.EMAIL)

        val db = dbHelper.readableDatabase

        // selection criteria
        val selection = "${DatabaseDefinitions.User.Columns.EMAIL} = ? AND ${DatabaseDefinitions.User.Columns.PASSWORD} = ?"

        // selection arguments
        val selectionArgs = arrayOf(email, password)

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(DatabaseDefinitions.User.TABLE_NAME, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false

    }
}