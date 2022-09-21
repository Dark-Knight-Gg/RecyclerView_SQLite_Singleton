package com.example.singleton_sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement

class Database(context: Context):SQLiteOpenHelper(context,"data.sql",null,1) {
    companion object{

        private var instance: Database? = null

        public fun getInstance(context: Context) :Database{
            if(instance==null){
                instance = Database(context)
            }
            return instance!!
        }
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }
    public fun QueryData(sql: String) {
        var data:SQLiteDatabase = writableDatabase
        data.execSQL(sql)
    }
    public fun Insert(name: String, describe: String, picture: ByteArray) {
        var db = writableDatabase
        var sql = "INSERT INTO Job VALUES(null,?,?,?)"
        var statement: SQLiteStatement = db.compileStatement(sql)
        statement.clearBindings()
        statement.bindString(1, name)
        statement.bindString(2, describe)
        statement.bindBlob(3, picture)
        statement.executeInsert()

    }
    public fun GetData(sql: String): Cursor {
        var data:SQLiteDatabase = readableDatabase
        return  data.rawQuery(sql,null)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}