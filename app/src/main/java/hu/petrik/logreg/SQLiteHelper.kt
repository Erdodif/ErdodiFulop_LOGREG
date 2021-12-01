package hu.petrik.logreg

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(context, DB.NAME.toString(), null, DB.VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = """CREATE TABLE ${DB.TABLE}(
                    | ${DB.COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                    | ${DB.COL_NAME} TEXT NOT NULL,
                    | ${DB.COL_EMAIL} TEXT NOT NULL,
                    | ${DB.COL_PASSWORD} TEXT NOT NULL,
                    | ${DB.COL_FULL_NAME} TEXT NOT NULL,
                    | UNIQUE (${DB.COL_NAME}),
                    | UNIQUE (${DB.COL_EMAIL})
                    | )""".trimMargin()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXIST ${DB.TABLE}")
        onCreate(db)
    }

    fun getById(id: Int): User? {
        val db = readableDatabase
        val result = db.rawQuery(
            "SELECT * FROM ${DB.TABLE} WHERE ${DB.COL_ID} LIKE ?", arrayOf(id.toString())
        )
        var user: User? = null
        if(result.moveToFirst()){
            user = User(
                result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4)
            )
            result.close()
        }
        return user
    }

    fun login(nameOrEmail: String, password: String): Int {
        val db = readableDatabase
        var ki = -1
        try {
            val result:Cursor = db.rawQuery(
                "SELECT * FROM ${DB.TABLE} WHERE ${DB.COL_NAME} LIKE ? OR ${DB.COL_EMAIL} LIKE ? ",
                arrayOf(nameOrEmail,nameOrEmail)
            )
            if(result.moveToFirst()){
                val user =
                    User(
                        result.getInt(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)
                    )
                if(user.password == password){
                    ki = user.id!!
                }
                result.close()
            }
            else{
                ki = -1
            }
        }
        catch (e:Exception){
            ki = -1
        }
        return ki
    }

    fun register(u:User):Boolean{
        val db = readableDatabase
        val values = ContentValues()
        values.put(DB.COL_NAME.toString(),u.name)
        values.put(DB.COL_EMAIL.toString(),u.email)
        values.put(DB.COL_PASSWORD.toString(),u.password)
        values.put(DB.COL_FULL_NAME.toString(),u.fullname)
        return db.insert(DB.TABLE.toString(),null,values) == (-1).toLong()
    }
}